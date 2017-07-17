package me.andreaiacono.voronoi.gui;

import me.andreaiacono.voronoi.core.Constants;
import me.andreaiacono.voronoi.core.Site;
import me.andreaiacono.voronoi.core.Voronoi;

import java.util.List;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

import static me.andreaiacono.voronoi.core.Constants.SIZE;

public class CanvasPanel extends JPanel {

    private Main main;
    private int visibleSites = Constants.INITIAL_SITES;
    private final Color[] colors = new Color[Constants.MAX_SITES];


    public CanvasPanel(Main main) {
        this.main = main;
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        generateColors(Constants.INITIAL_SITES);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int pointSize = 5;
        Voronoi voronoi = main.getVoronoi();
        int[] points = voronoi.findDiagrams();
        for (int i = 0; i < points.length; i++) {
            g.setColor(colors[points[i]]);
            int x = i / SIZE;
            int y = i % SIZE;
            g.drawLine(x, y, x, y);
        }

        List<Site> sites = voronoi.getSites();
        Color color = Color.GRAY;
        float mf = 1.0f;
        for (int i = 0; i < visibleSites; i++) {
            Site site = sites.get(i);
            g.setColor(colors[i]);
            g.fillOval(((int) (site.x * mf)), ((int) (site.y * mf)), pointSize, pointSize);
            g.setColor(Color.BLACK);
            g.drawOval(((int) (site.x * mf)), ((int) (site.y * mf)), pointSize, pointSize);
        }
    }

    public void setVisibleSites(int n) {
        visibleSites = n;
        generateColors(visibleSites);
        repaint();
    }

    public void generateColors(int n) {
        for (int i = 0; i < n; i++) {
            colors[i] = Color.getHSBColor((float) i / (float) n, 0.85f, 1.0f);
        }
    }

}
