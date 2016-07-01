package de.htwg.se.schach.model.impl;

import de.htwg.se.schach.model.I.PawnInter;

public class Pawn extends Piece implements PawnInter{
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



	/* (non-Javadoc)
	 * @see de.htwg.se.schach.model.impl.PawnInter#getDirection()
	 */
	@Override
	public int getDirection() {
		return direction;
	}



	/* (non-Javadoc)
	 * @see de.htwg.se.schach.model.impl.PawnInter#isFirstMove()
	 */
	@Override
	public boolean isFirstMove() {
		return firstMove;
	}



	/* (non-Javadoc)
	 * @see de.htwg.se.schach.model.impl.PawnInter#setFirstMove(boolean)
	 */
	@Override
	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}
}

