package de.htwg.se.schach.view.impl;

import de.htwg.se.schach.view.I.SignalInter;

public class Signal implements SignalInter {
	protected boolean inputGiven = false;
	protected String command;
	
	/* (non-Javadoc)
	 * @see de.htwg.se.schach.view.impl.SignalInter#inputGiven()
	 */
	@Override
	public synchronized boolean inputGiven() {
		return this.inputGiven;
	}
	
	/* (non-Javadoc)
	 * @see de.htwg.se.schach.view.impl.SignalInter#getCommand()
	 */
	@Override
	public synchronized String getCommand() {
		return this.command;
	}
	
	/* (non-Javadoc)
	 * @see de.htwg.se.schach.view.impl.SignalInter#setInput(java.lang.String)
	 */
	@Override
	public synchronized void setInput(String com) {
		this.command = com;
		this.inputGiven = true;
	}
	
	/* (non-Javadoc)
	 * @see de.htwg.se.schach.view.impl.SignalInter#reset()
	 */
	@Override
	public synchronized void reset() {
		this.inputGiven = false;
	}
}
