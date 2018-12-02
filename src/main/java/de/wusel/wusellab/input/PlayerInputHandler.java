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
                game.setPlayerDirection(1, Direction.to(0, -1));
                break;
            case Keys.UP:
                game.setPlayerDirection(1, Direction.to(0, 1));
                break;
            case Keys.LEFT:
                game.setPlayerDirection(1, Direction.to(-1, 0));
                break;
            case Keys.RIGHT:
                game.setPlayerDirection(1, Direction.to(1, 0));
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return super.keyUp(keycode);
    }
}
