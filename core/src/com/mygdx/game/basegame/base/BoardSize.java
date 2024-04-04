package com.mygdx.game.basegame.base;

public class BoardSize {
    private final int x;
    private final int y;

    public BoardSize(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "BoardSize{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
