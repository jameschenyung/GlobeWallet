package views;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel{
    private MainFrame frame;

    public HomePanel(MainFrame frame) {
        this.frame = frame;
        setLayout(new FlowLayout());

        JButton currencyConverterButton = new JButton("Currency Converter");
        currencyConverterButton.addActionListener(e -> frame.switchToPanel(new CurrencyConverterPanel(frame)));
        add(currencyConverterButton);

        JButton moneyTransferButton = new JButton("Money Transfer");
        moneyTransferButton.addActionListener(e -> frame.switchToPanel(new MoneyTransferPanel(frame)));
        add(moneyTransferButton);

        JButton accountButton = new JButton("My Account");
        accountButton.addActionListener(e -> frame.switchToPanel(new AccountPanel(frame)));
        add(accountButton);
    }
}
