package net.jmecn.rogue.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AssetFactory {

	public AssetFactory() {
	}
	
	public static final String ASSETS = "assets/";
	
	public static BufferedImage loadImage(String file) {
		try {
			return ImageIO.read(new File(ASSETS + file));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
