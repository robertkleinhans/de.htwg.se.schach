package de.htwg.se.schach.model;

import java.util.LinkedList;
import java.util.List;

import de.htwg.se.schach.control.Position;


public class Knight extends Piece {
    public Knight(int row, int column, int team) {
        super(team);
        this.row = row;
        this.column = column;
        this.cut = "KN";
    }
    
    public List<Position> getAllMoves() {
        List<Position> ret = new LinkedList<Position>();
        
        if(row+2 < 8) {
            if (column-1 >= 0) {
                ret.add(new Position(row+2,column-1));
            }
            if (column+1 < 8) {
                ret.add(new Position(row+2,column+1));
            }
        }
        if(row+1 < 8) {
            if (column-2 >= 0) {
                ret.add(new Position(row+1,column-2));
            }
            if (column+2 < 8) {
                ret.add(new Position(row+1, column+2));
            }
        }
        if(row-2 >= 0) {
            if(column-1 >= 0) {
                ret.add(new Position(row-2,column-1));
            }
            if(column+1 < 8) {
                ret.add(new Position(row-2,column+1));
            }
        }
        if(row-1 >= 0) {
            if(column-2 >= 0) {
                ret.add(new Position(row-1,column-2));
            }
            if(column+2 < 8) {
                ret.add(new Position(row-1,column+2));
            }
        }
        
        return ret;
    }
}

