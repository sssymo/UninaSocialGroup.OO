package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import classi.Gruppo;
import classiDao.GroupDao;
import classiDao.PostDao;
import classiDao.TagDao;
import controller.Controller;

public class GroupInfoInterface extends JFrame {

    private int currentUser;
    private Gruppo group;
    private Controller controller;
    private PostDao postDao;
//fw
    private String Tags="";
    public GroupInfoInterface(int currentUser, Gruppo group, Controller controller, PostDao postDao) {
        this.currentUser = currentUser;
        this.group = group;
        this.controller = controller;
        this.postDao = postDao;

        setTitle("Informazioni Gruppo - " + group.getNomeGruppo());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
        ImageIcon IconaFrame = new ImageIcon("./src/img/UNINASOCIALGROPICON.png");
        Image imgIconaFrame = IconaFrame.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
           setIconImage(imgIconaFrame);
Tags=TagDao.getTagsForGruppo(group.getIdGruppo());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(140,164,196));
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JLabel titleLabel = new JLabel("Informazioni Gruppo", SwingConstants.CENTER);
        titleLabel.setBackground(new Color(0, 0, 128));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3, 1)); 
        infoPanel.setBackground(new Color(140,164,196));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel labelPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        labelPanel.setBackground(new Color(140,164,196));

        JLabel creatorLabel = new JLabel("Creatore: "+GroupDao.GetCreatoreFromIdGruppo(group.getIdGruppo()));
        creatorLabel.setForeground(Color.WHITE);
      
        JLabel creationDateLabel = new JLabel("Data Creazione: " + group.getDataCreazione());
        creationDateLabel.setForeground(Color.WHITE);
        JLabel membersLabel = new JLabel("Numero Iscritti: "+GroupDao.getNumIscritti(group.getIdGruppo()));
        membersLabel.setForeground(Color.WHITE);
        JLabel membersListLabel = new JLabel("Lista Iscritti:");
        membersListLabel.setForeground(Color.WHITE);

        labelPanel.add(creatorLabel);
      
        labelPanel.add(creationDateLabel);
        JLabel postsLabel = new JLabel("Numero Post: " + postDao.GetNumPost(group.getIdGruppo())+"("+PostDao.GetNumPost(group.getIdGruppo(),currentUser)+")");
        postsLabel.setForeground(Color.WHITE);
        labelPanel.add(postsLabel);
        labelPanel.add(membersLabel);

        JTextArea membersTextArea = new JTextArea("membri: "+GroupDao.GetIscritti(group.getIdGruppo()));
        membersTextArea.setEditable(false);
        JScrollPane membersScrollPane = new JScrollPane(membersTextArea);
        labelPanel.add(membersScrollPane);

        infoPanel.add(labelPanel); 

        JTextArea tagsTextArea = new JTextArea("Tags :"+Tags);
        tagsTextArea.setEditable(false);
        tagsTextArea.setBackground(Color.white);
        JScrollPane tagsScrollPane = new JScrollPane(tagsTextArea);

        infoPanel.add(tagsScrollPane);
  
        boolean isCreator = GroupDao.CheckIfCreatore(currentUser,group.getIdGruppo());
        JButton editTagsButton = new JButton("Modifica Tag");
        if (isCreator) {
             editTagsButton.setText("Modifica Tag");
            editTagsButton.setBackground(new Color(60,92,156));
            editTagsButton.setForeground(Color.WHITE);
            editTagsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                  
        
                    String newTags = JOptionPane.showInputDialog(GroupInfoInterface.this, "Inserisci i tag:", Tags);
                    if(newTags!=Tags) {
                 
                    	
                    	 boolean inserisci=true;
                       
                         if (newTags.contains("*") || newTags.contains("[") || newTags.contains("]") ||
                     		 newTags.contains("^") || newTags.contains("!") || newTags.contains("@") ||
                     		 newTags.contains("#") || newTags.contains("$") || newTags.contains("%") ||
                     		 newTags.contains("&") || newTags.contains("(") || newTags.contains(")") ||
                      	   newTags.contains("-") || newTags.contains("+") || newTags.contains("=") ||
                      	    newTags.contains("?") || newTags.contains("<") || newTags.contains(">") ||
                      	    newTags.contains("/") || newTags.contains("\\") || newTags.contains(":") ||
                      	    newTags.contains(";") || newTags.contains("{") || newTags.contains("}") ||
                      	    newTags.contains("|") || newTags.contains("~") || newTags.contains("`") ||
                      	    newTags.contains("0") || newTags.contains("1") || newTags.contains("2") ||
                      	    newTags.contains("3") || newTags.contains("4") || newTags.contains("5") ||
                      	    newTags.contains("6") || newTags.contains("7") || newTags.contains("8") ||
                      	    newTags.contains("9")) {
                      	    inserisci = false;
                      	}
                         
                         	if(inserisci==true) {
                         		TagDao.DeleteTags(group.getIdGruppo());
                             String[] taglist = newTags.split(",");
							 for (String tag : taglist) {
							     tag = tag.trim();
							     if (tag.endsWith(".")) {
							         tag = tag.substring(0, tag.length() - 1);
							     }
							     if (!tag.isEmpty()) {
							    	 
							         TagDao.InsertTipologiaIfNotExistEAssociaAGruppo(tag, group.getIdGruppo());
							     }
							 }

                    	
                    	//infine
							 tagsTextArea.setText(newTags);
                    	Tags=newTags;
                    }
                }

        } });infoPanel.add(editTagsButton); }
        mainPanel.add(infoPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(60,92,156));

        JButton backButton = new JButton("Torna alla Home");
        backButton.setBackground(new Color(137,156,196));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                controller.showHomePage();
            }
        });

        JButton returnButton = new JButton("Torna al Gruppo");
        returnButton.setBackground(new Color(137,156,196));
        returnButton.setForeground(Color.WHITE);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                controller.showGroupInterface(currentUser, group);
            }
        });

        buttonPanel.add(backButton);
        buttonPanel.add(returnButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setVisible(true);
    }
}
