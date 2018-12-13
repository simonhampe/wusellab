package de.wusel.wusellab;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.wusel.wusellab.game.Game;
import de.wusel.wusellab.input.PlayerInputHandler;
import de.wusel.wusellab.render.GdxGameRenderer;

public class Application {

    public static void main(String[] args) {
        Game game = new Game();
        LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
        new LwjglApplication(new GdxGameRenderer(game), configuration);
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

}
