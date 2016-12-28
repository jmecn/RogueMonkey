package net.jmecn.rogue.core;

import static net.jmecn.rogue.core.Tile.*;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.jmecn.rogue.entity.Player;
import net.jmecn.rogue.map.MapFactory;
import net.jmecn.rogue.math.Vector2;

public class Game {

	static Logger logger = LoggerFactory.getLogger(Game.class);

	private MapFactory factory;
	private Map map;
	List<Map> maps;
	int level;

	Player player;

	public Game() {
		player = new Player();
		factory = new MapFactory();
		maps = new ArrayList<Map>();
		level = 0;
		downLevel();
	}

	public void downLevel() {
		level++;
		if (level > maps.size()) {
			map = factory.createMap();
			maps.add(map);
		} else {
			map = maps.get(level - 1);
		}

		int h = map.getHeight();
		int w = map.getWidth();
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				if (map.get(x, y) == UpStairs) {
					player.getLocation().set(x, y);
					break;
				}
			}
		}
	}

	public void upLevel() {
		if (level == 1) {
			return;
		}

		level--;
		map = maps.get(level - 1);

		int h = map.getHeight();
		int w = map.getWidth();
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				if (map.get(x, y) == DownStairs) {
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
		case Door:
			map.set(x, y, Floor);
			return;
		case DownStairs:
			player.getLocation().set(x, y);
			downLevel();
			break;
		case UpStairs:
			player.getLocation().set(x, y);
			upLevel();
			break;
		default:
			player.getLocation().set(x, y);
		}
	}

	// x0/y0 == the player
	// x1/y1 == the tile
	public boolean isVisible(int x, int y) {
		if (!map.contains(x, y)) {
			return false;
		}

		int x0 = player.getLocation().x;
		int y0 = player.getLocation().y;

		// get the deltas and steps for both axis
		int dx = Math.abs(x - x0);
		int dy = Math.abs(y - y0);

		// check view distance
		int r = player.getViewRadius();
		if (dx > r || dy > r) {
			return false;
		}

		int sx = x0 < x ? 1 : -1;
		int sy = y0 < y ? 1 : -1;

		// stores an error factor we use to change the axis coordinates
		int err = dx - dy;

		while (x0 != x || y0 != y) {
			// check our collision map to see if this tile blocks visibility
			int tile = map.get(x0, y0);
			if (tile == Wall || tile == Door || tile == Stone || tile == Tree)
				return false;

			// check our error value against our deltas to see if
			// we need to move to a new point on either axis
			int e2 = 2 * err;
			if (e2 > -dy) {
				err -= dy;
				x0 += sx;
			}
			if (e2 < dx) {
				err += dx;
				y0 += sy;
			}
		}

		// if we're here we hit no occluders and therefore can see this tile
		return true;
	}
	
}
