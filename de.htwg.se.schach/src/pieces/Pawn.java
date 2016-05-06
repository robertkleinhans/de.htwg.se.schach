package pieces;

import java.util.ArrayList;

import de.htwg.se.schach.Position;

public class Pawn extends Piece {
	
	private boolean first_move;
	
	public Pawn(int x, int y, Team team) {
		this.first_move = true;
		this.team = team;
		this.current_pos = new Position(x,y);
		this.start_position = new Position(x,y);
	}
	
	public ArrayList<int[]> showAvailableMoves() {
		int x = this.current_pos.getX();
		int y = this.current_pos.getY();
		ArrayList<int[]> moves = new ArrayList<int[]>();
		
		
		if (this.first_move) {
			if (y == 6) {
				int tmp[] = {x,y-1};
				moves.add(tmp);
			}
		}
		return moves;
	}
}
