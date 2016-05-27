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
    
    public void setRow(int row) {
    	if (row <= 7 && row >= 0) {
    		this.row = row;
    	}
    }
    
    public void setColumn(int column) {
    	if (column <= 7 && column >= 0) {
    		this.column = column;
    	}
    }
    
    public int getTeam() {
    	return team;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getColumn() {
        return column;
    }
    
    public String getName() {
        return cut;
    }
}
