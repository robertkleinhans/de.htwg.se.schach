package de.htwg.se.schach.model;



public class King extends Piece {
    public King(int row, int column, int team) {
        super(team);
        this.setRow(row);
        this.setColumn(column);
        this.setCut("KI");
    }
    
}

