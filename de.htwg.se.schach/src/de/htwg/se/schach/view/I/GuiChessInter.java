package de.htwg.se.schach.view.I;

import java.awt.Point;
import java.util.Map;

import de.htwg.se.schach.control.impl.Position;
import de.htwg.se.schach.model.impl.Piece;

public interface GuiChessInter {

	void initializePieces(Map<Position, Piece> figures);

	void handleClick(Point p);

	void addPiece(String fname, int x, int y);

	void show();

	void movePiece(Point start, Point end);

	void quit();

}