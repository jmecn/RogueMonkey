package net.jmecn.roguemonkey.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import net.jmecn.roguemonkey.core.Game;
import net.jmecn.roguemonkey.math.Vector2f;


public class MyListener implements MouseListener, KeyListener  {

	private Game game;
	private GameView view;
	
	private Vector2f NORTH = new Vector2f(0, -1);
	private Vector2f SOUTH = new Vector2f(0, 1);
	private Vector2f EAST = new Vector2f(1, 0);
	private Vector2f WEST = new Vector2f(-1, 0);
	
	public MyListener(Game game, GameView view) {
		this.game = game;
		this.view = view;
	}

	/**
	 * 根据此时鼠标点击的区域获取人物移动方向
	 * @param e按钮的点击事件
	 * @return
	 */
	private Vector2f getMoveDirection(MouseEvent e) {
		return getMoveDirection(e.getX(), e.getY());
	}
	
	/**
	 * 根据此时鼠标点击的区域获取人物移动方向
	 * @param e按钮的点击事件
	 * @return
	 */
	private Vector2f getMoveDirection(int x, int y) {
		int dx = x - GameView.CENTER_X;
		int dy = y - GameView.CENTER_Y;
		int xStep = dx>0?1:-1;
		int yStep = dy>0?1:-1;
		
		int absX = Math.abs(dx);
		int absY = Math.abs(dy);
		if (absX > absY) {
			if (absY / absX < 0.4142f) { 
				yStep = 0;
			}
		} else {
			if (absX / absY < 0.4142f) { 
				xStep = 0;
			}
		}
		
		Vector2f dir = new Vector2f(xStep, yStep);
		return dir;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {// left
			Vector2f dir = getMoveDirection(e);
			game.playerMove(dir);
			view.updateUI();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
			game.playerMove(NORTH);
			view.updateUI();
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:
			game.playerMove(SOUTH);
			view.updateUI();
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:
			game.playerMove(WEST);
			view.updateUI();
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:
			game.playerMove(EAST);
			view.updateUI();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
