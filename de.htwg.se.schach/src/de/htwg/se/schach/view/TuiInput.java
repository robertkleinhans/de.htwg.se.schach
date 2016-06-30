package de.htwg.se.schach.view;

import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class TuiInput extends Thread {
	Signal sig;
	
	public TuiInput(Signal sig) {
		this.sig = sig;
	}
	
	private static final Logger LOGGER = Logger.getLogger("de.htwg.se.schach.tuiInput");
	
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
