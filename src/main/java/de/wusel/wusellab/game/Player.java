package de.wusel.wusellab.game;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Player {

    private Position position;

    private Set<Direction> activeDirections = new HashSet<>();

    private Direction facing = Direction.DOWN;

    public Player(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Direction getEffectiveDirection() {
        return activeDirections.stream().reduce(Direction.NONE,
                (d1, d2) -> Direction.to(d1.getX() + d2.getX(), d1.getY() + d2.getY()));
    }

    public Set<Direction> getActiveDirections() {
        return activeDirections;
    }

    public void addActiveDirection(Direction direction) {
        activeDirections.add(direction);
        computeNewFacingDirection();
    }

    public void removeActiveDirection(Direction direction) {
        activeDirections.remove(direction);
        computeNewFacingDirection();
    }

    public Direction getFacingDirection() {
        return facing;
    }

    public boolean isMoving() {
        return !getEffectiveDirection().equals(Direction.NONE);
    }

    private void computeNewFacingDirection() {
        Direction effective = getEffectiveDirection();
        if (!effective.equals(Direction.NONE)) {
            for (Direction dir : Arrays.asList(Direction.RIGHT, Direction.LEFT, Direction.DOWN, Direction.UP)) {
                if (activeDirections.contains(dir)) {
                    facing = dir;
                    break;
                }
            }
        }
    }

    public Position calculateNewPosition() {
        return position.plus(getEffectiveDirection());
    }

}
