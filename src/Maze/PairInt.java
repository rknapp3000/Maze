package Maze;

public class PairInt {
    //coordinates for grid 
	private int x; 
	private int y; 
	
	// constructor for data fields above
	public PairInt(int x, int y) { 
		this.x = x; 
		this.y = y; 
	}
	// getters 
	public int getX() { return x; }
	public int getY() { return y; }
	
	// setters
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	
	@Override
	public boolean equals (Object p) { 
		if (p == null) { 
			return false; 
		}
		PairInt gridPoint = (PairInt) p; 
		return (this.x == gridPoint.x && this.y == gridPoint.y); 
	}
	
	@Override
	public String toString() { 
		return "(" + x + "," + " " + y + ")"; 
	}
	
	// method that returns a new copy of a PairInt
	public PairInt copy() { 
		return new PairInt (x, y); 
	}
}


 
// Name: Ryan Knapp
//Date: 4/16/2021
//Assignment 4: Recursion - Maze














