package me.andreaiacono.voronoi.core;

import java.util.Random;

public class Site {

    private final int SPEED = 3;

    private final int dx;
    private final int dy;
    public int x;
    public int y;

    public Site(int x, int y) {
        this.x = x;
        this.y = y;
        Random r = new Random();

        dx = r.nextInt(SPEED + SPEED + 1) - SPEED;
        dy = r.nextInt(SPEED + SPEED + 1) - SPEED;
//        System.out.println("dx=" + dx + " dy=" + dy);
    }

    public void move() {
        x += dx % Constants.SIZE;
        y += dy % Constants.SIZE;
//        if (x < 0) {
//            x = Constants.SIZE;
//        }
//
//        Math.max(0, Math.min(Constants.SIZE, dx));
//        this.y += Math.max(0, Math.min(Constants.SIZE, dy));
    }
}
