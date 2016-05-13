package de.htwg.se.schach;

public class Position {
	private int x;
	private int y;
	
	
	public Position(int x, int y) {
		assert(checkPos(x,y));
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setPos(int new_x, int new_y) {
		assert(checkPos(new_x,new_y));
		this.x = new_x;
		this.y = new_y;
	}
	
	static boolean checkPos(int x, int y) {
		return (x < 8 && x >= 0) && (y < 8 && y >= 0);
	}
	
	public String toString() {
		String s = String.format("X:%d Y:%d",this.x,this.y);
		return s;
	}
	
	@Override
	public int hashCode() {
		return this.x*10 +this.y;
	}
	

	public boolean equals(Object obj) {
		return obj.hashCode() == hashCode();
	}
}
