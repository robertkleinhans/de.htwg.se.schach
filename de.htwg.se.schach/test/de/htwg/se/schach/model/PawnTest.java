package de.htwg.se.schach.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import de.htwg.se.schach.control.Position;

public class PawnTest {
	@Test
	public void testGetAllMoves() {
		Pawn paw = new Pawn(0,2,1,0);
		assertEquals(1,paw.getDirection());
		List<Position> tmp = paw.getAllMoves();
		assertEquals(4,tmp.size());
		
		assertTrue(tmp.contains(new Position(1,2)));
		assertTrue(tmp.contains(new Position(2,2)));
		assertTrue(tmp.contains(new Position(1,3)));
		assertTrue(tmp.contains(new Position(1,1)));
		
		paw.changeFirstMove();
		tmp = paw.getAllMoves();
		assertTrue(tmp.contains(new Position(1,2)));
		assertTrue(tmp.contains(new Position(1,3)));
		assertTrue(tmp.contains(new Position(1,1)));
		
		paw.setRow(7);
		tmp = paw.getAllMoves();
		assertEquals(0,tmp.size());
		
		paw.setColumn(0);
		paw.setRow(0);
		tmp = paw.getAllMoves();
		assertEquals(2,tmp.size());
		
		paw.setColumn(7);
		tmp = paw.getAllMoves();
		assertEquals(2,tmp.size());
		
		Pawn paw_2 = new Pawn(0,0,-1,1);
		assertEquals(-1,paw_2.getDirection());
		paw_2.changeFirstMove();
		tmp = paw_2.getAllMoves();
		assertEquals(0, tmp.size());
		
	}
}
