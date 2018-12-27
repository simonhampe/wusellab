package de.wusel.wusellab.render;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
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

    private Map<Direction, Animation<TextureRegion>> player2Animations = new HashMap<>();
    private Map<Direction, Animation<TextureRegion>> player2StandingAnimations = new HashMap<>();

    private TextureRegion floorRegion;
    private TextureRegion finishedRegion;

    private ParticleEffect player1Effect = new ParticleEffect();
    private ParticleEffect player2Effect = new ParticleEffect();

    public GdxGameRenderer(Game game) {
        this.game = game;
    }

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);
        Texture animationSheetPlayer1 = new Texture(Gdx.files.internal("assets/sprites/deloran.png"));
        TextureRegion[][] regionsPlayer1 = TextureRegion.split(animationSheetPlayer1, animationSheetPlayer1.getWidth() / 6, animationSheetPlayer1.getHeight() / 4);

        Texture animationSheetPlayer2 = new Texture(Gdx.files.internal("assets/sprites/numenor.png"));
        TextureRegion[][] regionsPlayer2 = TextureRegion.split(animationSheetPlayer2, animationSheetPlayer2.getWidth() / 7, animationSheetPlayer2.getHeight() / 4);

        createPlayerAnimation(regionsPlayer1, regionsPlayer2);

        Texture floorTexture = new Texture(Gdx.files.internal("assets/tiles/floor.jpg"));
        floorTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        floorRegion = new TextureRegion(floorTexture);
        floorRegion.setRegion(0,0,game.getRoom().getWidth(), game.getRoom().getHeight());

        Texture finishedTexture = new Texture(Gdx.files.internal("assets/tiles/floorfinished.jpg"));
        finishedTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        finishedRegion = new TextureRegion(finishedTexture);
        finishedRegion.setRegion(0,0, game.getGoal().getWidth(), game.getGoal().getHeight());

        player1Effect.load(Gdx.files.internal("assets/particles/finished.p"), Gdx.files.internal("assets/particles"));
        player1Effect.scaleEffect(0.5f);
        player1Effect.start();
        player2Effect.load(Gdx.files.internal("assets/particles/finished.p"), Gdx.files.internal("assets/particles"));
        player2Effect.scaleEffect(0.5f);
        player2Effect.start();
    }

    private void createPlayerAnimation(TextureRegion[][] regionsPlayer1, TextureRegion[][] regionsPlayer2) {
        for(int i = 1; i <= 2; i++) {
            Map<Direction, Animation<TextureRegion>> movingMap =
                    i == 1? player1Animations : player2Animations;
            Map<Direction, Animation<TextureRegion>> standingMap =
                    i == 1? player1StandingAnimations : player2StandingAnimations;
            TextureRegion[][] regions =
                    i == 1? regionsPlayer1 : regionsPlayer2;

            TextureRegion[][] walkFrames = new TextureRegion[4][3];
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 3; col++) {
                    walkFrames[row][col] = regions[row][col];
                }
            }
            movingMap.put(Direction.UP, new Animation<>(0.25f, walkFrames[0]));
            movingMap.put(Direction.RIGHT, new Animation<>(0.25f, walkFrames[1]));
            movingMap.put(Direction.DOWN, new Animation<>(0.25f, walkFrames[2]));
            movingMap.put(Direction.LEFT, new Animation<>(0.25f, walkFrames[3]));

            TextureRegion[][] standFrames = new TextureRegion[4][1];
            for (int row = 0; row < 4; row++) {
                standFrames[row][0] = regions[row][1];
            }
            standingMap.put(Direction.UP, new Animation<>(0.25f, standFrames[0]));
            standingMap.put(Direction.RIGHT, new Animation<>(0.25f, standFrames[1]));
            standingMap.put(Direction.DOWN, new Animation<>(0.25f, standFrames[2]));
            standingMap.put(Direction.LEFT, new Animation<>(0.25f, standFrames[3]));
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFramePlayer1 =
                game.getPlayer1().isMoving() ?
                        player1Animations.get(game.getPlayer1().getFacingDirection()).getKeyFrame(stateTime, true) :
                        player1StandingAnimations.get(game.getPlayer1().getFacingDirection()).getKeyFrame(stateTime, true);

        TextureRegion currentFramePlayer2 =
                game.getPlayer2().isMoving() ?
                        player2Animations.get(game.getPlayer2().getFacingDirection()).getKeyFrame(stateTime, true) :
                        player2StandingAnimations.get(game.getPlayer2().getFacingDirection()).getKeyFrame(stateTime, true);
        player1Effect.setPosition(game.getPlayer1().getPosition().getX(), game.getPlayer1().getPosition().getY());
        player2Effect.setPosition(game.getPlayer2().getPosition().getX(), game.getPlayer2().getPosition().getY());

        spriteBatch.begin();
        spriteBatch.draw(floorRegion,0,0);
        spriteBatch.draw(finishedRegion, game.getGoal().getX(), game.getGoal().getY());

        spriteBatch.draw(currentFramePlayer1, game.getPlayer1().getPosition().getX(), game.getPlayer1().getPosition().getY());
        spriteBatch.draw(currentFramePlayer2, game.getPlayer2().getPosition().getX(), game.getPlayer2().getPosition().getY());

        if(game.getGoal().containsPoint(game.getPlayer1().getPosition())) {
            player1Effect.draw(spriteBatch, Gdx.graphics.getDeltaTime());
        }
        if(game.getGoal().containsPoint(game.getPlayer2().getPosition())) {
            player2Effect.draw(spriteBatch, Gdx.graphics.getDeltaTime());
        }
        spriteBatch.end();

    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }


}
