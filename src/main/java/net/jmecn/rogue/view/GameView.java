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
	
	public GameView() {
		this.setPreferredSize(new Dimension(CanvasSize.x, CanvasSize.y));
		this.setMinimumSize(new Dimension(CanvasSize.x, CanvasSize.y));
		
		buffer = new BufferedImage(CanvasSize.x, CanvasSize.y, BufferedImage.TYPE_INT_ARGB);
		
		local = new Vector2();
		local.addLocal(CanvasCenter);
		local.subtractLocal(TileCenter);
	}

	private void chaseCamera() {
		Creature player = game.getPlayer();
		Vector2 vec = player.getLocation();
		
		// 放大到世界坐标系
		vec.mult(TileSize, local);
		
		// 平移到屏幕中心
		local.addLocal(CanvasCenter);
		local.subtractLocal(TileCenter);
		
		logger.debug("Local={}", local);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(buffer, 0, 0, this);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}
	
	@Override
	public void initialize(Game game) {
		this.game = game;
	}

	@Override
	public void update(float tpf) {
		chaseCamera();

		// paint map
		Map map = game.getMap();
		Creature player = game.getPlayer();
		List<Creature> creatures = map.getCreatures();
		
		Vector2 vec = new Vector2();
		Vector2 tmp = new Vector2();
		for(int y=0; y<10; y++) {
			for(int x=0; x<10; x++) {
				vec.set(x, y);
				
				vec.mult(TileSize, tmp);
				tmp.subtractLocal(local);
			}
		}
		
		for(Creature c : creatures) {
			vec = c.getLocation();
			
			vec.mult(TileSize, tmp);
			tmp.subtractLocal(local);
		}
		
		vec = player.getLocation();
		
		vec.mult(TileSize, tmp);
		tmp.subtractLocal(local);
		
		// paint
		Graphics2D g2d = buffer.createGraphics();
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, CanvasSize.x, CanvasSize.y);
		
		g2d.setPaint(Color.WHITE);
		g2d.drawString("FPS:" + 1/tpf, 10, 30);
		g2d.dispose();
		
		repaint();
	}

	@Override
	public void terminate(Game game) {
	}
}
