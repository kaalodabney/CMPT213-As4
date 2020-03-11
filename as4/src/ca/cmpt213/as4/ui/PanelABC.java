package ca.cmpt213.as4.ui;


import ca.cmpt213.as4.model.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by kaalo on 2016-08-01.
 */
public abstract class PanelABC extends CustomResizablePanelABC {
    private final JLabel TITLE;
    private Model model;
    private JPanel content;

    public PanelABC(Model model, String title) {
        this.model = model;
        TITLE = new JLabel(title);
        //TITLE.setFont(new Font("Serif", Font.BOLD, 24)); //TODO remove this line
        content = new JPanel();
        content.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.gray));

        this.setLayout(new BorderLayout());
        this.add(TITLE, BorderLayout.NORTH);
        this.add(content, BorderLayout.CENTER);
        this.getPreferredSize();
        this.getMaximumSize();
    }

    protected Model getModel() {
        return model;
    }

    protected JPanel getContentPanel() {
        return content;
    }
}
