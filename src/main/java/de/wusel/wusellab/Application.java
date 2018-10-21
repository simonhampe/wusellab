package de.wusel.wusellab;

import de.wusel.wusellab.game.Game;
import de.wusel.wusellab.render.SwingGameRenderer;

import javax.swing.*;

public class Application {

    public static void main(String[] args) {
        Game game = new Game();

        JFrame frame = new JFrame("WUSEL!");
        frame.setContentPane(new SwingGameRenderer(game));
        frame.setSize(1024, 768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
