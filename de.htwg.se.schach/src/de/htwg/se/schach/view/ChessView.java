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
    private final int MAXCOLUMN = 7;
    private final int MAXROW = 7;  
    
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
    
    public void viewMovement(Position pos) {
        List<Position> ret = new LinkedList<Position>();
        Map<Position,Piece> figureHolder = movH.getField();
        ret = movH.getMovement(pos);
        StringBuilder sb = new StringBuilder();
        
        sb.append("|__||_A_||_B_||_C_||_D_||_E_||_F_||_G_||_H_|");
        LOGGER.info(sb.toString());
        sb.setLength(0);
        //System.out.printf("|__||_A_||_B_||_C_||_D_||_E_||_F_||_G_||_H_|%n");
        
        for (int i= 0; i <= MAXROW; i++) {

        	sb.append("|").append(String.valueOf(i+1)).append("_|");

            //System.out.printf("|%d_|",i+1);
            
            for (int j=0; j <= MAXCOLUMN; j++) {
                Position tmp = new Position(i,j);
                
                if(ret.contains(tmp)) {
                	sb.append(">");

                    //System.out.printf(">");
                    if(figureHolder.containsKey(tmp)) {
                    	
                    	sb.append(String.valueOf(figureHolder.get(tmp).getTeam())).append(figureHolder.get(tmp).getName());
                        
                    	//System.out.printf("%d%s",figureHolder.get(tmp).getTeam(),figureHolder.get(tmp).getName());
                    } else {
                    	sb.append("---");

                        //System.out.printf("---");
                    }
                    sb.append("<");

                    //System.out.printf("<");
                } else {
                    if(figureHolder.containsKey(tmp)) {
                    	
                    	sb.append("[").append(String.valueOf(figureHolder.get(tmp).getTeam())).append(figureHolder.get(tmp).getName()).append("]");

                    	//System.out.printf("[%d%s]",figureHolder.get(tmp).getTeam(),figureHolder.get(tmp).getName());
                    } else {
                    	sb.append("---");

                        //System.out.printf("[---]");
                    }
                }
            }
            LOGGER.info(sb.toString());
            sb.setLength(0);
            //System.out.printf("%n");
        }
        LOGGER.info(sb.toString());
        //System.out.printf("%n%n");
    }
    
    public void printField() {
        Map<Position,Piece> figureHolder = movH.getField();
        StringBuilder sb = new StringBuilder();
        
        sb.append("|__||_A_||_B_||_C_||_D_||_E_||_F_||_G_||_H_|");
        LOGGER.info(sb.toString());
        sb.setLength(0);
        //System.out.printf("|__||_A_||_B_||_C_||_D_||_E_||_F_||_G_||_H_|%n");
        
        for(int i = 0; i <= MAXROW; i++) {
            //System.out.printf("|%d_|",i+1);
            
        	sb.append("|").append(String.valueOf(i+1)).append("_|");
            
            for(int j = 0; j <= MAXCOLUMN; j++) {
                Position tmp = new Position(i,j);
                if(figureHolder.containsKey(tmp)) {
                	
                	sb.append("[").append(String.valueOf(figureHolder.get(tmp).getTeam())).append(figureHolder.get(tmp).getName()).append("]");
                    
                    //System.out.printf("[%d%s]",figureHolder.get(tmp).getTeam(),figureHolder.get(tmp).getName());
                } else {
                	sb.append("[---]");
                    //System.out.printf("[---]");
                }
            }
            LOGGER.info(sb.toString());
            sb.setLength(0);
            //System.out.printf("%n");
        }
        LOGGER.info(sb.toString());
        //System.out.printf("%n");
    }
}
