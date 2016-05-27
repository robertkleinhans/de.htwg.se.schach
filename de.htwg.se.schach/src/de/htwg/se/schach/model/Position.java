package de.htwg.se.schach.model;

public class Position {
    private final int row;
    private final int column;
    
    public Position (int row, int column) {
        this.row = row;
        this.column = column;
    }
    
    public int getRow() {
        return this.row;
    }
    
    public int getColumn() {
        return this.column;
    }
    
    public int[] getPosition() {
        int[] tmp = {this.row,this.column};
        return tmp;
    }
    
    
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof Position)) {
            return false;
        }
        Position tmp = (Position) other;
        return (this.row == tmp.row && this.column == tmp.column);
    }
    
    @Override
    public int hashCode() {
        return 10*row + column;
    }
}
