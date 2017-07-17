package me.andreaiacono.voronoi.gui;

import me.andreaiacono.voronoi.core.Constants;
import me.andreaiacono.voronoi.core.DistanceType;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlsPanel extends JPanel implements ActionListener, ChangeListener {

    private Main main;

    public ControlsPanel(Main main) {
        this.main = main;

        SpringLayout sl = new SpringLayout();
        setLayout(sl);

        JLabel distanceLabel = new JLabel("Distance: ");
        sl.putConstraint(SpringLayout.WEST, distanceLabel, 5, SpringLayout.WEST, this);
        sl.putConstraint(SpringLayout.NORTH, distanceLabel, 5, SpringLayout.NORTH, this);

        JRadioButton euclideanRadioButton = new JRadioButton("Euclidean");
        euclideanRadioButton.setActionCommand(DistanceType.EUCLIDEAN.toString());
        euclideanRadioButton.setSelected(true);
        euclideanRadioButton.addActionListener(this);

        sl.putConstraint(SpringLayout.WEST, euclideanRadioButton, 15, SpringLayout.WEST, this);
        sl.putConstraint(SpringLayout.NORTH, euclideanRadioButton, 5, SpringLayout.SOUTH, distanceLabel);

        JRadioButton manhattanRadioButton = new JRadioButton("Manhattan");
        manhattanRadioButton.setActionCommand(DistanceType.MANHATTAN.toString());
        manhattanRadioButton.addActionListener(this);

        sl.putConstraint(SpringLayout.WEST, manhattanRadioButton , 15, SpringLayout.WEST, this);
        sl.putConstraint(SpringLayout.NORTH, manhattanRadioButton, 5, SpringLayout.SOUTH, euclideanRadioButton);


        ButtonGroup distanceGroup = new ButtonGroup();
        distanceGroup.add(euclideanRadioButton);
        distanceGroup.add(manhattanRadioButton);

        JLabel sitesLabel = new JLabel("Sites: ");
        JSlider sitesSlider = new JSlider(JSlider.HORIZONTAL, 2, Constants.MAX_SITES, Constants.INITIAL_SITES);
        sitesSlider.setName("sites");
        sitesSlider.addChangeListener(this);
        sitesSlider.setPaintTicks(true);
        sitesSlider.setPaintLabels(true);

        sl.putConstraint(SpringLayout.WEST, sitesLabel, 5, SpringLayout.WEST, this);
        sl.putConstraint(SpringLayout.SOUTH, sitesLabel, -20, SpringLayout.SOUTH, this);

        sl.putConstraint(SpringLayout.WEST, sitesSlider, 0, SpringLayout.EAST, sitesLabel);
        sl.putConstraint(SpringLayout.EAST, sitesSlider, -5, SpringLayout.EAST, this);
        sl.putConstraint(SpringLayout.SOUTH, sitesSlider, -5, SpringLayout.SOUTH, this);

        add(distanceLabel);
        add(euclideanRadioButton);
        add(manhattanRadioButton);
        add(sitesLabel);
        add(sitesSlider);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String actionCommand = e.getActionCommand();

        if (actionCommand.equals(DistanceType.EUCLIDEAN.toString())) {
            main.getVoronoi().setDistanceType(DistanceType.EUCLIDEAN);
        }
        else if (actionCommand.equals(DistanceType.MANHATTAN.toString())) {
            main.getVoronoi().setDistanceType(DistanceType.MANHATTAN);
        }
        main.repaint();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int n = ((JSlider)e.getSource()).getValue();
        main.getVoronoi().setVisibleSites(n);
        main.getCanvasPanel().setVisibleSites(n);
    }

}
