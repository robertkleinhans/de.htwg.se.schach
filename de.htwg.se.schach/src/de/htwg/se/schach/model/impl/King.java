package de.htwg.se.schach.model.impl;

import de.htwg.se.schach.model.I.KingInter;

public class King extends Piece implements KingInter {
    public King(int row, int column, int team) {
        super(team);
        this.setRow(row);
        this.setColumn(column);
        this.setCut("KI");
    }
    
}

