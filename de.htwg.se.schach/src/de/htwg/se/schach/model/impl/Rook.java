package de.htwg.se.schach.model.impl;

import de.htwg.se.schach.model.I.RookInter;

public class Rook extends Piece implements RookInter {
    public Rook(int row, int column, int team) {
        super(team);
        this.setRow(row);
        this.setColumn(column);
        this.setCut("RO");
    }
}
