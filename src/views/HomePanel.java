package views;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import interface_adapter.CurrencyConverter.CurrencyConversionGateway;
import use_case.sendmoneytransfer.SendMoneyOutputBoundary;
import use_case.sendmoneytransfer.SendMoneyUserDataAccessInterface;

/**
 * A JPanel representing the home screen of the application.
 * This panel provides access to various features such as sending and receiving money, and managing user accounts.
 * It displays a background image and buttons for navigating to different parts of the application.
 */
public class HomePanel extends JPanel{
    private MainFrame frame;
    private Image backgroundImage;
    private SendMoneyUserDataAccessInterface sendMoneyUserDataAccess;
    private SendMoneyOutputBoundary sendMoneyOutputBoundary;
    private CurrencyConversionGateway currencyConversionGateway;

    /**
     * Constructs a HomePanel associated with the given MainFrame.
     *
     * @param frame The MainFrame that this panel is a part of.
     */
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

        gbc.gridx = 0;
        gbc.gridy = 0;
        JButton moneyTransferButton = new JButton("Send Money");
        moneyTransferButton.addActionListener(e -> {
            frame.switchToMoneyTransferPanel();
        });
        add(moneyTransferButton, gbc);


        gbc.gridy = 1;
        JButton receiveMoneyButton = new JButton("Receive Money");
        receiveMoneyButton.addActionListener(e -> {
            frame.switchToReceiveMoneyPanel();
        });
        add(receiveMoneyButton, gbc);

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
}
