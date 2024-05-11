package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import classi.gruppo;
import classiDao.GroupDao;
import classiDao.PostDao;
import classiDao.UserDao;
import controller.Controller;

public class ShowGroupInfo extends JFrame {

    private int currentUser;
    private gruppo group;
    private Controller controller;
    private PostDao postDao;

    public ShowGroupInfo(int currentUser, gruppo group, Controller controller, PostDao postDao) {
        this.currentUser = currentUser;
        this.group = group;
        this.controller = controller;
        this.postDao = postDao;

        setTitle("Informazioni Gruppo "+group.getNomeGruppo());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(137, 156, 196));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Informazioni Gruppo", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel creatorLabel = new JLabel("Creatore:");
        JLabel creatorValueLabel = new JLabel(GroupDao.GetCreatoreFromIdGruppo(group.getIdGruppo()));
        JLabel creationDateLabel = new JLabel("Data Creazione:");
       JLabel membersLabel = new JLabel("Numero Iscritti:");
 
        JLabel postsLabel = new JLabel("Numero Post:");
        JLabel tagsLabel = new JLabel("Tag:");

        infoPanel.add(tagsLabel);


        // Verifica se l'utente corrente è il creatore del gruppo
        //isCreator = GroupDao.GetCreatoreFromIdGruppo(group.getIdGruppo()).equals(UserDao.getUserNameById(currentUser));
boolean isCreator=true;
        // Se l'utente è il creatore del gruppo, aggiungi il pulsante per modificare i tag
        if (isCreator) {
            JButton editTagsButton = new JButton("Modifica Tag");
            editTagsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Apri una finestra di dialogo o una nuova interfaccia per modificare i tag
                    // Implementa la logica per aggiungere o rimuovere i tag
                }
            });
            infoPanel.add(editTagsButton);
        } else {
            // Se l'utente non è il creatore del gruppo, lascia uno spazio vuoto nella griglia
            infoPanel.add(new JLabel());
        }
        infoPanel.add(creatorLabel);
        infoPanel.add(creatorValueLabel);
        infoPanel.add(creationDateLabel);
      infoPanel.add(membersLabel);
      infoPanel.add(postsLabel);
   
        mainPanel.add(infoPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(137, 156, 196));

        JButton backButton = new JButton("Torna alla Home");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                controller.showHomePage();
            }
        });

        JButton returnButton = new JButton("Torna al Gruppo");
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
