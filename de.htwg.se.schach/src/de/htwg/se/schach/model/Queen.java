package de.htwg.se.schach.model;

import java.util.LinkedList;
import java.util.List;


public class Queen extends Piece {
    
    public Queen(int row, int column, int team) {
        super(team);
        this.row = row;
        this.column = column;
        this.cut = "QU";
    }
    
    public List<Position> getAllMoves() {
        List<Position> ret = new LinkedList<Position>();
        
        for (int i = 0; i <= MAX_ROW; i++) {
            ret.add(new Position(i,column));
            ret.add(new Position(row,i));
            if(row+i < 8) {
                if(column+i < 8) {
                    ret.add(new Position(row+i,column+i));
                }
                if(column-i >= 0) {
                    ret.add(new Position(row+i,column-i));
                }
            }
            if(row-i >= 0) {
                if(column+i < 8) {
                    ret.add(new Position(row-i,column+i));
                }
                if(column-i >= 0) {
                    ret.add(new Position(row-i,column-i));
                }
            }
        }
        Position own = new Position(row,column);
        while(ret.contains(own)) {
            ret.remove(own);
        }
        
        return ret;
    }
}

