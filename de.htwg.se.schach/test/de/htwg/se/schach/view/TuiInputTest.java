package de.htwg.se.schach.view;

import java.io.ByteArrayInputStream;

import org.junit.Test;

public class TuiInputTest {
	@Test
	public void testTuiInput() {
		Signal sig = new Signal();
		TuiInput tui = new TuiInput(sig);
		ByteArrayInputStream in = new ByteArrayInputStream("quit".getBytes());
		tui.start();
		System.setIn(in);
		System.setIn(System.in);
	}
}
