package de.htwg.se.schach.model.impl;

import de.htwg.se.schach.model.I.QueenInter;

public class Queen extends Piece implements QueenInter {
    
    public Queen(int row, int column, int team) {
        super(team);
        this.setRow(row);
        this.setColumn(column);
        this.setCut("QU");
    }
   
}

