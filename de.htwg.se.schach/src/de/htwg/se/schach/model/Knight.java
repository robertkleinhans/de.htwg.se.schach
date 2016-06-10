package de.htwg.se.schach.model;


public class Knight extends Piece {
    public Knight(int row, int column, int team) {
        super(team);
        this.row = row;
        this.column = column;
        this.cut = "KN";
    }

}

