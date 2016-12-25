package net.jmecn.rogue.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.jmecn.rogue.core.Service;
import net.jmecn.rogue.core.Game;
import net.jmecn.rogue.core.Map;
import static net.jmecn.rogue.core.Tile.*;

import net.jmecn.rogue.entity.Creature;
import net.jmecn.rogue.math.Vector2;

public class GameView extends Canvas implements Service {

	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(GameView.class);
	
	private final static Vector2 CanvasSize = new Vector2(800, 480);
	private final static Vector2 CanvasCenter = new Vector2(400, 240);
	private final static Vector2 TileSize = new Vector2(32, 32);
	private final static Vector2 TileCenter = new Vector2(16, 16);
	
	private Vector2 local;
	private Game game;
	
	private BufferedImage buffer;
	
	private float fps = 60;
	
	public GameView() {
		this.setSize(new Dimension(CanvasSize.x, CanvasSize.y));
		this.setPreferredSize(new Dimension(CanvasSize.x, CanvasSize.y));
		
		buffer = new BufferedImage(CanvasSize.x, CanvasSize.y, BufferedImage.TYPE_INT_ARGB);
		
		local = new Vector2();
	}

	private void chaseCamera() {
		Creature player = game.getPlayer();
		Vector2 vec = player.getLocation();
		
		// 放大到世界坐标系
		vec.mult(TileSize, local);
		
		// 平移到屏幕中心
		local.subtractLocal(CanvasCenter);
		local.addLocal(TileCenter);
	}
	
	@Override
	public void paint(Graphics g) {
		chaseCamera();
		
		Graphics2D g2d = buffer.createGraphics();
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, CanvasSize.x, CanvasSize.y);
		// paint
		drawMap(g2d);
		g2d.dispose();
		
		g.drawImage(buffer, 0, 0, this);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}
	
	int v = 0;
	private void drawMap(Graphics g) {
		
		// paint map
		Map map = game.getMap();
		List<Creature> creatures = map.getCreatures();
		
		if (v != map.get(1, 1)) {
			logger.debug("V changed: {}", v);
			v = map.get(1, 1);
		}
		Vector2 vec = new Vector2();
		int height = map.getHeight();
		int width = map.getWidth();
		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				vec.set(x, y);
				vec.multLocal(TileSize);
				vec.subtractLocal(local);
				
				if (vec.x + 32 < 0 || vec.y +32 < 0 || vec.x > CanvasSize.x || vec.y > CanvasSize.y) {
					continue;
				}
				int tile = map.get(x, y);
				switch (tile) {
				case Floor:
					drawTile(g, vec.x, vec.y, 0, 0);
					break;
				case Wall:
					drawTile(g, vec.x, vec.y, 64, 64);
					break;
				case Stone:
					drawTile(g, vec.x, vec.y, 96, 64);
					break;
				case Corridor:
					drawTile(g, vec.x, vec.y, 128, 96);
					break;
				case Tree:
					drawTile(g, vec.x, vec.y, 32, 32);
					break;
				case Door:
					drawTile(g, vec.x, vec.y, 192, 64);
					break;
				case UpStairs:
					drawTile(g, vec.x, vec.y, 192, 32);
					break;
				case DownStairs:
					drawTile(g, vec.x, vec.y, 160, 32);
					break;
				default :
					g.setColor(Color.BLACK);
					g.fillRect(vec.x, vec.y, 32, 32);
				}
			}
		}
		
		for(Creature c : creatures) {
			vec.set(c.getLocation());
			vec.multLocal(TileSize);
			vec.subtractLocal(local);
		}
		
		
		Creature player = game.getPlayer();
		
		vec.set(player.getLocation());
		vec.multLocal(TileSize);
		vec.subtractLocal(local);
		drawTile(g, vec.x, vec.y, 32, 576);
		
		
		g.setColor(Color.WHITE);
		g.drawString("FPS:"+(int)fps, 10, 20);
	}
	
	private BufferedImage atlas;
	@Override
	public void initialize(Game game) {
		this.game = game;
		atlas = AssetFactory.loadImage("Tiles/fantasy-tileset.png");
	}

	@Override
	public void update(float tpf) {
		fps = 1f / tpf;
		repaint();
	}

	@Override
	public void terminate(Game game) {
	}
	
	public void drawTile(Graphics g, int px, int py, int ax, int ay) {
		
		g.drawImage(atlas, px, py, px+32, py+32, ax, ay, ax+32, ay+32, null);
	}
}
