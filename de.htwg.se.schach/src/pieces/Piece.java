package pieces;

import de.htwg.se.schach.Position;

public abstract class Piece {
	enum Team {
		black, white;
	};
	
	protected Team team;
	protected Position current_pos;
	protected Position start_position;
}
