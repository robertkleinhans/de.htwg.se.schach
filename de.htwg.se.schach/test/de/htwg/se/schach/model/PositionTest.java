package de.htwg.se.schach.model;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.schach.control.Position;



public class PositionTest {

	Position pos;
	
	@Test
	public void testPosition() {
		pos = new Position(4,3);
		assertNotNull(pos);
		assertEquals(4,pos.getRow());
		assertEquals(3,pos.getColumn());
	}
	
	@Test
	public void testGet() {
		pos = new Position(3,7);
		assertEquals(3,pos.getRow());
		assertEquals(7,pos.getColumn());
	}
	
	@Test
	public void testHashcode() {
		Position pos = new Position(4,3);
		assertEquals(43,pos.hashCode());
	}
	
	@Test
	public void testEquals() {
		Position pos_1 = new Position(5,2);
		Position pos_2 = new Position(1,8);
		assertEquals(true,pos_1.equals(pos_1));
		assertEquals(false,pos_1.equals(pos_2));
	}
}
