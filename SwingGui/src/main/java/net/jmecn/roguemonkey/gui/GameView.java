package net.jmecn.roguemonkey.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import net.jmecn.roguemonkey.core.Game;
import net.jmecn.roguemonkey.math.Vector2f;
import net.jmecn.roguemonkey.model.IMap;
import net.jmecn.roguemonkey.model.Tile;
import tiled.core.MapLayer;
import tiled.core.TileLayer;

public class GameView extends JPanel {

	private static final long serialVersionUID = 1L;

	private Game game;

	// 手机屏幕参数
	public static int SCREEN_WIDTH = 480;
	public static int SCREEN_HEIGHT = 800;
	public static int PLAYER_X = 224;
	public static int PLAYER_Y = 384;
	public static int CENTER_X = 240;
	public static int CENTER_Y = 400;
	
	private float width;   //屏幕上小砖块的行列数
	private float height;

	BufferedImage buffer;
	Graphics2D g2d;

	public GameView(Game game) {
		
		this.game = game;
		
		width = (float) SCREEN_WIDTH / Tile.WIDTH;
		height = (float) SCREEN_HEIGHT / Tile.HEIGHT;
		
		//设置双缓冲，利用BufferedImage来获取一个绘制的Graphics2D
		buffer = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		g2d = (Graphics2D) buffer.getGraphics();
		
		// 设置画布尺寸
		Dimension dimen = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
		this.setPreferredSize(dimen);
		this.setMinimumSize(dimen);
	}

	public void paint(Graphics g) {

		//清屏
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

		// 背景层
		paintBg();
		
		// 人物层
		paintPlayer();
		
		// debug
		paintGui();
		
		// if (game.hasMessage())
		// paintDialog() {
		// LinkedList<String> messages;
		// String msg = game.getNextMessage();
		// 画个框
		// 画文字
		// }
		
		
		g.drawImage(buffer, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);
	}

	public void update(Graphics g) {
		paint(g);
	}

	private void paintBg() {
		
		
		Vector2f pLoc = game.getPlayer().getPosition();
		
		//屏幕在地图上的位置
		float left = pLoc.x - width * 0.5f + 0.5f;
		float top = pLoc.y - height * 0.5f + 0.5f;
		int x = (int) (left * Tile.WIDTH);
		int y = (int) (top * Tile.HEIGHT);
		
		
		IMap m = game.getMap();
		BufferedImage image = m.getImage();
		if (image == null) {
			image = getImage(m.getTiledMap());
			m.setImage(image);
		}
		g2d.drawImage(image, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, x, y, x+SCREEN_WIDTH, y+SCREEN_HEIGHT, null);
	}
	
	private void paintPlayer() {
		g2d.setColor(Color.GREEN);
		g2d.fillRect(PLAYER_X, PLAYER_Y, Tile.WIDTH, Tile.HEIGHT);
	}

	private void paintGui() {
	}

	private BufferedImage getImage(tiled.core.Map map) {
		int width = map.getWidth();
		int height = map.getHeight();
		int tileWidth = map.getTileWidth();
		int tileHeight = map.getTileHeight();
		
		int mapWidth = width * tileWidth;
		int mapHeight = height * tileHeight;
		BufferedImage image = new BufferedImage(mapWidth, mapHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D)image.createGraphics();
		
        g2d.setPaint(Color.white);
        g2d.fillRect(0, 0, mapWidth, mapHeight);
		
		for(int i=0; i<map.getLayerCount(); i++) {
			MapLayer layer = map.getLayer(i);
			if (layer.isVisible() && layer instanceof TileLayer) {
				TileLayer tl = (TileLayer)layer;
				for (int y = 0; y < height; y++) {
					for (int x = 0; x < width; x++) {
		                final tiled.core.Tile tile = tl.getTileAt(x, y);
		                if (tile == null) {
		                    continue;
		                }
		                final Image img = tile.getImage();
		                if (img == null) {
		                    continue;
		                }
		                g2d.drawImage(img, x * tileWidth, y * tileHeight, null);
		            }
		        }
			}
		}
		
		return image;
	}
}
