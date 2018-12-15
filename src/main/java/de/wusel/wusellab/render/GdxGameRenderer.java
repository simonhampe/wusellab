package de.wusel.wusellab.render;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.wusel.wusellab.game.Direction;
import de.wusel.wusellab.game.Game;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class GdxGameRenderer extends ApplicationAdapter {

    private Game game;
    private SpriteBatch spriteBatch;

    private float stateTime = 0f;

    private Map<Direction, Animation<TextureRegion>> player1Animations = new HashMap<>();
    private Map<Direction, Animation<TextureRegion>> player1StandingAnimations = new HashMap<>();

    public GdxGameRenderer(Game game) {
        this.game = game;
    }

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);
        Texture animationSheet = new Texture(Gdx.files.internal("assets/sprites/deloran.png"));
        TextureRegion[][] regions = TextureRegion.split(animationSheet, animationSheet.getWidth() / 6, animationSheet.getHeight() / 4);

        TextureRegion[][] walkFrames = new TextureRegion[4][3];
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 3; col++) {
                walkFrames[row][col] = regions[row][col];
            }
        }
        player1Animations.put(Direction.UP, new Animation<>(0.25f, walkFrames[0]));
        player1Animations.put(Direction.RIGHT, new Animation<>(0.25f, walkFrames[1]));
        player1Animations.put(Direction.DOWN, new Animation<>(0.25f, walkFrames[2]));
        player1Animations.put(Direction.LEFT, new Animation<>(0.25f, walkFrames[3]));

        TextureRegion[][] standFrames = new TextureRegion[4][1];
        for (int row = 0; row < 4; row++) {
            standFrames[row][0] = regions[row][1];
        }
        player1StandingAnimations.put(Direction.UP, new Animation<>(0.25f, standFrames[0]));
        player1StandingAnimations.put(Direction.RIGHT, new Animation<>(0.25f, standFrames[1]));
        player1StandingAnimations.put(Direction.DOWN, new Animation<>(0.25f, standFrames[2]));
        player1StandingAnimations.put(Direction.LEFT, new Animation<>(0.25f, standFrames[3]));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame =
                game.getPlayer1().isMoving() ?
                        player1Animations.get(game.getPlayer1().getFacingDirection()).getKeyFrame(stateTime, true) :
                        player1StandingAnimations.get(game.getPlayer1().getFacingDirection()).getKeyFrame(stateTime, true);
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, game.getPlayer1().getPosition().getX(), game.getPlayer1().getPosition().getY());
        spriteBatch.end();

    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }


}
