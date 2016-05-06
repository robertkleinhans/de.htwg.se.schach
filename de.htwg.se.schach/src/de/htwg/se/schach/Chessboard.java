package de.htwg.se.schach;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pieces.Queen;

public class Chessboard {
	private Map<Position,pieces.Piece> figure_map;
	private final int x_limit = 8;
	private final int y_limit = 8;
	
	public Chessboard() {
		this.figure_map = new HashMap<Position, pieces.Piece>();
		placeQueen();
	}
	
	public void printChessboard() {
		for (int i = 0; i < this.x_limit; i++) {
			for (int j = 0; j < this.y_limit; j++) {
				Position tmp_pos = new Position(j,i);
				if (figure_map.containsKey(tmp_pos)) {
					System.out.printf("[%s]",figure_map.get(tmp_pos).getNameShort());
				} else {
					System.out.printf("[--]");
				}
			}
			System.out.printf("\n");
		}
	}
	
	public void placeQueen() {
		Queen q = new Queen(2,3);
		System.out.println(q.getAllMoves().toString());
		printMoves(q.getAllMoves());
		this.figure_map.put(q.getCurPosition(), q);
		
	}
	
	public void printMoves(List<Position> tmp) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (tmp.contains(new Position(j,i))) {
					System.out.printf("[MM]");
				} else {
					System.out.printf("[--]");
				}
			}
			System.out.printf("\n");
		}
	}
}
