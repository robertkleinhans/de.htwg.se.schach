package de.htwg.se.schach.view;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import de.htwg.se.schach.control.Position;
import de.htwg.se.schach.model.Piece;


public class GuiChess {
	JFrame chessFrame;
	final int MAXROW = 7;
	final int MAXCOLUMN = 7;
	private Map<Point,JLabel>boardBrain;
	
	Signal sig;
	String first = "";
	
	int team = 0;
	
	public GuiChess(Signal sig) {
		boardBrain = new HashMap<Point, JLabel>();
		this.sig = sig;
		chessFrame = new JFrame("Chess");
        chessFrame.setContentPane(new GuiBoard());
        chessFrame.setSize(646,668);
        chessFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chessFrame.setResizable(false);
        
        for(int i = 0; i < 8; i++) {
        	char c = (char) ('A' + i);
        	JLabel label = new JLabel(String.valueOf(c));
        	label.setLocation(new Point((i*80)+30,0));
        	label.setSize(20,20);
        	chessFrame.add(label);
        	
        	JLabel num = new JLabel(String.valueOf(i+1));
        	num.setLocation(0,(80*i)+30);
        	num.setSize(20,20);
        	chessFrame.add(num);
        }
        
        
        chessFrame.setLayout(null);
        chessFrame.repaint();
        
        chessFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handle_click(e.getPoint());
            }
        });
	}
	
	
	void initialize_helper(Position tmp, Map<Position,Piece> figs) {
		StringBuilder sb = new StringBuilder();
		Point tmpPoint;
		if (figs.containsKey(tmp)) {
			Piece tmp_piece = figs.get(tmp);
        	if (tmp_piece.getTeam() == 0) {
        		sb.append("img/black");
        	} else {
        		sb.append("img/white");
        	}
        	
        	tmpPoint = new Point(tmp_piece.getColumn()*80, tmp_piece.getRow()*80);
        	
        	switch(tmp_piece.getCut()) {
        	
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
        	default:
        		break;
        	}
        	add_piece(sb.toString(),tmpPoint.x,tmpPoint.y);
		}
	}
	
	public void initialize_pieces(Map<Position,Piece> figures) {
		for(int i = 0; i <= MAXROW; i++) {
            
            for(int j = 0; j <= MAXCOLUMN; j++) {
            	
            	Position tmp = new Position(i,j);
                initialize_helper(tmp,figures);
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
        
        if(("").equals(first)) {
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
        int newX = x;
        int newY = y;
       
        int counter = 0;
        while (newX >= 80) {
        	newX-=80;
        	counter++;
        }
        newX = counter;
        
        counter = 0;
        while (newY >= 80) {
        	newY-=80;
        	counter++;
        }
        newY = counter;

        boardBrain.put(new Point(newX,newY), tmp);
        
        chessFrame.add(tmp);
    }
    
    public void show() {
    	chessFrame.setVisible(true);
    }
    

    public void move_piece(Point start, Point end) {
    	JLabel tmp = boardBrain.remove(start);

    	if(boardBrain.containsKey(end)) {
    		JLabel todo = boardBrain.remove(end);
    		chessFrame.remove(todo);
    	}
    	tmp.setLocation(new Point(end.x*80,end.y*80));
    	boardBrain.put(end, tmp);
    	chessFrame.validate();
    	chessFrame.repaint();
    }
    
    public void quit() {
    	chessFrame.setVisible(false);
    	chessFrame.dispose();
    }
}
