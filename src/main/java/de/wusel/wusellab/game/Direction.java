package de.wusel.wusellab.game;

import java.util.Objects;

public class Direction {

    public static final Direction NONE = new Direction(0, 0);
    public static final Direction UP = new Direction(0, 1);
    public static final Direction DOWN = new Direction(0, -1);
    public static final Direction LEFT = new Direction(-1, 0);
    public static final Direction RIGHT = new Direction(1, 0);

    private final int x;
    private final int y;

    private Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Direction to(int x, int y) {
        if (x == 0 && y == 0) {
            return Direction.NONE;
        }
        return new Direction(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direction direction = (Direction) o;
        return x == direction.x &&
                y == direction.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Direction{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
