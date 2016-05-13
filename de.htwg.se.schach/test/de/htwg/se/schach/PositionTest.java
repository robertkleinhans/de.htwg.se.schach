package de.htwg.se.schach;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.schach.model.Position;



public class PositionTest {

	Position pos;
	
	@Test
	public void testPosition() {
		pos = new Position(4,3);
		assertNotNull(pos);
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
		pos.setPos(-2,3);
		assertEquals(1,pos.getX());
		assertEquals(1,pos.getY());
		
		pos.setPos(0, 7);
		assertEquals(0,pos.getX());
		assertEquals(7,pos.getY());
		
	}

	@Test
	public void testCheckPos() {
		Position test = new Position(4,3);
		assertFalse(test.validPosition(-3,2));
		assertFalse(test.validPosition(4, -9));
		assertFalse(test.validPosition(3, 10));
		assertFalse(test.validPosition(10, 2));
		assertFalse(test.validPosition(20,40));
		assertTrue(test.validPosition(1, 7));
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
