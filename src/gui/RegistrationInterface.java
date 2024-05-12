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
        setTitle("Unina Social Network - Registrazione");
        setSize(800,600); 
        ImageIcon IconaFrame = new ImageIcon("./src/img/UNINASOCIALGROPICON.png");
        Image imgIconaFrame = IconaFrame.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        setIconImage(imgIconaFrame);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Georgia", Font.ITALIC, 20)); 
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Georgia", Font.ITALIC, 20)); 
        JButton returnToLoginButton = new JButton("Ritorna al Login");
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
                // Rimuovi il testo del placeholder quando il campo ottiene il focus
                if (new String(bioArea.getText()).equals("Inserisci la tua bio :)")) {
                	bioArea.setText("");
                	bioArea.setForeground(Color.BLACK); // Ripristina il colore del testo normale
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Aggiungi di nuovo il testo del placeholder se il campo è vuoto e perde il focus
                if (new String(bioArea.getText()).isEmpty()) {
                	
                	bioArea.setText("Inserisci la tua bio :)");
                    
                	bioArea.setForeground(Color.GRAY); // Imposta di nuovo il colore del testo del placeholder
                }
            }
        }); 
        
        JScrollPane bioScrollPane = new JScrollPane(bioArea);
        registerButton = new JButton("Registrati");
        registerButton.setFont(new Font("Georgia", Font.ITALIC, 20)); 
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Rimuovi il testo del placeholder quando il campo ottiene il focus
                if (new String(passwordField.getPassword()).equals("Password")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK); // Ripristina il colore del testo normale
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Aggiungi di nuovo il testo del placeholder se il campo è vuoto e perde il focus
                if (new String(passwordField.getPassword()).isEmpty()) {
                	passwordField.setVisible(true);
                	passwordField.setText("Password");
                    
                    passwordField.setForeground(Color.GRAY); // Imposta di nuovo il colore del testo del placeholder
                }
            }
        }); 
 
        usernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Rimuovi il testo del placeholder quando il campo ottiene il focus
                if (usernameField.getText().equals("Username")) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.BLACK); // Ripristina il colore del testo normale
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Aggiungi di nuovo il testo del placeholder se il campo è vuoto e perde il focus
                if (usernameField.getText().isEmpty()) {
                    usernameField.setText("Username");
                    usernameField.setForeground(Color.GRAY); // Imposta di nuovo il colore del testo del placeholder
                }
            }


        });
        

        JPanel contentPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(new Color(155, 10, 222));

        JPanel RegistrationPanel = new JPanel();
        RegistrationPanel.setLayout(new GridBagLayout());
        RegistrationPanel.setBackground(new Color(137, 156, 196));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Registrazione");
        titlePanel.setBackground(new Color(213, 220, 233));
        titleLabel.setFont(new Font("Georgia", Font.ITALIC, 30)); 
        titlePanel.add(titleLabel);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        RegistrationPanel.add(titlePanel, gbc);

        returnToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                controller.showLoginInterface();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        RegistrationPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        RegistrationPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        RegistrationPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        RegistrationPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        RegistrationPanel.add(bioLabel, gbc);
        gbc.gridx = 1;
        RegistrationPanel.add(bioScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        RegistrationPanel.add(registerButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        RegistrationPanel.add(returnToLoginButton, gbc);
        contentPane.add(RegistrationPanel);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String bio = bioArea.getText();
                if (username.isEmpty() || password.isEmpty()) {
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