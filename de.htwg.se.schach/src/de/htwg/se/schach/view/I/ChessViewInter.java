package de.htwg.se.schach.view.I;

import de.htwg.se.schach.control.I.PositionInter;
import de.htwg.se.schach.control.impl.Position;

public interface ChessViewInter {

	boolean movePiece(PositionInter start, Position end, int team);

	boolean checkPiece(PositionInter pos, int team);

	void viewMovement(PositionInter pos);

	void printField();

}