package main;

import ui.MainFrame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class PokerMain {
    public static void main(String[] args) {
        new PokerMain().run();
    }

    public void run() {
        Connection conn;
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("poker.config"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        String host = prop.getProperty("host");
        String database = prop.getProperty("database");
        String username = prop.getProperty("user");
        int port = Integer.parseInt(prop.getProperty("port"));
        int password = Integer.parseInt(prop.getProperty("pass"));
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://" + host + ":"
                    + port + "/" + database, username, password + "");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        AccountManager accountManager = new AccountManager(conn);
        MainFrame mainFrame = new MainFrame(accountManager);
    }
}
