package views;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class HomePanel extends JPanel{
    private MainFrame frame;
    private Image backgroundImage;

    public HomePanel(MainFrame frame) {
        this.frame = frame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.LINE_START;

        try {
            backgroundImage = ImageIO.read(new File("MoneyTransfer.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JButton moneyTransferButton = new JButton("Money Transfer");
        moneyTransferButton.setBounds(20, 200, 50, 10);
        moneyTransferButton.addActionListener(e -> frame.switchToPanel(new MoneyTransferPanel(frame)));
        add(moneyTransferButton, gbc);

        ImageIcon myAccount = new ImageIcon("MyAccount.png");
        Image accimage = myAccount.getImage();
        Image accnewing = accimage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon myAccIcon = new ImageIcon(accnewing);

        JButton accountButton = new JButton(myAccIcon);
        accountButton.setBounds(200, 200, 50, 50);
        accountButton.addActionListener(e -> frame.switchToPanel(new AccountPanel(frame)));
        add(accountButton);

//        setOpaque(false);
//        moneyTransferButton.setOpaque(false);
//        moneyTransferButton.setContentAreaFilled(false);
//        moneyTransferButton.setBorderPainted(false);
//
//        accountButton.setOpaque(false);
//        accountButton.setContentAreaFilled(false);
//        accountButton.setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}
