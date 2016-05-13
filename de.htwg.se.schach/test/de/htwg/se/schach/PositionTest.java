package de.htwg.se.schach;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;



public class PositionTest {

	Position pos;
	
	@Test
	public void testPosition() {
		try {
			pos = new Position(10,8);
			fail("Position build with invalid values!");
		} catch (AssertionError e) {
			assertNotNull(e.getMessage());
		}
	}
	
	@Test
	public void testGet() {
		pos = new Position(3,7);
		assertEquals(3,pos.getX());
		assertEquals(7,pos.getY());
	}
	
	@Test
	public void testSetPos() {
		pos = new Position(1,1);
		try {
			pos.setPos(-1,2);
			fail("Position set to invalid values (-1,2)!");
		} catch (AssertionError e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			pos.setPos(4, -1);
			fail("Position set to invalid values (4,-1)!");
		} catch (AssertionError e) {
			assertNotNull(e.getMessage());
		}
		
		pos.setPos(0, 7);
		assertEquals(0,pos.getX());
		assertEquals(7,pos.getY());
		
	}

	@Test
	public void testCheckPos() {
		try {
			Position.checkPos(-1,9);
			fail("Invalid values accepted (-1,9)!");
		} catch (AssertionError e) {
			assertNotNull(e.getMessage());
		}
		
		try {
			Position.checkPos(4,-2);
			fail("Invalid values accepted (4,-2)!");
		} catch (AssertionError e) {
			assertNotNull(e.getMessage());
		}
	}
	
	@Test
	public void testHashcode() {
		Position pos = new Position(4,3);
		assertEquals(43,pos.hashCode());
		pos.setPos(1, 7);
		assertEquals(17,pos.hashCode());
	}
	
	@Test
	public void testEquals() {
		Position pos_1 = new Position(5,2);
		Position pos_2 = new Position(1,8);
		assertEquals(true,pos_1.equals(pos_1));
		assertEquals(false,pos_1.equals(pos_2));
	}
	
	@Test
	public void testToString() {
		Position pos = new Position(4,3);
		assertEquals("X:4 Y:3",pos.toString());
		pos.setPos(1, 0);
		assertEquals("X:1 Y:0",pos.toString());
	}
}
