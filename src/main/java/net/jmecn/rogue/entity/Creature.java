package net.jmecn.rogue.entity;

import net.jmecn.rogue.math.Vector2;

public class Creature {

	private Vector2 location;

	public Creature() {
		location = new Vector2();
	}

	public Vector2 getLocation() {
		return location;
	}

}
