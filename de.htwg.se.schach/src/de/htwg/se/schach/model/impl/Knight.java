package de.htwg.se.schach.model.impl;

import de.htwg.se.schach.model.I.KnightInter;

public class Knight extends Piece implements KnightInter {
    public Knight(int row, int column, int team) {
        super(team);
        this.setRow(row);
        this.setColumn(column);
        this.setCut("KN");
    }

}

