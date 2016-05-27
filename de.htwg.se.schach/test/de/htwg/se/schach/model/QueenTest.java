package de.htwg.se.schach.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import de.htwg.se.schach.control.Position;

public class QueenTest {
	@Test
	public void testGetAllMoves() {
		Queen que = new Queen(0,0,1);
		List<Position> tmp = que.getAllMoves();
		assertEquals(21,tmp.size());
		for(int i = 1; i < 8; i++) {
			assertTrue(tmp.contains(new Position(i,0)));
			assertTrue(tmp.contains(new Position(0,i)));
			assertTrue(tmp.contains(new Position(i,i)));
		}
		
		que.setColumn(7);
		tmp = que.getAllMoves();
		assertEquals(21,tmp.size());
		for(int i = 1; i < 8; i++) {
			assertTrue(tmp.contains(new Position(i,7)));
			assertTrue(tmp.contains(new Position(0,i-1)));
			assertTrue(tmp.contains(new Position(i,7-i)));
		}
		
		que.setRow(7);
		tmp = que.getAllMoves();
		assertEquals(21,tmp.size());
		for(int i = 1; i < 8; i++) {
			assertTrue(tmp.contains(new Position(7,i-1)));
			assertTrue(tmp.contains(new Position(i-1,7)));
			assertTrue(tmp.contains(new Position(i-1,i-1)));
		}
		
		que.setColumn(0);
		tmp = que.getAllMoves();
		assertEquals(21,tmp.size());
		for(int i = 1; i < 8; i++) {
			assertTrue(tmp.contains(new Position(i-1,0)));
			assertTrue(tmp.contains(new Position(7,i)));
			assertTrue(tmp.contains(new Position(7-i,i)));
		}
	}
}