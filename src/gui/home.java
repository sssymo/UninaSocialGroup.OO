package gui;

import classi.Post;
import classi.gruppo;
import classi.notifica;
import classi.richiesta;
import classiDao.GroupDao;
import classiDao.NotificaDAO;
import classiDao.PostDao;
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
	private JScrollPane scrollPane;
    private int currentUser;
    private GroupDao groupDao;
    private richiestaDAO richiestaDao;
    private NotificaDAO notificationDao;
    private Controller controller;
    private PostDao postDao;

    public home(int currentUser, String nickname, GroupDao groupDao, NotificaDAO notificationDao, richiestaDAO richiestaDao, Controller controller,PostDao postDao) {
        this.currentUser = currentUser;
        this.controller = controller;
        this.notificationDao = notificationDao;
        this.richiestaDao = richiestaDao;
        this.postDao=postDao;

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
        
        
        //questo è per creare gruppi
        ImageIcon originalIcon7 = new ImageIcon("./src/img/add.png");
        Image img7 = originalIcon7.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon7 = new ImageIcon(img7);
           JButton CreateGroupButton = new JButton(resizedIcon7);
           
           CreateGroupButton.setToolTipText("Crea Gruppo");
           //questo è per fargli cambiare colo re quando ci passi sopra col mosue
           CreateGroupButton.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseEntered(java.awt.event.MouseEvent evt) {
            	   CreateGroupButton.setBackground(new Color(200, 200, 200)); // Cambia il colore del pulsante
               }
           });
           CreateGroupButton.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseExited(java.awt.event.MouseEvent evt) {
            	   CreateGroupButton.setBackground(UIManager.getColor("control")); // Rcosì reimpoosto il colore del pulsante al colore di defaudefault
           }
           });
           
           leftPanel.add(CreateGroupButton);

        
        
        

        //icona per visualizza gruppi
        ImageIcon originalIcon2 = new ImageIcon("./src/img/GroupsIcon.png");
        Image img2 = originalIcon2.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(img2);
        JButton groupsButton = new JButton(resizedIcon2);
        groupsButton.setToolTipText("Visualizza Richieste Inviate");
        
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

        //qui gestisco la parte di visualizzazione dei gruppi nella home
        //e quella dicreazione di un nuovo gruppo
        
        try {
            List<gruppo> gruppi_isc = groupDao.getGroupsByUser(currentUser);

            JPanel groupPanelsPanel = new JPanel();
            groupPanelsPanel.setLayout(new BoxLayout(groupPanelsPanel, BoxLayout.Y_AXIS)); // Layout a scatola verticale

            for (gruppo group : gruppi_isc) {
               
                JPanel groupPanel = new JPanel();
                groupPanel.setLayout(new BorderLayout());
                groupPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
                groupPanel.setMaximumSize(new Dimension(2000,120)); 
                JLabel nameLabel = new JLabel(group.getNomeGruppo());
                nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
                groupPanel.add(nameLabel, BorderLayout.NORTH);
                
                JTextArea descriptionArea = new JTextArea(group.getDescrizioneGruppo());
                descriptionArea.setEditable(false);
                descriptionArea.setLineWrap(true);
                descriptionArea.setWrapStyleWord(true);
                JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
                groupPanel.add(descriptionScrollPane, BorderLayout.CENTER);
                
                ImageIcon originalIcon8 = new ImageIcon("./src/img/enter.png");
                Image img8 = originalIcon8.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon8 = new ImageIcon(img8);
                JButton accessButton = new JButton(resizedIcon8);
                accessButton.setToolTipText("Accedi al gruppo");
                accessButton.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                    	accessButton.setBackground(new Color(200, 200, 200)); 
                    }
                });
                accessButton.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                    	accessButton.setBackground(UIManager.getColor("control")); // così reimpoosto il colore del pulsante al colore di defauefault
                }
                });
                accessButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.showGroupInterface(currentUser, group);
                    }
                });
                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                buttonPanel.add(accessButton);
                groupPanel.add(buttonPanel, BorderLayout.SOUTH);
                
                groupPanelsPanel.add(groupPanel);
            }
            
            
            JScrollPane scrollPane = new JScrollPane(groupPanelsPanel);
            contentPane.add(scrollPane, BorderLayout.CENTER);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setContentPane(contentPane);
        setVisible(true);



        //visualizza i gruppi a a cui hai inviato richiesta
        groupsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<gruppo> gruppi_richiesti = groupDao.getGroupsRequestedByUser(currentUser);
                
                if (!gruppi_richiesti.isEmpty()) {
                    JPanel groupPanelsPanel = new JPanel(new GridLayout(0, 1, 10, 10)); // Layout a griglia con 2 colonne e spaziatura di 10 pixel
                    groupPanelsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
                    groupPanelsPanel.setPreferredSize(new Dimension(200, 200));
                    for (gruppo gruppo : gruppi_richiesti) {
                        // Crea un pannello per il singolo gruppo richiesto
                        JPanel groupPanel = new JPanel(new BorderLayout());
                        groupPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
                        
                        // Aggiungi il nome del gruppo
                        JLabel groupNameLabel = new JLabel(gruppo.getNomeGruppo());
                        groupNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
                        groupPanel.add(groupNameLabel, BorderLayout.NORTH);
                        
                        // Aggiungi il dettaglio della richiesta
                        JLabel groupNameDetails = new JLabel("[Richiesta Inviata]");
                        groupPanel.add(groupNameDetails, BorderLayout.CENTER);
                        
                        // Aggiungi il pannello del< gruppo al pannello contenitore
                        groupPanelsPanel.add(groupPanel);
                        JButton deleteButton = new JButton("Cancella richiesta");
                  
                        deleteButton.setSize(100,200);
                        deleteButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent actionEvent) {
                                // Implementa qui la logica per cancellare la richiesta nel backend
								// Utilizza il DAO appropriato per cancellare la richiesta dall'utente corrente per il gruppo specifico
								boolean deleted = richiestaDao.deleteRequest(currentUser, gruppo.getIdGruppo()); // Sostituisci groupId con l'ID del gruppo
								if (deleted) {
									reloadGroups();
								} else {

								}
                            }
                            private void reloadGroups() {
                                groupPanelsPanel.removeAll();
                         
                                List<gruppo> gruppi_richiesti = groupDao.getGroupsRequestedByUser(currentUser);
                                if (gruppi_richiesti.isEmpty()) {
                                    JLabel noRequestsLabel = new JLabel("Nessuna richiesta inviata");
                                    noRequestsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                                    noRequestsLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                    groupPanelsPanel.add(noRequestsLabel, BorderLayout.CENTER);
                                }
                                for (gruppo gruppo : gruppi_richiesti) {
                                    JPanel groupPanel = createGroupPanel(gruppo);
                                    groupPanelsPanel.add(groupPanel);
                                }
                                groupPanelsPanel.revalidate();
                                groupPanelsPanel.repaint();
                            }

                            private JPanel createGroupPanel(gruppo gruppo) {
                                JPanel groupPanel = new JPanel(new BorderLayout());
                                groupPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));

                                JLabel groupNameLabel = new JLabel(gruppo.getNomeGruppo());
                                groupNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
                                groupPanel.add(groupNameLabel, BorderLayout.NORTH);

                                JLabel groupNameDetails = new JLabel("[Richiesta Inviata]");
                                groupPanel.add(groupNameDetails, BorderLayout.CENTER);

                                JButton deleteButton = new JButton("Cancella richiesta");
                                deleteButton.setSize(new Dimension(100, 150));
                                deleteButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent actionEvent) {
                                        boolean deleted = richiestaDao.deleteRequest(currentUser, gruppo.getIdGruppo()); 
                                        if (deleted) {
                                            reloadGroups();
                                        } else {
                                        }
                                    }
                                });
                                groupPanel.add(deleteButton, BorderLayout.EAST);
                            
                                return groupPanel;
                            }
                            });
                        groupPanel.add(deleteButton, BorderLayout.EAST);
                        }
                    
                  JScrollPane scrollPane = new JScrollPane(groupPanelsPanel);
                    scrollPane.setPreferredSize(new Dimension(400, 250));
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    JOptionPane.showMessageDialog(home.this, scrollPane);
                     } else {
                    JOptionPane.showMessageDialog(home.this, "Nessuna richiesta inviata");
                }
            }
        });


        //torna indietro al login
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.showLoginInterface();
                dispose();
            }
        });

        //cerca dei gruppi
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

        //vedi le notifiche, per adesso le uniche notifiche che si vedono sono 
        //quelle di richieste di accesso a gruppi
        notificationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
					List<notifica> notifications = notificationDao.getNotificheForUser(currentUser);
					List<richiesta> RichiesteDiEssereAggiuntoAiTuoiGruppi = richiestaDAO.VediRichiesteDiIscrizioneAiTuoiGruppi(currentUser);
					controller.showNotificationsInterface(notifications,RichiesteDiEssereAggiuntoAiTuoiGruppi);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });

     
        
        //crea gruppo apre un popup dove inserire dati del gruppo da creare
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

    
    
   
}
