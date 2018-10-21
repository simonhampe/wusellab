package de.wusel.wusellab.game;

public class Game {

	private final Room room = new Room(800, 600);

	private final Player player1 = new Player(Position.at(0, 50));
	private final Player player2 = new Player(Position.at(50, 0));

	public void step() {

	}

	public Room getRoom() {
		return room;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

}
