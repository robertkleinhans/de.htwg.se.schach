package de.htwg.se.schach.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import de.htwg.se.schach.control.Position;

public class KingTest {
	@Test
	public void testGetAllMoves() {
		King king = new King(0,0,0);
		assertEquals(0,king.getTeam());
		List<Position> tmp = king.getAllMoves();
		assertEquals(3,tmp.size());
		
		assertTrue(tmp.contains(new Position(0,1)));
		assertTrue(tmp.contains(new Position(1,0)));
		assertTrue(tmp.contains(new Position(1,1)));
		
		king.setColumn(7);
		tmp = king.getAllMoves();
		assertEquals(3,tmp.size());
		
		assertTrue(tmp.contains(new Position(0,6)));
		assertTrue(tmp.contains(new Position(1,6)));
		assertTrue(tmp.contains(new Position(1,7)));
		
		king.setRow(7);
		tmp = king.getAllMoves();
		assertEquals(3,tmp.size());
		
		assertTrue(tmp.contains(new Position(6,6)));
		assertTrue(tmp.contains(new Position(6,7)));
		assertTrue(tmp.contains(new Position(7,6)));
		
		king.setColumn(0);
		tmp = king.getAllMoves();
		assertEquals(3,tmp.size());
		
		assertTrue(tmp.contains(new Position(6,0)));
		assertTrue(tmp.contains(new Position(6,1)));
		assertTrue(tmp.contains(new Position(7,1)));
	}
}
