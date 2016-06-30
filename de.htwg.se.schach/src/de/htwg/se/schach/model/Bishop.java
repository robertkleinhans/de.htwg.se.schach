package de.htwg.se.schach.model;


public class Bishop extends Piece{
    public Bishop(int row, int column, int team) {
        super(team);
        this.setRow(row);
        this.setColumn(column);
        this.setCut("BI");
    }
}

