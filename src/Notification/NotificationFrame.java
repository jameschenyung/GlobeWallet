package Notification;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents a notification frame for displaying messages.
 */
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

    /**
     * Displays a given message in the notification frame.
     * @param message the message to be displayed
     */
    public void showMessage(String message) {
        messageArea.setText(message);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Bank user = new Bank("ABC", 100);
                user.receiveMoney(50);
            } catch (Exception e) {
                Logger.getLogger(NotificationFrame.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }
}

/**
 * This class represents a bank account with basic functionalities.
 */
class Bank {
    private String userName;
    private double balance;
    private NotificationFrame notifier;

    public Bank(String userName, double balance) {
        this.userName = userName;
        this.balance = balance;
        this.notifier = new NotificationFrame();
    }

    /**
     * Processes the reception of money and updates the balance.
     * @param amount the amount of money received
     */
    public void receiveMoney(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }

        balance += amount;
        String message = "Hello " + userName + "!\n\nYou have received a transfer of $" + amount + ".\nYour new balance is $" + balance + ".";
        notifier.showMessage(message);
    }
}
