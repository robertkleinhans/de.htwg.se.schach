package de.htwg.se.schach;

import de.htwg.se.schach.model.Chessboard;

public class Chess {
	public static void main(String[] args) {
		Chessboard chess = new Chessboard();
		System.out.printf("\n-----------\n");
		chess.printChessboard();
	}
}
