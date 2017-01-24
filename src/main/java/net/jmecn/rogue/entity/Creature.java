package net.jmecn.rogue.entity;

import java.util.LinkedList;
import java.util.List;

import net.jmecn.rogue.math.Vector2;

public class Creature {

	private Vector2 location;

	protected int hp;
	protected int maxHearth;
	protected int atk;
	protected int def;
	protected boolean dead;
	
	
	protected LinkedList<Item> slots;
	
	public Creature() {
		location = new Vector2();
		dead = false;
	}

	public Vector2 getLocation() {
		return location;
	}

	/**
	 * 检查背包中道具数量
	 * @param id
	 * @param count
	 * @return
	 */
	public boolean checkItemCount(int id, int count) {
		int sum = 0;
		
		return (sum >= count);
	}
	
	public void addItem(Item item) {
	}
	
	public void addItem(Item item, int count) {
	}
	
	public void removeItem(Item item, int count) {
		
	}
	
	public List<Item> getItems() {
		return slots;
	}
}
