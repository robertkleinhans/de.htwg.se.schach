package de.htwg.se.schach.model;

import static org.junit.Assert.*;

import org.junit.Test;


public class KnightTest {
	@Test
	public void testKnight() {
		Knight kn = new Knight(2,3,0);
		assertNotNull(kn);
		assertEquals(2,kn.getRow());
		assertEquals(3,kn.getColumn());
		assertEquals("KN",kn.getName());
		assertEquals(0,kn.getTeam());
	}
}
