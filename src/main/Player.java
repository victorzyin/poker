package main;

import ui.MainFrame;

public class Player {
    private AccountManager accountManager;
    private String username;
    private int gameCoins;

    public Player(String username, AccountManager accountManager) {
        gameCoins = 0;
        this.accountManager = accountManager;
        this.username = username;
    }

    public void joinGame() {
        gameCoins = 200;
        accountManager.addCoins(username, -200);
    }

    public int getGameCoins() {
        return gameCoins;
    }

    public void addGameCoins(int coins) {
        gameCoins += coins;
    }

    public void leaveGame() {
        accountManager.addCoins(username, gameCoins);
    }
}
