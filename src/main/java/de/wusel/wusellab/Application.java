package de.wusel.wusellab;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.wusel.wusellab.game.Game;
import de.wusel.wusellab.input.PlayerInputHandler;
import de.wusel.wusellab.render.GdxGameRenderer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Application {

    private static ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) {
        Game game = new Game();
        LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
        configuration.width = 800;
        configuration.height = 600;
        new LwjglApplication(new GdxGameRenderer(game), configuration);
        Gdx.input.setInputProcessor(new PlayerInputHandler(game));
        executorService.scheduleWithFixedDelay(new GameStepper(game), 0, 30, TimeUnit.MILLISECONDS);
    }

    private static class GameStepper implements  Runnable {

        private Game game;

        public GameStepper(Game game) {
            this.game = game;
        }

        @Override
        public void run() {
            game.step();
        }
    }

}
