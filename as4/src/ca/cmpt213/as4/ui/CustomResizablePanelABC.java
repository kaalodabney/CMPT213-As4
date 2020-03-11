package ca.cmpt213.as4.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kaalo on 2016-08-07.
 */
public class CustomResizablePanelABC extends JPanel {
    protected void resizeOnlyHorizontal() {
        Dimension prefSize = this.getPreferredSize();
        Dimension newSize = new Dimension(Integer.MAX_VALUE, (int) prefSize.getHeight());
        this.setMaximumSize(newSize);
    }

    protected void resizeOnlyVertical() {
        Dimension prefSize = this.getPreferredSize();
        Dimension newSize = new Dimension((int) prefSize.getWidth(), Integer.MAX_VALUE);
        this.setMaximumSize(newSize);
    }

    protected void noResize() {
        Dimension prefSize = this.getPreferredSize();
        Dimension newSize = new Dimension((int) prefSize.getWidth(), (int) prefSize.getHeight());
        this.setMaximumSize(newSize);
    }
}
