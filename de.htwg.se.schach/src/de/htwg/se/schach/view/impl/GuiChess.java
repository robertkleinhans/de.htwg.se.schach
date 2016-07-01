package de.htwg.se.schach.view.impl;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import de.htwg.se.schach.control.I.PositionInter;
import de.htwg.se.schach.control.impl.Position;
import de.htwg.se.schach.model.I.PieceInter;
import de.htwg.se.schach.model.impl.Piece;
import de.htwg.se.schach.view.I.GuiChessInter;
import de.htwg.se.schach.view.I.SignalInter;


public class GuiChess implements GuiChessInter {
	JFrame chessFrame;
	static final int MAXROW = 7;
	static final int MAXCOLUMN = 7;
	private Map<Point,JLabel>boardBrain;
	
	SignalInter sig;
	String first = "";
	
	int team = 0;
	
	public GuiChess(SignalInter sig) {
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
                handleClick(e.getPoint());
            }
        });
	}
	
	
	void initializeHelper(PositionInter tmp, Map<Position,Piece> figs) {
		StringBuilder sb = new StringBuilder();
		Point tmpPoint;
		if (figs.containsKey(tmp)) {
			PieceInter tmpPiece = figs.get(tmp);
        	if (tmpPiece.getTeam() == 0) {
        		sb.append("img/black");
        	} else {
        		sb.append("img/white");
        	}
        	
        	tmpPoint = new Point(tmpPiece.getColumn()*80, tmpPiece.getRow()*80);
        	sb.append("_");
        	sb.append(tmpPiece.getCut());
        	sb.append(".png");
        	addPiece(sb.toString(),tmpPoint.x,tmpPoint.y);
		}
	}
	
	/* (non-Javadoc)
	 * @see de.htwg.se.schach.view.impl.GuiChessInter#initializePieces(java.util.Map)
	 */
	@Override
	public void initializePieces(Map<Position,Piece> figures) {
		for(int i = 0; i <= MAXROW; i++) {
            
            for(int j = 0; j <= MAXCOLUMN; j++) {
            	
            	PositionInter tmp = new Position(i,j);
                initializeHelper(tmp,figures);
            }
        }
		
	}
	

    /* (non-Javadoc)
	 * @see de.htwg.se.schach.view.impl.GuiChessInter#handleClick(java.awt.Point)
	 */
    @Override
	public void handleClick(Point p) {
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
        
        char colC = (char) (col+65);
        StringBuilder sb = new StringBuilder();
        
        if(("").equals(first)) {
        	sb.append(colC).append(String.valueOf(row+1));
        	this.first = sb.toString();
        	sb.setLength(0);
        	sb.append("show ").append(this.first);
        	this.sig.setInput(sb.toString());
        } else {
        	sb.append(this.first).append("-").append(colC).append(String.valueOf(row+1));
        	this.sig.setInput(sb.toString());
        	this.first = "";
        }
        
    }
    
    
    /* (non-Javadoc)
	 * @see de.htwg.se.schach.view.impl.GuiChessInter#addPiece(java.lang.String, int, int)
	 */
    @Override
	public void addPiece(String fname, int x, int y) {
        
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
    
    /* (non-Javadoc)
	 * @see de.htwg.se.schach.view.impl.GuiChessInter#show()
	 */
    @Override
	public void show() {
    	chessFrame.setVisible(true);
    }
    

    /* (non-Javadoc)
	 * @see de.htwg.se.schach.view.impl.GuiChessInter#movePiece(java.awt.Point, java.awt.Point)
	 */
    @Override
	public void movePiece(Point start, Point end) {
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
    
    /* (non-Javadoc)
	 * @see de.htwg.se.schach.view.impl.GuiChessInter#quit()
	 */
    @Override
	public void quit() {
    	chessFrame.setVisible(false);
    	chessFrame.dispose();
    }
}
