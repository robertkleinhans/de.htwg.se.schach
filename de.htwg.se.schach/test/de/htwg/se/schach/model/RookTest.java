package de.htwg.se.schach.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import de.htwg.se.schach.control.Position;

public class RookTest {
	@Test
	public void testGetAllMoves() {
		Rook roo = new Rook(0,0,1);
		List<Position> tmp = roo.getAllMoves();
		assertEquals(14,tmp.size());
		for(int i = 1; i < 8; i++) {
			assertTrue(tmp.contains(new Position(i,0)));
			assertTrue(tmp.contains(new Position(0,i)));
		}
	}
}
