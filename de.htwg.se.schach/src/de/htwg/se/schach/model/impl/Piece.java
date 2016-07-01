package de.htwg.se.schach.model.impl;

import de.htwg.se.schach.model.I.PieceInter;

public abstract class Piece implements PieceInter {
    static final int MAXROW = 7;
    static final int MAXCOLUMN = 7;
    private int row;
    private int column;
    private String cut;
    private final int team;
    
    public Piece(int team) {
        this.team = team;
    }
    
    /* (non-Javadoc)
	 * @see de.htwg.se.schach.model.impl.PieceInter#setRow(int)
	 */
    @Override
	public void setRow(int row) {
    	if (row <= 7 && row >= 0) {
    		this.row = row;
    	}
    }
    
    /* (non-Javadoc)
	 * @see de.htwg.se.schach.model.impl.PieceInter#setColumn(int)
	 */
    @Override
	public void setColumn(int column) {
    	if (column <= 7 && column >= 0) {
    		this.column = column;
    	}
    }
    
    /* (non-Javadoc)
	 * @see de.htwg.se.schach.model.impl.PieceInter#getTeam()
	 */
    @Override
	public int getTeam() {
    	return team;
    }
    
    /* (non-Javadoc)
	 * @see de.htwg.se.schach.model.impl.PieceInter#getRow()
	 */
    @Override
	public int getRow() {
        return row;
    }
    
    /* (non-Javadoc)
	 * @see de.htwg.se.schach.model.impl.PieceInter#getColumn()
	 */
    @Override
	public int getColumn() {
        return column;
    }
    
    /* (non-Javadoc)
	 * @see de.htwg.se.schach.model.impl.PieceInter#getName()
	 */
    @Override
	public String getName() {
        return getCut();
    }

	/* (non-Javadoc)
	 * @see de.htwg.se.schach.model.impl.PieceInter#getCut()
	 */
	@Override
	public String getCut() {
		return cut;
	}

	/* (non-Javadoc)
	 * @see de.htwg.se.schach.model.impl.PieceInter#setCut(java.lang.String)
	 */
	@Override
	public void setCut(String cut) {
		this.cut = cut;
	}
}
