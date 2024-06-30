package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;
import classiDao.UserDao;
import controller.Controller;
import classi.*;
public class RegistrationInterface extends JFrame {

    private UserDao userDao;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextArea bioArea;
    private JButton registerButton;

    public RegistrationInterface(final UserDao userDao, Controller controller) {
        this.userDao = userDao;
        this.userDao = userDao;
        setTitle("Unina Social Network - Registrazione");
        setSize(800, 600);
        ImageIcon IconaFrame = new ImageIcon("./src/img/UNINASOCIALGROPICON.png");
        Image imgIconaFrame = IconaFrame.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        setIconImage(imgIconaFrame);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Georgia", Font.ITALIC, 20));
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Georgia", Font.ITALIC, 20));
        JButton returnToLoginButton = new JButton("Ritorna al Login");
        returnToLoginButton.setToolTipText("Ritorna al Login");
        //questo è per fargli cambiare colo re quando ci passi sopra col mosue
        returnToLoginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	returnToLoginButton.setBackground(new Color(200, 200, 200));
            }
        });
        returnToLoginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	returnToLoginButton.setBackground(UIManager.getColor("control")); // Rcosì reimpoosto il colore del pulsante al colore di defaudefault
        }
        });
        returnToLoginButton.setFont(new Font("Georgia", Font.ITALIC, 20));
        JLabel bioLabel = new JLabel("Bio:");
        bioLabel.setFont(new Font("Georgia", Font.ITALIC, 20));
        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Georgia", Font.ITALIC, 20));
        usernameField.setText("Username");
        usernameField.setForeground(Color.GRAY);
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Georgia", Font.ITALIC, 20));
        passwordField.setText("Password");
        passwordField.setForeground(Color.GRAY);
        bioArea = new JTextArea(5, 20);
        bioArea.setFont(new Font("Georgia", Font.ITALIC, 20));
        bioArea.setText("Inserisci la tua bio :)");
        bioArea.setForeground(Color.GRAY);
        bioArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
          
                if (new String(bioArea.getText()).equals("Inserisci la tua bio :)")) {
                	bioArea.setText("");
                	bioArea.setForeground(Color.BLACK); 
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
       
                if (new String(bioArea.getText()).isEmpty()) {
                	
                	bioArea.setText("Inserisci la tua bio :)");
                    
                	bioArea.setForeground(Color.GRAY); 
                }
            }
        }); 
        
        JScrollPane bioScrollPane = new JScrollPane(bioArea);
        registerButton = new JButton("Registrati");
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
        

        JPanel contentPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(new Color(155, 10, 222));

        JPanel registrationPanel = new JPanel();
        registrationPanel.setLayout(new GridBagLayout());
        registrationPanel.setBackground(new Color(137, 156, 196));

        GridBagConstraints gbcTitle = new GridBagConstraints();
        gbcTitle.insets = new Insets(10, 10, 10, 10);
        gbcTitle.fill = GridBagConstraints.HORIZONTAL;
        gbcTitle.gridx = 0;
        gbcTitle.gridy = 0;
        gbcTitle.gridwidth = 3;

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Registrazione");
        titlePanel.setBackground(new Color(213, 220, 233));
        titleLabel.setFont(new Font("Georgia", Font.ITALIC, 30));
        titlePanel.add(titleLabel);
        registrationPanel.add(titlePanel, gbcTitle);

        returnToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                controller.showLoginInterface();
            }
        });
        GridBagConstraints gbcUsernameLabel = new GridBagConstraints();
        gbcUsernameLabel.insets = new Insets(10, 10, 10, 10);
        gbcUsernameLabel.fill = GridBagConstraints.HORIZONTAL;
        gbcUsernameLabel.gridx = 0;
        gbcUsernameLabel.gridy = 1;
        gbcUsernameLabel.gridwidth = 1;
        registrationPanel.add(usernameLabel, gbcUsernameLabel);

        GridBagConstraints gbcUsernameField = new GridBagConstraints();
        gbcUsernameField.insets = new Insets(10, 10, 10, 10);
        gbcUsernameField.fill = GridBagConstraints.HORIZONTAL;
        gbcUsernameField.gridx = 1;
        gbcUsernameField.gridy = 1;
        gbcUsernameField.gridwidth = 1;
        registrationPanel.add(usernameField, gbcUsernameField);

        GridBagConstraints gbcPasswordLabel = new GridBagConstraints();
        gbcPasswordLabel.insets = new Insets(10, 10, 10, 10);
        gbcPasswordLabel.fill = GridBagConstraints.HORIZONTAL;
        gbcPasswordLabel.gridx = 0;
        gbcPasswordLabel.gridy = 2;
        gbcPasswordLabel.gridwidth = 1;
        registrationPanel.add(passwordLabel, gbcPasswordLabel);

        GridBagConstraints gbcPasswordField = new GridBagConstraints();
        gbcPasswordField.insets = new Insets(10, 10, 10, 10);
        gbcPasswordField.fill = GridBagConstraints.HORIZONTAL;
        gbcPasswordField.gridx = 1;
        gbcPasswordField.gridy = 2;
        gbcPasswordField.gridwidth = 1;
        registrationPanel.add(passwordField, gbcPasswordField);

        GridBagConstraints gbcBioLabel = new GridBagConstraints();
        gbcBioLabel.insets = new Insets(10, 10, 10, 10);
        gbcBioLabel.fill = GridBagConstraints.HORIZONTAL;
        gbcBioLabel.gridx = 0;
        gbcBioLabel.gridy = 3;
        gbcBioLabel.gridwidth = 1;
        registrationPanel.add(bioLabel, gbcBioLabel);

        GridBagConstraints gbcBioScrollPane = new GridBagConstraints();
        gbcBioScrollPane.insets = new Insets(10, 10, 10, 10);
        gbcBioScrollPane.fill = GridBagConstraints.HORIZONTAL;
        gbcBioScrollPane.gridx = 1;
        gbcBioScrollPane.gridy = 3;
        gbcBioScrollPane.gridwidth = 1;
        registrationPanel.add(bioScrollPane, gbcBioScrollPane);

        GridBagConstraints gbcRegisterButton = new GridBagConstraints();
        gbcRegisterButton.insets = new Insets(10, 10, 10, 10);
        gbcRegisterButton.fill = GridBagConstraints.HORIZONTAL;
        gbcRegisterButton.gridx = 0;
        gbcRegisterButton.gridy = 4;
        gbcRegisterButton.gridwidth = 2;
        registrationPanel.add(registerButton, gbcRegisterButton);

        GridBagConstraints gbcReturnToLoginButton = new GridBagConstraints();
        gbcReturnToLoginButton.insets = new Insets(10, 10, 10, 10);
        gbcReturnToLoginButton.fill = GridBagConstraints.HORIZONTAL;
        gbcReturnToLoginButton.gridx = 0;
        gbcReturnToLoginButton.gridy = 5;
        gbcReturnToLoginButton.gridwidth = 2;
        registrationPanel.add(returnToLoginButton, gbcReturnToLoginButton);

        contentPane.add(registrationPanel);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String bio = bioArea.getText();
                if (username.isEmpty() || password.isEmpty() || username.contains("Username")) {
                    JOptionPane.showMessageDialog(RegistrationInterface.this,
                            "Username and password fields are required.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (password.length() < 8) {
                    JOptionPane.showMessageDialog(RegistrationInterface.this,
                            "Password should be at least 8 characters",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    if (userDao.CheckUserAlreadyExistDuringRegistration(username)) {
                        JOptionPane.showMessageDialog(RegistrationInterface.this,
                                "User Already Exists",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                    	//2 è un id messo in generale, poi il db si occuperà di assegnarlo
                        Utente u = new Utente(username, 2, password, bio);
                        try {
                            userDao.salvaUtente(u);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(RegistrationInterface.this,
                                "User registered successfully!",
                                "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}