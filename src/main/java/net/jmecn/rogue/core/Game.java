package net.jmecn.rogue.core;

import static net.jmecn.rogue.core.Tile.*;

import java.util.ArrayList;
import java.util.List;

import net.jmecn.rogue.entity.Player;
import net.jmecn.rogue.map.MapFactory;
import net.jmecn.rogue.math.Vector2;

public class Game {
	Player player;
	Map map;
	List<Map> maps;
	MapFactory factory;

	public Game() {
		player = new Player();
		factory = new MapFactory();
		maps = new ArrayList<Map>();
		
		downLevel();
	}

	public void downLevel() {
		map = factory.createMap();
		maps.add(map);
		
		int h = map.getHeight();
		int w = map.getWidth();
		
		for(int y=0; y<h; y++) {
			for(int x=0; x<w; x++) {
				if (map.get(x, y) == UpStairs) {
					player.getLocation().set(x, y);
					break;
				}
			}
		}
	}
	public Player getPlayer() {
		return player;
	}

	public Map getMap() {
		return map;
	}

	public void walk(Vector2 dir) {
		int x = player.getLocation().x + dir.x;
		int y = player.getLocation().y + dir.y;

		if (!map.contains(x, y))
			return;
		int tile = map.get(x, y);
		switch (tile) {
		case Wall:
		case Stone:
		case Tree:
		case Unused:
			return;
		case DownStairs:
			downLevel();
			break;
		case UpStairs:
		default :
			player.getLocation().set(x, y);
		}
	}
}
