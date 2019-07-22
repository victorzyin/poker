package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuPanel extends JPanel {

    private static final String imagePath = "resources/background_cards.jpg";

    private BufferedImage image;
    private JButton createGame, buyCoins, logout;
    private JLabel username, coins;

    public MenuPanel(JFrame frame) {
        setLayout(new GridBagLayout());
        setSize(frame.getSize());
        createGame = new JButton("Create Game");
        createGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        createGame.addActionListener(new CreateGameListener());
        buyCoins = new JButton("Buy Coins");
        buyCoins.setAlignmentX(Component.CENTER_ALIGNMENT);
        buyCoins.addActionListener(new BuyCoinsListener());
        logout = new JButton("Log Out");
        logout.setAlignmentX(Component.CENTER_ALIGNMENT);
        logout.addActionListener(new LogoutListener());
        add(createGame);
        add(buyCoins);
        add(logout);
        setVisible(false);
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            throw new RuntimeException("Image file not found for menu panel");
        }
    }

    public void login(String username, int coins) {
        this.username = new JLabel("User: " + username + "  ");
        this.username.setForeground(Color.WHITE);
        this.coins = new JLabel("Coins: " + coins + "");
        this.coins.setForeground(Color.WHITE);
        add(this.username);
        add(this.coins);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_FAST), 0, 0, this);
    }

    class CreateGameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }

    }

    class BuyCoinsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }

    }

    class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }

    }

}
