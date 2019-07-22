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

public class LoginPanel extends JPanel {
    private static final String imagePath = "resources/backgrounds/background_cards.jpg";
    private MainFrame mainFrame;
    private AccountManager accountManager;
    private BufferedImage background;
    private JButton login;
    private JTextField username;

    public LoginPanel(MainFrame mainFrame, AccountManager accountManager) {
        setSize(mainFrame.getSize());
        setVisible(true);
        setLayout(new GridBagLayout());

        username = new JTextField();
        username.setColumns(10);

        login = new JButton("login");
        login.addActionListener(new LoginListener());

        add(username);
        add(login);

        this.accountManager = accountManager;
        this.mainFrame = mainFrame;
        try {
            background = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            throw new RuntimeException("Image file not found for login panel");
        }
    }

    public void clearText() {
        username.setText("");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background.getScaledInstance(getWidth(), getHeight(), Image.SCALE_FAST), 0, 0, this);
    }

    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            accountManager.login(username.getText().toLowerCase());
            mainFrame.login(username.getText().toLowerCase());
        }
    }
}
