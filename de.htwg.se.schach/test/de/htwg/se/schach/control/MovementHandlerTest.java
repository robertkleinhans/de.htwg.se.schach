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
	
	@Test
	public void testGetPawnMovement() {
		//Stage 1
		MovementHandler mov = new MovementHandler();
		assertTrue(mov.movePiece(new Position(6,1), new Position(4,1), 1));
		assertTrue(mov.movePiece(new Position(4,1), new Position(3,1), 1));
		assertTrue(mov.movePiece(new Position(3,1), new Position(2,1), 1));
		assertFalse(mov.movePiece(new Position(2,1), new Position(1,1), 1));
		mov.getMovement(new Position(0,1));
		
		//Stage 2
		mov = new MovementHandler();
		assertTrue(mov.movePiece(new Position(1,0), new Position(3,0), 0));
		assertTrue(mov.movePiece(new Position(3,0), new Position(4,0), 0));
		mov.getMovement(new Position(6,0));
		assertTrue(mov.movePiece(new Position(1,2), new Position(3,2), 0));
		assertTrue(mov.movePiece(new Position(3,2), new Position(4,2), 0));
		
		assertTrue(mov.movePiece(new Position(6,1), new Position(5,1), 1));
		mov.getMovement(new Position(5,1));
		
		//Stage 3
		mov = new MovementHandler();
		assertTrue(mov.movePiece(new Position(1,0), new Position(2,0), 0));
		assertTrue(mov.movePiece(new Position(1,2), new Position(2,2), 0));
		mov.getMovement(new Position(1,1));
		
		//Stage 4
		mov = new MovementHandler();
		assertTrue(mov.removePiece(new Position(6,0)));
		assertTrue(mov.removePiece(new Position(7,0)));
		for (int i = 1; i < 7; i++) {
			assertTrue(mov.movePiece(new Position(i,0), new Position(i+1,0), 0));
			mov.getMovement(new Position(i+1,0));
		}
		
		//Stage 5
		mov = new MovementHandler();
		assertTrue(mov.removePiece(new Position(1,0)));
		assertTrue(mov.removePiece(new Position(0,0)));
		
		for (int i = 6; i > 0; i--) {
			assertTrue(mov.movePiece(new Position(i,0),new Position(i-1,0),1));
			mov.getMovement(new Position(i-1,0));
		}
	}
	
	@Test
	public void testGetKingMovement() {
		//Stage 1
		MovementHandler mov = new MovementHandler();
		assertTrue(mov.removePiece(new Position(1,4)));
		
		mov.getMovement(new Position(0,4));
		mov.getMovement(new Position(7,4));
		for(int i = 0; i < 6; i++) {
			assertTrue(mov.movePiece(new Position(i,4), new Position(i+1,4), 0));
			mov.getMovement(new Position(i+1,4));
		}
		
		//Stage 2
		mov = new MovementHandler();
		assertTrue(mov.removePiece(new Position(0,3)));
		assertTrue(mov.removePiece(new Position(0,2)));
		assertTrue(mov.removePiece(new Position(0,1)));
		assertTrue(mov.removePiece(new Position(0,0)));
		for (int i = 4; i > 0; i--) {
			assertTrue(mov.movePiece(new Position(0,i), new Position(0,i-1), 0));
			mov.getMovement(new Position(0,i-1));
		}
		
		//Stage 3
		mov = new MovementHandler();
		assertTrue(mov.removePiece(new Position(0,5)));
		assertTrue(mov.removePiece(new Position(0,6)));
		assertTrue(mov.removePiece(new Position(0,7)));
		for (int i = 4; i < 7; i++) {
			assertTrue(mov.movePiece(new Position(0,i), new Position(0,i+1), 0));
			mov.getMovement(new Position(0,i+1));
		}
		
		//Stage 4
		mov = new MovementHandler();
		assertTrue(mov.removePiece(new Position(7,3)));
		assertTrue(mov.removePiece(new Position(7,2)));
		assertTrue(mov.removePiece(new Position(7,1)));
		assertTrue(mov.removePiece(new Position(7,0)));
		
		for (int i = 4; i > 0; i--) {
			assertTrue(mov.movePiece(new Position(7,i), new Position(7,i-1), 1));
			mov.getMovement(new Position(7,i-1));
		}
		
		//Stage 5
		mov = new MovementHandler();
		assertTrue(mov.removePiece(new Position(7,5)));
		assertTrue(mov.removePiece(new Position(7,6)));
		assertTrue(mov.removePiece(new Position(7,7)));
		for (int i = 4; i < 7; i++) {
			assertTrue(mov.movePiece(new Position(7,i), new Position(7,i+1), 1));
			mov.getMovement(new Position(7,i+1));
		}
	}
	
	@Test
	public void testGetHorVerMovement() {
		//Stage 1
		MovementHandler mov = new MovementHandler();
		mov.getMovement(new Position(0,0));
		mov.getMovement(new Position(0,7));
		mov.getMovement(new Position(7,7));
		mov.getMovement(new Position(7,0));
		
		//Stage 2
		assertTrue(mov.removePiece(new Position(1,0)));
		assertTrue(mov.movePiece(new Position(0,0), new Position(6,0), 0));
		mov.getMovement(new Position(6,0));
		assertTrue(mov.movePiece(new Position(6,0), new Position(4,0), 0));
		mov.getMovement(new Position(4,0));
		
		//Stage 3
		mov = new MovementHandler();
		assertTrue(mov.removePiece(new Position(6,0)));
		assertTrue(mov.movePiece(new Position(7,0), new Position(1,0), 1));
		mov.getMovement(new Position(1,0));
		
		//Stage 4
		mov = new MovementHandler();
		assertTrue(mov.removePiece(new Position(6,7)));
		assertTrue(mov.movePiece(new Position(7,7), new Position(1,7), 1));
		mov.getMovement(new Position(1,7));
		assertTrue(mov.movePiece(new Position(1,7), new Position(4,7), 1));
		mov.getMovement(new Position(4,7));
	}
	
	@Test
	public void testGetDiagonalMovement() {
		MovementHandler mov = new MovementHandler();
	}
}
