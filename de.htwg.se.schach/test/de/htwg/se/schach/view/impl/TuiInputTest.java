package de.htwg.se.schach.view.impl;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import de.htwg.se.schach.view.I.SignalInter;
import de.htwg.se.schach.view.impl.Signal;
import de.htwg.se.schach.view.impl.TuiInput;

public class TuiInputTest {
	@Test
	public void testTuiInput() {
		SignalInter sig = new Signal();
		TuiInput tui = new TuiInput(sig);
		ByteArrayInputStream in = new ByteArrayInputStream("quit".getBytes());
		tui.start();
		System.setIn(in);
		System.setIn(System.in);
	}
	
	@Test
	public void testInterrupt() {
		SignalInter sig = new Signal();
		TuiInput tui = new TuiInput(sig);
		tui.start();
		tui.interrupt();
	}
	
	@Test
	public void testInput() {
		SignalInter sig = new Signal();
		TuiInput tui = new TuiInput(sig);
		ByteArrayInputStream in = new ByteArrayInputStream("A1-A3".getBytes());
		tui.start();
		System.setIn(in);
		in = new ByteArrayInputStream("quit".getBytes());
	}
}
