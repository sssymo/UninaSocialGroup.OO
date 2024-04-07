package gui;

import classi.gruppo;
import classi.notifica;
import classiDao.GroupDao;
import classiDao.NotificaDAO;
import classiDao.UserDao;
import controller.Controller;
import classiDao.richiestaDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class home extends JFrame {

    private int currentUser;
    private GroupDao groupDao;
    private richiestaDAO richiestaDao;
    private NotificaDAO notificationDao;
    private Controller controller;

    public home(int currentUser, String nickname, GroupDao groupDao, NotificaDAO notificationDao, richiestaDAO richiestaDao, Controller controller) {
        this.currentUser = currentUser;
        this.controller = controller;
        this.notificationDao = notificationDao;
        this.richiestaDao = richiestaDao;

        setTitle("Unina Social Network - Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout());

        JPanel bannerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
               
                g.setColor(new Color(60, 92, 156));
                g.fillRect(0, 0, getWidth(), getHeight());
                
                g.setColor(Color.WHITE);
                
                g.setFont(new Font("Georgia", Font.ITALIC, 24));
                FontMetrics fm = g.getFontMetrics();
                String bannerText = "UNINA SOCIAL NETWORK";
                int x = (getWidth() - fm.stringWidth(bannerText)) / 2;
                int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                g.drawString(bannerText, x, y);
            }
        };
        bannerPanel.setPreferredSize(new Dimension(800, 100));
        contentPane.add(bannerPanel, BorderLayout.NORTH);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(new Color(137, 156, 196));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel usernameLabel = new JLabel("Benvenuto " + nickname);
        leftPanel.add(usernameLabel);

        JTextField searchField = new JTextField();
        searchField.setMaximumSize(new Dimension(200, 30));
        leftPanel.add(searchField);

        //icona per il bottone di ricerca , le ridimensiono perchè altrimenti sarebbero enormi
        ImageIcon originalIcon1 = new ImageIcon("./src/img/SearchIcon.png");
     Image img1 = originalIcon1.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
     ImageIcon resizedIcon1 = new ImageIcon(img1);
        JButton searchButton = new JButton(resizedIcon1);
        leftPanel.add(searchButton);

        //icona per visualizza gruppi
        ImageIcon originalIcon2 = new ImageIcon("./src/img/GroupsIcon.png");
        Image img2 = originalIcon2.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(img2);
        JButton groupsButton = new JButton(resizedIcon2);
        leftPanel.add(groupsButton);

        //icona per visualizza notifiche
        ImageIcon originalIcon = new ImageIcon("./src/img/campanellina.png");
     Image img = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
     ImageIcon resizedIcon = new ImageIcon(img);
        JButton notificationsButton = new JButton(resizedIcon);
        
        //icona per tornare indietro
        ImageIcon originalIcon3 = new ImageIcon("./src/img/GoBackIcon.png");
     Image img3 = originalIcon3.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
     ImageIcon resizedIcon3 = new ImageIcon(img3);
        leftPanel.add(notificationsButton);
        JButton backButton = new JButton(resizedIcon3);
     
        leftPanel.add(backButton);
        contentPane.add(leftPanel, BorderLayout.WEST);

        // Pannello per Posts
        JPanel PostPanel = new JPanel();
        PostPanel.setLayout(new BoxLayout(PostPanel, BoxLayout.Y_AXIS));
        PostPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        PostPanel.setBackground(new Color(213,220,233));

        //posts
        List<String> Posts = generatepost();
        for (String Post : Posts) {
            JLabel postLabel = new JLabel(Post);
            PostPanel.add(postLabel);
        }

        JScrollPane postsScrollPane = new JScrollPane(PostPanel);
        postsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        contentPane.add(postsScrollPane, BorderLayout.CENTER);

        
        setContentPane(contentPane);
        setVisible(true);

      
        groupsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<String> gruppi_iscritto = groupDao.getGroupsByUser(currentUser);
                    List<String> gruppi_richiesti = groupDao.getGroupsRequestedByUser(currentUser);

                    if (!gruppi_richiesti.isEmpty() || !gruppi_iscritto.isEmpty()) {
                        JPanel groupPanel = new JPanel(new GridLayout(1, 1));
                        JPanel scrollPanel = new JPanel();
                        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));

                        for (String group : gruppi_richiesti) {

                            JLabel groupNameLabel = new JLabel(group);
                            JLabel groupNameDetails = new JLabel("[Richiesta Inviata]");

                            JPanel groupRow = new JPanel(new FlowLayout(FlowLayout.CENTER));

                            groupRow.add(groupNameLabel);
                            groupRow.add(groupNameDetails);
                            scrollPanel.add(groupRow);
                        }

                        for (String group : gruppi_iscritto) {

                            JLabel groupNameLabel = new JLabel(group);
                            JButton groupNameDetails = new JButton("Accedi");

                            JPanel groupRow = new JPanel(new FlowLayout(FlowLayout.CENTER));

                            groupRow.add(groupNameLabel);
                            groupRow.add(groupNameDetails);
                            scrollPanel.add(groupRow);
                        }

                        JScrollPane scrollPane = new JScrollPane(scrollPanel);
                        scrollPane.setPreferredSize(new Dimension(300, 150));
                        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                        JOptionPane.showMessageDialog(home.this, scrollPane);
                    } else {

                        JOptionPane.showMessageDialog(home.this,
                                "Nessun gruppo trovato");
                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

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
                                    boolean a = false;
                                    try {
                                        a = richiestaDao.insertRichiesta(currentUser, group.getIdGruppo());
                                    } catch (SQLException e1) {
                                        e1.printStackTrace();
                                    }
                                    if (a) {
                                        JOptionPane.showMessageDialog(home.this,
                                                "Richiesta inviata per unirsi al gruppo: " + group.getNomeGruppo());
                                    } else {
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
    }

   
    private List<String> generatepost() {
    	//questo poi va tolto è giusto per un idea
        List<String> posts= new java.util.ArrayList<>();
        posts.add("qui per adesso");
        posts.add("metto stringhe a caso");
        posts.add("ma ci dovrebbero andare i post");
        return posts;
    }
}
