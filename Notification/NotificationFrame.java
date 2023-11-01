

import javax.swing.*;
import java.awt.*;

public class NotificationFrame extends JFrame {

    private JTextArea messageArea;

    public NotificationFrame() {
        setTitle("Money Transfer Notification");
        setSize(300, 150);
        setLayout(new BorderLayout());

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setWrapStyleWord(true);
        messageArea.setLineWrap(true);

        add(new JScrollPane(messageArea), BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> this.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void showMessage(String message) {
        messageArea.setText(message);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Bank user = new Bank("ABC", 100);
            user.receiveMoney(50);
        });
    }
}

class Bank {
    private String userName;
    private double balance;
    private NotificationFrame notifier;

    public Bank(String userName, double balance) {
        this.userName = userName;
        this.balance = balance;
        this.notifier = new NotificationFrame();
    }

    public void receiveMoney(double amount) {
        balance += amount;
        String message = "Hello " + userName + " !" + "\n\nYou have received a transfer of $" + amount + ".\nYour new balance is $" + balance + ".";
        notifier.showMessage(message);
    }
}
