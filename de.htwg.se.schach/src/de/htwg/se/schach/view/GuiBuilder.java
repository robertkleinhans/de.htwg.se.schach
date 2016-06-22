package de.htwg.se.schach.view;

import de.htwg.se.schach.control.MovementHandler;

public class GuiBuilder {
	public static void main(String[] args) {
		
		MovementHandler mov = new MovementHandler();
		
		GuiChess test = new GuiChess(mov);
		
		

		
		test.show_frame();
	}
}
