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
/**
 *
 * @author rob
 */
public class ChessView {
    private final int MAX_COLUMN = 7;
    private final int MAX_ROW = 7;  
    
    private MovementHandler movH;
    
    public ChessView() {
        this.movH = new MovementHandler();
    }
    
    public boolean movePiece(Position start, Position end, int team) {
        return movH.movePiece(start, end, team);
    }
    
    public boolean checkPiece(Position pos, int team) {
        return movH.checkPiece(pos, team);
    }
    
    public void viewMovement(Position pos) {
        List<Position> ret = new LinkedList<Position>();
        Map<Position,Piece> figure_holder = movH.getField();
        
        ret = movH.getMovement(pos);
        
        System.out.printf("|__||_A_||_B_||_C_||_D_||_E_||_F_||_G_||_H_|\n");
        
        for (int i= 0; i <= MAX_ROW; i++) {
            
            System.out.printf("|%d_|",i+1);
            
            for (int j=0; j <= MAX_COLUMN; j++) {
                Position tmp = new Position(i,j);
                
                if(ret.contains(tmp)) {
                    System.out.printf(">");
                    if(figure_holder.containsKey(tmp)) {
                        System.out.printf("%d%s",figure_holder.get(tmp).team,figure_holder.get(tmp).getName());
                    } else {
                        System.out.printf("---");
                    }
                    System.out.printf("<");
                } else {
                    if(figure_holder.containsKey(tmp)) {
                        System.out.printf("[%d%s]",figure_holder.get(tmp).team,figure_holder.get(tmp).getName());
                    } else {
                        System.out.printf("[---]");
                    }
                }
            }
            System.out.printf("\n");
        }
        System.out.printf("\n\n");
    }
    
    public void printField() {
        Map<Position,Piece> figure_holder = movH.getField();
        
        System.out.printf("|__||_A_||_B_||_C_||_D_||_E_||_F_||_G_||_H_|\n");
        
        for(int i = 0; i <= MAX_ROW; i++) {
            System.out.printf("|%d_|",i+1);
            for(int j = 0; j <= MAX_COLUMN; j++) {
                Position tmp = new Position(i,j);
                if(figure_holder.containsKey(tmp)) {
                    System.out.printf("[%d%s]",figure_holder.get(tmp).team,figure_holder.get(tmp).getName());
                } else {
                    System.out.printf("[---]");
                }
            }
            System.out.printf("\n");
        }
        System.out.printf("\n");
    }
}
