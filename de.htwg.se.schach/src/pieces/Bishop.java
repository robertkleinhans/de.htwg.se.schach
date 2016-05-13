package pieces;

import de.htwg.se.schach.Position;

public class Bishop extends Piece {

	public Bishop(int x, int y) {
		this.start_position = new Position(x,y);
		this.current_pos = new Position(x,y);
		this.shortcut = "BI";
	}
	
	
	
}
