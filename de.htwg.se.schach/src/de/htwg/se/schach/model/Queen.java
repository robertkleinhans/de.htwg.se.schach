package de.htwg.se.schach.model;



public class Queen extends Piece {
    
    public Queen(int row, int column, int team) {
        super(team);
        this.setRow(row);
        this.setColumn(column);
        this.setCut("QU");
    }
   
}

