package net.jmecn.rogue.core;

public interface Service {
	
	public void initialize(Game game);
	
	public void update(float tpf);
	
	public void terminate(Game game);
	
}
