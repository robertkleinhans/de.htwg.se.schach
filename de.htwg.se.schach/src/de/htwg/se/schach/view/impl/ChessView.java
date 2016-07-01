/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwg.se.schach.view.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.htwg.se.schach.control.I.MovementHandlerInter;
import de.htwg.se.schach.control.I.PositionInter;
import de.htwg.se.schach.control.impl.*;
import de.htwg.se.schach.model.impl.Piece;
import de.htwg.se.schach.view.I.ChessViewInter;

import org.apache.log4j.Logger;
/**
 *
 * @author rob
 */
public class ChessView implements ChessViewInter {
    private static final int MAXCOLUMN = 7;
    private static final int MAXROW = 7;  
    
    private static final Logger LOGGER = Logger.getLogger("de.htwg.se.schach.chessView");
    
    private MovementHandlerInter movH;
    
    public ChessView(MovementHandlerInter movement) {
        this.movH = movement;
    }
    
    /* (non-Javadoc)
	 * @see de.htwg.se.schach.view.impl.ChessViewInter#movePiece(de.htwg.se.schach.control.I.PositionInter, de.htwg.se.schach.control.impl.Position, int)
	 */
    @Override
	public boolean movePiece(PositionInter start, Position end, int team) {
        return movH.movePiece(start, end, team);
    }
    
    /* (non-Javadoc)
	 * @see de.htwg.se.schach.view.impl.ChessViewInter#checkPiece(de.htwg.se.schach.control.I.PositionInter, int)
	 */
    @Override
	public boolean checkPiece(PositionInter pos, int team) {
        return movH.checkPiece(pos, team);
    }
    
    String innerPosition(PositionInter pos, Map<Position,Piece> figs) {
    	StringBuilder sb = new StringBuilder();
    	if(figs.containsKey(pos)) {
        	
        	sb.append(String.valueOf(figs.get(pos).getTeam())).append(figs.get(pos).getName());
            
        } else {
        	sb.append("---");


        }
        return sb.toString();
    }
    
    /* (non-Javadoc)
	 * @see de.htwg.se.schach.view.impl.ChessViewInter#viewMovement(de.htwg.se.schach.control.I.PositionInter)
	 */
    @Override
	public void viewMovement(PositionInter pos) {
        List<Position> ret = new LinkedList<Position>();
        Map<Position,Piece> figureHolder = movH.getField();
        ret = movH.getMovement(pos);
        StringBuilder sb = new StringBuilder();
        
        sb.append("|__||_A_||_B_||_C_||_D_||_E_||_F_||_G_||_H_|");
        LOGGER.info(sb.toString());
        sb.setLength(0);
        
        for (int i= 0; i <= MAXROW; i++) {

        	sb.append("|").append(String.valueOf(i+1)).append("_|");

            
            for (int j=0; j <= MAXCOLUMN; j++) {
                PositionInter tmp = new Position(i,j);
                
                if(ret.contains(tmp)) {
                	sb.append(">");

                    
                    sb.append(innerPosition(tmp,figureHolder));
                    sb.append("<");

              
                } else {
                    sb.append("[");
                    sb.append(innerPosition(tmp,figureHolder));
                    sb.append("]");
                }
            }
            LOGGER.info(sb.toString());
            sb.setLength(0);
          
        }
        LOGGER.info(sb.toString());
    
    }
    
    /* (non-Javadoc)
	 * @see de.htwg.se.schach.view.impl.ChessViewInter#printField()
	 */
    @Override
	public void printField() {
        Map<Position,Piece> figureHolder = movH.getField();
        StringBuilder sb = new StringBuilder();
        
        sb.append("|__||_A_||_B_||_C_||_D_||_E_||_F_||_G_||_H_|");
        LOGGER.info(sb.toString());
        sb.setLength(0);

        
        for(int i = 0; i <= MAXROW; i++) {
   
            
        	sb.append("|").append(String.valueOf(i+1)).append("_|");
            
            for(int j = 0; j <= MAXCOLUMN; j++) {
                PositionInter tmp = new Position(i,j);
                sb.append("[");
                sb.append(innerPosition(tmp,figureHolder));
                sb.append("]");
            }
            LOGGER.info(sb.toString());
            sb.setLength(0);
      
        }
        LOGGER.info(sb.toString());
    
    }
}
