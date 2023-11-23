package views;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;

public class MoneyTransferPanel extends JPanel {
    private MainFrame frame;

    public MoneyTransferPanel(MainFrame frame){
        this.frame = frame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> frame.switchToPanel(new HomePanel(frame)));
        gbc.gridy++;
        add(backButton, gbc);
    }
}
