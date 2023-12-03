package views;

import javax.swing.*;
import java.awt.*;
import objects.User;

/**
 * A JPanel representing the user details section of the application.
 * This panel displays the user's information such as name and email. It also provides
 * a back button for navigation and allows for interaction with a MyDetailsViewListener
 * for handling user-triggered actions.
 */
public class MyDetailsPanel extends JPanel {
   private MyDetailsViewListener listener;

   // UI Components
   private JLabel nameLabel;
   private JLabel emailLabel;
   private MainFrame frame;

   /**
    * Constructs a MyDetailsPanel associated with the given MainFrame.
    * This panel is initialized with UI components to display user details.
    *
    * @param frame The MainFrame that this panel is a part of.
    */
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

   /**
    * Sets the user details to be displayed on the panel.
    *
    * @param user The user whose details are to be displayed.
    */
   public void setUserDetails(User user) {
      nameLabel.setText("Name: " + user.getFullName());
//      emailLabel.setText("Email: " + user.getEmail());
   }

   /**
    * Displays an error message to the user.
    *
    * @param message The error message to be displayed.
    */
   public void showError(String message) {
      JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
   }

   /**
    * Registers a listener for handling user interactions.
    *
    * @param listener The MyDetailsViewListener to be registered.
    */
   public void registerListener(MyDetailsViewListener listener) {
      this.listener = listener;
      // You can also add action listeners to buttons or other interactive components here
      // For example: someButton.addActionListener(e -> listener.onSomeAction());
   }

   // Add additional methods if needed, for example, methods for handling button clicks
}
