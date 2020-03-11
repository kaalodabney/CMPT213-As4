package ca.cmpt213.as4.ui;

import ca.cmpt213.as4.model.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by kaalo on 2016-08-03.
 */
public class CourseOfferingPanel extends PanelABC {
    private JPanel content;
    private ArrayList<Integer> yearList;

    public CourseOfferingPanel(Model model) {
        super(model, "Course Offerings by Semester");
        yearList = new ArrayList<>();
        content = getContentPanel();
        content.setPreferredSize(new Dimension(1000, 1000));
        registerAsObserver();
    }

    private void makeGrid(Course course) {
        JPanel grid = content;
        grid.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        addSemesterLabels(grid, constraints);
        addYearLabels(grid, constraints, course);
        addSemesterCells(grid, constraints, course);

    }

    private void addSemesterCells(JPanel grid, GridBagConstraints constraints, Course course) {
        for (int i = 0; i < yearList.size(); i++) {
            for (int j = 0; j < 3; j++) {
                constraints.weightx = 1.0;
                constraints.weighty = 1.0;
                constraints.gridy = i + 1;
                constraints.gridx = j + 1;
                constraints.fill = GridBagConstraints.BOTH;
                grid.add(makeSemesterCell(i, j, course), constraints);
            }
        }
    }

    private JPanel makeSemesterCell(int yearListIndex, int semester, Course course) {
        JPanel cell = new JPanel();
        cell.setLayout(new GridBagLayout());
        GridBagConstraints cellConstraints = new GridBagConstraints();
        cellConstraints.fill = GridBagConstraints.HORIZONTAL;
        cellConstraints.anchor = GridBagConstraints.NORTH;
        cellConstraints.weightx = 1.0;
        cellConstraints.weighty = 0.0;

        String season;
        switch (semester) {
            case 0:
                season = "Spring";
                break;
            case 1:
                season = "Summer";
                break;
            case 2:
                season = "Fall";
                break;
            default:
                season = "invalid semester entry";
                break;
        }

        int i = 0;
        for (CourseOffering offering : course.getCourseOfferingList()) {
            if ((offering.getSemester().getYear() == yearList.get(yearListIndex)) && (offering.getSemester().getSeason().equals(season))) {
                cellConstraints.gridy = i;
                cell.add(makeClassButton(offering, course.getSubject() + " " + course.getCatalognumber()), cellConstraints);
                i++;
            }
        }

        cellConstraints.gridy = i;
        cellConstraints.fill = GridBagConstraints.BOTH;
        cellConstraints.anchor = GridBagConstraints.SOUTH;
        cellConstraints.weighty = 1.0;
        cellConstraints.gridheight = GridBagConstraints.REMAINDER;
        cell.add(Box.createGlue(), cellConstraints);

        cell.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.gray, Color.gray));
        cell.setPreferredSize(new Dimension(175, 30 * i));
        return cell;
    }

    private JButton makeClassButton(CourseOffering offering, String course) {
        JButton button = new JButton(course + " - " + offering.getLocation());
        //TODO button action listeners
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getModel().setSelectedCourseOffering(offering);
            }
        });

        return button;
    }

    private void addSemesterLabels(JPanel grid, GridBagConstraints constraints) {
        constraints.ipady = 10;
        constraints.weighty = 0.0;
        constraints.weightx = 1.0;
        constraints.gridy = 0;
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.WEST;
        grid.add(new JLabel("Spring", SwingConstants.LEFT), constraints);

        constraints.gridx = 2;
        grid.add(new JLabel("Summer", SwingConstants.LEFT), constraints);

        constraints.gridx = 3;
        grid.add(new JLabel("Fall", SwingConstants.LEFT), constraints);
    }

    private void addYearLabels(JPanel grid, GridBagConstraints constraints, Course course) {
        for (CourseOffering offering : course.getCourseOfferingList()) {
            addYearToList(offering.getSemester().getYear());
        }

        java.util.Collections.sort(yearList);
        for (int i = 0; i < yearList.size(); i++) {
            constraints.weightx = 0.0;
            constraints.weighty = 1.0;
            constraints.gridx = 0;
            constraints.gridy = i + 1;
            constraints.ipadx = 10;
            constraints.anchor = GridBagConstraints.NORTH;
            grid.add(new JLabel(yearList.get(i).toString()), constraints);
        }

    }

    private void addYearToList(int year) {
        boolean add = true;
        if (!yearList.isEmpty()) {
            for (Integer y : yearList) {
                if (y.equals(year)) {
                    add = false;
                }
            }
        }

        if (add) {
            yearList.add(year);
        }
    }

    private void registerAsObserver() {
        getModel().addObserver(() -> updateOfferingGrid(), "course");
    }

    private void updateOfferingGrid() {
        content.removeAll();
        yearList.clear();
        if (getModel().getSelectedCourse() != null) {
            makeGrid(getModel().getSelectedCourse());
        }
        updateUI();
    }
}
