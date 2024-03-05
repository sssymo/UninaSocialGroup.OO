package gui;
import java.awt.GridLayout;
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

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel bioLabel = new JLabel("Bio:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        bioArea = new JTextArea(5, 20);
        JScrollPane bioScrollPane = new JScrollPane(bioArea);
        registerButton = new JButton("Register");

        // Impostazione del layout GridBagLayout
        JPanel contentPane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Aggiunta dei componenti al pannello con GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(usernameLabel, gbc);
        gbc.gridy++;
        contentPane.add(passwordLabel, gbc);
        gbc.gridy++;
        contentPane.add(bioLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentPane.add(usernameField, gbc);
        gbc.gridy++;
        contentPane.add(passwordField, gbc);
        gbc.gridy++;
        contentPane.add(bioScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        contentPane.add(registerButton, gbc);
        gbc.gridy++;

       
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

        // Impostazione delle proprietà della finestra
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("User Registration");
        setContentPane(contentPane);
        pack();
        setLocationRelativeTo(null); // Posiziona la finestra al centro dello schermo
        setVisible(true);
    }
}
