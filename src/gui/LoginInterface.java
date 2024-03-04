package gui;
import classiDao.UserDao;
import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        
        JButton registerButton = new JButton("Register"); // Pulsante per registrarsi
        registerButton.setFont(new Font("Arial", Font.BOLD, 14)); // Imposta il font

       
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(usernameLabel, gbc);
        gbc.gridy++;
        loginPanel.add(usernameField, gbc);
        gbc.gridy++;
        loginPanel.add(passwordLabel, gbc);
        gbc.gridy++;
        loginPanel.add(passwordField, gbc);
        gbc.gridwidth = 1;
        gbc.gridy++;
        loginPanel.add(loginButton, gbc);
        gbc.gridx = 1;
        loginPanel.add(registerButton, gbc);

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
                       
                        usernameField.setText("");
                        passwordField.setText("");
                    }
                } catch (SQLException ex) {
                    
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(LoginInterface.this,
                            "An error occurred while attempting to authenticate. Please try again later.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.showRegistrationInterface(); 
            }
        });

        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
