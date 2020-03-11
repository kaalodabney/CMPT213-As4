package ca.cmpt213.as4.ui;

import ca.cmpt213.as4.barGraph.BarGraphIcon;
import ca.cmpt213.as4.barGraph.BarGraphModel;
import ca.cmpt213.as4.model.*;

import javax.swing.*;

/**
 * Created by kaalo on 2016-08-03.
 */
public class CourseStatsPanel extends PanelABC {
    private JPanel content;
    private JLabel semesterBarGraph;
    private JLabel campusBarGraph;
    private BarGraphModel semesterGraphModel;
    private BarGraphModel campusGraphModel;
    private JLabel courseTitle;

    public CourseStatsPanel(Model model) {
        super(model, "Statistics");
        resizeOnlyHorizontal();
        content = getContentPanel();
        semesterBarGraph = makeSemestersBarGraph();
        campusBarGraph = makeCampusBarGraph();
        courseTitle = new JLabel("Course:");

        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        content.add(courseTitle);
        content.add(new JLabel(" "));
        content.add(new JLabel("Semester Offerings:"));
        content.add(semesterBarGraph);
        content.add(new JLabel(" "));
        content.add(new JLabel(("Campus Offerings:")));
        content.add(campusBarGraph);
        registerAsObserver();
    }

    private JLabel makeSemestersBarGraph() {
        String[] barTitles = {"Spring", "Summer", "Fall"};
        int[] data = {0, 0, 0};
        semesterGraphModel = new BarGraphModel(data, barTitles);
        BarGraphIcon graphIcon = new BarGraphIcon(semesterGraphModel, 250, 200);
        return new JLabel(graphIcon);
        }

    private JLabel makeCampusBarGraph() {
        String[] barTitles = {"Bby", "Sry", "Van", "Other"};
        int[] data = {0, 0, 0, 0};
        campusGraphModel = new BarGraphModel(data, barTitles);
        BarGraphIcon graphIcon = new BarGraphIcon(campusGraphModel, 250, 200);
        return new JLabel(graphIcon);
    }

    private int[] getSemesterGraphData(Course course) {
        int[] data = {0, 0, 0};
        if (course != null) {
            for (CourseOffering offering : course.getCourseOfferingList()) {
                switch (offering.getSemester().getSeason()) {
                    case "Spring":
                        data[0]++;
                        break;
                    case "Summer":
                        data[1]++;
                        break;
                    case "Fall":
                        data[2]++;
                        break;
                    default:
                        assert false;
                        break;
                }
            }
        }

        return data;
    }

    private int[] getCampusGraphData(Course course) {
        int[] data = {0, 0, 0, 0};
        if (course != null) {
            for (CourseOffering offering : course.getCourseOfferingList()) {
                switch (offering.getLocation()) {
                    case "BURNABY":
                        data[0]++;
                        break;
                    case "SURREY":
                        data[1]++;
                        break;
                    case "HRBRCNTR":
                        data[2]++;
                        break;
                    default:
                        data[3]++;
                        break;
                }
            }
        }

        return data;
    }

    private void registerAsObserver() {
        getModel().addObserver(() -> updateGraphs(), "course");
    }

    private void updateGraphs() {
        if(getModel().getSelectedCourse() == null){
            courseTitle.setText("Course:");
        }else {
            courseTitle.setText("Course: " + getModel().getSelectedCourse().getSubject() + getModel().getSelectedCourse().getCatalognumber());
        }
        semesterGraphModel.setData(getSemesterGraphData(getModel().getSelectedCourse()));
        campusGraphModel.setData(getCampusGraphData(getModel().getSelectedCourse()));
        updateUI();
    }
}
