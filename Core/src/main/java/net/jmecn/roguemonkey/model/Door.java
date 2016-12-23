package net.jmecn.roguemonkey.model;

public class Door {

	public int x, y;
	public int width, height;
	
	public int toLevel;
	public int toX, toY;
	
	public Door(int x, int y, int width, int height, int toLevel, int toX, int toY) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.toLevel = toLevel;
		this.toX = toX;
		this.toY = toY;
	}
	
}
