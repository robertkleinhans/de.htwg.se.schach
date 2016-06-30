package de.htwg.se.schach.model;


public class Knight extends Piece {
    public Knight(int row, int column, int team) {
        super(team);
        this.setRow(row);
        this.setColumn(column);
        this.setCut("KN");
    }

}

