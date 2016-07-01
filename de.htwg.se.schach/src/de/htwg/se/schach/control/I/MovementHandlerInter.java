package de.htwg.se.schach.control.I;

import java.util.List;
import java.util.Map;

import de.htwg.se.schach.control.impl.Position;
import de.htwg.se.schach.model.impl.Piece;

public interface MovementHandlerInter {

	Map<Position, Piece> getField();

	boolean movePiece(PositionInter start, Position end, int team);

	boolean checkPiece(PositionInter pos, int team);

	boolean removePiece(PositionInter pos);

	int checkWin();

	List<Position> getMovement(PositionInter pos);

}