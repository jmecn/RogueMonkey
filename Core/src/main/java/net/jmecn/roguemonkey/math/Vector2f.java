package net.jmecn.roguemonkey.math;

public class Vector2f {

	public float x, y;

	public Vector2f() {
		x = y = 0f;
	}

	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void multLocal(float scalor) {
		this.x *= scalor;
		this.y *= scalor;
	}
	
	public void multLocal(Vector2f s) {
		this.x *= s.x;
		this.y *= s.y;
	}
	
	//改变坐标值
	public void addLocal(Vector2f s) {
		this.x += s.x;
		this.y += s.y;
	}
	
	public void addLocal(float x, float y) {
		this.x += x;
		this.y += y;
	}
}
