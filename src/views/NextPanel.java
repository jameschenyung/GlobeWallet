package views;

import javax.swing.*;

/**
 * A JPanel representing an additional or subsequent screen in the application.
 * This panel is a simple component that displays a label indicating it is a "next" panel,
 * typically used for demonstration or as a placeholder for further content.
 */
public class NextPanel extends JPanel {
    private JFrame parentFrame;

    /**
     * Constructs a NextPanel associated with the given parent JFrame.
     * Initializes the panel with a label to indicate its purpose or position in the application flow.
     *
     * @param parentFrame The parent JFrame to which this panel belongs.
     */
    public NextPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        add(new JLabel("This is the next panel."));
    }
}