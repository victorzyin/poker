package main;

import ui.MainFrame;

public class BotGame implements Game {
    private AccountManager accountManager;
    private MainFrame mainFrame;
    private Player player;
    private int smallBlind, bigBlind;

    public BotGame(Player player, MainFrame mainFrame, AccountManager accountManager, int smallBlind, int bigBlind) {
        this.player = player;
        this.mainFrame = mainFrame;
        this.accountManager = accountManager;
        this.smallBlind = smallBlind;
        this.bigBlind = bigBlind;
    }

    @Override
    public void start() {

    }

}
