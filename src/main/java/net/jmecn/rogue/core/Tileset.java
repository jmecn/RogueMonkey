package net.jmecn.rogue.core;

public enum Tileset {

	Unused(false),
	Floor(288, 64),
	Wall(64, 0, false),
	Stone(0, 160, false),
	Corridor(192, 192),
	Door(224, 64),
	UpStairs(192, 32),
	DownStairs(160, 32),
	Chest(96, 64, false),
	Water(320, 0, false),
	Grass(256, 64),
	Dirt(0, 128),
	Moss(96, 64),
	Tree(256, 128, false),
	Mage(32, 64),
	Skeleton(32, 96),
	;
	
	public final static int WIDTH = 32;
	public final static int HEIGHT = 32;
	
	public int x, y;
	public boolean passable;
	
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
