package net.jmecn.rogue;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.jmecn.rogue.core.Game;
import net.jmecn.rogue.core.NanoTimer;
import net.jmecn.rogue.core.Service;
import net.jmecn.rogue.view.GameView;
import net.jmecn.rogue.view.InputListener;

public class Main implements Runnable {

	static Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}

	private boolean started;
	private boolean enabled;

	private List<Service> services;

	private NanoTimer timer;
	private Game game;
	private GameView view;
	private InputListener listener;

	ExecutorService exec = Executors.newFixedThreadPool(1);

	public Main() {
		timer = new NanoTimer();
		game = new Game();
		view = new GameView();

		listener = new InputListener(view);
		view.addKeyListener(listener);

		services = new ArrayList<Service>();
		services.add(view);
		services.add(listener);
	}

	private void createFrame() {
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

			@Override
			public void windowActivated(WindowEvent e) {
				enabled = true;
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				enabled = false;
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

		// 顺序初始化所有服务
		int len = services.size();
		for (int i = 0; i < len; i++) {
			Service s = services.get(i);
			s.initialize(game);
		}

		// 创建窗口
		createFrame();

		// 启动主线程
		exec.execute(this);

		started = true;
		enabled = true;
		logger.info("开始游戏");
	}

	public void stop() {
		if (!started) {
			return;
		}

		// 关闭主线程
		exec.shutdown();

		// 逆序清理所有的服务
		int len = services.size();
		for (int i = len - 1; i >= 0; i--) {
			Service s = services.get(i);
			s.terminate(game);
		}
		started = false;
		enabled = false;
		logger.info("游戏结束");
	}

	@Override
	public void run() {
		while (started) {
			timer.update();
			if (enabled) {
				int len = services.size();
				for (int i = 0; i < len; i++) {
					Service s = services.get(i);
					s.update(timer.getTimePerFrame());
				}
			}
		}
	}
}
