package de.wusel.wusellab.game;

public class Room {

	private final int width;
	private final int height;

	public Room(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean contains(Position position) {
		int x = position.getX();
		int y = position.getY();
		return 0 <= x && x <= width && 0 <= y && y <= height;
	}

}
