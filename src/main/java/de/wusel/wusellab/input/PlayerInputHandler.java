package de.wusel.wusellab.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import de.wusel.wusellab.game.Direction;
import de.wusel.wusellab.game.Game;

public class PlayerInputHandler extends InputAdapter {

    private Game game;

    public PlayerInputHandler(Game game) {
        this.game = game;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Keys.DOWN:
                game.addPlayerDirection(1, Direction.DOWN);
                break;
            case Keys.UP:
                game.addPlayerDirection(1, Direction.UP);
                break;
            case Keys.LEFT:
                game.addPlayerDirection(1, Direction.LEFT);
                break;
            case Keys.RIGHT:
                game.addPlayerDirection(1, Direction.RIGHT);
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Keys.DOWN:
                game.removePlayerDirection(1, Direction.DOWN);
                break;
            case Keys.UP:
                game.removePlayerDirection(1, Direction.UP);
                break;
            case Keys.LEFT:
                game.removePlayerDirection(1, Direction.LEFT);
                break;
            case Keys.RIGHT:
                game.removePlayerDirection(1, Direction.RIGHT);
                break;
        }
        return true;
    }
}
