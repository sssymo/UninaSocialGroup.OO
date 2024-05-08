package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
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

    public GroupInterface(int currentUser, gruppo group, Controller controller,PostDao postDao,NotificaDAO notificaDao
    		) {
        this.group = group;
        this.postDao=postDao;
        

        setTitle("UninaSocialGroup - " + group.getNomeGruppo());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        
        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        //è il nome del gruppo
        JLabel bannerLabel = new JLabel(group.getNomeGruppo());
        bannerLabel.setFont(new Font("Georgia",Font.ITALIC,30));
        
        bannerLabel.setPreferredSize(new Dimension(Short.MAX_VALUE, 100));
        bannerLabel.setOpaque(true); 
        bannerLabel.setBackground(new Color(60,92,156)); 
        bannerLabel.add(Box.createHorizontalStrut(40));
        add(bannerLabel, BorderLayout.NORTH);
        
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(new Color(60, 92, 156));

        JButton createPostButton = new JButton("Crea nuovo post");
        leftPanel.add(createPostButton);

        // JTextField accanto al pulsante, da modificare in quanto non responsive
        JTextField postTextField = new JTextField();
        postTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, postTextField.getPreferredSize().height)); // Per farlo espandersi in orizzontale
        leftPanel.add(postTextField);

        add(leftPanel, BorderLayout.WEST);

        add(leftPanel, BorderLayout.WEST);

        
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(137, 156, 196));
        centerPanel.setLayout(new GridLayout(0, 1));

        List<Post> Posts = postDao.RecuperaPost(currentUser, group.getIdGruppo());
        for (Post p : Posts) {
            JLabel postLabel = new JLabel("Post di " + UserDao.getUserNameById(p.getIdutente()) + " : " + p.getDesc());
            
            postLabel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(140,164,196), 4),
                    BorderFactory.createEmptyBorder(30, 10, 30, 10) 
                ));
            centerPanel.add(postLabel);
            centerPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(140,164,196), 4),
                    BorderFactory.createEmptyBorder(30, 10, 30, 10) 
                ));
            centerPanel.add(Box.createVerticalStrut(30));
            centerPanel.add(Box.createHorizontalStrut(20));
        }

        JScrollPane scrollPane = new JScrollPane(centerPanel);
        scrollPane.setBackground(new Color(60, 92, 156));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane, BorderLayout.CENTER);

      
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(new Color(60, 92, 156));

        JButton backButton = new JButton("Torna alla Home");
        bottomPanel.add(backButton);

JButton leaveGroupButton = new JButton("Abbandona il gruppo");
bottomPanel.add(leaveGroupButton);

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
        backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				controller.showHomePage();
				
			}
        	
        });

        
        createPostButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String desc= postTextField.getText();
				Timestamp currentTime = new Timestamp(System.currentTimeMillis());
				PostDao.InserisciPost(currentUser,group.getIdGruppo(),desc,currentTime);
				notificaDao.SendNotificaForPost(currentUser, group.getIdGruppo(),desc,currentTime);
                //to do : send notifica ad utenti iscritti
				  updatePostPanel(); // così aggiorrno il pannello dei post dopo aver inserito un nuovo post
				
			}
	        
	        private void updatePostPanel() {
	            centerPanel.removeAll(); // Rimuovo tutti i componenti dal pannello centrale
	            List<Post> posts = PostDao.RecuperaPost(currentUser, group.getIdGruppo()); // Recupero i nuovi post dal database
	            for (Post p : posts) {
	                JLabel postLabel = new JLabel("Post di " + UserDao.getUserNameById(p.getIdutente()) + " : " + p.getDesc());
	                
	                postLabel.setBorder(BorderFactory.createCompoundBorder(
	                        BorderFactory.createLineBorder(new Color(140,164,196), 4),
	                        BorderFactory.createEmptyBorder(30, 10, 30, 10) 
	                    ));
	                centerPanel.add(postLabel);
	                centerPanel.setBorder(BorderFactory.createCompoundBorder(
	                        BorderFactory.createLineBorder(new Color(140,164,196), 4),
	                        BorderFactory.createEmptyBorder(30, 10, 30, 10) 
	                    ));
	                centerPanel.add(Box.createVerticalStrut(30));
	                centerPanel.add(Box.createHorizontalStrut(20));
	            }
	            revalidate(); // Rendo effettive le modifiche al pannello
	            repaint(); // Riinserisco il pannello
	        }
        });

        
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
    

}
