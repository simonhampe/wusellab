package de.wusel.wusellab.game;

public class Position {

	private final int x;
	private final int y;

	private Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Position plus(Direction direction) {
		if (direction == Direction.NONE) {
			return this;
		}
		return Position.at(x + direction.getX(), y + direction.getY());
	}

	public static Position at(int x, int y) {
		return new Position(x, y);
	}

}
