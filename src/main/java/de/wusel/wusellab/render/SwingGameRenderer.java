package de.wusel.wusellab.render;

import de.wusel.wusellab.game.Game;

import javax.swing.*;
import java.awt.*;

public class SwingGameRenderer extends JPanel implements Renderer {

    public static final Color ROOM_COLOR = Color.WHITE;
    public static final Color PLAYER_COLOR = Color.BLACK;

    public static final int PLAYER_SIZE = 10;

    private Game game;

    public SwingGameRenderer(Game game) {
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (game != null) {
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.setColor(ROOM_COLOR);
            graphics2D.fillRect(0, 0, game.getRoom().getWidth(), game.getRoom().getHeight());


            graphics2D.setColor(PLAYER_COLOR);
            graphics2D.drawOval(game.getPlayer1().getPosition().getX(), game.getPlayer1().getPosition().getY(),
                    10, 10);
            graphics2D.drawOval(game.getPlayer2().getPosition().getX(), game.getPlayer2().getPosition().getY(),
                    10, 10);
        }
    }

    @Override
    public void update() {
        this.repaint();
    }
}
