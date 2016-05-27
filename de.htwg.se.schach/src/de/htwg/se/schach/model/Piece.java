package de.htwg.se.schach.model;

import java.util.List;

import de.htwg.se.schach.control.Position;

public abstract class Piece {
    final int MAX_ROW = 7;
    final int MAX_COLUMN = 7;
    public int row;
    public int column;
    public String cut;
    public final int team;
    
    public Piece(int team) {
        this.team = team;
    }
    
    public abstract List<Position> getAllMoves();
    
    public int getRow() {
        return row;
    }
    
    public int getColumn() {
        return column;
    }
    
    public String getName() {
        return cut;
    }
    
    public int[] getPosition() {
        int[] tmp = {row,column};
        return tmp;
    }
}
