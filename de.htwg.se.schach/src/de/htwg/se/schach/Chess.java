package de.htwg.se.schach;

import org.apache.log4j.PropertyConfigurator;

public class Chess {
	
	private Chess() {
		
	}
	
	public static void main(String[] args) {
		
		PropertyConfigurator.configure("log4j.properties");
		
		PlayerHandler play = new PlayerHandler();
		play.startGame();
	}
}
