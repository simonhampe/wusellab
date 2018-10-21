package de.wusel.wusellab.game;

public class Direction {

	public static final Direction NONE = Direction.to(0, 0);

	private final int x;
	private final int y;

	private Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public static Direction to(int x, int y) {
		if (x == 0 && y == 0) {
			return Direction.NONE;
		}
		return new Direction(x, y);
	}
}
