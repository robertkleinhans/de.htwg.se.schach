package de.htwg.se.schach.view;

import java.awt.Point;

import org.junit.Test;

import de.htwg.se.schach.control.MovementHandler;

public class GuiChessTest {
	@Test
	public void testGuiChess() {
		Signal sig = new Signal();
		GuiChess gui = new GuiChess(sig);
		MovementHandler mov = new MovementHandler();
		
		gui.handleClick(new Point(80,80));
		gui.handleClick(new Point(200,200));
		
		gui.initializePieces(mov.getField());
		
		gui.movePiece(new Point(1,1), new Point(3,1));
		gui.movePiece(new Point(0,0), new Point(3,3));
		gui.show();
		gui.quit();
		
	}
}
