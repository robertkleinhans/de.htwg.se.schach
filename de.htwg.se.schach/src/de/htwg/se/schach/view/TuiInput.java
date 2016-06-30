package de.htwg.se.schach.view;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class TuiInput extends Thread {
	Signal sig;
	
	public TuiInput(Signal sig) {
		this.sig = sig;
	}
	
	@Override
	public void run() {
		Scanner scan = new Scanner(System.in);
		String holder;
		while(!Thread.currentThread().isInterrupted()) {
			try{
				holder = scan.nextLine();
				this.sig.set_input(holder);
				if(holder.equals("quit")) {
					break;
				}
			} catch(NoSuchElementException e) {
				//do nothing
			}
		}
		scan.close();
	}
}
