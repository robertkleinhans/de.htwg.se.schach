/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwg.se.schach;


import de.htwg.se.schach.view.*;

import java.awt.Point;

import de.htwg.se.schach.control.MovementHandler;
import de.htwg.se.schach.control.Position;
/**
 *
 * @author rob
 */
public class PlayerHandler {
    private ChessView view;
    private GuiChess gui;
    private MovementHandler mov;
    String invalid = ">>> Invalid command!";
    String colError = ">>> The column must be between A and H!";
    String rowError = ">>> The row must be between 0 and 7!";
    
    
    public PlayerHandler() {
    	mov = new MovementHandler();
    	this.view = new ChessView(mov);
        view.printField();
    }
    
    
    public void startGame() {
        gameHandler();
    }
    
    public void handleShow(String inp) {
        String[] parts = inp.split(" ");
        if (parts.length != 2) {
            System.out.println(invalid);
            return;
        }

        // - 65 to map A to 0, B to 1, ...
        int colStart = ((int) parts[1].charAt(0)) - 65;
        // - 49 to map '1' to 0, '2' to 1, ...
        int rowStart = (int) parts[1].charAt(1) - 49;
        
        if(!checkValue(colStart)) {
            System.out.println(colError);
            return;
        } else if (!checkValue(rowStart)) {
            System.out.println(rowError);
            return;
        }
        Position start = new Position(rowStart, colStart);
        
        view.viewMovement(start);
        
        
    }
    
    public boolean handleInput(String inp,int team) {
    	
        if (("quit").equals(inp)) {
            return true;
        } else if (inp.startsWith("show")) {
            handleShow(inp);
            return false;
        }
        
        String[] parts = inp.split("-");
        if (parts.length != 2) {
            System.out.println(invalid);
            return false;
        }
        if(parts[0].length() != 2 || parts[1].length() != 2) {
            System.out.println(invalid);
            return false;
        }
        // - 65 to map A to 0, B to 1, ...
        int colStart = ((int) parts[0].charAt(0)) - 65;
        // - 49 to map '1' to 0, '2' to 1, ...
        int rowStart = (int) parts[0].charAt(1) - 49;
        
        if(!checkValue(colStart)) {
            System.out.println(colError);
            return false;
        } else if (!checkValue(rowStart)) {
            System.out.println(rowError);
            return false;
        }
        Position start = new Position(rowStart, colStart);
        if(!view.checkPiece(start, team)) {
            System.out.println(">>> None of your Pieces are on this position!");
            return false;
        }
        
        // - 65 to map A to 0, B to 1, ...
        int colEnd = ((int) parts[1].charAt(0)) - 65;
        // - 49 to map '1' to 0, '2' to 1, ...
        int rowEnd = (int) parts[1].charAt(1) - 49;
        
        if(!checkValue(colEnd)) {
            System.out.println(colError);
            return false;
        } else if (!checkValue(rowEnd)) {
            System.out.println(rowError);
            return false;
        }
        
        Position end = new Position(rowEnd, colEnd);
        if(view.movePiece(start, end, team)) {
        	gui.move_piece(new Point(colStart,rowStart), new Point(colEnd,rowEnd));
            return true;
        } else {
            System.out.println(">>> Unknown Error : Could not make the move!");
            return false;
        }
    }
    
    public boolean checkValue(int val) {
        return val <= 7 && val >= 0;
    }
    
    
    
    public void gameHandler() {
        int turn = 1;
        Signal sig = new Signal();
        String com;
        
        this.gui = new GuiChess(sig);
        gui.initialize_pieces(this.mov.getField());
        TuiInput tui = new TuiInput(sig);
        tui.start();
        gui.show();
        
        System.out.println("HINT: Write the movement like this: A2-A3");
        System.out.println("HINT: To see aviable moves: show D7");
        System.out.println(">>> Game started:");
        
        while (true) {
        	if(mov.checkWin() != -1) {
        		System.out.println("[MATCH END]");
        		System.out.printf("PLAYER %d WON!%n", mov.checkWin());
        		gui.quit();
        		System.out.println(">>> Closing now!");
        		break;
        	}
        	
        	
            System.out.printf(">>>[Player%d]: ",turn);
            
            while(!sig.input_given()) {
            	// busy waiting
            }
            com = sig.get_command();
            System.out.printf("%n");
            if(("quit").equals(com)) {
            	gui.quit();
            	System.out.println(">>> Closing now!");
            	break;
            }
            
            if(handleInput(com,turn)) {
                view.printField();
                turn = (turn+1)%2;
	                
	        }
            sig.reset();
        }
        
    }
}
