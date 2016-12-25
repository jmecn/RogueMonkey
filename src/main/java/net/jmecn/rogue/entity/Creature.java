package net.jmecn.rogue.entity;

import net.jmecn.rogue.math.Vector2;

public class Creature {

	private Vector2 location;

	protected int hp;
	protected int maxHearth;
	protected int atk;
	protected int def;
	protected boolean dead;
	
	public Creature() {
		location = new Vector2();
		dead = false;
	}

	public Vector2 getLocation() {
		return location;
	}

}
