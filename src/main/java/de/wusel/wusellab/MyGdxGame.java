package de.wusel.wusellab;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.wusel.wusellab.game.Game;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MyGdxGame extends ApplicationAdapter {

    private Game game;
    private ShapeRenderer shapeRenderer;
    private Animation<TextureRegion> animation;
    private Texture animationSheet;
    private SpriteBatch spriteBatch;

    private float stateTime = 0f;

    public MyGdxGame(Game game) {
        this.game = game;
    }

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        spriteBatch = new SpriteBatch();
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);
        animationSheet = new Texture(Gdx.files.internal("assets/sprites/deloran.png"));
        TextureRegion[][] regions = TextureRegion.split(animationSheet, animationSheet.getWidth() / 6, animationSheet.getHeight() / 4);

        TextureRegion[] walkFrames = new TextureRegion[3];
        for (int i = 0; i < 3; i++) {
            walkFrames[i] = regions[1][i];
        }
        animation = new Animation<>(0.25f, walkFrames);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, game.getPlayer1().getPosition().getX(), game.getPlayer1().getPosition().getY());
        spriteBatch.end();

    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }


}
