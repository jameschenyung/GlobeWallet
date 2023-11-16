package views;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Globe Wallet");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        switchToPanel(new InitPanel(this));
        setVisible(true);
    }

    public void switchToPanel(JPanel panel) {
        setContentPane(panel);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
