package de.htwg.se.schach.model;


public class Rook extends Piece {
    public Rook(int row, int column, int team) {
        super(team);
        this.setRow(row);
        this.setColumn(column);
        this.setCut("RO");
    }
}
