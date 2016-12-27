package net.jmecn.rogue.ai;

import net.jmecn.rogue.core.Game;
import net.jmecn.rogue.core.Service;

public class AiService implements Service {

	private Game game;
	@Override
	public void initialize(Game game) {
		this.game = game;
	}

	@Override
	public void update(float tpf) {
		// TODO Auto-generated method stub

	}

	@Override
	public void terminate(Game game) {
		// TODO Auto-generated method stub

	}

}
