package de.htwg.se.schach.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import de.htwg.se.schach.control.Position;

public class KnightTest {
	@Test
	public void testGetAllMoves() {
		Knight kni = new Knight(0,0,0);
		assertEquals(0,kni.getTeam());
		List<Position> tmp = kni.getAllMoves();
		assertEquals(2,tmp.size());
		
		assertTrue(tmp.contains(new Position(2,1)));
		assertTrue(tmp.contains(new Position(1,2)));
		
		kni.setColumn(7);
		tmp = kni.getAllMoves();
		assertEquals(2,tmp.size());
		
		assertTrue(tmp.contains(new Position(1,5)));
		assertTrue(tmp.contains(new Position(2,6)));
		
		kni.setRow(7);
		tmp = kni.getAllMoves();
		assertEquals(2,tmp.size());
		
		assertTrue(tmp.contains(new Position(5,6)));
		assertTrue(tmp.contains(new Position(6,5)));
		
		kni.setColumn(0);
		tmp = kni.getAllMoves();
		assertEquals(2,tmp.size());
		
		assertTrue(tmp.contains(new Position(5,1)));
		assertTrue(tmp.contains(new Position(6,2)));
		
	}
}
