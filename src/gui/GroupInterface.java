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
import classi.gruppo;
import classiDao.GroupDao;
import classiDao.NotificaDAO;
import classiDao.PostDao;
import classiDao.UserDao;
import controller.Controller;

public class GroupInterface extends JFrame {

    private gruppo group;
    private PostDao postDao;
    private NotificaDAO notificaDao;
    private JTextField postTextField ;
    private JPanel centerPanel;
    public JScrollPane scrollPane;

    public GroupInterface(int currentUser, gruppo group, Controller controller, PostDao postDao, NotificaDAO notificaDao) {
        this.group = group;
        this.postDao = postDao;
        this.notificaDao = notificaDao;

        setTitle("UninaSocialGroup - " + group.getNomeGruppo());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);        ImageIcon IconaFrame = new ImageIcon("./src/img/UNINASOCIALGROPICON.png");
     Image imgIconaFrame = IconaFrame.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        setIconImage(imgIconaFrame);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        // Banner del gruppo
        JLabel bannerLabel = new JLabel(group.getNomeGruppo());
        bannerLabel.setFont(new Font("Georgia", Font.ITALIC, 24));
        bannerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bannerLabel.setForeground(new Color(213,220,233));
        bannerLabel.setOpaque(true);
        bannerLabel.setBackground(new Color(60, 92, 156));
        bannerLabel.setBorder(BorderFactory.createLineBorder(new Color(	0,	51	,153), 2));
        
        

        add(bannerLabel, BorderLayout.NORTH);

        // Pnnello per i post al centro
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createLineBorder(new Color(	0,	51	,153), 2));
        centerPanel.setBackground(new Color(137, 156, 196));
        JScrollPane scrollPane = new JScrollPane(centerPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Pulsante "Indietro" in alto a destra
        JPanel topRightPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 0, 5)); // GridLayout con 2 righe, 1 colonna
        buttonPanel.setBackground(new Color(60, 92, 156));
        JButton backButton = new JButton("Indietro");
        buttonPanel.add(backButton);
        topRightPanel.setBackground(new Color(60, 92, 156));
        JButton leaveGroupButton = new JButton("Abbandona il gruppo");
        buttonPanel.add(leaveGroupButton);
        
        topRightPanel.add(buttonPanel, BorderLayout.NORTH);
        add(topRightPanel, BorderLayout.EAST);
  


        // annello per l'inserimento di un post in basso
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JTextField postTextField = new JTextField();
        bottomPanel.setBackground(new Color(60, 92, 156));
        JButton createPostButton = new JButton("Crea nuovo post");
        bottomPanel.add(postTextField, BorderLayout.CENTER);
        bottomPanel.add(createPostButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                controller.showHomePage();
            }
        });

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
                // Se viene premuto "Invio"
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	String desc = postTextField.getText();
                	//se il post non è vuoto
                	if(!desc.isEmpty()) {
                        postTextField.setText("");
                        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                        PostDao.InserisciPost(currentUser, group.getIdGruppo(), desc, currentTime);
                        notificaDao.SendNotificaForPost(currentUser, group.getIdGruppo(), desc, currentTime);
                        updatePostPanel(); // Aggiorna il pannello dei post dopo l'inserimento di uo no uno nuovo
                    
                	}

}                    SwingUtilities.invokeLater(() -> {
    JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
    verticalScrollBar.setValue(verticalScrollBar.getMaximum());
});

            }
         
            private void updatePostPanel() {
                centerPanel.removeAll(); // Rimuove tutti i componenti dal pannello centrale
                List<Post> posts = PostDao.RecuperaPost(currentUser, group.getIdGruppo()); // Recupera i nuovi post dal database
                for (Post p : posts) {
                    JLabel postLabel = new JLabel(  UserDao.getUserNameById(p.getIdutente()) + " : " + p.getDesc());
                    centerPanel.add(postLabel);
                    centerPanel.add(Box.createVerticalStrut(10));
                }
                revalidate(); // Rende effettive le modifiche al pannello
                repaint(); // Riaggiorna il pannello
                
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
        
        createPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String desc = postTextField.getText();
            	if(!desc.isEmpty()) {
                    postTextField.setText("");
                    Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                    PostDao.InserisciPost(currentUser, group.getIdGruppo(), desc, currentTime);
                    notificaDao.SendNotificaForPost(currentUser, group.getIdGruppo(), desc, currentTime);
                    updatePostPanel(); 
                
                    SwingUtilities.invokeLater(() -> {
                        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
                        verticalScrollBar.setValue(verticalScrollBar.getMaximum());
                    });
            	}
}

            // aggiorno il pannello dei post
            private void updatePostPanel() {
                centerPanel.removeAll(); // Rimuove tutti i componenti dal pannello centrale
                JLabel groupInfoLabel = new JLabel("Gruppo creato da " + GroupDao.GetCreatoreFromIdGruppo(group.getIdGruppo()) + " il " + group.getDataCreazione());
                groupInfoLabel.setForeground(Color.white);
                centerPanel.add(groupInfoLabel);
                centerPanel.add(Box.createVerticalStrut(10));
                List<Post> posts = PostDao.RecuperaPost(currentUser, group.getIdGruppo()); // Recupera i nuovi post dal database
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
