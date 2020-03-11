package ca.cmpt213.as4.ui;

import ca.cmpt213.as4.model.Model;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

/**
 * Created by kaalo on 2016-08-01.
 */
public class MainUi {
    private Model model;
    private JFrame frame;
    private JPanel left;
    private JPanel mid;
    private JPanel right;

    public MainUi() {
        frame = new JFrame();
        left = new JPanel();
        mid = new JPanel();
        right = new JPanel();
        model = new Model();

        try {
            model.loadFile();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Data file (" + model.getInputFile().getAbsolutePath() + ") not found.");
            System.exit(0);
        }


        CourseListFilterPanel filterPanel = new CourseListFilterPanel(model);
        CourseListPanel listPanel = new CourseListPanel(model);
        CourseOfferingPanel offeringPanel = new CourseOfferingPanel(model);
        CourseStatsPanel statsPanel = new CourseStatsPanel(model);
        CourseDetailsPanel detailsPanel = new CourseDetailsPanel(model);


        left.setLayout(new BoxLayout(left, BoxLayout.PAGE_AXIS));
        left.add(filterPanel);
        left.add(listPanel);

        mid.setLayout(new BoxLayout(mid, BoxLayout.PAGE_AXIS));
        mid.add(offeringPanel);

        right.setLayout(new BoxLayout(right, BoxLayout.PAGE_AXIS));
        right.add(statsPanel);
        right.add(detailsPanel);

        frame.setLayout(new BorderLayout());
        frame.add(left, BorderLayout.WEST);
        frame.add(mid, BorderLayout.CENTER);
        frame.add(right, BorderLayout.EAST);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        MainUi mainUI = new MainUi();
    }

    public Component getFrame() {
        return frame;
    }
}
