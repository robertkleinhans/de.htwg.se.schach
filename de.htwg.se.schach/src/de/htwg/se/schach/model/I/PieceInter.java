package de.htwg.se.schach.model.I;

public interface PieceInter {

	void setRow(int row);

	void setColumn(int column);

	int getTeam();

	int getRow();

	int getColumn();

	String getName();

	String getCut();

	void setCut(String cut);

}