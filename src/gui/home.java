package gui;

import classi.gruppo;
import classi.notifica;
import classiDao.GroupDao;
import classiDao.NotificaDAO;
import classiDao.UserDao;
import controller.Controller;
import classiDao.richiestaDAO;
import classiDao.NotificaDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class home extends JFrame {

    private String currentUser;
    private GroupDao groupDao;
    private richiestaDAO richiestaDao;
    private NotificaDAO notificationDao;
    private Controller controller;

    public home(final String currentUser, String nickname, GroupDao groupDao, NotificaDAO notificationDao2, richiestaDAO richiestaDao, Controller controller) {
        this.currentUser = currentUser;
        this.controller = controller;
        this.notificationDao = notificationDao2;
        this.richiestaDao = richiestaDao;

        setTitle("Unina Social Network - Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        final JTextField searchField = new JTextField();
        JButton searchButton = new JButton("Cerca gruppo");
        JButton groupsButton = new JButton("Visualizza i gruppi");
        JButton notificationsButton = new JButton("Visualizza le notifiche");
        JButton backButton = new JButton("Indietro");

        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        JLabel usernameLabel = new JLabel("Benvenuto " + nickname);

        usernameLabel.setFont(buttonFont);
        searchField.setFont(buttonFont);
        searchButton.setFont(buttonFont);
        groupsButton.setFont(buttonFont);
        backButton.setFont(buttonFont);
        notificationsButton.setFont(buttonFont);
        searchButton.setForeground(Color.WHITE);
        groupsButton.setForeground(Color.WHITE);
        notificationsButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.RED);
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
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.SOUTH;
        contentPane.add(backButton, gbc);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.showLoginInterface();
                dispose();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText();
                System.out.println("Azione eseguita!");

                try {
                    List<gruppo> groups = groupDao.searchGroupByName(searchTerm);
                    if (!groups.isEmpty()) {
                        JPanel groupPanel = new JPanel(new GridLayout(1, 1));
                        JPanel scrollPanel = new JPanel();
                        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));

                        for (gruppo group : groups) {
                            JButton joinButton = new JButton("+");
                            JLabel groupNameLabel = new JLabel(group.getNomeGruppo());

                            joinButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                	//da aggiungere
                                    boolean a = false;
									try {
										a = richiestaDao.insertRichiesta(currentUser, group.getIdGruppo());
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									//to do , pulsante "-" che permetta di fare una delete della richiesta.
                                    if(a==true) {
                                        JOptionPane.showMessageDialog(home.this,
                                                "Richiesta inviata per unirsi al gruppo: " + group.getNomeGruppo());
                                    }
                                    else {
                                        JOptionPane.showMessageDialog(home.this,
                                                "E' stata già inviata una richiesta, attendere l'accettazione da parte del creatore",
                                                "Errore", JOptionPane.ERROR_MESSAGE);
                                   
                                    }

                                }
                            });

                            JPanel groupRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
                            groupRow.add(joinButton);
                            groupRow.add(groupNameLabel);
                            scrollPanel.add(groupRow);
                        }

                        JScrollPane scrollPane = new JScrollPane(scrollPanel);
                        scrollPane.setPreferredSize(new Dimension(150, 200));
                        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                        JOptionPane.showMessageDialog(home.this, scrollPane);
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

        notificationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<notifica> notifications = notificationDao.getAllUserNotifications(currentUser);
                controller.showNotificationsInterface(notifications);
            }
        });

        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
