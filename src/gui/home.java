package gui;
import classiDao.GroupDao;
<<<<<<< HEAD
import classiDao.NotificaDAO;
import controller.Controller;
import classi.notifica;
=======
import classiDao.NotificationDao;
import controller.Controller;

>>>>>>> branch 'main' of https://github.com/sssymo/UninaSocialGroup.OO.git
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class home extends JFrame {

    private String currentUser;
    private GroupDao groupDao;
    private NotificaDAO notificationDao;
    private Controller controller;

    public home(final String currentUser, final GroupDao groupDao, NotificaDAO notificationDao, Controller controller) {
        this.currentUser = currentUser;
        this.groupDao = groupDao;
        this.notificationDao = notificationDao;
        this.controller = controller;

        setTitle("Unina Social Network - Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Centra la finestra

        JPanel contentPane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        final JTextField searchField = new JTextField();
        JButton searchButton = new JButton("Cerca gruppo");
        JButton groupsButton = new JButton("Visualizza i gruppi");
        JButton notificationsButton = new JButton("Visualizza le notifiche");

        // Imposta il font e il colore dei componenti
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
<<<<<<< HEAD
        JLabel usernameLabel = new JLabel("Benvenuto " + currentUser);
        searchField.setFont(buttonFont);
        searchButton.setFont(buttonFont);
        groupsButton.setFont(buttonFont);
        notificationsButton.setFont(buttonFont);
        searchButton.setForeground(Color.WHITE);
        groupsButton.setForeground(Color.WHITE);
        notificationsButton.setForeground(Color.WHITE);
        searchButton.setBackground(new Color(88, 10, 180));
        groupsButton.setBackground(new Color(88, 10, 180));
        notificationsButton.setBackground(new Color(88, 10, 180));

        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(usernameLabel, gbc);
        gbc.gridy++;
        contentPane.add(searchField, gbc);

        gbc.gridy++;
        contentPane.add(searchButton, gbc);

        gbc.gridy++;
        contentPane.add(groupsButton, gbc);

        gbc.gridy++;
        contentPane.add(notificationsButton, gbc);

=======
        JLabel usernameLabel = new JLabel("Benvenuto "+currentUser);
        searchField.setFont(buttonFont);
        searchButton.setFont(buttonFont);
        groupsButton.setFont(buttonFont);
        notificationsButton.setFont(buttonFont);
        searchButton.setForeground(Color.WHITE);
        groupsButton.setForeground(Color.WHITE);
        notificationsButton.setForeground(Color.WHITE);
        searchButton.setBackground(new Color(88, 10, 180)); 
        groupsButton.setBackground(new Color(88, 10, 180)); 
        notificationsButton.setBackground(new Color(88, 10, 180)); 
>>>>>>> branch 'main' of https://github.com/sssymo/UninaSocialGroup.OO.git

  
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(usernameLabel,gbc);
        gbc.gridy++;
        contentPane.add(searchField, gbc);

        gbc.gridy++;
        contentPane.add(searchButton, gbc);

        gbc.gridy++;
        contentPane.add(groupsButton, gbc);

        gbc.gridy++;
        contentPane.add(notificationsButton, gbc);

    
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText();
                System.out.println("Azione eseguita!"); // Aggiungi una stampa di debug per verificare se l'evento viene catturato

                try {
                    boolean groupFound = groupDao.searchGroupByName(currentUser, searchTerm);
                    if (groupFound) {
                        JOptionPane.showMessageDialog(home.this,
                                "Gruppo trovato: " + searchTerm);
                    } else {
                        JOptionPane.showMessageDialog(home.this,
                                "Nessun gruppo trovato con il nome: " + searchTerm);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(home.this,
                            "Si è verificato un errore durante la ricerca del gruppo.",
                            "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Aggiungi ActionListener per il pulsante delle notifiche
        notificationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Recupera le notifiche per l'utente corrente
                List<notifica> notifications = notificationDao.getNotificheForUser(currentUser);

                // Visualizza la schermata delle notifiche
                controller.showNotificationsInterface(notifications);
            }
        });

        setContentPane(contentPane);
<<<<<<< HEAD
        setLocationRelativeTo(null);
=======
        setLocationRelativeTo(null); 
>>>>>>> branch 'main' of https://github.com/sssymo/UninaSocialGroup.OO.git
        setVisible(true);
    }
}
