package gui;
import classiDao.UserDao;
import controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class LoginInterface extends JFrame {

    private UserDao userDao;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginInterface(final UserDao userDao, final Controller controller) {
        this.userDao = userDao;

        setTitle("Unina Social Network - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        JPanel contentPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(new Color(155, 10, 222));

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBackground(new Color(222, 240, 255)); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JPanel form=new JPanel(new FlowLayout(FlowLayout.CENTER));
        form.setLayout(new BorderLayout());
        form.setBackground(new Color(255, 83, 22));
        
        

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); 
        
        titlePanel.add(titleLabel);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(titlePanel, gbc);
        
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14)); 
        usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14)); 
        passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14)); 
        
        JButton registerButton = new JButton("Register"); // 
        registerButton.setFont(new Font("Arial", Font.BOLD, 14)); 

       
        gbc.gridx = 0;
        gbc.gridy = 1;
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
        
        form.add(loginPanel);

        contentPane.add(form, BorderLayout.CENTER);
//quando premo invio dopo aver digitato username e password 
//posso accedere senza premere sul tasto invio
        usernameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());

                    try {
                        boolean authenticated = userDao.authenticateUser(username, password);
                        if (authenticated) {
                            JOptionPane.showMessageDialog(LoginInterface.this,
                                    "Login successful! Welcome to Unina Social Network!",
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                            setVisible(false);
                            controller.loginSuccessful(username,password);
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
            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());

                    try {
                        boolean authenticated = userDao.authenticateUser(username, password);
                        if (authenticated) {
                            JOptionPane.showMessageDialog(LoginInterface.this,
                                    "Login successful! Welcome to Unina Social Network!",
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                            setVisible(false);
                            controller.loginSuccessful(username,password);
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
            }
        });
        
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
                        controller.loginSuccessful(username,password);
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
