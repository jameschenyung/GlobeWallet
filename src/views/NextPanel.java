package views;

import javax.swing.*;

public class NextPanel extends JPanel {
    private JFrame parentFrame;

    public NextPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        add(new JLabel("This is the next panel."));
    }
}