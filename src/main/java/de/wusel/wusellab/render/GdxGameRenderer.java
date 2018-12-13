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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GdxGameRenderer extends ApplicationAdapter {

    private Game game;
    private SpriteBatch spriteBatch;

    private float stateTime = 0f;

    private Map<Direction, Animation<TextureRegion>> player1Animations = new HashMap<>();

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
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = player1Animations.get(getDominantPlayerDirection(game.getPlayer1().getActiveDirections())).getKeyFrame(stateTime, true);
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, game.getPlayer1().getPosition().getX(), game.getPlayer1().getPosition().getY());
        spriteBatch.end();

    }

    private Direction getDominantPlayerDirection(Set<Direction> activeDirections) {
        // Always prefer horizontal movements
        for (Direction dir : Arrays.asList(Direction.RIGHT, Direction.LEFT, Direction.DOWN, Direction.UP)) {
            if (activeDirections.contains(dir))
                return dir;
        }
        return Direction.RIGHT;
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }


}
