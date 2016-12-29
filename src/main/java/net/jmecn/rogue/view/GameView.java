package net.jmecn.rogue.view;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.jmecn.rogue.core.Tileset;
import net.jmecn.rogue.core.Game;
import net.jmecn.rogue.core.Map;

import static net.jmecn.rogue.core.Tile.*;

import net.jmecn.rogue.entity.Creature;
import net.jmecn.rogue.math.Vector2;

public class GameView extends Canvas {

	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(GameView.class);

	private final static Vector2 CanvasSize = new Vector2(800, 480);
	private final static Vector2 CanvasCenter = new Vector2(400, 240);
	private final static Vector2 TileSize = new Vector2(32, 32);
	private final static Vector2 TileCenter = new Vector2(16, 16);

	private Vector2 local;
	private Game game;

	private BufferedImage buffer;
	private BufferedImage atlas;
	
	private BufferedImage mapBackground;
	
	public GameView(Game game) {
		this.setSize(new Dimension(CanvasSize.x, CanvasSize.y));
		this.setPreferredSize(new Dimension(CanvasSize.x, CanvasSize.y));

		buffer = new BufferedImage(CanvasSize.x, CanvasSize.y, BufferedImage.TYPE_INT_ARGB);

		local = new Vector2();
		
		this.game = game;
		atlas = AssetFactory.loadImage("Tiles/tiles.png");
		mapBackground = AssetFactory.loadImage("gui/map_background.png");
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
		drawScene(g2d);
		drawGui(g2d);

		g2d.dispose();

		g.drawImage(buffer, 0, 0, this);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	/**
	 * Draw game scene
	 * @param g
	 */
	private void drawScene(Graphics2D g) {

		// paint map
		Map map = game.getMap();
		Creature player = game.getPlayer();
		List<Creature> creatures = map.getCreatures();

		Vector2 vec = new Vector2();
		int height = map.getHeight();
		int width = map.getWidth();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				vec.set(x, y);
				vec.multLocal(TileSize);
				vec.subtractLocal(local);

				// 屏幕外
				if (vec.x + TileSize.x < 0 || vec.y + TileSize.y < 0 || vec.x > CanvasSize.x || vec.y > CanvasSize.y) {
					continue;
				}

				// 无内容
				int tile = map.get(x, y);
				if (tile == Unused) {
					continue;
				}

				//
				int light = 0;
				if (game.isVisible(x, y)) {
					map.setExplored(x, y);
					light = 1;
				} else {
					if (map.isExplored(x, y)) {
						// fog
						light = 0;
					} else {
						// can't see it
						continue;
					}
				}

				Tileset ts = Tileset.values()[tile + 1];
				if (light == 1) {
					drawTile(g, vec, ts);
				} else {
					g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.3f));// 1.0f为透明度
					drawTile(g, vec, ts);
					g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
				}
			}
		}

		for (Creature c : creatures) {
			vec.set(c.getLocation());

			// 视野外
			if (!game.isVisible(vec.x, vec.y)) {
				continue;
			}

			vec.multLocal(TileSize);
			vec.subtractLocal(local);

			// 屏幕外
			if (vec.x + 32 < 0 || vec.y + 32 < 0 || vec.x > CanvasSize.x || vec.y > CanvasSize.y) {
				continue;
			}

			drawTile(g, vec, Tileset.Skeleton);
		}

		vec.set(player.getLocation());
		vec.multLocal(TileSize);
		vec.subtractLocal(local);
		drawTile(g, vec, Tileset.Mage);

	}
	
	private void drawGui(Graphics2D g) {
		drawMinimap(g);
		
		g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 32));
		g.setColor(Color.WHITE);
		g.drawString("Level:" + game.getLevel(), 10, 32);
	}

	private void drawMinimap(Graphics2D g) {
		// paint map
		Map map = game.getMap();
		Creature player = game.getPlayer();
		
		Vector2 vec = new Vector2();
		vec.set(player.getLocation());

		int radius = 16;
		int size = 6;
		int locX = 580;
		int locY = 10;
		
		int width = size * (radius * 2 + 1);
		g.drawImage(mapBackground, locX, locY, locX+width+9, locY+width+9, 0, 0, 64, 64, null);
		
		int sx = vec.x - radius;
		int sy = vec.y - radius;
		int ex = vec.x + radius;
		int ey = vec.y + radius;
		
		for (int y = sy, py = locY+5; y <= ey; y++, py += size ) {
			for (int x = sx, px = locX+5; x <= ex; x++, px += size) {
				
				if (!map.contains(x, y) || map.get(x, y) == Unused) {
					continue;
				}

				boolean hasExplored = map.isExplored(x, y);
				if (!hasExplored) {
					continue;
				}
				
				g.setColor(Color.BLACK);
				int tile = map.get(x, y);
				switch (tile) {
				case Floor:
				case Corridor:
				case Dirt:
				case Moss:
				case Grass:
					g.setColor(Color.WHITE);
					break;
				case Wall:
				case Stone:
				case Tree:
					g.setColor(Color.GRAY);
					break;
				case Door:
					g.setColor(Color.YELLOW);
					break;
				case UpStairs:
					g.setColor(Color.RED);
					break;
				case DownStairs:
					g.setColor(Color.GREEN);
					break;
				}
				g.fillRect(px, py, size, size);
			}
		}
		
		g.setColor(Color.BLUE);
		g.fillRect(size * radius + 5 + locX, size * radius + 5 + locY, size, size);
	}
	
	private void drawTile(Graphics g, Vector2 vec, Tileset ts) {
		int ax = ts.x;
		int ay = ts.y;

		int px = vec.x;
		int py = vec.y;
		g.drawImage(atlas,
				px, py,
				px + Tileset.WIDTH,
				py + Tileset.HEIGHT,
				ax, ay,
				ax + Tileset.WIDTH,
				ay + Tileset.HEIGHT, null);
	}
}
