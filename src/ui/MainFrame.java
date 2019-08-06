package ui;

import main.AccountManager;
import main.BotGame;
import main.Game;
import main.Player;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;


public class MainFrame extends JFrame {
    private AccountManager accountManager;
    private Player player;
    private LoginPanel loginPanel;
    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private Dimension screenSize;
    private Game game;

    public MainFrame(AccountManager accountManager) {
        this.accountManager = accountManager;
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("Poker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationRelativeTo(null);

        loginPanel = new LoginPanel(this, accountManager);
        menuPanel = new MenuPanel(this, accountManager);
        gamePanel = new GamePanel(this, accountManager);

        add(loginPanel);
        add(menuPanel);
        add(gamePanel);
        setVisible(true);
    }

    public void login(String username) {
        player = new Player(username, accountManager);
        loginPanel.setVisible(false);
        menuPanel.login(username, accountManager.getCoins(username));
        menuPanel.setVisible(true);
    }

    public void logout() {
        menuPanel.setVisible(false);
        loginPanel.clearText();
        loginPanel.setVisible(true);
    }

    public void joinGame() {
        menuPanel.setVisible(false);
        player.joinGame();
        gamePanel.setPlayer(player);
        gamePanel.setVisible(true);
        game = new BotGame(player, this, accountManager, 1, 2);
    }

    public void leaveGame() {
        gamePanel.setVisible(false);
        menuPanel.updateCoins();
        player.leaveGame();
        menuPanel.setVisible(true);
    }

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
