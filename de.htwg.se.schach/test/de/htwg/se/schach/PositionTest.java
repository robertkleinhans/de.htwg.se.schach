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
			pos.setPos(4, -1);
			fail("Position set to invalid values (4,-1)!");
			pos.setPos(-2, 9);
			fail("Position set to invalid values (-2,9)!");
			pos.setPos(-5, 11);
			fail("Position set to invalid values (-5,11)!");
		} catch (AssertionError e) {
			assertNotNull(e.getMessage());
		}
		
		pos.setPos(0, 7);
		assertEquals(0,pos.getX());
		assertEquals(7,pos.getY());
		
	}

}
