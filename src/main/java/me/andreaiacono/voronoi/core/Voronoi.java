package me.andreaiacono.voronoi.core;

import me.andreaiacono.voronoi.gui.Main;

import java.util.*;
import java.util.List;
import java.util.stream.IntStream;

import static me.andreaiacono.voronoi.core.Constants.SIZE;

public class Voronoi {

    DistanceType distanceType;

    List<Site> sites = new ArrayList<>();

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
            addSite(new Site(x, y));
        });
    }

    public void addSite(Site site) {
        sites.add(site);
    }

    private double distance(int point, Site site) {

        switch (distanceType) {
            case EUCLIDEAN:
                return euclideanDistance(point, site);
            case MANHATTAN:
                return manhattanDistance(point, site);
        }
        throw new IllegalArgumentException("Should not arrive here");
    }

    public int[] findDiagrams() {

        int[] points = new int[SIZE * SIZE];

        for (int i = 0; i < SIZE * SIZE; i++) {
            double min = Double.MAX_VALUE;
            int index = 0;
            Site p = new Site(i / SIZE, i % SIZE);
            for (int j = 0; j < sites.size(); j++) {
                double distance = distance(i, sites.get(j));
                if (min > distance) {
                    min = distance;
                    index = j;
                }
            }
            points[i] = index;
        }

        return points;
    }


    private double euclideanDistance(int point, Site site) {
        int x1 = point / SIZE;
        int y1 = point % SIZE;
        double dx = site.x - x1;
        double dy = site.y - y1;
        return Math.sqrt(dx * dx + dy * dy); // Euclidean distance formula
    }

    private double manhattanDistance(int point, Site site) {
        int x1 = point / SIZE;
        int y1 = point % SIZE;
        double dx = site.x - x1;
        double dy = site.y - y1;
        return Math.abs(dx) + Math.abs(dy);
    }

    public List<Site> getSites() {
        return sites;
    }

    public void setDistanceType(DistanceType distanceType) {
        this.distanceType = distanceType;
    }

}
