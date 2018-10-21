package de.wusel.wusellab;

import de.wusel.wusellab.game.Direction;
import de.wusel.wusellab.game.Game;
import de.wusel.wusellab.render.SwingGameRenderer;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Application {

    public static void main(String[] args) {
        Game game = new Game();

        JFrame frame = new JFrame("WUSEL!");
        SwingGameRenderer contentPane = new SwingGameRenderer(game);
        frame.setContentPane(contentPane);
        frame.setSize(1024, 768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        game.setPlayerDirection(1, Direction.to(-1, 0));
                        break;
                    case KeyEvent.VK_RIGHT:
                        game.setPlayerDirection(1, Direction.to(1, 0));
                        break;
                    case KeyEvent.VK_UP:
                        game.setPlayerDirection(1, Direction.to(0, -1));
                        break;
                    case KeyEvent.VK_DOWN:
                        game.setPlayerDirection(1, Direction.to(0, 1));
                        break;
                }
            }
        });

        while (true) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            game.step();
            contentPane.update();
        }

    }
}
