package ui;

import main.AccountManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuPanel extends JPanel {
    private static final String imagePath = "resources/backgrounds/background_cards.jpg";

    private AccountManager accountManager;
    private BufferedImage background;
    private MainFrame mainFrame;
    private JButton joinGame, buyCoins, logout;
    private JLabel usernameLabel, coinsLabel;
    private String username;

    public MenuPanel(MainFrame mainFrame, AccountManager accountManager) {
        setLayout(new GridBagLayout());
        setSize(mainFrame.getSize());
        joinGame = new JButton("Join Game");
        joinGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        joinGame.addActionListener(new JoinGameListener());
        buyCoins = new JButton("Buy Coins");
        buyCoins.setAlignmentX(Component.CENTER_ALIGNMENT);
        buyCoins.addActionListener(new BuyCoinsListener());
        logout = new JButton("Log Out");
        logout.setAlignmentX(Component.CENTER_ALIGNMENT);
        logout.addActionListener(new LogoutListener());
        add(joinGame);
        add(buyCoins);
        add(logout);
        setVisible(false);
        this.accountManager = accountManager;
        this.mainFrame = mainFrame;
        try {
            background = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            throw new RuntimeException("Image file not found for menu panel");
        }
    }

    public void login(String username, int coins) {
        this.username = username;
        usernameLabel = new JLabel("User: " + username + "  ");
        usernameLabel.setForeground(Color.WHITE);
        coinsLabel = new JLabel("Coins: " + coins + "");
        coinsLabel.setForeground(Color.WHITE);
        add(usernameLabel);
        add(coinsLabel);
    }

    public void updateCoins() {
        coinsLabel.setText("Coins: " + accountManager.getCoins(username) + "");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background.getScaledInstance(getWidth(), getHeight(), Image.SCALE_FAST), 0, 0, this);
    }

    class JoinGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (accountManager.getCoins(username) >= 200) {
                mainFrame.joinGame();
            }
        }
    }

    class BuyCoinsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            accountManager.addCoins(username, 100);
            updateCoins();
        }
    }

    class LogoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            remove(usernameLabel);
            remove(coinsLabel);
            mainFrame.logout();
        }
    }
}
