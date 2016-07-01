package de.htwg.se.schach.control.I;

public interface PositionInter {

	int getRow();

	int getColumn();

	boolean equals(Object other);

	int hashCode();

}