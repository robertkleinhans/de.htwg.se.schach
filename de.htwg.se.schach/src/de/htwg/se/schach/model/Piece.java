package de.htwg.se.schach.model;


public abstract class Piece {
    final static int maxROW = 7;
    final static int maxCOLUMN = 7;
    private int row;
    private int column;
    private String cut;
    private final int team;
    
    public Piece(int team) {
        this.team = team;
    }
    
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
        return getCut();
    }

	public String getCut() {
		return cut;
	}

	public void setCut(String cut) {
		this.cut = cut;
	}
}
