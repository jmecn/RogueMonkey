package net.jmecn.rogue.core;

public enum Tileset {

	Unused(false),
	Floor(0,0),
	Wall(64, 64, false),
	Stone(96, 64, false),
	Corridor(128, 96),
	Door(192, 64),
	UpStairs(192, 32),
	DownStairs(160, 32),
	Chest(0, 128, false),
	Water,
	Grass,
	Dirt,
	Moss,
	Tree,
	;
	
	public final static int WIDTH = 32;
	public final static int HEIGHT = 32;
	
	int x, y;
	boolean passable;
	
	private Tileset() {
		this.x = y = 0;
		this.passable = true;
	}
	
	private Tileset(boolean passable) {
		x = y = 0;
		this.passable = passable;
	}
	
	private Tileset(int x, int y) {
		this.x = x;
		this.y = y;
		this.passable = true;
	}
	
	private Tileset(int x, int y, boolean passable) {
		this.x = x;
		this.y = y;
		this.passable = passable;
	}
	
	public boolean isPassable() {
		return passable;
	}
}
