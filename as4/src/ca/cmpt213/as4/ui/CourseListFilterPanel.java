package ca.cmpt213.as4.ui;

import ca.cmpt213.as4.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by kaalo on 2016-08-01.
 */
public class CourseListFilterPanel extends PanelABC {
    private JPanel content;

    public CourseListFilterPanel(Model model) {
        super(model, "Course List Filter");
        content = getContentPanel();
        this.resizeOnlyHorizontal();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));

        JPanel comboBoxPanel = new JPanel();
        JLabel comboLabel = new JLabel("Department ");
        Vector<String> options = this.getModel().getSubjectVectorList();
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBoxPanel.setLayout(new BoxLayout(comboBoxPanel, BoxLayout.LINE_AXIS));
        comboBoxPanel.add(comboLabel);
        comboBoxPanel.add(comboBox);
        comboBoxPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(comboBoxPanel);

        JPanel filterChoices = new JPanel();
        filterChoices.setLayout(new BoxLayout(filterChoices, BoxLayout.PAGE_AXIS));
        JCheckBox undergradCheckBox = new JCheckBox("Include Undergrad Courses");
        JCheckBox gradCheckBox = new JCheckBox("Include Grad Courses");
        filterChoices.add(undergradCheckBox);
        filterChoices.add(gradCheckBox);
        content.add(filterChoices);

        JButton updateButton = new JButton("Update Course List");
        updateButton.addActionListener(new ActionListener() {
                                           @Override
                                           public void actionPerformed(ActionEvent e) {
                                               String department = comboBox.getSelectedItem().toString();
                                               boolean underGrad = undergradCheckBox.isSelected();
                                               boolean grad = gradCheckBox.isSelected();
                                               getModel().setSelectedDepartment(department, underGrad, grad);
                                           }
                                       }
        );
        content.add(updateButton);

    }
}
