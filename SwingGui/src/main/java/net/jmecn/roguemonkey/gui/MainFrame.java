package net.jmecn.roguemonkey.gui;

import javax.swing.JFrame;

import net.jmecn.roguemonkey.core.Game;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private Game game;
	private GameView view;
	
	public MainFrame() {
		game = new Game();
		view = new GameView(game);
		MyListener listener = new MyListener(game, view);
		view.addMouseListener(listener);
		view.addKeyListener(listener);
		
		this.addKeyListener(listener);
		
		this.setContentPane(view);
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MainFrame app = new MainFrame();
		app.setVisible(true);
	}
}
