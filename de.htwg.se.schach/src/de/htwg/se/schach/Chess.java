package de.htwg.se.schach;



public class Chess {
	
	private Chess() {
		
	}
	
	public static void main(String[] args) {
		PlayerHandler play = new PlayerHandler();
		play.startGame();
	}
}
