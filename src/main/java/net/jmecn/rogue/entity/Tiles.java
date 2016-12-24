package net.jmecn.rogue.entity;

public enum Tiles {

	Floor,
	Wall(64, 64, false),
	UpStairs(192, 32),
	DownStaris(160, 32);
	
	public int x, y;
	public int width, height;
	public boolean walkable;
	
	private Tiles() {
		x = y = 0;
		width = height = 32;
		walkable = true;
	}

	private Tiles(int x, int y) {
		this.x = x;
		this.y = y;
		width = height = 32;
		this.walkable = true;
	}
	
	private Tiles(int x, int y, boolean walkable) {
		this.x = x;
		this.y = y;
		width = height = 32;
		this.walkable = walkable;
	}
	
	private Tiles(int x, int y, int width, int height, boolean walkable) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.walkable = walkable;
	}

}
