package de.htwg.se.schach.model;



public class Pawn extends Piece{
    public boolean firstMove;
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
}

