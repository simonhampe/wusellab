package de.wusel.wusellab;

import de.wusel.wusellab.game.Game;
import de.wusel.wusellab.render.SwingGameRenderer;

import javax.swing.*;

public class Application {

    public static void main(String[] args) {
        Game game = new Game();

        JFrame frame = new JFrame("WUSEL!");
        SwingGameRenderer contentPane = new SwingGameRenderer(game);
        frame.setContentPane(contentPane);
        frame.setSize(1024, 768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        game.step();
        contentPane.update();
    }
}
