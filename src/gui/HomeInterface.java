package gui;

import classi.Post;
import classi.Gruppo;
import classi.Notifica;
import classi.Richiesta;
import classiDao.GroupDao;
import classiDao.NotificaDAO;
import classiDao.PostDao;
import classiDao.TagDao;
import classiDao.UserDao;
import controller.Controller;
import classiDao.RichiestaDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

public class HomeInterface extends JFrame {
	private JScrollPane scrollPane;
    private int currentUser;
    private GroupDao groupDao;
    private RichiestaDAO richiestaDAO;
    private NotificaDAO notificationDao;
    private Controller controller;
    private PostDao postDao;
    JPanel groupPanelsPanel = new JPanel();
private TagDao tagdao;
JTextField searchField2 = new JTextField(30);
    public HomeInterface(int currentUser, String nickname, GroupDao groupDao, NotificaDAO notificationDao, RichiestaDAO richiestaDAO, Controller controller,PostDao postDao,TagDao tagdao) {
        this.currentUser = currentUser;
        this.controller = controller;
        this.notificationDao = notificationDao;
        this.richiestaDAO = richiestaDAO;
        this.tagdao=tagdao;
        this.postDao=postDao;

        setTitle("Unina Social Network - Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        ImageIcon IconaFrame = new ImageIcon("./src/img/UNINASOCIALGROPICON.png");
     Image imgIconaFrame = IconaFrame.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        setIconImage(imgIconaFrame);
        setLocationRelativeTo(null);
searchField2.setText("cerca gruppi per nome, tag e descrizione. ");
searchField2.setForeground(Color.GRAY);
searchField2.addFocusListener(new FocusListener() {
    @Override
    public void focusGained(FocusEvent e) {

        if (searchField2.getText().equals("cerca gruppi per nome, tag e descrizione. ")) {
            searchField2.setText("");
            searchField2.setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
       
        if (searchField2.getText().isEmpty()) {
            searchField2.setText("cerca gruppi per nome, tag e descrizione. ");
            searchField2.setForeground(Color.GRAY); 
        }
    }
});
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
                searchButton.setBackground(new Color(200, 200, 200)); 
            }
        });
        searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchButton.setBackground(UIManager.getColor("control")); // Rcosì reimpoosto il colore del pulsante al colore di defaudefault
        }
        });
        
        leftPanel.add(searchButton);
        
        
        //questo è per creare gruppi
        ImageIcon originalIcon7 = new ImageIcon("./src/img/CreaGruppoIcon.png");
        Image img7 = originalIcon7.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon7 = new ImageIcon(img7);
           JButton CreateGroupButton = new JButton(resizedIcon7);
           
           CreateGroupButton.setToolTipText("Crea Gruppo");
           //questo è per fargli cambiare colo re quando ci passi sopra col mosue
           CreateGroupButton.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseEntered(java.awt.event.MouseEvent evt) {
            	   CreateGroupButton.setBackground(new Color(200, 200, 200));
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
            	groupsButton.setBackground(new Color(200, 200, 200)); 
            }
        });
        groupsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	groupsButton.setBackground(UIManager.getColor("control")); 
        }
        });
        
        //icona per visualizza notifiche
        ImageIcon originalIcon = new ImageIcon("./src/img/VediNotifiche.png");
     Image img = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
     ImageIcon resizedIcon = new ImageIcon(img);
        JButton notificationsButton = new JButton(resizedIcon);
        notificationsButton.setToolTipText("Visualizza Notifiche");
        notificationsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	notificationsButton.setBackground(new Color(200, 200, 200));
            }
        });
        notificationsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	notificationsButton.setBackground(UIManager.getColor("control"));     }
        });
        ImageIcon originalIconr = new ImageIcon("./src/img/VediReport.png");
        Image imgr = originalIconr.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIconr = new ImageIcon(imgr);
        JButton reportButton = new JButton(resizedIconr);

        reportButton.setToolTipText("Vedi I Tuoi Report");
        
        reportButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	reportButton.setBackground(new Color(200, 200, 200)); 
            }
        });
        reportButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	reportButton.setBackground(UIManager.getColor("control")); }
        });
        
        leftPanel.add(reportButton);
        
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
            List<Gruppo> gruppi_isc = groupDao.getGroupsByUser(currentUser);
            

            
            groupPanelsPanel.setLayout(new BoxLayout(groupPanelsPanel, BoxLayout.Y_AXIS));
            
            if(gruppi_isc.isEmpty()) {
                JLabel NoGroupLabel = new JLabel("non sei iscritto a nessun gruppo, creane uno oppure invia una richiesta!");
                NoGroupLabel.setFont(new Font("Georgia", Font.ROMAN_BASELINE, 14));
                groupPanelsPanel.add(Box.createVerticalStrut(10));
                groupPanelsPanel.add(NoGroupLabel, BorderLayout.NORTH);
              
            }
            
            searchField2.setMaximumSize(new Dimension(2000, 30)); 
            groupPanelsPanel.add(searchField2, BorderLayout.NORTH);
            searchField2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    reloadGroups();
                }

            });

 
            for (Gruppo group : gruppi_isc) {
               if(group.getTagList().contains(searchField2.getText()) || group.getNomeGruppo().contains(searchField2.getText())
               		|| searchField2.getText().isEmpty()|| group.getDescrizioneGruppo().contains(searchField2.getText()) 
               		|| searchField2.getText().equals("cerca gruppi per nome, tag e descrizione. ")|| searchField2.getText()=="") {
            	   
             
                JPanel groupPanel = new JPanel();
                groupPanel.setLayout(new BorderLayout());
                groupPanel.setBorder(BorderFactory.createLineBorder(new Color(	0,	51	,153), 2));
                groupPanel.setMaximumSize(new Dimension(2000,120)); 
                groupPanel.setBackground(new Color(240	,248,	255));
                JLabel nameLabel = new JLabel(group.getNomeGruppo());
                nameLabel.setFont(new Font("Georgia", Font.ROMAN_BASELINE, 18));
                groupPanel.add(nameLabel, BorderLayout.NORTH);
                
                JTextArea descriptionArea = new JTextArea(group.getDescrizioneGruppo());
                descriptionArea.setEditable(false);
                descriptionArea.setLineWrap(true);
                descriptionArea.setWrapStyleWord(true);
                JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
                groupPanel.add(descriptionScrollPane, BorderLayout.CENTER);
                
                ImageIcon originalIcon8 = new ImageIcon("./src/img/AccediAlGruppo.png");
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
                buttonPanel.setBackground(new Color(240	,248,	255));
                buttonPanel.add(accessButton);
                groupPanel.add(buttonPanel, BorderLayout.SOUTH);
                
                groupPanelsPanel.add(groupPanel);
            }

            JScrollPane scrollPane = new JScrollPane(groupPanelsPanel);
            contentPane.add(scrollPane, BorderLayout.CENTER);
        }  } catch (SQLException e) {
            e.printStackTrace();
        }

        setContentPane(contentPane);
        setVisible(true);



        groupsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIManager.put("OptionPane.background", new Color(140,164,196)); 
            UIManager.put("OptionPane.messageForeground",  new Color(140,164,196)); 

                List<Gruppo> gruppi_richiesti = groupDao.getGroupsRequestedByUser(currentUser);
                
                if (!gruppi_richiesti.isEmpty()) {
                    JPanel groupPanelsPanel = new JPanel(new GridLayout(0, 1, 10, 10)); 
                    groupPanelsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
                    groupPanelsPanel.setPreferredSize(new Dimension(200, 200));
                    for (Gruppo gruppo : gruppi_richiesti) {
                        JPanel groupPanel = new JPanel(new BorderLayout());
                        groupPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
                        
                        JLabel groupNameLabel = new JLabel(gruppo.getNomeGruppo());
                        groupNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
                        groupPanel.add(groupNameLabel, BorderLayout.NORTH);
                        
                        JLabel groupNameDetails = new JLabel("[Richiesta Inviata]");
                        groupPanel.add(groupNameDetails, BorderLayout.CENTER);
                        
                        groupPanelsPanel.add(groupPanel);
                        JButton deleteButton = new JButton("Cancella richiesta");
                  
                        deleteButton.setSize(100,200);
                        deleteButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent actionEvent) {
                     		boolean deleted = richiestaDAO.deleteRequest(currentUser, gruppo.getIdGruppo()); 			if (deleted) {
									reloadGroups();
								} else {

								}
                            }
                            private void reloadGroups() {
                                groupPanelsPanel.removeAll();
                         
                                List<Gruppo> gruppi_richiesti = groupDao.getGroupsRequestedByUser(currentUser);
                                if (gruppi_richiesti.isEmpty()) {
                                    JLabel noRequestsLabel = new JLabel("Nessuna richiesta inviata");
                                    noRequestsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                                    noRequestsLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                    groupPanelsPanel.add(noRequestsLabel, BorderLayout.CENTER);
                                }
                                for (Gruppo gruppo : gruppi_richiesti) {
                                    JPanel groupPanel = createGroupPanel(gruppo);
                                    groupPanelsPanel.add(groupPanel);
                                }
                                groupPanelsPanel.revalidate();
                                groupPanelsPanel.repaint();
                            }

                            private JPanel createGroupPanel(Gruppo gruppo) {
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
                                        boolean deleted = richiestaDAO.deleteRequest(currentUser, gruppo.getIdGruppo()); 
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
                 
                    JOptionPane.showConfirmDialog(HomeInterface.this, scrollPane, "Gruppi Richiesti", JOptionPane.CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    
                     } else {
                    JOptionPane.showMessageDialog(HomeInterface.this, "Nessuna richiesta inviata");
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
                UIManager.put("OptionPane.background", new Color(140,164,196)); // Sfondo blu
            UIManager.put("OptionPane.messageForeground",  new Color(140,164,196)); 
            
                String searchTerm = searchField.getText();
                System.out.println("Azione eseguita!");

                try {
                    List<Gruppo> groups = groupDao.searchGroupByName(searchTerm);
                    if (!groups.isEmpty()) {
                        JPanel groupPanel = new JPanel(new GridLayout(1, 1));
                        JPanel scrollPanel = new JPanel();
                        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));

                        for (Gruppo group : groups) {
                            JButton joinButton = new JButton("+");
                            
                            JLabel groupNameLabel = new JLabel(group.getNomeGruppo());
                            joinButton.setToolTipText("Invia Richiesta");
                            joinButton.addMouseListener((MouseListener) new MouseAdapter() {
                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    joinButton.setBackground(new Color(160, 196, 156)); 
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    joinButton.setBackground(UIManager.getColor("Button.background")); 
                                }
                            });
                            joinButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    boolean a = false;
                                    try {
                                        a = richiestaDAO.insertRichiesta(currentUser, group.getIdGruppo());
                                    } catch (SQLException e1) {
                                        e1.printStackTrace();
                                    }
                                    if (a) {
                                        JOptionPane.showMessageDialog(HomeInterface.this,
                                                "Richiesta inviata per unirsi al gruppo: " + group.getNomeGruppo());
                                    } else {
                                        JOptionPane.showMessageDialog(HomeInterface.this,
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
                        JOptionPane.showConfirmDialog(HomeInterface.this, scrollPane, "risultati", JOptionPane.CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    } else {

                        JOptionPane.showMessageDialog(HomeInterface.this,
                                "Nessun gruppo trovato con il nome: " + searchTerm);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(HomeInterface.this,
                            "Si è verificato un errore durante la ricerca del gruppo.",
                            "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        notificationsButton.addActionListener(new ActionListener() {
        	
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
					List<Notifica> notifications = notificationDao.getNotificheForUser(currentUser);
					List<Richiesta> RichiesteDiEssereAggiuntoAiTuoiGruppi = RichiestaDAO.VediRichiesteDiIscrizioneAiTuoiGruppi(currentUser);
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
                Dimension dialogSize = new Dimension(300, 250);
                dialogPanel.setPreferredSize(dialogSize);
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.anchor = GridBagConstraints.WEST;

                JTextField groupNameField = new JTextField("inserisci un nome");
                groupNameField.setPreferredSize(new Dimension(150, 30));
                groupNameField.addFocusListener(new FocusListener() {
                    public void focusGained(FocusEvent e) {
                        if (groupNameField.getText().equals("inserisci un nome")) {
                        	groupNameField.setText("");
                        	groupNameField.setForeground(Color.BLACK);
                        }
                    }

                    public void focusLost(FocusEvent e) {
                        if (groupNameField.getText().isEmpty()) {
                        	groupNameField.setText("inserisci un nome");
                        	groupNameField.setForeground(Color.GRAY);
                        }
                    }
                });
                groupNameField.setForeground(Color.GRAY);
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
                JTextArea descriptionArea = new JTextArea("inserisci una descrizione :) ");
               
                descriptionArea.setLineWrap(true);
                descriptionArea.setWrapStyleWord(true);
                JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
                descriptionArea.setPreferredSize(new Dimension(150, 30));
                descriptionArea.addFocusListener(new FocusListener() {
                    public void focusGained(FocusEvent e) {
                        if (descriptionArea.getText().equals("inserisci una descrizione :) ")) {
                        	descriptionArea.setText("");
                        	descriptionArea.setForeground(Color.BLACK);
                        }
                    }

                    public void focusLost(FocusEvent e) {
                        if (descriptionArea.getText().isEmpty()) {
                        	descriptionArea.setText("inserisci una descrizione :) ");
                        	descriptionArea.setForeground(Color.GRAY);
                        }
                    }
                });
                descriptionArea.setForeground(Color.GRAY);
                dialogPanel.add(descriptionScrollPane, gbc);
                gbc.gridy++;
                dialogPanel.add(new JLabel("Tags:"), gbc);
                gbc.gridy++;
                
                
                JTextField tagsField = new JTextField("separali con una virgola e falli terminare con un punto") {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);

                        if (getText().isEmpty() && !isFocusOwner()) {
                            Graphics2D g2 = (Graphics2D) g.create();
                            g2.setColor(Color.GRAY);
                            g2.setFont(getFont().deriveFont(Font.ITALIC));
                            int x = getInsets().left; 
                            int y = (getHeight() - g2.getFontMetrics().getHeight()) / 2; 
                            g2.drawString(getText(), x, y);
                            g2.dispose();
                        }
                    }
                };
                tagsField.setPreferredSize(new Dimension(150, 30));
                tagsField.addFocusListener(new FocusListener() {
                    public void focusGained(FocusEvent e) {
                        if (tagsField.getText().equals("separali con una virgola e falli terminare con un punto")) {
                            tagsField.setText("");
                            tagsField.setForeground(Color.BLACK);
                        }
                    }

                    public void focusLost(FocusEvent e) {
                        if (tagsField.getText().isEmpty()) {
                            tagsField.setText("separali con una virgola e falli terminare con un punto");
                            tagsField.setForeground(Color.GRAY);
                        }
                    }
                });
                tagsField.setForeground(Color.GRAY);
                dialogPanel.add(tagsField, gbc);
                UIManager.put("OptionPane.background", new Color(140,164,196)); // Sfondo blu
            UIManager.put("OptionPane.messageForeground",  new Color(140,164,196)); // Testo bianco

                int result = JOptionPane.showConfirmDialog(HomeInterface.this, dialogPanel,
                        "Crea Nuovo Gruppo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    String groupName = groupNameField.getText();
                    String description = descriptionArea.getText();
                    String TagList=tagsField.getText();
                    boolean inserisci=true;
                    //groupname e taglist non possono contenere caratteri speciali e in più groupname non può essere vuoto
                    if (TagList.contains("*") || TagList.contains("[") || TagList.contains("]") ||
                    	    TagList.contains("^") || TagList.contains("!") || TagList.contains("@") ||
                    	    TagList.contains("#") || TagList.contains("$") || TagList.contains("%") ||
                    	    TagList.contains("&") || TagList.contains("(") || TagList.contains(")") ||
                    	    TagList.contains("-") || TagList.contains("+") || TagList.contains("=") ||
                    	    TagList.contains("?") || TagList.contains("<") || TagList.contains(">") ||
                    	    TagList.contains("/") || TagList.contains("\\") || TagList.contains(":") ||
                    	    TagList.contains(";") || TagList.contains("{") || TagList.contains("}") ||
                    	    TagList.contains("|") || TagList.contains("~") || TagList.contains("`") ||
                    	    TagList.contains("0") || TagList.contains("1") || TagList.contains("2") ||
                    	    TagList.contains("3") || TagList.contains("4") || TagList.contains("5") ||
                    	    TagList.contains("6") || TagList.contains("7") || TagList.contains("8") ||
                    	    TagList.contains("9")) {
                    	    inserisci = false;
                    	}
                    if(groupName.trim().isEmpty()) {inserisci=false;}

                    if (groupName.contains("*") || groupName.contains("[") || groupName.contains("]") ||
                    	    groupName.contains("^") || groupName.contains("!") || groupName.contains("@") ||
                    	    groupName.contains("#") || groupName.contains("$") || groupName.contains("%") ||
                    	    groupName.contains("&") || groupName.contains("(") || groupName.contains(")") ||
                    	    groupName.contains("-") || groupName.contains("+") || groupName.contains("=") ||
                    	    groupName.contains("?") || groupName.contains("<") || groupName.contains(">") ||
                    	    groupName.contains("/") || groupName.contains("\\") || groupName.contains(":") ||
                    	    groupName.contains(";") || groupName.contains("{") || groupName.contains("}") ||
                    	    groupName.contains("|") || groupName.contains("~") || groupName.contains("`") ||
                    	    groupName.contains("0") || groupName.contains("1") || groupName.contains("2") ||
                    	    groupName.contains("3") || groupName.contains("4") || groupName.contains("5") ||
                    	    groupName.contains("6") || groupName.contains("7") || groupName.contains("8") ||
                    	    groupName.contains("9")) {
                    	    inserisci = false;
                    	}
                    if(groupName.contains("inserisci un nome")) {
                    	inserisci=false;
                    }
                    
                    
                    
                    	if(inserisci==true) {
                    	
                        try {
                            int id=groupDao.CreateGroup(groupName, description, currentUser);


                            String[] tags = TagList.split(",");
                            for (String tag : tags) {
         
                                tag = tag.trim();
                                if (tag.endsWith(".")) {
                                    tag = tag.substring(0, tag.length() - 1);
                                }
 
                                if (!tag.isEmpty()) {
                   
                                    tagdao.InsertTipologiaIfNotExistEAssociaAGruppo(tag, id);
                                }
                            }
                           //rigenero la home
                            reloadGroups();
                           
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(HomeInterface.this, "Nessun gruppo creato, il nome non può essere vuoto e i  tag non possono contenere caratteri speciali.", "Errore", JOptionPane.ERROR_MESSAGE);

   }

                }
            }




        });
        
        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	controller.showReportInterface(currentUser);
            }
        });

    }


    private void reloadGroups() {
        groupPanelsPanel.removeAll();
        
        try {
            List<Gruppo> gruppi_isc = groupDao.getGroupsByUser(currentUser);
            searchField2.setMaximumSize(new Dimension(2000, 30)); 
            groupPanelsPanel.add(searchField2, BorderLayout.NORTH);
            for (Gruppo group : gruppi_isc) {
                if (group.getTagList().contains(searchField2.getText()) || group.getNomeGruppo().contains(searchField2.getText())
                		|| searchField2.getText().isEmpty()|| group.getDescrizioneGruppo().contains(searchField2.getText()) 
                		|| searchField2.getText().equals("cerca gruppi per nome, tag e descrizione. ") || searchField2.getText()=="") {
                    JPanel groupPanel = createGroupPanel(group);
                    groupPanelsPanel.add(groupPanel);
                }
            }
            
            groupPanelsPanel.revalidate();
            groupPanelsPanel.repaint();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private JPanel createGroupPanel(Gruppo group) {
        JPanel groupPanel = new JPanel(new BorderLayout());
        groupPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 153), 2));
        groupPanel.setMaximumSize(new Dimension(2000, 120));
        groupPanel.setBackground(new Color(240, 248, 255));

        JLabel nameLabel = new JLabel(group.getNomeGruppo());
        nameLabel.setFont(new Font("Georgia", Font.ROMAN_BASELINE, 18));
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
                accessButton.setBackground(UIManager.getColor("control"));
            }
        });
        accessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.showGroupInterface(currentUser, group);
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.add(accessButton);
        groupPanel.add(buttonPanel, BorderLayout.SOUTH);

        return groupPanel;
    }

    
   
}
