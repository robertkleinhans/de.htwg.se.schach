package de.htwg.se.schach.model;



public class King extends Piece {
    public King(int row, int column, int team) {
        super(team);
        this.row = row;
        this.column = column;
        this.cut = "KI";
    }
    
}

