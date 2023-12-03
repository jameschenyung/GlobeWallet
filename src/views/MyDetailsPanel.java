package views;

import javax.swing.*;
import java.awt.*;
import objects.User;


public class MyDetailsPanel extends JPanel {
   // UI Components
   private JLabel nameLabel;
   private JLabel emailLabel;
   private MainFrame frame;

   public MyDetailsPanel(MainFrame frame) {
      this.frame = frame;
      initializeUI();
      addBackButton();
   }

   private void initializeUI() {
      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Vertical layout

      // Initialize components
      nameLabel = new JLabel("Name: ");
      emailLabel = new JLabel("Email: ");
      // Initialize other components here

      // Set fonts or styles if needed
      nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
      emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
      // Style other components

      // Add components to the panel
      this.add(nameLabel);
      this.add(emailLabel);
      // Add other components

      // Optionally add some padding or separators for better UI
      this.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer between components
   }

   private void addBackButton() {
      JButton backButton = new JButton("Back");
      backButton.addActionListener(e -> goBack());
      this.add(backButton);
   }

   private void goBack() {
      frame.switchToPanel(new HomePanel(frame));
   }
   public void setUserDetails(User user) {
      nameLabel.setText("Name: " + user.getFullName());
//      emailLabel.setText("Email: " + user.getEmail());
   }

   public void showError(String message) {
      JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
   }


   // Add additional methods if needed, for example, methods for handling button clicks
}
