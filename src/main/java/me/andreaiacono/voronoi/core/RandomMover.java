package me.andreaiacono.voronoi.core;

import javax.swing.*;

public class RandomMover extends SwingWorker<Void, Void> {

    private Voronoi voronoi;

    public RandomMover(Voronoi voronoi) {
        this.voronoi = voronoi;
    }

    @Override
    protected Void doInBackground() throws Exception {
        int visibleSites = voronoi.visibleSites;
        for (int i=0; i < visibleSites; i++) {
            voronoi.getSites().get(i).move();
        }
        return null;
    }
}