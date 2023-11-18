package views;

import javax.swing.*;

public class AccountPanel extends JPanel {
    private MainFrame frame;

    public AccountPanel(MainFrame frame) {
        this.frame = frame;

        JButton signOutButton = new JButton("Sign Out");
        signOutButton.addActionListener(e -> signOut());
        add(signOutButton);
    }

    private void signOut() {
        int response = JOptionPane.showConfirmDialog(frame,
                "Are you sure you want to sign out?",
                "Sign Out",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            clearSessionData();
            frame.switchToPanel(new SignInPanel(frame));
        }
    }

    private void clearSessionData(){

    }
}
