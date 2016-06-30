package de.htwg.se.schach.view;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.schach.control.MovementHandler;
import de.htwg.se.schach.control.Position;
import de.htwg.se.schach.view.ChessView;

public class ChessViewTest {

	@Test
	public void testChessView() {
		MovementHandler mov = new MovementHandler();
		ChessView view = new ChessView(mov);
		view.printField();
	}
	
	@Test
	public void testMovePiece() {
		MovementHandler mov = new MovementHandler();
		ChessView view = new ChessView(mov);
		assertTrue(view.movePiece(new Position(1,0), new Position(3,0), 0));
	}
	
	@Test
	public void testCheckPiece() {
		MovementHandler mov = new MovementHandler();
		ChessView view = new ChessView(mov);
		assertTrue(view.checkPiece(new Position(0,1), 0));
	}
	
	@Test
	public void testViewMovement() {
		MovementHandler mov = new MovementHandler();
		ChessView view = new ChessView(mov);
		view.viewMovement(new Position(0,1));
		assertTrue(view.movePiece(new Position(0,1), new Position(2,2), 0));
		assertTrue(view.movePiece(new Position(2,2), new Position(4,3), 0));
		view.viewMovement(new Position(4,3));
	}
}
