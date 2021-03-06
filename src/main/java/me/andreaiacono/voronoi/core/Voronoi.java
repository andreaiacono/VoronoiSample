package me.andreaiacono.voronoi.core;

import java.util.*;
import java.util.List;
import java.util.stream.IntStream;

import static me.andreaiacono.voronoi.core.Constants.SIZE;

public class Voronoi {

    DistanceType distanceType;
    List<Site> sites = new ArrayList<>();
    int visibleSites = Constants.INITIAL_SITES;

    public Voronoi() {
        Random r = new Random();
        distanceType = DistanceType.EUCLIDEAN;
        IntStream.range(0, Constants.MAX_SITES).forEach(n -> {
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
            case CANBERRA:
                return canberraDistance(point, site);
            case CHEBYSHEV:
                return chebyshevDistance(point, site);
            case MANHATTAN:
                return manhattanDistance(point, site);
            case COSINE:
                return cosineDistance(point, site);
        }
        throw new IllegalArgumentException("Should not arrive here");
    }

    public int[] findDiagrams() {

        int[] points = new int[SIZE * SIZE];

        for (int i = 0; i < SIZE * SIZE; i++) {
            double min = Double.MAX_VALUE;
            int index = 0;
            for (int j = 0; j < visibleSites; j++) {
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
        int dx = site.x - x1;
        int dy = site.y - y1;
        return Math.sqrt(dx * dx + dy * dy);
    }

    private double chebyshevDistance(int point, Site site) {
        int x1 = point / SIZE;
        int y1 = point % SIZE;
        int dx = site.x - x1;
        int dy = site.y - y1;
//        return Math.pow(Math.pow(Math.abs(dx), 3) + Math.pow(Math.abs(dy), 3), 1/3);
        return Math.max(Math.abs(dx), Math.abs(dy));
    }

    private double canberraDistance(int point, Site site) {
        int x1 = point / SIZE;
        int y1 = point % SIZE;
        int dx = site.x - x1;
        int dy = site.y - y1;
        double denX = Math.max(0.00000000001, x1 + site.x);
        double denY = Math.max(0.00000000001, y1 + site.y);

        return Math.abs(dx) / denX + Math.abs(dy) / denY;
    }

    private double manhattanDistance(int point, Site site) {
        int x1 = point / SIZE;
        int y1 = point % SIZE;
        double dx = site.x - x1;
        double dy = site.y - y1;
        return Math.abs(dx) + Math.abs(dy);
    }

    private double cosineDistance(int point, Site site) {
        int x1 = point / SIZE;
        int y1 = point % SIZE;
        double num = x1 * site.x + y1 * site.y;
        double den = Math.sqrt(x1 * x1 + site.x * site.x) + Math.sqrt(y1 * y1 + site.y * site.y);
        if (den == 0) {
            den = 0.0000000001;
        }
        return 1 - (num / den);
    }

    public List<Site> getSites() {
        return sites;
    }

    public void setDistanceType(DistanceType distanceType) {
        this.distanceType = distanceType;
    }

    public void setVisibleSites(int value) {
        visibleSites = value;
    }
}
