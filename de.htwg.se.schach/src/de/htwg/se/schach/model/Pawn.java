package de.htwg.se.schach.model;

import java.util.LinkedList;
import java.util.List;

import de.htwg.se.schach.control.Position;

public class Pawn extends Piece{
    private boolean firstMove;
    private final int direction;
    
    public Pawn(int row, int column, int direction, int team) {
        super(team);
        this.row = row;
        this.column = column;
        this.direction = direction;
        this.cut = "PA";
        firstMove = true;
    }
    
    public int getDirection() {
    	return this.direction;
    }
    
    public void changeFirstMove() {
    	firstMove = false;
    }
    
    public List<Position> getAllMoves() {
        List<Position> ret = new LinkedList<Position>();
        if(firstMove) {
            ret.add(new Position(row+2*direction,column));
        } 
        if (row+direction >= 0 && row+direction < 8) {
            ret.add(new Position(row+direction,column));
            if (column+1 < 8) {
                ret.add(new Position(row+direction,column+1));
            }
            if (column-1 >= 0) {
                ret.add(new Position(row+direction,column-1));
            }
        } 
        
        
        
        return ret;
    }
}

