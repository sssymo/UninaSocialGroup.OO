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
import classi.Gruppo;
import classiDao.GroupDao;
import classiDao.UserDao;
import controller.Controller;
import classiDao.PostDao;
import javax.swing.UIManager;

public class ReportInterface  extends JFrame {
int currentUser;
Controller controller;

	public ReportInterface(int currentUser,Controller controller,List<Gruppo> gruppiutente) {
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
        titleLabel.setBackground(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setBackground(UIManager.getColor("InternalFrame.inactiveTitleGradient"));
        JTextArea reportTextArea = new JTextArea();
        JComboBox<Gruppo> groupComboBox = new JComboBox<>();
        groupComboBox.setBackground(UIManager.getColor("InternalFrame.resizeIconHighlight"));
        groupComboBox.setPreferredSize(new Dimension(200, 30));
        comboBoxPanel.add(new JLabel("Select Group: "));
        comboBoxPanel.add(groupComboBox);

        for (Gruppo g : gruppiutente) {
            groupComboBox.addItem(g); //ho fatto un tostring apposito 
        }

        groupComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gruppo selectedGruppo = (Gruppo) groupComboBox.getSelectedItem();
                if (selectedGruppo != null) {
                   System.out.println(selectedGruppo.getDataCreazione()+" " +selectedGruppo.getNomeGruppo()+" "+selectedGruppo.getIdGruppo() );

                    int maxLikes = PostDao.getMaxLikesByGroupCreationDate(selectedGruppo.getIdGruppo(),selectedGruppo.getDataCreazione());
                    int minComments = PostDao.getPostWithMinComments(selectedGruppo.getIdGruppo());
                   int minLikes = PostDao.getPostWithMinLikes(selectedGruppo.getIdGruppo());
                   int maxComments = PostDao.getPostWithMaxComments(selectedGruppo.getIdGruppo());
                   System.out.println("Max Likes for Group: " + maxLikes);
                   System.out.println("Min Likes for Group: " + minLikes);
                   System.out.println("Max Comments for Group: " + maxComments);
                   System.out.println("Min Comments for Group: " + minComments);
                    String reportText = "\n"+"  Groupname: " + selectedGruppo.getNomeGruppo() +"   ";
                    reportText += "  Creation Date: " + selectedGruppo.getDataCreazione() + "\n"+"\n";
                    reportText += "  Post with Max Likes: " + maxLikes +"   " ;
                    reportText += "  Min Likes: " + minLikes + "\n"+"\n";
                    reportText += "  Post with Max Comments: " + maxComments+"   ";
                    reportText += "  Min Comments: " + minComments + "\n"+"\n";
                    reportText += "  Number of Subscribed Users: " + GroupDao.getNumIscritti(selectedGruppo.getIdGruppo()) + "\n"+"\n";
                     reportTextArea.setText(reportText);



                }
            }
        });
        centerPanel.add(comboBoxPanel, BorderLayout.NORTH);

        JPanel reportPanel = new JPanel(new BorderLayout());
        reportPanel.setBackground(Color.WHITE);
        
        reportTextArea.setEditable(false);
        reportTextArea.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
        JScrollPane scrollPane = new JScrollPane(reportTextArea);
        reportPanel.add(scrollPane, BorderLayout.CENTER);

        centerPanel.add(reportPanel, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JButton GoBackButton = new JButton("Torna Alla Home");
        GoBackButton.setBackground(new Color(224, 255, 255));
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
