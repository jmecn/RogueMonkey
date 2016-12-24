package net.jmecn.rogue.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import net.jmecn.rogue.core.Game;
import net.jmecn.rogue.core.Service;

public class InputListener implements KeyListener, Service {

	private Game game;
	private GameView view;
	
	public InputListener(GameView view) {
		this.view = view;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		switch(code) {
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
		case KeyEvent.VK_NUMPAD3:
			// move north
			break;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_NUMPAD2:
			// move south
			break;
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_NUMPAD4:
			// move west
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_NUMPAD6:
			// move east
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void initialize(Game game) {
		this.game = game;
	}

	@Override
	public void update(float tpf) {
		
	}

	@Override
	public void terminate(Game game) {
	}

}
