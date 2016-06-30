package de.htwg.se.schach.view;

public class Signal {
	protected boolean input_given = false;
	protected String command;
	
	public synchronized boolean input_given() {
		return this.input_given;
	}
	
	public synchronized String get_command() {
		return this.command;
	}
	
	public synchronized void set_input(String com) {
		this.command = com;
		this.input_given = true;
	}
	
	public synchronized void reset() {
		this.input_given = false;
	}
}
