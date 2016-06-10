package de.htwg.se.schach.model;



public class Queen extends Piece {
    
    public Queen(int row, int column, int team) {
        super(team);
        this.row = row;
        this.column = column;
        this.cut = "QU";
    }
   
}

