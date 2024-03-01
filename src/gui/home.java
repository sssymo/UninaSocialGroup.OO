package gui;

import classiDao.GroupDao;
import classiDao.NotificationDao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import controller.Controller;

public class Home extends JFrame {

    private String currentUser;
    private GroupDao groupDao;
    private NotificationDao notificationDao;
    private Controller controller;

    public Home(String currentUser, GroupDao groupDao, NotificationDao notificationDao, Controller controller) {
        this.currentUser = currentUser;
        this.groupDao = groupDao;
        this.notificationDao = notificationDao;
        this.controller = controller;

        setTitle("Unina Social Network - Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(4, 1));

        JTextField searchField = new JTextField();
        JButton searchButton = new JButton("Cerca gruppo");
        JButton groupsButton = new JButton("Visualizza i gruppi");
        JButton notificationsButton = new JButton("Visualizza le notifiche");

        contentPane.add(searchField);
        contentPane.add(searchButton);
        contentPane.add(groupsButton);
        contentPane.add(notificationsButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText();
                try {
                    boolean groupFound = groupDao.searchGroupByName(currentUser, searchTerm);
                    if (groupFound) {
                        JOptionPane.showMessageDialog(Home.this,
                                "Gruppo trovato: " + searchTerm);
                    } else {
                        JOptionPane.showMessageDialog(Home.this,
                                "Nessun gruppo trovato con il nome: " + searchTerm);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(Home.this,
                            "Si è verificato un errore durante la ricerca del gruppo.",
                            "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setContentPane(contentPane);
        setVisible(true);
    }
    //da aggiungere e da spostare nella classe controller quello che succede quando vengono premuti
    //pulsante notfiche e pulsante tutti i gruppi
}
