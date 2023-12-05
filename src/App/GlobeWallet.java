package App;

import views.MainFrame;

import javax.swing.SwingUtilities;

public class GlobeWallet {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            }
        });
    }
}
