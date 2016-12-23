package net.jmecn.roguemonkey.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import tiled.core.MapObject;
import tiled.core.ObjectGroup;
import tiled.core.TileLayer;

public class IMap {

	/**
	 * 地图类
	 * @param args
	 */
	protected int width;
	protected int height;
	protected int[][] data;
	
	protected List<Door> doors;
	
	protected BufferedImage image;
	protected tiled.core.Map tiledMap;
	
	public IMap() {
		width = 60;
		height = 40;
		data = new int[40][60];
	}
	
	public IMap(tiled.core.Map tiledMap) {
		doors = new ArrayList<Door>();
		setTiledMap(tiledMap);
		
	}
	
	public List<Door> getDoors() {
		return doors;
	}

	public IMap(int width, int height) {
		this.width = width;
		this.height = height;
		data = new int[height][width];
	}

	public boolean contains(int x, int y) {
		return (x >= 0 && y >= 0 && x < width && y < height);
	}

	public void setData(int[][] data) {
		this.data = data;
	}
	
	public void set(int x, int y, int value) {
		data[y][x] = value;
	}
	
	/**
	 * 根据索引获取地图二维数组中的地图图块
	 * @param x
	 * @param y
	 * @return
	 */
	public int get(int x, int y) {
		return data[y][x];
	}

	public int[][] getData() {
		return data;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		if (this.image != null && image != this.image) {
			// TODO release the old bitmap
		}
		this.image = image;
	}

	public tiled.core.Map getTiledMap() {
		return tiledMap;
	}

	public void setTiledMap(tiled.core.Map tiledMap) {
		this.tiledMap = tiledMap;
		this.width = tiledMap.getWidth();
		this.height = tiledMap.getHeight();
		this.data = new int[height][width];

		// read the collision map
		TileLayer layer = (TileLayer)tiledMap.getLayer(0);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int id = layer.getTileAt(x, y).getId();

				data[y][x] = id;
			}
		}
		
		// read all the objects
		ObjectGroup objects = (ObjectGroup)tiledMap.getLayer(1);
		for(MapObject obj : objects) {
			String type = obj.getType();
			if ("door".equals(type)) {
				int x=(int) obj.getX();
				int y=(int) obj.getY();
				int dx=(int) obj.getWidth();
				int dy=(int) obj.getHeight();
				int  toLevel =Integer.parseInt( obj.getProperties().getProperty("toLevel"));
				int toX = Integer.parseInt(obj.getProperties().getProperty("toX"));
				int toY = Integer.parseInt(obj.getProperties().getProperty("toY"));
				Door door=new Door(x, y, dx,dy , toLevel, toX, toY);
				doors.add(door);
			} else if ("spawn".equals(type)) {
				
			}
		}
		
		// release the old bitmap
		image = null;
	}
	
}
