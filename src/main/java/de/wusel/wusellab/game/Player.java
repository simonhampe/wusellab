package de.wusel.wusellab.game;

public class Player {

	private Position position;
	private Direction direction = Direction.to(1, 1);

	public Player(Position position) {
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Position calculateNewPosition() {
		return position.plus(direction);
	}

}
