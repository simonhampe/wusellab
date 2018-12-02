package de.wusel.wusellab;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.wusel.wusellab.game.Game;
import de.wusel.wusellab.input.PlayerInputHandler;

public class Application {

    public static void main(String[] args) {
        Game game = new Game();
        LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
        new LwjglApplication(new MyGdxGame(game), configuration);
        Gdx.input.setInputProcessor(new PlayerInputHandler(game));
        while (true) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            game.step();
        }
    }

//    public static void main(String[] args) {
//
//        final GLProfile profile = GLProfile.get(GLProfile.GL2);
//        GLCapabilities capabilities = new GLCapabilities(profile);
//        JoglGameRenderer joglGameRenderer = new JoglGameRenderer(capabilities, game);
//        joglGameRenderer.setSize(1024, 768);
//
//        JFrame frame = new JFrame("WUSEL!");
//        frame.getContentPane().add(joglGameRenderer);
//        frame.setSize(1024, 768);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
////        SwingGameRenderer contentPane = new SwingGameRenderer(game);
////        frame.setContentPane(contentPane);
//
//        frame.addKeyListener(new KeyAdapter() {
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//                switch (e.getKeyCode()) {
//                    case KeyEvent.VK_LEFT:
//                        game.setPlayerDirection(1, Direction.to(-1, 0));
//                        break;
//                    case KeyEvent.VK_RIGHT:
//                        game.setPlayerDirection(1, Direction.to(1, 0));
//                        break;
//                    case KeyEvent.VK_UP:
//                        game.setPlayerDirection(1, Direction.to(0, -1));
//                        break;
//                    case KeyEvent.VK_DOWN:
//                        game.setPlayerDirection(1, Direction.to(0, 1));
//                        break;
//                }
//            }
//        });
//
//
//    }
}
