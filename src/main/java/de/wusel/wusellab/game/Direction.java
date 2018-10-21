package de.wusel.wusellab.game;

public class Direction {

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
		return new Direction(x, y);
	}
}
