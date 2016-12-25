package net.jmecn.rogue.math;

public class Vector2 implements Cloneable {

	public final static Vector2 ZERO = new Vector2(0, 0);
	public final static Vector2 UNIT_X = new Vector2(1, 0);
	public final static Vector2 UNIT_Y = new Vector2(0, 1);

	public int x;
	public int y;

	public Vector2() {
		x = y = 0;
	}

	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Vector2(Vector2 v) {
		this.x = v.x;
		this.y = v.y;
	}

	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void set(Vector2 v) {
		if (v == null) {
			return;
		}
		this.x = v.x;
		this.y = v.y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Vector2 add(Vector2 v, Vector2 result) {
		if (v == null) {
			return null;
		}
		if (result == null) {
			result = new Vector2();
		}

		result.x = x + v.x;
		result.y = y + v.y;

		return result;
	}

	public Vector2 addLocal(Vector2 v) {
		if (v == null) {
			return null;
		}

		x += v.x;
		y += v.y;

		return this;
	}

	public Vector2 subtract(Vector2 v, Vector2 result) {
		if (v == null) {
			return null;
		}
		if (result == null) {
			result = new Vector2();
		}

		result.x = x - v.x;
		result.y = y - v.y;

		return result;
	}

	public Vector2 subtractLocal(Vector2 v) {
		if (v == null) {
			return null;
		}

		x -= v.x;
		y -= v.y;

		return this;
	}

	public Vector2 mult(Vector2 v, Vector2 result) {
		if (v == null) {
			return null;
		}
		if (result == null) {
			result = new Vector2();
		}

		result.x = x * v.x;
		result.y = y * v.y;

		return result;
	}

	public Vector2 multLocal(Vector2 v) {
		if (v == null) {
			return null;
		}

		x *= v.x;
		y *= v.y;

		return this;
	}

	public Vector2 divide(Vector2 v, Vector2 result) {
		if (v == null) {
			return null;
		}
		if (result == null) {
			result = new Vector2();
		}

		result.x = x / v.x;
		result.y = y / v.y;

		return result;
	}

	public Vector2 divideLocal(Vector2 v) {
		if (v == null) {
			return null;
		}

		x /= v.x;
		y /= v.y;

		return this;
	}

	public Vector2 negate() {
		return new Vector2(-x, -y);
	}

	public Vector2 negateLocal() {
		x = -x;
		y = -y;
		return this;
	}

	public int dot(Vector2 v) {
		if (v == null) {
			return 0;
		}

		return x * v.x + y * v.y;
	}

	public int determinant(Vector2 v) {
		if (v == null) {
			return 0;
		}

		return (x * v.y) - (y * v.x);
	}

	public double length() {
		return Math.sqrt(x * x + y * y);
	}

	public int lengthSquared() {
		return x * x + y * y;
	}

	public double distance(Vector2 v) {
		if (v == null) {
			return 0;
		}
		int dx = x - v.x;
		int dy = y - v.y;

		return Math.sqrt(dx * dx + dy * dy);
	}

	public int distanceSquared(Vector2 v) {
		if (v == null) {
			return 0;
		}
		int dx = x - v.x;
		int dy = y - v.y;

		return dx * dx + dy * dy;
	}

	@Override
	public Vector2 clone() {
		return new Vector2(x, y);
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;

		if (o instanceof Vector2) {
			Vector2 v = (Vector2) o;
			return (v.x == x && v.y == y);
		}

		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}