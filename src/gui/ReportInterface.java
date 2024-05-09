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

import classiDao.GroupDao;
import classiDao.UserDao;
import controller.Controller;
public class ReportInterface  extends JFrame {
int currentUser;
Controller controller;

	public ReportInterface(int currentUser,Controller controller) {
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
     
            List<String> groupNames = GroupDao.getGroupsByCreatore(currentUser);
            JComboBox groupComboBox = new JComboBox<>(groupNames.toArray(new String[0]));
            groupComboBox.setPreferredSize(new Dimension(200, 30));
            comboBoxPanel.add(new JLabel("Select Group: "));
            comboBoxPanel.add(groupComboBox);
  

        centerPanel.add(comboBoxPanel, BorderLayout.NORTH);

        JPanel reportPanel = new JPanel(new BorderLayout());
        reportPanel.setBackground(Color.WHITE);
        JTextArea reportTextArea = new JTextArea();
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

