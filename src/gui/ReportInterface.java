package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import classi.Post;
import classi.gruppo;
import classiDao.GroupDao;
import classiDao.UserDao;
import controller.Controller;
import classiDao.PostDao;

public class ReportInterface  extends JFrame {
int currentUser;
Controller controller;

	public ReportInterface(int currentUser,Controller controller,List<gruppo> gruppiutente) {
        this.currentUser = currentUser;
        this.controller = controller;

        setTitle("Unina Social Network - Report for " + UserDao.getUserNameById(currentUser));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        ImageIcon IconaFrame = new ImageIcon("./src/img/UNINASOCIALGROPICON.png");
     Image imgIconaFrame = IconaFrame.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        setIconImage(imgIconaFrame);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(137, 156, 196));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(137, 156, 196));

        JLabel titleLabel = new JLabel("Report Interface", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setBackground(Color.WHITE);
        JTextArea reportTextArea = new JTextArea();
        JComboBox<gruppo> groupComboBox = new JComboBox<>();
        groupComboBox.setPreferredSize(new Dimension(200, 30));
        comboBoxPanel.add(new JLabel("Select Group: "));
        comboBoxPanel.add(groupComboBox);

        // Popola la combobox con oggetti gruppo
        for (gruppo g : gruppiutente) {
            groupComboBox.addItem(g); //ho fatto un tostring apposito che stampa solo il nome, tanto a prescindere sa qual'è l'oggetto.
        }

        groupComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gruppo selectedGruppo = (gruppo) groupComboBox.getSelectedItem();
                if (selectedGruppo != null) {
                   System.out.println(selectedGruppo.getDataCreazione()+" " +selectedGruppo.getNomeGruppo()+" "+selectedGruppo.getIdGruppo() );
                    // Recupera i dati dal database utilizzando la data di creazione
                	
                    int maxLikes = PostDao.getMaxLikesByGroupCreationDate(selectedGruppo.getIdGruppo(),selectedGruppo.getDataCreazione());
                    int minComments = PostDao.getPostWithMinComments(selectedGruppo.getIdGruppo());
                   int minLikes = PostDao.getPostWithMinLikes(selectedGruppo.getIdGruppo());
                   int maxComments = PostDao.getPostWithMaxComments(selectedGruppo.getIdGruppo());
                   System.out.println("Max Likes for Group: " + maxLikes);
                   System.out.println("Min Likes for Group: " + minLikes);
                   System.out.println("Max Comments for Group: " + maxComments);
                   System.out.println("Min Comments for Group: " + minComments);
                    String reportText = "Group: " + selectedGruppo.getNomeGruppo() + "\n";
                    reportText += "Creation Date: " + selectedGruppo.getDataCreazione() + "\n";
                    reportText += "Max Likes: " + maxLikes + "\n";
                    reportText += "Min Likes: " + minLikes + "\n";
                    reportText += "Max Comments: " + maxComments+ "\n";
                    reportText += "Min Comments: " + minComments + "\n";
                     reportTextArea.setText(reportText);


                }
            }
        });
        centerPanel.add(comboBoxPanel, BorderLayout.NORTH);

        JPanel reportPanel = new JPanel(new BorderLayout());
        reportPanel.setBackground(Color.WHITE);
        
        reportTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportTextArea);
        reportPanel.add(scrollPane, BorderLayout.CENTER);

        centerPanel.add(reportPanel, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JButton GoBackButton = new JButton("Torna Alla Home");
        GoBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              controller.showHomePage();
            }
        });
        mainPanel.add(GoBackButton, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setVisible(true);
    }
	}
