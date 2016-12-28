package net.jmecn.rogue;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.jmecn.rogue.core.Game;
import net.jmecn.rogue.view.GameView;
import net.jmecn.rogue.view.InputListener;

public class Main {

	static Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}

	private boolean started;

	private Game game;
	private GameView view;
	private InputListener listener;

	public Main() {
		game = new Game();
		view = new GameView(game);

		listener = new InputListener(game, view);
		view.addKeyListener(listener);
	}

	private void createFrame() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			logger.warn("Could not set native look and feel.");
		}
		
		JFrame frame = new JFrame("Rogue Monkey");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);

		frame.add(view);
		frame.pack();

		frame.addKeyListener(listener);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				stop();
			}
		});

		// 窗口居中
		Dimension frameSize = frame.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int locX = (screenSize.width - frameSize.width) / 2;
		int locY = (screenSize.height - frameSize.height) / 2;
		frame.setLocation(locX, locY);

		// 显示窗口
		frame.setVisible(true);
	}

	public void start() {
		if (started) {
			return;
		}

		// 创建窗口
		createFrame();

		started = true;
		
		logger.info("开始游戏");
	}

	public void stop() {
		if (!started) {
			return;
		}

		logger.info("游戏结束");
		
		System.exit(0);
	}

}
