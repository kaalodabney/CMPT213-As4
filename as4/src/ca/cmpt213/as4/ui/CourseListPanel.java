package ca.cmpt213.as4.ui;

import ca.cmpt213.as4.model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * Created by kaalo on 2016-08-03.
 */
public class CourseListPanel extends PanelABC {
    private JPanel content;
    private JList list;
    private ListSelectionModel listSelectionModel;
    private JScrollPane listScroller;

    public CourseListPanel(Model model) {
        super(model, "Course List");
        content = getContentPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.LINE_AXIS));
        list = new JList();
        listSelectionModel = list.getSelectionModel();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
        list.setFixedCellWidth(100);
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (list.getSelectedValue() == null) {
                    getModel().setSelectedCourse(null);
                    getModel().setSelectedCourseOffering(null);
                } else {
                    getModel().setSelectedCourse(list.getSelectedValue().toString());
                }
            }
        });
        listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 450));
        content.add(listScroller);
        registerAsObserver();
    }

    private void registerAsObserver() {
        getModel().addObserver(() -> updateCourseList(), "department");
    }

    private void updateCourseList() {
        list.setListData(getModel().getCourseVectorList());
    }
}
