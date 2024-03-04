package gui;


import classiDao.UserDao;
import classiDao.GroupDao;
import controller.Controller;
import gui.home;
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
        setSize(350, 200);

        // Creazione dei componenti
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(new Color(240, 240, 240)); // Colore di sfondo

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBackground(new Color(255, 255, 255)); // Colore di sfondo
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Imposta il font
        usernameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Imposta il font
        passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14)); // Imposta il font

        // Aggiunta dei componenti al pannello di login
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(usernameLabel, gbc);
        gbc.gridy++;
        loginPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        loginPanel.add(usernameField, gbc);
        gbc.gridy++;
        loginPanel.add(passwordField, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        loginPanel.add(loginButton, gbc);

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
                        
                        setVisible(false);
                        controller.loginSuccessful(username);
                        
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
        setLocationRelativeTo(null); 
        setVisible(true);
    }

}
