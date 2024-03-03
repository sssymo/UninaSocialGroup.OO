package gui;


import classiDao.UserDao;
import controller.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginInterface extends JFrame {

    private UserDao userDao;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginInterface(final UserDao userDao, Controller controller) {
        this.userDao = userDao;

        setTitle("Unina Social Network - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        // Creazione dei componenti
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        // Aggiunta dei componenti al pannello di login
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel()); // Spazio vuoto
        loginPanel.add(loginButton);

        contentPane.add(loginPanel, BorderLayout.CENTER);

        // Gestione dell'evento di login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                try {
                    boolean authenticated = userDao.authenticateUser(username, password);
                    if (authenticated) {
                        JOptionPane.showMessageDialog(LoginInterface.this,
                                "Login successful! Welcome to Unina Social Network!",
                                "Success", JOptionPane.INFORMATION_MESSAGE);
                        // Qui potresti aprire la schermata principale dell'app
                    } else {
                        JOptionPane.showMessageDialog(LoginInterface.this,
                                "Invalid username or password. Please try again.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        // Pulisci i campi di input
                        usernameField.setText("");
                        passwordField.setText("");
                    }
                } catch (SQLException ex) {
                    // Gestione dell'eccezione SQLException
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(LoginInterface.this,
                            "An error occurred while attempting to authenticate. Please try again later.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        setContentPane(contentPane);
        setVisible(true);
    }

}
