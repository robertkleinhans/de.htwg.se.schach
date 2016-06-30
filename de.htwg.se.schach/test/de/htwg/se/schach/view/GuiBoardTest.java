package de.htwg.se.schach.view;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.junit.Test;


public class GuiBoardTest {
	
	@Test
	public void testGuiBoard() {
		GuiBoard board = new GuiBoard();
		board.getPreferredSize();
		BufferedImage bi = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bi.createGraphics();
		board.paintComponent(g);
		
	}
}
