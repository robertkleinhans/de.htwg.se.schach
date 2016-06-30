/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwg.se.schach.view;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import de.htwg.se.schach.model.Piece;
import de.htwg.se.schach.control.*;
import org.apache.log4j.Logger;
/**
 *
 * @author rob
 */
public class ChessView {
    private static final int MAXCOLUMN = 7;
    private static final int MAXROW = 7;  
    
    private static final Logger LOGGER = Logger.getLogger("de.htwg.se.schach.chessView");
    
    private MovementHandler movH;
    
    public ChessView(MovementHandler movement) {
        this.movH = movement;
    }
    
    public boolean movePiece(Position start, Position end, int team) {
        return movH.movePiece(start, end, team);
    }
    
    public boolean checkPiece(Position pos, int team) {
        return movH.checkPiece(pos, team);
    }
    
    String innerPosition(Position pos, Map<Position,Piece> figs) {
    	StringBuilder sb = new StringBuilder();
    	if(figs.containsKey(pos)) {
        	
        	sb.append(String.valueOf(figs.get(pos).getTeam())).append(figs.get(pos).getName());
            
        } else {
        	sb.append("---");


        }
        return sb.toString();
    }
    
    public void viewMovement(Position pos) {
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
                Position tmp = new Position(i,j);
                
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
    
    public void printField() {
        Map<Position,Piece> figureHolder = movH.getField();
        StringBuilder sb = new StringBuilder();
        
        sb.append("|__||_A_||_B_||_C_||_D_||_E_||_F_||_G_||_H_|");
        LOGGER.info(sb.toString());
        sb.setLength(0);

        
        for(int i = 0; i <= MAXROW; i++) {
   
            
        	sb.append("|").append(String.valueOf(i+1)).append("_|");
            
            for(int j = 0; j <= MAXCOLUMN; j++) {
                Position tmp = new Position(i,j);
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
