package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import classi.Post;
import classi.gruppo;
import classiDao.PostDao;
import classiDao.UserDao;
import controller.Controller;

public class GroupInterface extends JFrame {

    private gruppo group;
    private PostDao postDao;

    public GroupInterface(int currentUser, gruppo group, Controller controller,PostDao postDao) {
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
        centerPanel.setBackground(new Color(137,156,196));
        centerPanel.setLayout(new GridLayout(0, 1));

        List<Post> Posts=postDao.RecuperaPost(currentUser,group.getIdGruppo());
        for (Post p: Posts) { 
            JLabel postLabel = new JLabel("Post di "+UserDao.getUserNameById(p.getIdutente())+" : "+p.getDesc() );
            centerPanel.add(postLabel);
        }

        JScrollPane scrollPane = new JScrollPane(centerPanel);
        scrollPane.setBackground(new Color(60, 92, 156));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

      
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(new Color(60, 92, 156));

        JButton backButton = new JButton("Indietro");
        bottomPanel.add(backButton);
        
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
				PostDao.InserisciPost(currentUser,group.getIdGruppo(),desc);
				
			}
        	
        });
        
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

}
