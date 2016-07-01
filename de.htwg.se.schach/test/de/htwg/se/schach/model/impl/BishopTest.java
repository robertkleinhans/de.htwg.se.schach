package de.htwg.se.schach.model.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.schach.model.impl.Bishop;


public class BishopTest {
	 
	@Test
	public void testBishop() {
		Bishop bi = new Bishop(2,3,0);
		assertNotNull(bi);
		assertEquals(2,bi.getRow());
		assertEquals(3,bi.getColumn());
		assertEquals("BI",bi.getName());
		assertEquals(0,bi.getTeam());
	}
	
	@Test
	public void testSetColumn() {
		Bishop bi = new Bishop(4,5,0);
		bi.setColumn(-1);
		assertEquals(5,bi.getColumn());
		bi.setColumn(8);
		assertEquals(5,bi.getColumn());
		bi.setColumn(2);
		assertEquals(2,bi.getColumn());
	}
	
	@Test
	public void testSetRow() {
		Bishop bi = new Bishop(2,2,1);
		bi.setRow(-2);
		assertEquals(2,bi.getRow());
		bi.setRow(9);
		assertEquals(2,bi.getRow());
		bi.setRow(5);
		assertEquals(5,bi.getRow());
	}
}
