package de.htwg.se.schach.model;

import java.util.LinkedList;
import java.util.List;

import de.htwg.se.schach.control.Position;


public class Rook extends Piece {
    public Rook(int row, int column, int team) {
        super(team);
        this.row = row;
        this.column = column;
        this.cut = "RO";
    }
    
    public List<Position> getAllMoves() {
        List<Position> ret = new LinkedList<Position>();
        
        for (int i = 0; i <= MAX_ROW; i++) {
            ret.add(new Position(i,column));
            ret.add(new Position(row,i));
        }
        Position own = new Position(row,column);
        while(ret.contains(own)) {
            ret.remove(own);
        }
        
        return ret;
    }    
}
