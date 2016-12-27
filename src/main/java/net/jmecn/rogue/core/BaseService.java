package net.jmecn.rogue.core;

public class BaseService implements Service {

	private Game game;
	private boolean enabled = false;

	@Override
	public void initialize(Game game) {
		this.game = game;
		enabled = true;
	}

	@Override
	public void update(float tpf) {

	}

	@Override
	public void terminate(Game game) {
		enabled = false;
	}

}
