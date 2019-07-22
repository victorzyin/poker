package ui;

import main.AccountManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {
    private static final String imagePath = "resources/backgrounds/poker_table.jpg";
    private static final String cardPath = "resources/cards/";
    private MainFrame mainFrame;
    private AccountManager accountManager;
    private BufferedImage background;
    private JPanel topPanel, bottomPanel;
    private JLabel topCards, bottomCards;

    public GamePanel(MainFrame mainFrame, AccountManager accountManager) {
        setLayout(new BorderLayout());
        setSize(mainFrame.getSize());
        setVisible(false);

        this.mainFrame = mainFrame;
        this.accountManager = accountManager;
        Image cards;
        try {
            background = ImageIO.read(new File(imagePath));
            cards = ImageIO.read(new File(cardPath + "two_cards.png"));

        } catch (IOException e) {
            throw new RuntimeException("Image file not found for game panel");
        }
        ImageIcon icon = new ImageIcon(cards.getScaledInstance(getWidth() / 10, getHeight() / 10, Image.SCALE_FAST));

        topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        add(topPanel, BorderLayout.PAGE_START);
        topCards = new JLabel();
        topCards.setIcon(icon);
        topPanel.add(topCards, new GridBagLayout());

        bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        add(bottomPanel, BorderLayout.PAGE_END);
        bottomCards = new JLabel();
        bottomCards.setIcon(icon);
        bottomPanel.add(bottomCards, new GridBagLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background.getScaledInstance(getWidth(), getHeight(), Image.SCALE_FAST), 0, 0, this);
    }
}
