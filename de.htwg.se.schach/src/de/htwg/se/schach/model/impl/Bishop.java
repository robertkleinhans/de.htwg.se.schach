package de.htwg.se.schach.model.impl;

import de.htwg.se.schach.model.I.BishopInter;

public class Bishop extends Piece implements BishopInter{
    public Bishop(int row, int column, int team) {
        super(team);
        this.setRow(row);
        this.setColumn(column);
        this.setCut("BI");
    }
}

