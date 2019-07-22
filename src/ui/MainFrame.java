package ui;

import main.AccountManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private AccountManager accountManager;
    private LoginPanel login;
    private MenuPanel menu;
    private Dimension screenSize;

    public MainFrame(AccountManager accountManager) {
        this.accountManager = accountManager;
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("Poker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationRelativeTo(null);

        login = new LoginPanel(this, accountManager);
        menu = new MenuPanel(this);

        add(login);
        add(menu);
        setVisible(true);
    }

    public void login(String username) {
        login.setVisible(false);
        menu.login(username, accountManager.getCoins(username));
        menu.setVisible(true);
    }

}
