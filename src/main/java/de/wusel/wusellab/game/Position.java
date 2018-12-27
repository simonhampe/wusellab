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

	/**
	 * This will modify the position in the given direction using the given speed.
	 * More precisely, it will normalize the direction vector to a length of one, multiply it with the speed factor,
	 * then add it to the current position and round each coordinate to the nearest integer
	 * @param direction The direction in which to change the position
	 * @param speed The speed multiplier for the direction
	 * @return The new position
	 */
	public Position plus(Direction direction, double speed) {
		if (direction == Direction.NONE) {
			return this;
		}
		double directionSize = Math.sqrt(direction.getX() * direction.getX() + direction.getY() * direction.getY());

		double newX = ((double) x) + ((double) direction.getX()) * speed * (1 / directionSize);
		double newY = ((double) y) + ((double) direction.getY()) * speed * (1 / directionSize);
		return Position.at((int) Math.round(newX), (int)Math.round(newY));
	}

	public static Position at(int x, int y) {
		return new Position(x, y);
	}

}
