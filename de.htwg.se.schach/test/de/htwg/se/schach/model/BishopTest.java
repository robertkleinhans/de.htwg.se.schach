package de.htwg.se.schach.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import de.htwg.se.schach.control.Position;

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
	
	@Test
	public void testGetAllMoves() {
		Bishop bi = new Bishop(0,0,0);
		List<Position> tmp = bi.getAllMoves();
		assertEquals(7, tmp.size());
		for(int i = 1; i < 8; i++) {
			assertTrue(tmp.contains(new Position(i,i)));
		}
		
		bi.setColumn(7);
		tmp = bi.getAllMoves();
		assertEquals(7, tmp.size());
		for(int i = 1; i < 8; i++) {
			assertTrue(tmp.contains(new Position(8-i,i-1)));
		}
	}

}
