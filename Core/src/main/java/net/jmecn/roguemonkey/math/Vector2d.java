package net.jmecn.roguemonkey.math;

/**
 *数据运算类 
 * @author ruanko
 */
public class Vector2d {
	public int x, y;

	public Vector2d() {
		x = y = 0;
	}

	public Vector2d(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Vector2d mult(Vector2d s) {
		return new Vector2d(x * s.x, y * s.y);
	}
	
	public Vector2d multLocal(float scalor) {
		this.x *= scalor;
		this.y *= scalor;
		return this;
	}
	
	public Vector2d multLocal(Vector2d s) {
		this.x *= s.x;
		this.y *= s.y;
		return this;
	}
	
	//除法
	public Vector2d divide(Vector2d s) {
		if (s.x == 0 || s.y == 0)
			throw new RuntimeException("Can't be divided by zero");
		
		return new Vector2d(x / s.x, y / s.y);
	}
	
    //加法
	public Vector2d add(Vector2d s) {
		return new Vector2d(x + s.x, y + s.y);
	}
	
	public Vector2d add(int x, int y) {
		return new Vector2d(x + x, y + y);
	}
	
	public Vector2d addLocal(Vector2d s) {
		this.x += s.x;
		this.y += s.y;
		return this;
	}
	
	public double length() {
		return Math.sqrt(x * x + y * y);
	}
	
	//碰撞检测
	public double distance(Vector2d s) {
		int dx = x - s.x;
		int dy = y - s.y;
		return Math.sqrt(dx * dx + dy * dy);
	}
}
