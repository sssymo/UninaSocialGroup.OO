package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Timestamp;
import java.util.List;

import classi.Post;
import classi.Gruppo;
import classiDao.GroupDao;
import classiDao.NotificaDAO;
import classiDao.PostDao;
import classiDao.UserDao;
import controller.Controller;

public class GroupInterface extends JFrame {

    private Gruppo group;
    private PostDao postDao;
    private NotificaDAO notificaDao;
    private JTextField postTextField ;
    private JPanel centerPanel;
    public JScrollPane scrollPane;

    public GroupInterface(int currentUser, Gruppo group, Controller controller, PostDao postDao, NotificaDAO notificaDao) {
        this.group = group;
        this.postDao = postDao;
        this.notificaDao = notificaDao;

        setTitle("UninaSocialGroup - " + group.getNomeGruppo());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);        ImageIcon IconaFrame = new ImageIcon("./src/img/UNINASOCIALGROPICON.png");
     Image imgIconaFrame = IconaFrame.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        setIconImage(imgIconaFrame);

        BorderLayout layout = new BorderLayout();
        getContentPane().setLayout(layout);

        // Banner del gruppo
        JLabel bannerLabel = new JLabel(group.getNomeGruppo());
        bannerLabel.setFont(new Font("Georgia", Font.ITALIC, 24));
        bannerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bannerLabel.setForeground(new Color(213,220,233));
        bannerLabel.setOpaque(true);
        bannerLabel.setBackground(new Color(60, 92, 156));
        bannerLabel.setBorder(BorderFactory.createLineBorder(new Color(	0,	51	,153), 2));
        
        

        getContentPane().add(bannerLabel, BorderLayout.NORTH);

        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createLineBorder(new Color(	0,	51	,153), 2));
        centerPanel.setBackground(new Color(137, 156, 196));
        JScrollPane scrollPane = new JScrollPane(centerPanel);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel topRightPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 1, 10));
        buttonPanel.setBackground(new Color(60, 92, 156));
        ImageIcon originalIcon3 = new ImageIcon("./src/img/GoBackIcon.png");
     Image img3 = originalIcon3.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
     ImageIcon resizedIcon3 = new ImageIcon(img3);
     
     ImageIcon originalIcon5 = new ImageIcon("./src/img/infogruppo.png");
     Image img5 = originalIcon5.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
     ImageIcon resizedIcon5 = new ImageIcon(img5);
     JButton infoButton = new JButton(resizedIcon5);
     infoButton.setToolTipText("Informazioni Gruppo");
     
     infoButton.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseEntered(java.awt.event.MouseEvent evt) {
         	infoButton.setBackground(new Color(200, 200, 200)); 
         }
     });
     infoButton.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseExited(java.awt.event.MouseEvent evt) {
         	infoButton.setBackground(UIManager.getColor("control")); 
     }
     });
     JButton backButton = new JButton(resizedIcon3);
     buttonPanel.add(backButton);
     
     backButton.setToolTipText("Torna Indietro");
     
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	backButton.setBackground(new Color(200, 200, 200)); 
            }
        });
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	backButton.setBackground(UIManager.getColor("control")); 
        }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                controller.showHomePage();
            }
        });
     
     buttonPanel.add(infoButton);
     
             infoButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                   
                     controller.ShowGroupInfo(currentUser,group);
                 }
             });
        topRightPanel.setBackground(new Color(60, 92, 156));
        
        
        ImageIcon originalIcon4 = new ImageIcon("./src/img/escidalgruppo.png");
        Image img4 = originalIcon4.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon4 = new ImageIcon(img4);
        
        JButton leaveGroupButton = new JButton(resizedIcon4);
        
        leaveGroupButton.setToolTipText("Esci Dal Gruppo");
        
        leaveGroupButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	leaveGroupButton.setBackground(new Color(200, 200, 200)); 
            }
        });
        leaveGroupButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	leaveGroupButton.setBackground(UIManager.getColor("control")); // così reimpoosto il colore del pulsante al colore di defaudefault
        }
        });
        
        buttonPanel.add(leaveGroupButton);
        

//ok
        topRightPanel.add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(topRightPanel, BorderLayout.EAST);



        JPanel bottomPanel = new JPanel(new BorderLayout());
        JTextField postTextField = new JTextField();
        postTextField.setMinimumSize(new Dimension(13, 27));
        bottomPanel.setBackground(new Color(60, 92, 156));
        JButton createPostButton = new JButton("Pubblica Post");
        bottomPanel.add(postTextField, BorderLayout.CENTER);
        bottomPanel.add(createPostButton, BorderLayout.EAST);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        leaveGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler abbandonare il gruppo?", "Conferma", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean leftGroup = GroupDao.LasciaGruppo(currentUser, group.getIdGruppo());
                    if (leftGroup) {
                        dispose();
                        controller.showHomePage();
                    } else {
                        
                    }
                }
            }
        });
        

        postTextField.addKeyListener(new KeyListener() {
            
            @Override
            public void keyPressed(KeyEvent e) {
            
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	String desc = postTextField.getText();
       
                	if(!desc.isEmpty()) {
                        postTextField.setText("");
                        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                        PostDao.InserisciPost(currentUser, group.getIdGruppo(), desc, currentTime);
                    //    notificaDao.SendNotificaForPost(currentUser, group.getIdGruppo(), desc, currentTime);
                        updatePostPanel(); // Aaaggiorno il pannello dei post dopo l'inserimento di uo no uno nuovo
                    
                	}

}                    SwingUtilities.invokeLater(() -> {
    JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
    verticalScrollBar.setValue(verticalScrollBar.getMaximum());
});

            }
         
            private void updatePostPanel() {
                centerPanel.removeAll(); 
                JLabel groupInfoLabel = new JLabel("Gruppo creato da " + GroupDao.GetCreatoreFromIdGruppo(group.getIdGruppo()) + " il " + group.getDataCreazione());
                groupInfoLabel.setForeground(Color.white);
                centerPanel.add(groupInfoLabel);
                centerPanel.add(Box.createVerticalStrut(10));
                List<Post> posts = PostDao.RecuperaPost(currentUser, group.getIdGruppo()); 
                for (Post p : posts) {
                    JLabel postLabel = new JLabel(  UserDao.getUserNameById(p.getIdutente()) + " : " + p.getDesc());
                    centerPanel.add(postLabel);
                    centerPanel.add(Box.createVerticalStrut(10));
                }
                revalidate();
                repaint(); 
                
            }

            

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

		
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
        });
        
        createPostButton.setToolTipText("Pubblica Post");
        
        createPostButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	createPostButton.setBackground(new Color(200, 200, 200)); 
            }
        });
        createPostButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	createPostButton.setBackground(UIManager.getColor("control")); // così reimpoosto il colore del pulsante al colore di defaudefault
        }
        });
        
        createPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String desc = postTextField.getText();
            	if(!desc.isEmpty()) {
                    postTextField.setText("");
                    Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                    PostDao.InserisciPost(currentUser, group.getIdGruppo(), desc, currentTime);
                   // notificaDao.SendNotificaForPost(currentUser, group.getIdGruppo(), desc, currentTime);
                    updatePostPanel(); 
                
                    SwingUtilities.invokeLater(() -> {
                        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
                        verticalScrollBar.setValue(verticalScrollBar.getMaximum());
                    });
            	}
}

            // aggiorno il pannello dei post
            private void updatePostPanel() {
                centerPanel.removeAll(); 
                JLabel groupInfoLabel = new JLabel("Gruppo creato da " + GroupDao.GetCreatoreFromIdGruppo(group.getIdGruppo()) + " il " + group.getDataCreazione());
                groupInfoLabel.setForeground(Color.white);
                centerPanel.add(groupInfoLabel);
                centerPanel.add(Box.createVerticalStrut(10));
                List<Post> posts = PostDao.RecuperaPost(currentUser, group.getIdGruppo()); 
                for (Post p : posts) {
                	
                    JLabel postLabel = new JLabel(  UserDao.getUserNameById(p.getIdutente()) + " : " + p.getDesc());
                    centerPanel.add(postLabel);
                    centerPanel.add(Box.createVerticalStrut(10));
                }
                revalidate(); 
                repaint();
            }
        });

        loadInitialPosts(currentUser);
        //in tal modo quando apro la pagina del gruppo scendo automaticamente quanto 
        //più giù possibile , così da trovarmi i post recenti.
        SwingUtilities.invokeLater(() -> {
            JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
            verticalScrollBar.setValue(verticalScrollBar.getMaximum());
        });
        setVisible(true);
    }


    private void loadInitialPosts(int currentUser) {
        JLabel groupInfoLabel = new JLabel("Gruppo creato da " + GroupDao.GetCreatoreFromIdGruppo(group.getIdGruppo()) + " il " + group.getDataCreazione());
        groupInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        groupInfoLabel.setForeground(Color.white);
        centerPanel.add(groupInfoLabel);
        centerPanel.add(Box.createVerticalStrut(10));

        List<Post> posts = postDao.RecuperaPost(currentUser, group.getIdGruppo());
        for (Post p : posts) {
            JLabel postLabel = new JLabel(  UserDao.getUserNameById(p.getIdutente()) + " : " + p.getDesc());
            centerPanel.add(postLabel);
            centerPanel.add(Box.createVerticalStrut(10));
            
        }
			
		
       

    }

}
