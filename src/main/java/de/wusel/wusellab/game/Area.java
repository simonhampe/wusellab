package de.wusel.wusellab.game;

public class Area {

    private int x;
    private int y;
    private int width;
    private int height;

    public Area(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean containsPoint(Position position) {
        return x <= position.getX() && position.getX() <= x + width &&
                y <= position.getY() && position.getY() <= y + height;
    }

}
