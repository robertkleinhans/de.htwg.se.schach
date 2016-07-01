package de.htwg.se.schach.view.impl;

import java.awt.Point;

import org.junit.Test;

import de.htwg.se.schach.control.I.MovementHandlerInter;
import de.htwg.se.schach.control.impl.MovementHandler;
import de.htwg.se.schach.view.I.GuiChessInter;
import de.htwg.se.schach.view.I.SignalInter;
import de.htwg.se.schach.view.impl.GuiChess;
import de.htwg.se.schach.view.impl.Signal;

public class GuiChessTest {
	@Test
	public void testGuiChess() {
		SignalInter sig = new Signal();
		GuiChessInter gui = new GuiChess(sig);
		MovementHandlerInter mov = new MovementHandler();
		
		gui.handleClick(new Point(80,80));
		gui.handleClick(new Point(200,200));
		
		gui.initializePieces(mov.getField());
		
		gui.movePiece(new Point(1,1), new Point(3,1));
		gui.movePiece(new Point(0,0), new Point(3,3));
		gui.show();
		gui.quit();
		
	}
}
