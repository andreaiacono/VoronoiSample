package me.andreaiacono.voronoi.core;

import me.andreaiacono.voronoi.gui.Main;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.IntStream;

public class Voronoi {

    DistanceType distanceType;

    List<Point> sites = new ArrayList<>();

    int SIZE = 500;

    private Main main;
    private int colorsNumber;
    Random r;

    public Voronoi(Main main, int colorsNumber) {
        this.main = main;
        this.colorsNumber = colorsNumber;
        r = new Random();
        distanceType = DistanceType.EUCLIDEAN;

        IntStream.range(0, 50).forEach(n -> {
            int x = Math.abs(r.nextInt()) % SIZE;
            int y = Math.abs(r.nextInt()) % SIZE;
            addPoint(new Point(x, y));
        });
    }

    public void addPoint(Point point) {
        sites.add(point);
    }

    public int[] findDiagrams() {

        int[] points = new int[SIZE * SIZE];

        for (int i=0; i<SIZE*SIZE; i++) {
            double min = Double.MAX_VALUE;
            int index = 0;
            Point p = new Point(i / SIZE, i % SIZE);
            for (int j=0; j<sites.size(); j++) {
                double distance = 0;
                switch (distanceType) {
                    case EUCLIDEAN:
                        distance = euclideanDistance(p, sites.get(j));
                        break;
                    case MANHATTAN:
                        distance = manhattanDistance(p, sites.get(j));
                }

                if (min > distance) {
                    min = distance;
                    index = j;
                }
            }
            points[i] = index;
        }

        return points;
    }


    private double euclideanDistance(Point p1, Point p2) {
        double dx = p2.getX() - p1.getX();
        double dy = p2.getY() - p1.getY();
        return Math.sqrt(dx * dx + dy * dy); // Euclidean distance formula
    }

    private double manhattanDistance(Point p1, Point p2) {
        double dx = p2.getX() - p1.getX();
        double dy = p2.getY() - p1.getY();
        return Math.abs(dx) + Math.abs(dy);
    }

    public List<Point> getSites() {
        return sites;
    }

    public void setDistanceType(DistanceType distanceType) {
        this.distanceType = distanceType;
    }



}
