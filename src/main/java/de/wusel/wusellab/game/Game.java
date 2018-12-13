package de.wusel.wusellab.game;

public class Game {

    private final Room room = new Room(800, 600);

    private final Player player1 = new Player(Position.at(0, 50));
    private final Player player2 = new Player(Position.at(50, 0));

    public Game() {
    }

    public synchronized void step() {
        updatePlayer(player1);
        updatePlayer(player2);
    }

    private void updatePlayer(Player player) {
        Position newPosition = player.calculateNewPosition();
        if (room.contains(newPosition)) {
            player.setPosition(newPosition);
        }
    }

    public synchronized void addPlayerDirection(int playerIndex, Direction direction) {
        getPlayer(playerIndex).addActiveDirection(direction);
    }

    public synchronized void removePlayerDirection(int playerIndex, Direction direction) {
        getPlayer(playerIndex).removeActiveDirection(direction);
    }

    private Player getPlayer(int playerIndex) {
        if (playerIndex == 1) {
            return player1;
        }
        if (playerIndex == 2) {
            return player2;
        }
        throw new IllegalArgumentException("Player " + playerIndex + " does not exist.");
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
