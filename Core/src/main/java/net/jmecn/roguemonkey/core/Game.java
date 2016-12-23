package net.jmecn.roguemonkey.core;

import java.util.ArrayList;
import java.util.List;

import net.jmecn.roguemonkey.math.Vector2f;
import net.jmecn.roguemonkey.model.Door;
import net.jmecn.roguemonkey.model.IMap;
import net.jmecn.roguemonkey.model.Item;
import net.jmecn.roguemonkey.model.Monster;
import net.jmecn.roguemonkey.model.Player;
import net.jmecn.roguemonkey.model.Tile;
import tiled.core.Map;

public class Game {

	private int mapLevel; // 0:森林 1：山洞 2：墓地
	private Player player;

	List<IMap> maps;
	List<Monster> monsters;
	List<Item> items;

	public Game() {
		maps = new ArrayList<IMap>();
		monsters = new ArrayList<Monster>();
		items = new ArrayList<Item>();

		// init maps
		addMap(AssetFactory.FOREST);
		addMap(AssetFactory.CAVE);
		addMap(AssetFactory.TOMB);

		mapLevel = 0;

		player = new Player();
		player.setPosition(3, 3);
	}

	private void addMap(String name) {
		// init maps
		Map map = AssetFactory.loadMap(name);
		if (map != null) {
			maps.add(new IMap(map));
		}
	}

	public IMap getMap() {
		return maps.get(mapLevel);
	}

	// 设置当前的地图层数
	public void setMapLevel(int mapLevel) {
		this.mapLevel = mapLevel;
	}

	public Player getPlayer() {
		return player;
	}

	/**
	 * 根据获取的方向控制人物行走
	 * 
	 * @param dir
	 */
	public void playerMove(Vector2f dir) {
		if (collision(dir)) {
			player.getPosition().addLocal(dir);
		}
	}

	/**
	 * 对人物和地图元素进行碰撞检测,并根据结果进行相应的操作
	 * 
	 * @param dir
	 * @return
	 */
	public boolean collision(Vector2f dir) {
		// 获取此时的地图Map
		IMap map = getMap();
		Vector2f target = player.getPosition();
		float px = target.x + dir.x;
		float py = target.y + dir.y;
		
		if (checkDoors(px, py)) {
			return false;
		}

		// 人物将要移动到下一目标砖块索引
		int x = (int) (px);
		int y = (int) (py);
		if (!map.contains(x, y)) {
			return false;
		}

		int targetTile = map.get(x, y);
		if (targetTile != Tile.FLOOR) {
			return false;
		} else {
			return true;
		}
	}

	private boolean checkDoors(float x, float y) {
		boolean flag = false;
		List<Door> doors = getMap().getDoors();
		for (Door door : doors) {
			// rect 1
			int rectX1 = door.x;
			int rectY1 = door.y;
			int rectW1 = door.width;
			int rectH1 = door.height;

			int rectX2 = (int) (x * Tile.WIDTH);
			int rectY2 = (int) (y * Tile.HEIGHT);
			int rectW2 = Tile.WIDTH;
			int rectH2 = Tile.HEIGHT;

			if (rectX1 >= rectX2 && rectX1 >= rectX2 + rectW2) {
				continue;
			} else if (rectX1 <= rectX2 && rectX1 + rectW1 <= rectX2) {
				continue;
			} else if (rectY1 >= rectY2 && rectY1 >= rectY2 + rectH2) {
				continue;
			} else if (rectY1 <= rectY2 && rectY1 + rectH1 <= rectY2) {
				continue;
			}
			setMapLevel(door.toLevel);
			player.setPosition(door.toX, door.toY);
			flag = true;
			break;
		}

		return flag;
	}

	public void playerMove(int x, int y) {
		player.getPosition().addLocal(x, y);
	}

}
