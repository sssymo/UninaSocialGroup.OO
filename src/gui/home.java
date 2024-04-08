package gui;

import classi.gruppo;
import classi.notifica;
import classi.richiesta;
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
                
                g.setColor(new Color(213,220,233));
                
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
        usernameLabel.setFont(new Font("Georgia",Font.ITALIC,12));
        leftPanel.add(usernameLabel);

        JTextField searchField = new JTextField();
        searchField.setMaximumSize(new Dimension(200, 30));
        leftPanel.add(searchField);

        //icona per il bottone di ricerca , le ridimensiono perchè altrimenti sarebbero enormi
        ImageIcon originalIcon1 = new ImageIcon("./src/img/SearchIcon.png");
     Image img1 = originalIcon1.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
     ImageIcon resizedIcon1 = new ImageIcon(img1);
        JButton searchButton = new JButton(resizedIcon1);
        searchButton.setToolTipText("Cerca Gruppi");
        //questo è per fargli cambiare colo re quando ci passi sopra col mosue
        searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchButton.setBackground(new Color(200, 200, 200)); // Cambia il colore del pulsante
            }
        });
        searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchButton.setBackground(UIManager.getColor("control")); // Rcosì reimpoosto il colore del pulsante al colore di defaudefault
        }
        });
        
        leftPanel.add(searchButton);

        //icona per visualizza gruppi
        ImageIcon originalIcon2 = new ImageIcon("./src/img/GroupsIcon.png");
        Image img2 = originalIcon2.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(img2);
        JButton groupsButton = new JButton(resizedIcon2);
        groupsButton.setToolTipText("Visualizza Gruppi");
        
        leftPanel.add(groupsButton);

        groupsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	groupsButton.setBackground(new Color(200, 200, 200)); // Cambio il colore del pulsante
            }
        });
        groupsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	groupsButton.setBackground(UIManager.getColor("control")); // così reimpoosto il colore del pulsante al colore di default
        }
        });
        
        //icona per visualizza notifiche
        ImageIcon originalIcon = new ImageIcon("./src/img/campanellina.png");
     Image img = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
     ImageIcon resizedIcon = new ImageIcon(img);
        JButton notificationsButton = new JButton(resizedIcon);
        notificationsButton.setToolTipText("Visualizza Notifiche");
        notificationsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	notificationsButton.setBackground(new Color(200, 200, 200)); // Cambia il colore del pulsante
            }
        });
        notificationsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	notificationsButton.setBackground(UIManager.getColor("control")); // così reimpoosto il colore del pulsante al colore di defauefault
        }
        });
        
        
        
        //icona per tornare indietro
        ImageIcon originalIcon3 = new ImageIcon("./src/img/GoBackIcon.png");
     Image img3 = originalIcon3.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
     ImageIcon resizedIcon3 = new ImageIcon(img3);
     
        leftPanel.add(notificationsButton);
        JButton backButton = new JButton(resizedIcon3);
        backButton.setToolTipText("Torna Indietro");
     
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	backButton.setBackground(new Color(200, 200, 200)); // Cambia il colore del pulsante
            }
        });
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	backButton.setBackground(UIManager.getColor("control")); // così reimpoosto il colore del pulsante al colore di defaudefault
        }
        });
        
        leftPanel.add(backButton);
        contentPane.add(leftPanel, BorderLayout.WEST);

        // Pannello per Posts
        JPanel PostPanel = new JPanel();
        PostPanel.setLayout(new BoxLayout(PostPanel, BoxLayout.Y_AXIS));
        PostPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        PostPanel.setBackground(new Color(213,220,233));
        
        JPanel createPostPanel = new JPanel();
        createPostPanel.setBackground(new Color(213,220,233));
        createPostPanel.setLayout(new BoxLayout(createPostPanel, BoxLayout.X_AXIS));
        createPostPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Allinea a sinistra i componenti

        //questo è per concedere la possibilità all'utente 
        //di creare un gruppo direttamente dalla home
        JButton CreateGroupButton =new JButton("Crea Gruppo");
        createPostPanel.add(CreateGroupButton);
        createPostPanel.add(Box.createHorizontalStrut(10));
        
        //(createPostButton)una volta digitato il testo e premuto invio
        //l'idea è quella di aprire  un altro pannello
        //con un menu a tendina dove si possa scegliere 
        //il gruppo, oppure magari inserrire il menu
        //a tendina esattamente di fianco 
        //(da implementare ci sarebbe anche la possibilità di usare emoji, ma non penso 
        //sia possibile)
        
        JButton createPostButton = new JButton("Crea Nuovo Post");
        createPostPanel.add(createPostButton);
        createPostPanel.add(Box.createHorizontalStrut(10));

        JTextField textField = new JTextField();
        textField.setMaximumSize(new Dimension(200, 30));
        createPostPanel.add(textField);

        PostPanel.add(createPostPanel);
        PostPanel.add(Box.createVerticalStrut(20));
        
        //posts
        List<String> Posts = generatepost();
        
        for (String Post : Posts) {
            JLabel postLabel = new JLabel(Post);
            postLabel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(140,164,196), 4), // Bordo nero sottile
                    BorderFactory.createEmptyBorder(30, 10, 30, 10) // Margine interno
                ));
            
            
            PostPanel.add(postLabel);
            PostPanel.add(Box.createVerticalStrut(40));
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
            	try {
					List<notifica> notifications = notificationDao.getAllUserNotifications(currentUser);
					List<richiesta> RichiesteDiEssereAggiuntoAiTuoiGruppi = richiestaDAO.VediRichiesteDiIscrizioneAiTuoiGruppi(currentUser);
					controller.showNotificationsInterface(notifications,RichiesteDiEssereAggiuntoAiTuoiGruppi);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        
        CreateGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//manca da gestire la questione tag
                JPanel dialogPanel = new JPanel(new GridBagLayout());
                
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.anchor = GridBagConstraints.WEST;

                JTextField groupNameField = new JTextField();
                groupNameField.setPreferredSize(new Dimension(150, 30));
                dialogPanel.add(new JLabel("Nome del Gruppo:"), gbc);
                gbc.gridy++;
                dialogPanel.add(groupNameField, gbc);

                gbc.gridy++;
                dialogPanel.add(new JLabel("Descrizione:"), gbc);
                gbc.gridy++;
                gbc.fill = GridBagConstraints.BOTH;
                gbc.weightx = 1.0;
                gbc.weighty = 1.0;
                JTextArea descriptionArea = new JTextArea();
                descriptionArea.setPreferredSize(new Dimension(150, 50));
                descriptionArea.setLineWrap(true);
                descriptionArea.setWrapStyleWord(true);
                JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
             
                dialogPanel.add(descriptionScrollPane, gbc);
               

                int result = JOptionPane.showConfirmDialog(home.this, dialogPanel,
                        "Crea Nuovo Gruppo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    String groupName = groupNameField.getText();
                    String description = descriptionArea.getText();

                    try {
                        groupDao.CreateGroup(groupName, description, currentUser);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
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
