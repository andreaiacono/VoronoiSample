package me.andreaiacono.voronoi.gui;

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

        JRadioButton euclideanRadioButton = new JRadioButton("Euclidean Distance");
        euclideanRadioButton.setActionCommand(DistanceType.EUCLIDEAN.toString());
        euclideanRadioButton.setSelected(true);
        euclideanRadioButton.addActionListener(this);

        sl.putConstraint(SpringLayout.WEST, euclideanRadioButton, 5, SpringLayout.WEST, this);
        sl.putConstraint(SpringLayout.NORTH, euclideanRadioButton, 5, SpringLayout.NORTH, this);

        JRadioButton manhattanRadioButton = new JRadioButton("Manhattan Distance");
        manhattanRadioButton.setActionCommand(DistanceType.MANHATTAN.toString());
        manhattanRadioButton.addActionListener(this);

        sl.putConstraint(SpringLayout.WEST, manhattanRadioButton , 5, SpringLayout.WEST, this);
        sl.putConstraint(SpringLayout.NORTH, manhattanRadioButton, 5, SpringLayout.SOUTH, euclideanRadioButton);


        ButtonGroup distanceGroup = new ButtonGroup();
        distanceGroup.add(euclideanRadioButton);
        distanceGroup.add(manhattanRadioButton);

        JButton addPointsButton = new JButton("Add Points");
        addPointsButton.addActionListener(this);

        sl.putConstraint(SpringLayout.WEST, addPointsButton, 5, SpringLayout.WEST, this);
        sl.putConstraint(SpringLayout.SOUTH, addPointsButton, -5, SpringLayout.SOUTH, this);



        JLabel pointsLabel = new JLabel("Points: ");
        JSlider pointsSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 10);
        pointsSlider.setName("points");
        pointsSlider.addChangeListener(this);
        pointsSlider.setPaintTicks(true);
        pointsSlider.setPaintLabels(true);

        sl.putConstraint(SpringLayout.WEST, pointsLabel, 5, SpringLayout.WEST, this);
        sl.putConstraint(SpringLayout.SOUTH, pointsLabel, -10, SpringLayout.NORTH, addPointsButton);

        sl.putConstraint(SpringLayout.WEST, pointsSlider, 5, SpringLayout.EAST, pointsLabel);
        sl.putConstraint(SpringLayout.EAST, pointsSlider, -5, SpringLayout.EAST, this);
        sl.putConstraint(SpringLayout.SOUTH, pointsSlider, 5, SpringLayout.NORTH, addPointsButton);

        add(euclideanRadioButton);
        add(manhattanRadioButton);
        add(addPointsButton);
        add(pointsLabel);
        add(pointsSlider);
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

    }

}
