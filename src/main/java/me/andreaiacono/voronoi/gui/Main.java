package me.andreaiacono.voronoi.gui;

import me.andreaiacono.voronoi.core.RandomMover;
import me.andreaiacono.voronoi.core.Site;
import me.andreaiacono.voronoi.core.Voronoi;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {

    private final ControlsPanel controlsPanel;
    private final CanvasPanel canvasPanel;
    private RandomMover randomMover;
    private final Timer timer;
    private Voronoi voronoi;

    private JLabel statusBar;

    public static void main(String[] args) throws Exception {
        new Main();
    }

    public Main() {

        super("Voronoi Diagrams");

        voronoi = new Voronoi();
        setSize(750, 627);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        }
        catch (Exception e) {
            // just tried
        }

        controlsPanel = new ControlsPanel(this);
        canvasPanel = new CanvasPanel(this);

        JSplitPane divider = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, canvasPanel, controlsPanel);
        divider.setDividerLocation(600);
        add(divider, BorderLayout.CENTER);

        timer = new Timer(50, this);
        setVisible(true);
    }

    public Voronoi getVoronoi() {
        return voronoi;
    }

    public CanvasPanel getCanvasPanel() {
        return canvasPanel;
    }

    public void restartTimer() {
        timer.restart();
    }

    public void stopTimer() {
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        new SwingWorker<Void, Void>() {
//            @Override
//            protected Void doInBackground() throws Exception {
//                voronoi.getSites().forEach(Site::move);
//                return null;
//            }
//        }.execute();

        randomMover = new RandomMover(getVoronoi());
        randomMover.execute();
        repaint();
    }
}


