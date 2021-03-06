package de.htwg.se.schach.model.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.schach.model.impl.Queen;

public class QueenTest {
	@Test
	public void testQueen() {
		Queen qu = new Queen(2,3,0);
		assertNotNull(qu);
		assertEquals(2,qu.getRow());
		assertEquals(3,qu.getColumn());
		assertEquals("QU",qu.getName());
		assertEquals(0,qu.getTeam());
	}
}