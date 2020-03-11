package ca.cmpt213.as4.ui;

import ca.cmpt213.as4.model.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kaalo on 2016-08-01.
 */
public class CourseDetailsPanel extends PanelABC {
    JPanel content;
    JPanel courseInfo;
    JPanel sectionInfo;

    public CourseDetailsPanel(Model model) {
        super(model, "Details of Course Offering");
        content = getContentPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        //resizeOnlyHorizontal();

        courseInfo = makeCourseInfo();
        sectionInfo = makeSectionInfo();

        content.add(courseInfo);
        content.add(sectionInfo);
        registerAsObserver();
    }

    private JPanel makeCourseInfo() {
        JPanel courseInfo = new JPanel();
        courseInfo.setLayout(new BoxLayout(courseInfo, BoxLayout.LINE_AXIS));

        JPanel courseInfoLabels = new JPanel();
        courseInfoLabels.setLayout(new BoxLayout(courseInfoLabels, BoxLayout.PAGE_AXIS));
        courseInfoLabels.add(new JLabel("Course Name:"));
        courseInfoLabels.add(new JLabel("Semester:"));
        courseInfoLabels.add(new JLabel("Location:"));
        courseInfoLabels.add(new JLabel("Instructors:"));


        JTextArea courseInfoTextArea = new JTextArea(5, 0);
        courseInfoTextArea.setLineWrap(true);
        courseInfoTextArea.setWrapStyleWord(true);

        courseInfo.add(courseInfoLabels);
        courseInfo.add(courseInfoTextArea);

        return courseInfo;
    }

    private JPanel makeCourseInfo(Course course, CourseOffering offering) {
        JPanel courseInfo = new JPanel();
        courseInfo.setLayout(new BoxLayout(courseInfo, BoxLayout.LINE_AXIS));

        JPanel courseInfoLabels = new JPanel();
        courseInfoLabels.setLayout(new BoxLayout(courseInfoLabels, BoxLayout.PAGE_AXIS));
        courseInfoLabels.add(new JLabel("Course Name:"));
        courseInfoLabels.add(new JLabel("Semester:"));
        courseInfoLabels.add(new JLabel("Location:"));
        courseInfoLabels.add(new JLabel("Instructors:"));


        JTextArea courseInfoTextArea = new JTextArea(5, 0);
        courseInfoTextArea.setLineWrap(true);
        courseInfoTextArea.setWrapStyleWord(true);
        courseInfoTextArea.append(course.getSubject() + " " + course.getCatalognumber() + '\n');
        courseInfoTextArea.append(offering.getSemester().getSEMESTERCODE() + '\n');
        courseInfoTextArea.append(offering.getLocation() + '\n');
        courseInfoTextArea.append(offering.getInstructors());

        courseInfo.add(courseInfoLabels);
        courseInfo.add(courseInfoTextArea);

        return courseInfo;
    }

    private JPanel makeSectionInfo() {
        JPanel sectionInfo = new JPanel();
        sectionInfo.setLayout(new GridLayout(0, 2));
        sectionInfo.add(new JLabel("Section Type"));
        sectionInfo.add(new JLabel("Enrollment (filled/cap)"));

        return sectionInfo;
    }

    private JPanel makeSectionInfo(CourseOffering offering) {
        JPanel sectionInfo = new JPanel();
        sectionInfo.setLayout(new GridLayout(0, 2));
        sectionInfo.add(new JLabel("Section Type"));
        sectionInfo.add(new JLabel("Enrollment (filled/cap)"));
        for (CourseComponent c : offering.getCourseComponentList()) {
            sectionInfo.add(new JLabel(c.getComponentCode()));
            sectionInfo.add(new JLabel(c.getEnrollmentTotal() + "/" + c.getEnrollmentCapacity()));
        }
        return sectionInfo;
    }

    private void registerAsObserver() {
        getModel().addObserver(() -> updateDetails(), "offering");
    }

    private void updateDetails() {
        content.removeAll();
        courseInfo.removeAll();
        sectionInfo.removeAll();

        if (getModel().getSelectedOffering() != null) {
            courseInfo = makeCourseInfo(getModel().getSelectedCourse(), getModel().getSelectedOffering());
            sectionInfo = makeSectionInfo(getModel().getSelectedOffering());
        } else {
            courseInfo = makeCourseInfo();
            sectionInfo = makeSectionInfo();
        }
        content.add(courseInfo);
        content.add(sectionInfo);
        updateUI();
    }


}