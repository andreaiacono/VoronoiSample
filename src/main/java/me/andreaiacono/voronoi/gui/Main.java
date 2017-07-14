package me.andreaiacono.voronoi.gui;

import me.andreaiacono.voronoi.core.Voronoi;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class Main extends JFrame {

    private final ControlsPanel controlsPanel;
    private final CanvasPanel canvasPanel;
    private Voronoi voronoi;

    private JLabel statusBar;
    private JProgressBar progressBar;

    public static void main(String[] args) throws Exception {
        new Main();
    }

    public Main() {

        super("Voronoi Diagrams");

        voronoi = new Voronoi(this, 50);
        setSize(800, 600);
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

//        add(createStatusPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createStatusPanel() {
        // sets the status bar
        statusBar = new JLabel("Ready");
        statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        add(statusBar, BorderLayout.SOUTH);

        JPanel statusPanel = new JPanel(new GridBagLayout());
//        GridBagConstraints c = new GridBagConstraints();
//        c.fill = GridBagConstraints.HORIZONTAL;
//        progressBar = new JProgressBar(0, 100);
//        progressBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
//        progressBar.setValue(0);
//        c.gridx = 0;
//        c.weightx = 1;
//        statusPanel.add(statusBar, c);
//        c.gridx = 10;
//        c.weightx = 0;
//        statusPanel.add(progressBar, c);
        return statusPanel;
    }

    public Voronoi getVoronoi() {
        return voronoi;
    }



}
