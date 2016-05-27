package de.htwg.se.schach.model;

import java.util.LinkedList;
import java.util.List;

import de.htwg.se.schach.control.Position;


public class King extends Piece {
    public King(int row, int column, int team) {
        super(team);
        this.row = row;
        this.column = column;
        this.cut = "KI";
    }
    
    public List<Position> getAllMoves() {
        List<Position> ret = new LinkedList<Position>();
        
        if(row+1 < 8) {
            ret.add(new Position(row+1,column));
            if(column+1 < 8) {
                ret.add(new Position(row+1,column+1));
                ret.add(new Position(row,column+1));
            }
            if(column-1 >= 0) {
                ret.add(new Position(row+1,column-1));
                ret.add(new Position(row,column-1));
            }
        }
        if(row-1 >= 0) {
            ret.add(new Position(row-1,column));
            if(column+1 < 8) {
                ret.add(new Position(row-1,column+1));
                ret.add(new Position(row,column+1));
            }
            if(column-1 >= 0) {
                ret.add(new Position(row-1,column-1));
                ret.add(new Position(row,column-1));
            }
        }
        
        return ret;
    }
}

