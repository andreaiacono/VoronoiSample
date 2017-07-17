package me.andreaiacono.voronoi.gui;

import me.andreaiacono.voronoi.core.Site;
import me.andreaiacono.voronoi.core.Voronoi;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class CanvasPanel extends JPanel implements MouseListener {

    private final Color[] colors;
    private Main main;

    public CanvasPanel(Main main) {

        colors = generateColors(100);

        this.main = main;
        this.addMouseListener(this);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int pointSize = 8;
        int size = 500;
        Voronoi voronoi = main.getVoronoi();
        int[] points = voronoi.findDiagrams();
        for (int i=0; i<points.length; i++) {
            g.setColor(colors[points[i]]);
            int x = i/size;
            int y = i%size;
            g.drawLine(x,y,x,y);
        }


        List<Site> sites = voronoi.getSites();
        Color color = Color.GRAY;
        float mf = 1.0f;
        sites.forEach(site -> {
            g.setColor(color);
            g.fillOval(((int) (site.x * mf)), ((int) (site.y * mf)), pointSize, pointSize);
            g.setColor(Color.BLACK);
            g.drawOval(((int) (site.x * mf)), ((int) (site.y * mf)), pointSize, pointSize);
        });

    }

    public Color[] generateColors(int n)
    {
        Color[] cols = new Color[n];
        for(int i = 0; i < n; i++)
        {
            cols[i] = Color.getHSBColor((float) i / (float) n, 0.85f, 1.0f);
        }
        return cols;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Site site = new Site(e.getX(), e.getY());
        main.getVoronoi().addSite(site);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
