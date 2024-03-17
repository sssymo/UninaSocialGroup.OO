package gui;

import classi.gruppo;
import classi.notifica;
import classiDao.GroupDao;
import classiDao.NotificaDAO;
import controller.Controller;
import classiDao.richiestaDAO;
import classiDao.NotificationDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class home extends JFrame {

    private String currentUser;
    private GroupDao groupDao;
    private richiestaDAO richiestaDao;
    private NotificaDAO notificationDao;
    private Controller controller;

    public home(final String currentUser, GroupDao groupDao, NotificaDAO notificationDao2,richiestaDAO richiestaDao,Controller controller) {
        this.currentUser = currentUser;
        this.controller = controller;
        this.notificationDao = notificationDao2;
        this.richiestaDao = richiestaDao;

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

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText();
                System.out.println("Azione eseguita!"); // Aggiungi una stampa di debug per verificare se l'evento viene catturato

                try {
                    List<gruppo> groups = groupDao.searchGroupByName(searchTerm);
                    if (!groups.isEmpty()) {
                        JPanel groupPanel = new JPanel(new GridLayout(groups.size(), 1));
                        for (gruppo group : groups) {
                            JButton joinButton= new JButton("+");
                            JLabel groupNameLabel = new JLabel(group.getNomeGruppo());

                            joinButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        richiestaDao.insertRichiesta(currentUser, group.getIdGruppo(), LocalDateTime.now());
                                        JOptionPane.showMessageDialog(home.this,
                                                "Richiesta inviata per unirsi al gruppo: " + group.getNomeGruppo());
                                    } catch (SQLException ex) {
                                        ex.printStackTrace();
                                        JOptionPane.showMessageDialog(home.this,
                                                "Si è verificato un errore durante l'invio della richiesta.",
                                                "Errore", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            });

                            JPanel groupRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
                            groupRow.add(joinButton);
                            groupRow.add(groupNameLabel);
                            groupPanel.add(groupRow);
                        }
                        JOptionPane.showMessageDialog(home.this, groupPanel);
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
          
            	List<notifica> notifications = notificationDao2.getAllUserNotifications(currentUser);

                // Visualizza la schermata delle notifiche
                controller.showNotificationsInterface(notifications);
            }
        });

        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setLocationRelativeTo(null); 
        setVisible(true);
    }