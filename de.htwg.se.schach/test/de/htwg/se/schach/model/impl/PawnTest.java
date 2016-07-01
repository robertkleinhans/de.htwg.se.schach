package de.htwg.se.schach.model.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.schach.model.impl.Pawn;

public class PawnTest {
	@Test
	public void testPawn() {
		Pawn pa = new Pawn(2,3,1,0);
		assertNotNull(pa);
		assertEquals(2,pa.getRow());
		assertEquals(3,pa.getColumn());
		assertEquals("PA",pa.getName());
		assertEquals(0,pa.getTeam());
	}
}
