package net.jmecn.rogue.entity;

public class Player extends Creature {

	int gold;
	int level;
	int hungry;
	int thirty;
	int viewRadius;
	
	public Player() {
		hp = 100;
		maxHearth = 100;
		gold = 0;
		level = 1;
		viewRadius = 3;
	}
	
	public void addGold(int amount) {
		gold += amount;
	}
	
	public int getViewRadius() {
		return viewRadius;
	}
	
	public void addItem(Item item) {
		slots.add(item);
	}

	public boolean ongoingQuest(int id) {
		return true;
	}
	public void attack(Monster monster) {
		
		//
		
		if (monster.hp <= 0) {
			
		}
	}
}
