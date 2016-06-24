package de.htwg.se.schach.view;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import de.htwg.se.schach.control.MovementHandler;
import de.htwg.se.schach.control.Position;
import de.htwg.se.schach.model.Piece;


public class GuiChess {
	JFrame chess_frame;
	final int MAX_ROW = 7;
	final int MAX_COLUMN = 7;
	private Map<Point,JLabel>board_brain;
	
	private MovementHandler movH;
	
	Point last_click;
	
	int team = 0;
	
	public GuiChess(MovementHandler mov) {
		movH = mov;
		board_brain = new HashMap<Point, JLabel>();
		
		chess_frame = new JFrame("Chess");
        chess_frame.setContentPane(new GuiBoard());
        chess_frame.setSize(740,760);
        chess_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chess_frame.setResizable(false);
        
        chess_frame.setLayout(null);
        
        chess_frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handle_click(e.getPoint());
            }
        });
        
        initialize_pieces(movH.getField());
	}
	
	public void initialize_pieces(Map<Position,Piece> figures) {
		StringBuilder sb = new StringBuilder();
		Point tmp_point;
		for(int i = 0; i <= MAX_ROW; i++) {
            
            for(int j = 0; j <= MAX_COLUMN; j++) {
            	
                Position tmp = new Position(i,j);
                
                if(figures.containsKey(tmp)) {
                	Piece tmp_piece = figures.get(tmp);
                	if (tmp_piece.team == 0) {
                		sb.append("img/white");
                	} else {
                		sb.append("img/black");
                	}
                	
                	tmp_point = calc_point(new Point(tmp_piece.column, tmp_piece.row));
                	
                	switch(tmp_piece.cut) {
                	
                	case "BI":
                		sb.append("_bishop.png");
                		break;
                	case "KI":
                		sb.append("_king.png");
                		break;
                	case "KN":
                		sb.append("_knight.png");
                		break;
                	case "PA":
                		sb.append("_pawn.png");
                		break;
                	case "QU":
                		sb.append("_queen.png");
                		break;
                	case "RO":
                		sb.append("_rook.png");
                		break;
                	}
                	add_piece(sb.toString(),tmp_point.x,tmp_point.y);
            		sb.setLength(0);
                }
            }
        }
		
	}
	
	public void setTeam(int new_team) {
		this.team = new_team;
	}
	
	public Point calc_point(Point tmp) {
		int x = tmp.x;
		int y = tmp.y;
		
		Point ret = new Point(40+(81*x),40+(81*y));
		return ret;
	}
	

    public void handle_click(Point p) {
        int x = p.x - 43;
        int y = p.y - 65;
        int click_x;
        int click_y;
        int counter = 0;
        while (true) {
            if (x-80 <= 0) {
            	click_x = counter+1;
                break;
            } else {
                x -= 80;
                counter++;
            }
        }
        
        counter = 0;
        while (true) {
            if (y-80 <= 0) {
            	click_y = counter+1;
                break;
            } else {
                y -= 80;
                counter ++;
            }
        }

        Point cur_click = new Point(click_x, click_y);
        
        if(board_brain.containsKey(cur_click)) {
        	show_piece(cur_click);
        	
        }
        
        
        if (cur_click != last_click && last_click != null) {
        	move_piece(last_click, cur_click,this.team);
        }
        
        last_click = cur_click;
        
    }
    
    public void show_piece(Point start) {
    	Position pos = new Position(start.y-1,start.x-1);

    	
    	List<Position> move = new LinkedList<Position>();
    	
    	move = movH.getMovement(pos);

    	System.out.println(move.size());
    }
    
    public void move_piece(Point start, Point end, int team_id) {
    	Position pos_sta = new Position(start.y-1,start.x-1);
    	Position pos_end = new Position(end.y-1,end.x-1);
    	
    	List<Position> tmp_mov = new LinkedList<Position>();
    	tmp_mov = movH.getMovement(pos_sta);
    	
    	
    	if(tmp_mov.contains(pos_end)) {
    		if(board_brain.containsKey(end)) {
    			board_brain.remove(end);
    		}

    		movH.movePiece(pos_sta, pos_end, team_id);

    		
    		JLabel tmp_label = board_brain.remove(start);
    		System.out.println(end);
    		tmp_label.setLocation(calc_point(new Point(end.x-1,end.y-1)));

    		board_brain.put(end, tmp_label);
    		
    	}
    }
    
    public void add_piece(String fname, int x, int y) {
        
        ImageIcon icon = new ImageIcon(fname);
        
        JLabel tmp = new JLabel(icon);
        tmp.setLocation(x,y);
        tmp.setSize(81,81);
        
       
        int counter = 0;
        while (true) {
            if (x-80 <= 0) {
            	x = counter+1;
                break;
            } else {
                x -= 80;
                counter++;
            }
        }
        
        counter = 0;
        while (true) {
            if (y-80 <= 0) {
            	y = counter+1;
                break;
            } else {
                y -= 80;
                counter ++;
            }
        }
        
        board_brain.put(new Point(x,y), tmp);
        
        chess_frame.add(tmp);
    }
    
    public void remove_piece(int x, int y) {
    	Point tmp = new Point(x,y);
    	board_brain.remove(tmp);
    }
    
    public void show_frame() {
        chess_frame.setVisible(true);
    }
}
