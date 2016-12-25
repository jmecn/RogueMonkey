package net.jmecn.rogue.entity;

public class Player extends Creature {

	int gold;
	int level;
	
	public Player() {
		hp = 100;
		maxHearth = 100;
		gold = 0;
		level = 1;
	}
	
	public void addGold(int amount) {
		gold += amount;
	}
}
