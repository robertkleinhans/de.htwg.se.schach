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
	
	Signal sig;
	String first = "";
	
	int team = 0;
	
	public GuiChess(Signal sig) {
		board_brain = new HashMap<Point, JLabel>();
		this.sig = sig;
		chess_frame = new JFrame("Chess");
        chess_frame.setContentPane(new GuiBoard());
        chess_frame.setSize(646,668);
        chess_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chess_frame.setResizable(false);
        
        for(int i = 0; i < 8; i++) {
        	char c = (char) ('A' + i);
        	JLabel label = new JLabel(String.valueOf(c));
        	label.setLocation(new Point((i*80)+30,0));
        	label.setSize(20,20);
        	chess_frame.add(label);
        	
        	JLabel num = new JLabel(String.valueOf(i+1));
        	num.setLocation(0,(80*i)+30);
        	num.setSize(20,20);
        	chess_frame.add(num);
        }
        
        
        chess_frame.setLayout(null);
        chess_frame.repaint();
        
        chess_frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handle_click(e.getPoint());
            }
        });
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
                		sb.append("img/black");
                	} else {
                		sb.append("img/white");
                	}
                	
                	tmp_point = new Point((tmp_piece.column*80), (tmp_piece.row*80));
                	
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
	

    public void handle_click(Point p) {
        int x = p.x - 25;
        int y = p.y - 25;
        int row = 0;
        int col = 0;
        int counter = 0;
        while (x > 80) {
        	x-=80;
        	counter++;
        }
        col = counter;
        counter = 0;
        
        while (y > 80) {
        	y-= 80;
        	counter++;
        }
        row = counter;
        
        char col_c = (char) (col+65);
        StringBuilder sb = new StringBuilder();
        
        if(first.equals("")) {
        	sb.append(col_c).append(String.valueOf(row+1));
        	this.first = sb.toString();
        	sb.setLength(0);
        	sb.append("show ").append(this.first);
        	this.sig.set_input(sb.toString());
        } else {
        	sb.append(this.first).append("-").append(col_c).append(String.valueOf(row+1));
        	this.sig.set_input(sb.toString());
        	this.first = "";
        }
        
    }
    
    
    public void add_piece(String fname, int x, int y) {
        
        ImageIcon icon = new ImageIcon(fname);
        
        JLabel tmp = new JLabel(icon);
        tmp.setLocation(x,y);
        tmp.setSize(80,80);
        
       
        int counter = 0;
        while (x >= 80) {
        	x-=80;
        	counter++;
        }
        x = counter;
        
        counter = 0;
        while (y >= 80) {
        	y-=80;
        	counter++;
        }
        y = counter;
        System.out.println(new Point(x,y));
        board_brain.put(new Point(x,y), tmp);
        
        chess_frame.add(tmp);
    }
    
    public void show() {
    	chess_frame.setVisible(true);
    }
    
    public void remove_piece(int x, int y) {
    	Point tmp = new Point(x,y);
    	board_brain.remove(tmp);
    }
    
    public void move_piece(Point start, Point end) {
    	JLabel tmp = board_brain.remove(start);

    	if(board_brain.containsKey(end)) {
    		JLabel todo = board_brain.remove(end);
    		chess_frame.remove(todo);
    	}
    	tmp.setLocation(new Point(end.x*80,end.y*80));
    	board_brain.put(end, tmp);
    	chess_frame.validate();
    	chess_frame.repaint();
    }
    
    public void quit() {
    	chess_frame.setVisible(false);
    	chess_frame.dispose();
    }
}
