package de.htwg.se.schach.view.impl;

import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import de.htwg.se.schach.view.I.SignalInter;
import de.htwg.se.schach.view.I.TuiInputInter;

public class TuiInput extends Thread implements TuiInputInter {
	SignalInter sig;
	private static final Logger LOGGER = Logger.getLogger("de.htwg.se.schach.tuiInput");
	
	public TuiInput(SignalInter sig) {
		this.sig = sig;
	}
	
	
	
	/* (non-Javadoc)
	 * @see de.htwg.se.schach.view.impl.TuiInputInter#run()
	 */
	@Override
	public void run() {
		Scanner scan = new Scanner(System.in);
		String holder;
		
		
		while(!Thread.currentThread().isInterrupted()) {
			try{
				holder = scan.nextLine();
				this.sig.setInput(holder);
				if(("quit").equals(holder)) {
					break;
				}
			} catch(NoSuchElementException e) {
				LOGGER.info(e);
			}
		}
		scan.close();
	}
}
