package pieces;

import de.htwg.se.schach.Position;

public class Queen extends Piece {
	
	public Queen(int x, int y) {
		this.start_position = new Position(x,y);
		this.current_pos = new Position(x,y);
		this.shortcut = "QE";
	}
}
