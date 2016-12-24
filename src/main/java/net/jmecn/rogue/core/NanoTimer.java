package net.jmecn.rogue.core;

public class NanoTimer {

	private static final long NANO_SECOND = 1000000000L;
	private static final float INVERSE_NANO_SECOND = 1f / NANO_SECOND;

	private long startTime;
	private long previousTime;
	private long timePerFrame;
	private float tpf;
    private float fps;

	public NanoTimer() {
		startTime = System.nanoTime();
	}

	public long getTime() {
		return System.nanoTime() - startTime;
	}

	public float getTimeInSecond() {
		return getTime() * INVERSE_NANO_SECOND;
	}

	public void update() {
		timePerFrame = getTime() - previousTime;
		tpf = timePerFrame * INVERSE_NANO_SECOND;
		fps = 1.0f / tpf;
		previousTime = getTime();
	}

	public void reset() {
		startTime = System.nanoTime();
		previousTime = getTime();
	}
	
	public float getTimePerFrame() {
		return tpf;
	}

	public float getFrameRate() {
		return fps;
	}

}
