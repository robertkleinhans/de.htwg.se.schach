package de.htwg.se.schach.model;



public class Pawn extends Piece{
    private boolean firstMove;
    private final int direction;
    
    
    
    public Pawn(int row, int column, int direction, int team) {
        super(team);
        this.setRow(row);
        this.setColumn(column);
        this.direction = direction;
        this.setCut("PA");
        setFirstMove(true);
    }



	public int getDirection() {
		return direction;
	}



	public boolean isFirstMove() {
		return firstMove;
	}



	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}
}

