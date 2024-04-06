package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        setTitle("Unina Social Network - Registration");
      
        setSize(600, 400);
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JButton returnToLoginButton = new JButton("Ritorna al Login");
        JLabel bioLabel = new JLabel("Bio:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        bioArea = new JTextArea(5, 20);
        JScrollPane bioScrollPane = new JScrollPane(bioArea);
        registerButton = new JButton("Register");

        JPanel contentPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(new Color(155, 10, 222));

        JPanel RegistrationPanel = new JPanel();
        RegistrationPanel.setLayout(new GridBagLayout());
        RegistrationPanel.setBackground(new Color(222, 240, 255)); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); 
        titlePanel.add(titleLabel);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        RegistrationPanel.add(titlePanel, gbc);
       
        
        returnToLoginButton.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                dispose();
                // Torno al login
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
        registerButton.addActionListener(e -> {
           
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String bio = bioArea.getText();
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(RegistrationInterface.this,
                        "Username and password fields are required.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(password.length()<8){
                JOptionPane.showMessageDialog(RegistrationInterface.this,
                        "Password should be at least 8 characters",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }//qui andranno probabilmente aggiunti altri controlli sul formato dell'inputut
            else {
            	//controllo se username esiste già
            	if(userDao.CheckUserAlreadyExistDuringRegistration(username)) {
                    JOptionPane.showMessageDialog(RegistrationInterface.this,
                            "User AlreaAAAd Exist",
                            "Error", JOptionPane.ERROR_MESSAGE);
            		return;
            	}
            	else {
            		//inserimento dei dati
            		Utente u =new Utente(username,2,password,bio);
            		try {
						userDao.salvaUtente(u);
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
                	//back to login interface
                    
                    JOptionPane.showMessageDialog(RegistrationInterface.this,
                            "User registered successfully!",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
            	}
            	
            	
            	

            
            

            }

        });

    
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setContentPane(contentPane);
      
        setLocationRelativeTo(null); 
        setVisible(true);
    }
}
