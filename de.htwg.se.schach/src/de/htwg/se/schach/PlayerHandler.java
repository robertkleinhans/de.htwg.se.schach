/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwg.se.schach;


import de.htwg.se.schach.control.I.MovementHandlerInter;
import de.htwg.se.schach.control.I.PositionInter;
import de.htwg.se.schach.control.impl.MovementHandler;
import de.htwg.se.schach.control.impl.Position;
import de.htwg.se.schach.view.I.ChessViewInter;
import de.htwg.se.schach.view.I.GuiChessInter;
import de.htwg.se.schach.view.I.SignalInter;
import de.htwg.se.schach.view.impl.*;

import org.apache.log4j.Logger;


import java.awt.Point;
/**
 *
 * @author rob
 */
public class PlayerHandler {
    private ChessViewInter view;
    private GuiChessInter gui;
    private MovementHandlerInter mov;
    String invalid = ">>> Invalid command!";
    String colError = ">>> The column must be between A and H!";
    String rowError = ">>> The row must be between 0 and 7!";
    SignalInter sig;
    
    
    private static final Logger LOGGER = Logger.getLogger("de.htwg.se.schach.playerHandler");
    
    public PlayerHandler() {
    	mov = new MovementHandler();
    	this.view = new ChessView(mov);
        view.printField();
        sig = new Signal();
    }
    
    
    public void startGame() {
        gameHandler(this.sig);
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
        PositionInter start = new Position(rowStart, colStart);
        
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
    
    boolean moveHelper(PositionInter start, Position end, int team, int colStart, int rowStart,
    		int colEnd, int rowEnd) {
    	if(view.movePiece(start, end, team)) {
        	gui.movePiece(new Point(colStart,rowStart), new Point(colEnd,rowEnd));
            return true;
        } else {
        	LOGGER.info(">>> Unknown Error : Could not make the move!");
            return false;
        }
    }
    
    boolean checkNumberString(String str) {
    	String[] parts = str.split("-");
    	
    	int colStart = ((int) parts[0].charAt(0)) - 65;

        int rowStart = (int) parts[0].charAt(1) - 49;
        if(!checkNumbers(colStart,rowStart)) {
        	return false;
        }
        
        int colEnd = ((int) parts[1].charAt(0)) - 65;

        int rowEnd = (int) parts[1].charAt(1) - 49;
        
        if(!checkNumbers(colEnd,rowEnd)) {
        	return false;
        }
        return true;
    }
    
    public boolean handleInput(String inp,int team) {
    	
    	if(("quit").equals(inp)) {
        	return true;
        }
    	
        if(!preCheck(inp) || !checkNumberString(inp)) {
        	return false;
        }      
        
        String[] parts = inp.split("-");

        int colStart = ((int) parts[0].charAt(0)) - 65;

        int rowStart = (int) parts[0].charAt(1) - 49;
        
        PositionInter start = new Position(rowStart, colStart);
        if(!view.checkPiece(start, team)) {
            LOGGER.info(">>> None of your Pieces are on this position!");
            return false;
        }
        
        int colEnd = ((int) parts[1].charAt(0)) - 65;

        int rowEnd = (int) parts[1].charAt(1) - 49;
        
        Position end = new Position(rowEnd, colEnd);
        return moveHelper(start,end,team,colStart,rowStart,colEnd,rowEnd);
    }
    
    public boolean checkValue(int val) {
        return val <= 7 && val >= 0;
    }
    
    
    
    public void gameHandler(SignalInter sig) {
        int turn = 1;
        
        String com;
        Boolean quitFlag = false;
        
        StringBuilder sb = new StringBuilder();
        
        this.gui = new GuiChess(sig);
        gui.initializePieces(this.mov.getField());
        TuiInput tui = new TuiInput(sig);
        tui.start();
        gui.show();
        
        LOGGER.info(">>>>> NEW GAME STARTED !!!!! <<<<<");
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
        		if(mov.checkWin() == 1) {
        			sb.append("WHITE WON!");
        		} else {
        			sb.append("BLACK WON!");
        		}
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
