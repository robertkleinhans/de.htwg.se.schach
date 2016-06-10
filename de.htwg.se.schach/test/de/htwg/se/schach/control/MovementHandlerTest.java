package de.htwg.se.schach.control;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import de.htwg.se.schach.control.MovementHandler;
import de.htwg.se.schach.model.Piece;

public class MovementHandlerTest {

	@Test
	public void testMovementHandler() {
		MovementHandler mov = new MovementHandler();
		Map<Position,Piece> tmp = mov.getField();
		assertTrue(tmp.size() == 32);
	}
	
	@Test
	public void testGetMovement() {
		MovementHandler mov = new MovementHandler();
		List<Position> mov_list;
		for(int i = 0; i < 8; i++) {
			mov_list = mov.getMovement(new Position(0,i));
		}
		mov_list = mov.getMovement(new Position(1,0));
		mov_list = mov.getMovement(new Position(2,0));
		assertTrue(mov_list.isEmpty());
	}
	
	@Test
	public void testCheckPiece() {
		MovementHandler mov = new MovementHandler();
		assertTrue(mov.checkPiece(new Position(0,0), 0));
		assertFalse(mov.checkPiece(new Position(0,0), 1));
		assertFalse(mov.checkPiece(new Position(3,3), 1));
	}
	
	@Test
	public void testRemovePiece() {
		MovementHandler mov = new MovementHandler();
		assertFalse(mov.removePiece(new Position(3,3)));
		assertTrue(mov.removePiece(new Position(0,0)));
	}
	
	@Test
	public void testCheckWin() {
		MovementHandler mov = new MovementHandler();
		assertTrue(mov.checkWin() == -1);
		assertTrue(mov.removePiece(new Position(0,4)));
		assertTrue(mov.checkWin() == 1);
		
		mov = new MovementHandler();
		assertTrue(mov.checkWin() == -1);
		assertTrue(mov.removePiece(new Position(7,4)));
		assertTrue(mov.checkWin() == 0);
		
		assertTrue(mov.removePiece(new Position(0,4)));
		assertTrue(mov.checkWin() == -1);
	}
	
	@Test
	public void testMovePiece() {
		MovementHandler mov = new MovementHandler();
		assertFalse(mov.movePiece(new Position(3,3), new Position(3,4), 0));
		assertFalse(mov.movePiece(new Position(1,0), new Position(3,0), 1));
		assertTrue(mov.movePiece(new Position(1,0), new Position(3,0), 0));
		assertFalse(mov.movePiece(new Position(0,0), new Position(0,4), 0));
		assertTrue(mov.movePiece(new Position(0,1), new Position(2,0), 0));
	}
}
