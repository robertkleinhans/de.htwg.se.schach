package de.htwg.se.schach.view.I;

public interface SignalInter {

	boolean inputGiven();

	String getCommand();

	void setInput(String com);

	void reset();

}