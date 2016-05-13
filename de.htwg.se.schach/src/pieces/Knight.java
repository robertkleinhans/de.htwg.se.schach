package pieces;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.schach.Position;

public class Knight extends Piece {
	public Knight(int x, int y) {
		this.start_position = new Position(x,y);
		this.current_pos = new Position(x,y);
		this.shortcut = "KN";
	}
	
	public List<Position> getAllMoves() {
		List<Position> ret_list = new ArrayList<Position>();
		int x = this.current_pos.getX();
		int y = this.current_pos.getY();
		
		//TODO: Movement!

		return ret_list;
	}
}
