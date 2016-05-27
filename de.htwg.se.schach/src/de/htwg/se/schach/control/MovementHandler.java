package de.htwg.se.schach.control;

import java.util.HashMap;
import java.util.Map;

import de.htwg.se.schach.model.*;




public class MovementHandler {
    private final int MAX_COLUMN = 7;
    private final int MAX_ROW = 7;  
    private Map<Position,Piece>figure_holder;
    
    public MovementHandler() {
        figure_holder = new HashMap<Position,Piece>();
        for (int i = 0; i <= MAX_COLUMN; i++) {
            figure_holder.put(new Position(1,i),new Pawn(1,i,1,0));
            figure_holder.put(new Position(6,i),new Pawn(6,i,-1,1));
            
            if(i == 0 || i == 7) {
                figure_holder.put(new Position(0,i),new Rook(0,i,0));
                figure_holder.put(new Position(7,i),new Rook(7,i,1));
            } else if (i == 1 || i == 6) {
                figure_holder.put(new Position(0,i),new Knight(0,i,0));
                figure_holder.put(new Position(7,i),new Knight(7,i,1));
            } else if (i == 2 || i == 5) {
                figure_holder.put(new Position(0,i),new Bishop(0,i,0));
                figure_holder.put(new Position(7,i),new Bishop(7,i,1));
            } else if (i == 3) {
                figure_holder.put(new Position(0,i),new Queen(0,i,0));
                figure_holder.put(new Position(7,i),new Queen(7,i,1));
            } else if (i == 4) {
                figure_holder.put(new Position(0,i),new King(0,i,0));
                figure_holder.put(new Position(7,i),new King(7,i,1));
            }
        }
    }
    
    
    public void printField() {
        for(int i = 0; i <= MAX_ROW; i++) {
            for(int j = 0; j <= MAX_COLUMN; j++) {
                Position tmp = new Position(i,j);
                if(figure_holder.containsKey(tmp)) {
                    System.out.printf("[%s]",figure_holder.get(tmp).getName());
                } else {
                    System.out.printf("[--]");
                }
            }
            System.out.printf("\n");
        }
        System.out.printf("\n");
    }
    
}
