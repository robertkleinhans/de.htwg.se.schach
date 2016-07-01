package de.htwg.se.schach.control.impl;

import de.htwg.se.schach.control.I.PositionInter;

public class Position implements PositionInter {
    private final int row;
    private final int column;
    
    public Position (int row, int column) {
        this.row = row;
        this.column = column;
    }
    
    /* (non-Javadoc)
	 * @see de.htwg.se.schach.control.impl.PositionInter#getRow()
	 */
    @Override
	public int getRow() {
        return this.row;
    }
    
    /* (non-Javadoc)
	 * @see de.htwg.se.schach.control.impl.PositionInter#getColumn()
	 */
    @Override
	public int getColumn() {
        return this.column;
    } 
    
    /* (non-Javadoc)
	 * @see de.htwg.se.schach.control.impl.PositionInter#equals(java.lang.Object)
	 */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof Position)) {
            return false;
        }
        Position tmp = (Position) other;
        return this.row == tmp.row && this.column == tmp.column;
    }
    
    /* (non-Javadoc)
	 * @see de.htwg.se.schach.control.impl.PositionInter#hashCode()
	 */
    @Override
    public int hashCode() {
        return 10*row + column;
    }
}
