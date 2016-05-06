package pieces;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.schach.Position;

public class Queen extends Piece {
	
	public Queen(int x, int y) {
		this.start_position = new Position(x,y);
		this.current_pos = new Position(x,y);
		this.shortcut = "QE";
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
			
			if (x+i < 8 && y+i < 8) {
				ret_list.add(new Position(x+i,y+i));
			}
			if (x-i >= 0 && y-i >= 0) {
				ret_list.add(new Position(x-i,y-i));
			}
			if (x+i < 8 && y-i >= 0) {
				ret_list.add(new Position(x+i,y-i));
			}
			if (x-i >= 0 && y+i < 8) {
				ret_list.add(new Position(x-i,y+i));
			}
		}
		
		
		
		
		return ret_list;
	}
}
