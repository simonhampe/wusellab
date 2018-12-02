package de.wusel.wusellab;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.wusel.wusellab.game.Game;

public class MyGdxGame extends ApplicationAdapter {

    private Game game;
    private ShapeRenderer shapeRenderer;

    public MyGdxGame(Game game) {
        this.game = game;
    }

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.circle(game.getPlayer1().getPosition().getX(), game.getPlayer1().getPosition().getY(), 10);
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
    }


}
