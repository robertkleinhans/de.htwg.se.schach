package de.htwg.se.schach.view;

public class Signal {
	protected boolean inputGiven = false;
	protected String command;
	
	public synchronized boolean inputGiven() {
		return this.inputGiven;
	}
	
	public synchronized String getCommand() {
		return this.command;
	}
	
	public synchronized void setInput(String com) {
		this.command = com;
		this.inputGiven = true;
	}
	
	public synchronized void reset() {
		this.inputGiven = false;
	}
}
