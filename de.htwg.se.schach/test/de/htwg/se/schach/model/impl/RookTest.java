package de.htwg.se.schach.model.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.schach.model.impl.Rook;

public class RookTest {
	@Test
	public void testRook() {
		Rook ro = new Rook(2,3,0);
		assertNotNull(ro);
		assertEquals(2,ro.getRow());
		assertEquals(3,ro.getColumn());
		assertEquals("RO",ro.getName());
		assertEquals(0,ro.getTeam());
	}
}
