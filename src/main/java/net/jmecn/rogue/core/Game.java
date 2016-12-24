package net.jmecn.rogue.core;


import net.jmecn.rogue.entity.Creature;
import net.jmecn.rogue.map.MapFactory;

public class Game {
	Creature player;
	Map map;
	MapFactory factory;
	public Game() {
		player = new Creature();
		factory = new MapFactory();
		map = factory.createMap();
		
	}

	public Creature getPlayer() {
		return player;
	}

	public Map getMap() {
		return map;
	}

}
