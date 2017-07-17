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

        JRadioButton chebyshevRadioButton = new JRadioButton("Chebyshev");
        chebyshevRadioButton.setActionCommand(DistanceType.CHEBYSHEV.toString());
        chebyshevRadioButton.addActionListener(this);

        sl.putConstraint(SpringLayout.WEST, chebyshevRadioButton, 15, SpringLayout.WEST, this);
        sl.putConstraint(SpringLayout.NORTH, chebyshevRadioButton, 5, SpringLayout.SOUTH, euclideanRadioButton);

        JRadioButton canberraRadioButton = new JRadioButton("Canberra");
        canberraRadioButton.setActionCommand(DistanceType.CANBERRA.toString());
        canberraRadioButton.addActionListener(this);

        sl.putConstraint(SpringLayout.WEST, canberraRadioButton, 15, SpringLayout.WEST, this);
        sl.putConstraint(SpringLayout.NORTH, canberraRadioButton, 5, SpringLayout.SOUTH, chebyshevRadioButton);


        JRadioButton manhattanRadioButton = new JRadioButton("Manhattan");
        manhattanRadioButton.setActionCommand(DistanceType.MANHATTAN.toString());
        manhattanRadioButton.addActionListener(this);

        sl.putConstraint(SpringLayout.WEST, manhattanRadioButton, 15, SpringLayout.WEST, this);
        sl.putConstraint(SpringLayout.NORTH, manhattanRadioButton, 5, SpringLayout.SOUTH, canberraRadioButton);


        ButtonGroup distanceGroup = new ButtonGroup();
        distanceGroup.add(canberraRadioButton);
        distanceGroup.add(euclideanRadioButton);
        distanceGroup.add(chebyshevRadioButton);
        distanceGroup.add(manhattanRadioButton);

        JLabel sitesLabel = new JLabel("Sites: ");
        JSlider sitesSlider = new JSlider(JSlider.HORIZONTAL, 2, Constants.MAX_SITES, Constants.INITIAL_SITES);
        sitesSlider.setName("sites");
        sitesSlider.addChangeListener(this);
        sitesSlider.setPaintTicks(true);
        sitesSlider.setPaintLabels(true);

        sl.putConstraint(SpringLayout.WEST, sitesLabel, 5, SpringLayout.WEST, this);
        sl.putConstraint(SpringLayout.NORTH, sitesLabel, 40, SpringLayout.SOUTH, manhattanRadioButton);

        sl.putConstraint(SpringLayout.WEST, sitesSlider, 0, SpringLayout.EAST, sitesLabel);
        sl.putConstraint(SpringLayout.EAST, sitesSlider, -5, SpringLayout.EAST, this);
        sl.putConstraint(SpringLayout.NORTH, sitesSlider, 32, SpringLayout.SOUTH, manhattanRadioButton);


        JButton animationButton = new JButton("Start animation");
        animationButton.setActionCommand("start");
        sl.putConstraint(SpringLayout.WEST, animationButton, 5, SpringLayout.WEST, this);
        sl.putConstraint(SpringLayout.EAST, animationButton, -5, SpringLayout.EAST, this);
        sl.putConstraint(SpringLayout.SOUTH, animationButton, -5, SpringLayout.SOUTH, this);
        animationButton.addActionListener(e -> {
            if (animationButton.getActionCommand().equals("start")) {
                main.restartTimer();
                animationButton.setActionCommand("stop");
                animationButton.setText("Stop Animation");
            } else {
                main.stopTimer();
                animationButton.setActionCommand("start");
                animationButton.setText("Start Animation");
            }
        });

        add(animationButton);
        add(distanceLabel);
        add(canberraRadioButton);
        add(euclideanRadioButton);
        add(chebyshevRadioButton);
        add(manhattanRadioButton);
        add(sitesLabel);
        add(sitesSlider);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String actionCommand = e.getActionCommand();

        if (actionCommand.equals(DistanceType.EUCLIDEAN.toString())) {
            main.getVoronoi().setDistanceType(DistanceType.EUCLIDEAN);
        } else if (actionCommand.equals(DistanceType.CHEBYSHEV.toString())) {
            main.getVoronoi().setDistanceType(DistanceType.CHEBYSHEV);
        } else if (actionCommand.equals(DistanceType.CANBERRA.toString())) {
            main.getVoronoi().setDistanceType(DistanceType.CANBERRA);
        } else if (actionCommand.equals(DistanceType.MANHATTAN.toString())) {
            main.getVoronoi().setDistanceType(DistanceType.MANHATTAN);
        }
        main.repaint();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int n = ((JSlider) e.getSource()).getValue();
        main.getVoronoi().setVisibleSites(n);
        main.getCanvasPanel().setVisibleSites(n);
    }

}
