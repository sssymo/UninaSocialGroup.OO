package gui;
import classiDao.UserDao;
import controller.Controller;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class LoginInterface extends JFrame {


    private JTextField usernameField;
    private JPasswordField passwordField;
    private static final long serialVersionUID = 1L;
    public LoginInterface(final UserDao userDao, final Controller controller) {




    	setTitle("Unina Social Network - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        ImageIcon IconaFrame = new ImageIcon("./src/img/UNINASOCIALGROPICON.png");
        Image imgIconaFrame = IconaFrame.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        setIconImage(imgIconaFrame);
        JPanel contentPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(new Color(137, 156, 196));

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBackground(new Color(137, 156, 196));

        JPanel form = new JPanel(new FlowLayout(FlowLayout.CENTER));
        form.setLayout(new BorderLayout());
        form.setBackground(new Color(137, 156, 196));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(new Color(213, 220, 233));
        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Georgia", Font.ITALIC, 30));
        titlePanel.add(titleLabel);

        GridBagConstraints gbcTitle = new GridBagConstraints();
        gbcTitle.insets = new Insets(10, 10, 10, 10);
        gbcTitle.fill = GridBagConstraints.HORIZONTAL;
        gbcTitle.gridx = 0;
        gbcTitle.gridy = 0;
        gbcTitle.gridwidth = 2;
        loginPanel.add(titlePanel, gbcTitle);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Georgia", Font.ITALIC, 20));
        GridBagConstraints gbcUsernameLabel = new GridBagConstraints();
        gbcUsernameLabel.insets = new Insets(10, 10, 10, 10);
        gbcUsernameLabel.fill = GridBagConstraints.HORIZONTAL;
        gbcUsernameLabel.gridx = 0;
        gbcUsernameLabel.gridy = 1;
        gbcUsernameLabel.gridwidth = 2;
        loginPanel.add(usernameLabel, gbcUsernameLabel);

        usernameField = new JTextField(20);
        usernameField.setText("Username");
        usernameField.setForeground(Color.GRAY);
        usernameField.setFont(new Font("Georgia", Font.ITALIC, 20));
            usernameField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                  
                    if (usernameField.getText().equals("Username")) {
                        usernameField.setText("");
                        usernameField.setForeground(Color.BLACK); 
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
              
                    if (usernameField.getText().isEmpty()) {
                        usernameField.setText("Username");
                        usernameField.setForeground(Color.GRAY); 
                    }
                }


            });
            
            GridBagConstraints gbcUsernameField = new GridBagConstraints();
            gbcUsernameField.insets = new Insets(10, 10, 10, 10);
            gbcUsernameField.fill = GridBagConstraints.HORIZONTAL;
            gbcUsernameField.gridx = 0;
            gbcUsernameField.gridy = 2;
            gbcUsernameField.gridwidth = 2;
            loginPanel.add(usernameField, gbcUsernameField);

            JLabel passwordLabel = new JLabel("Password:");
            passwordLabel.setFont(new Font("Georgia", Font.ITALIC, 20));
            GridBagConstraints gbcPasswordLabel = new GridBagConstraints();
            gbcPasswordLabel.insets = new Insets(10, 10, 10, 10);
            gbcPasswordLabel.fill = GridBagConstraints.HORIZONTAL;
            gbcPasswordLabel.gridx = 0;
            gbcPasswordLabel.gridy = 3;
            gbcPasswordLabel.gridwidth = 2;
            loginPanel.add(passwordLabel, gbcPasswordLabel);

            passwordField = new JPasswordField(20);
            passwordField.setText("Password");
            passwordField.setForeground(Color.GRAY);
            passwordField.setFont(new Font("Georgia", Font.ITALIC, 20));
            passwordField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
              
                    if (new String(passwordField.getPassword()).equals("Password")) {
                        passwordField.setText("");
                        passwordField.setForeground(Color.BLACK); 
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                  
                    if (new String(passwordField.getPassword()).isEmpty()) {
                    	passwordField.setVisible(true);
                    	passwordField.setText("Password");
                        
                        passwordField.setForeground(Color.GRAY);
                    }
                }
            }); 
  
            GridBagConstraints gbcPasswordField = new GridBagConstraints();
            gbcPasswordField.insets = new Insets(10, 10, 10, 10);
            gbcPasswordField.fill = GridBagConstraints.HORIZONTAL;
            gbcPasswordField.gridx = 0;
            gbcPasswordField.gridy = 4;
            gbcPasswordField.gridwidth = 2;
            loginPanel.add(passwordField, gbcPasswordField);

            JButton loginButton = new JButton("Accedi");
            loginButton.setToolTipText("Accedi");
            //questo è per fargli cambiare colo re quando ci passi sopra col mosue
            loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                	loginButton.setBackground(new Color(200, 200, 200));
                }
            });
            loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseExited(java.awt.event.MouseEvent evt) {
                	loginButton.setBackground(UIManager.getColor("control")); // Rcosì reimpoosto il colore del pulsante al colore di defaudefault
            }
            });
            loginButton.setFont(new Font("Georgia", Font.ITALIC, 20));
            GridBagConstraints gbcLoginButton = new GridBagConstraints();
            gbcLoginButton.insets = new Insets(10, 10, 10, 10);
            gbcLoginButton.fill = GridBagConstraints.HORIZONTAL;
            gbcLoginButton.gridx = 0;
            gbcLoginButton.gridy = 5;
            gbcLoginButton.gridwidth = 1;
            loginPanel.add(loginButton, gbcLoginButton);

            JButton registerButton = new JButton("Registrati");
            registerButton.setToolTipText("Registrati");
            //questo è per fargli cambiare colo re quando ci passi sopra col mosue
            registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                	registerButton.setBackground(new Color(200, 200, 200));
                }
            });
            registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseExited(java.awt.event.MouseEvent evt) {
                	registerButton.setBackground(UIManager.getColor("control")); // Rcosì reimpoosto il colore del pulsante al colore di defaudefault
            }
            });
            registerButton.setFont(new Font("Georgia", Font.ITALIC, 20));
            GridBagConstraints gbcRegisterButton = new GridBagConstraints();
            gbcRegisterButton.insets = new Insets(10, 10, 10, 10);
            gbcRegisterButton.fill = GridBagConstraints.HORIZONTAL;
            gbcRegisterButton.gridx = 1;
            gbcRegisterButton.gridy = 5;
            loginPanel.add(registerButton, gbcRegisterButton);

            form.add(loginPanel);
            contentPane.add(form, BorderLayout.CENTER);

//quando premo invio dopo aver digitato username e password 
//posso accedere senza premere sul tasto invio, la stessa cosa andrebbe fatta con la registrazione
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
