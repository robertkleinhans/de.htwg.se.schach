package pieces;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.schach.Position;

public class Rook extends Piece {
	public Rook(int x, int y) {
		this.start_position = new Position(x,y);
		this.current_pos = new Position(x,y);
		this.shortcut = "RO";
	}
	
	public List<Position> getAllMoves() {
		List<Position> ret_list = new ArrayList<Position>();
		int x = this.current_pos.getX();
		int y = this.current_pos.getY();
		
		//horizontal+vertical movement:
		for(int i = 0; i < 8; i++) {
			//vertical
			ret_list.add(new Position(x,i));
			//horizontal
			ret_list.add(new Position(i,y));
		}
		return ret_list;
	}
}
