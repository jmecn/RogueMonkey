package net.jmecn.roguemonkey.model;

import net.jmecn.roguemonkey.math.Vector2f;

public class Player {

	private Vector2f position;
	// 外观

	public Player() {
		position = new Vector2f();
	}
	
	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(float x, float y) {
		this.position.x = x;
		this.position.y = y;
	}

}
