package main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountManager {

    private Connection conn;

    public AccountManager(Connection conn) {
        this.conn = conn;
        createTables();
    }

    public void login(String username) {
        ResultSet accounts = getAccounts();
        try {
            while (accounts.next()) {
                String name = accounts.getString(1);
                if (name.equals(username)) {
                    return;
                }
            }
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO ACCOUNTS VALUES ('" + username + "', 5000);");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public int getCoins(String username) {
        ResultSet accounts = getAccounts();
        try {
            while (accounts.next()) {
                String name = accounts.getString(1);
                if (name.equals(username)) {
                    return accounts.getInt(2);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return -1;
    }

    public void addCoins(String username, int coins) {
        int current = getCoins(username);
        if (current == -1) {
            throw new RuntimeException("Invalid user");
        }
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE ACCOUNTS SET COINS = " + (current + coins) + " WHERE NAME = '" + username + "'");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResultSet getAccounts() {
        ResultSet result;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT * FROM ACCOUNTS;");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    public void createTables() {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ACCOUNTS (NAME TEXT, COINS INT);");
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
