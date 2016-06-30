/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwg.se.schach;


import de.htwg.se.schach.view.*;

import org.apache.log4j.Logger;


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
    
    private static final Logger LOGGER = Logger.getLogger("de.htwg.se.schach.playerHandler");
    
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
        	LOGGER.info(invalid);
            return;
        }

        int colStart = ((int) parts[1].charAt(0)) - 65;
        int rowStart = (int) parts[1].charAt(1) - 49;
        
        if(!checkValue(colStart)) {
        	LOGGER.info(colError);
            return;
        } else if (!checkValue(rowStart)) {
        	LOGGER.info(rowError);
            return;
        }
        Position start = new Position(rowStart, colStart);
        
        view.viewMovement(start);
        
        
    }
    
    boolean preCheck(String inp) {
    	if (inp.startsWith("show")) {
            handleShow(inp);
            return false;
        }
        
        String[] parts = inp.split("-");
        if (parts.length != 2) {
        	LOGGER.info(invalid);
            return false;
        }
        if(parts[0].length() != 2 || parts[1].length() != 2) {
        	LOGGER.info(invalid);
            return false;
        }
        return true;
    }
    
    boolean checkNumbers(int valA, int valB) {
    	
    	if(!checkValue(valA)) {
        	LOGGER.info(colError);
            return false;
        } else if (!checkValue(valB)) {
        	LOGGER.info(rowError);
            return false;
        }
    	return true;
    }
    
    boolean moveHelper(Position start, Position end, int team, int colStart, int rowStart,
    		int colEnd, int rowEnd) {
    	if(view.movePiece(start, end, team)) {
        	gui.movePiece(new Point(colStart,rowStart), new Point(colEnd,rowEnd));
            return true;
        } else {
        	LOGGER.info(">>> Unknown Error : Could not make the move!");
            return false;
        }
    }
    
    public boolean handleInput(String inp,int team) {
    	
    	if(("quit").equals(inp)) {
        	return true;
        }
    	
        if(!preCheck(inp)) {
        	return false;
        }      
        
        String[] parts = inp.split("-");

        int colStart = ((int) parts[0].charAt(0)) - 65;

        int rowStart = (int) parts[0].charAt(1) - 49;
        if(!checkNumbers(colStart,rowStart)) {
        	return false;
        }
        
        Position start = new Position(rowStart, colStart);
        if(!view.checkPiece(start, team)) {
            LOGGER.info(">>> None of your Pieces are on this position!");
            return false;
        }
        

        int colEnd = ((int) parts[1].charAt(0)) - 65;

        int rowEnd = (int) parts[1].charAt(1) - 49;
        
        if(!checkNumbers(colEnd,rowEnd)) {
        	return false;
        }
        
        Position end = new Position(rowEnd, colEnd);
        return moveHelper(start,end,team,colStart,rowStart,colEnd,rowEnd);
    }
    
    public boolean checkValue(int val) {
        return val <= 7 && val >= 0;
    }
    
    
    
    public void gameHandler() {
        int turn = 1;
        Signal sig = new Signal();
        String com;
        Boolean quitFlag = false;
        
        StringBuilder sb = new StringBuilder();
        
        this.gui = new GuiChess(sig);
        gui.initializePieces(this.mov.getField());
        TuiInput tui = new TuiInput(sig);
        tui.start();
        gui.show();
        
        LOGGER.info("HINT: Write the movement like this: A2-A3");
        LOGGER.info("HINT: To see aviable moves: show D7");
        LOGGER.info(">>> Game started:");

        while (!quitFlag) {
        	
        	
        	sb.setLength(0);
        	sb.append(">>>[Player").append(String.valueOf(turn)).append("]: ");
        	LOGGER.info(sb.toString());

            
            while(!sig.inputGiven()) {
            	// busy waiting
            }
            com = sig.getCommand();
            
            LOGGER.info(com);

            
            
            if(handleInput(com,turn)) {
                view.printField();
                turn = (turn+1)%2;
	                
	        }
            sig.reset();
            
            if(mov.checkWin() != -1) {
        		LOGGER.info("[MATCH END]");
        		sb.setLength(0);
        		sb.append("PLAYER ").append(String.valueOf(mov.checkWin())).append(" WON!%n");
        		LOGGER.info(sb.toString());
        		LOGGER.info(">>> Closing now!");
        		gui.quit();
        		

        		quitFlag = true;
        	}
            if(("quit").equals(com)) {
            	gui.quit();
            	LOGGER.info(">>> Closing now!");

            	quitFlag = true;
            }
        }
        
    }
}
