package ui;

import main.AccountManager;
import main.Player;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {
    private static final String imagePath = "resources/backgrounds/poker_table.jpg";
    private static final String cardPath = "resources/cards/";
    private MainFrame mainFrame;
    private AccountManager accountManager;
    private BufferedImage background;
    private JPanel topPanel, bottomPanel, leftPanel, rightPanel;
    private JLabel topCards, bottomCards, gameCoins;
    private JButton leave, bet, fold, call;
    private JTextField amount;
    private Player player;

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
        topPanel.setLayout(new GridBagLayout());
        topPanel.setBackground(Color.WHITE);
        add(topPanel, BorderLayout.PAGE_START);
        topCards = new JLabel();
        topCards.setIcon(icon);
        topPanel.add(topCards);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());
        bottomPanel.setBackground(Color.WHITE);
        add(bottomPanel, BorderLayout.PAGE_END);
        bottomCards = new JLabel();
        bottomCards.setIcon(icon);
        bottomPanel.add(bottomCards);

        rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout());
        rightPanel.setBackground(Color.WHITE);
        add(rightPanel, BorderLayout.LINE_END);
        leave = new JButton("Leave Game");
        leave.addActionListener(new LeaveListener());
        rightPanel.add(leave);

        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);
        add(leftPanel, BorderLayout.LINE_START);
        gameCoins = new JLabel("Stack: 0");
        amount = new JTextField();
        amount.setColumns(8);
        amount.setMaximumSize(amount.getPreferredSize());
        bet = new JButton("Bet");
        call = new JButton("Call");
        fold = new JButton("Fold");
        leftPanel.add(gameCoins);
        leftPanel.add(amount);
        leftPanel.add(bet);
        leftPanel.add(call);
        leftPanel.add(fold);
    }

    public void setPlayer(Player player) {
        this.player = player;
        gameCoins.setText("Stack: " + player.getGameCoins());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background.getScaledInstance(getWidth(), getHeight(), Image.SCALE_FAST), 0, 0, this);
    }

    class LeaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.leaveGame();
        }
    }
}
