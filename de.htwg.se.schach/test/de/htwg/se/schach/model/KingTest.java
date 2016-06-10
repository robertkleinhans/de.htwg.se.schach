package de.htwg.se.schach.model;

import static org.junit.Assert.*;

import org.junit.Test;


public class KingTest {
	@Test
	public void testKing() {
		King ki = new King(2,3,0);
		assertNotNull(ki);
		assertEquals(2,ki.getRow());
		assertEquals(3,ki.getColumn());
		assertEquals("KI",ki.getName());
		assertEquals(0,ki.getTeam());
	}
}
