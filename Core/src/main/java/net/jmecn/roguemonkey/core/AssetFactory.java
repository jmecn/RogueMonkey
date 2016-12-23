package net.jmecn.roguemonkey.core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import tiled.core.Map;
import tiled.io.TMXMapReader;

public class AssetFactory {

	public final static String CAVE = "cave.tmx";
	public final static String FOREST = "forest.tmx";
	public final static String TOMB = "tomb.tmx";

	public final static String MAP_PATH = "assets" + File.separatorChar + "maps" + File.separatorChar + "%s";
	public final static String IMG_PATH = "assets" + File.separatorChar + "images" + File.separatorChar + "%s";

	private AssetFactory() {
	}

	public static BufferedImage loadImage(String name) {
		BufferedImage image = null;
		String fileName = String.format(IMG_PATH, name);
		try {
			image = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public static Map loadMap(String name) {
		Map map = null;
		try {
			TMXMapReader reader = new TMXMapReader();
			map = reader.readMap(String.format(MAP_PATH, name));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public static void main(String[] args) {
		Map map = AssetFactory.loadMap(FOREST);
		System.out.println(map.getLayer(0).getName());
		System.out.println(map.getLayer(1).getName());
	}
}
