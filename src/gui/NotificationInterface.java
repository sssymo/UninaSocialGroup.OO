package gui;

import classi.notifica;
import classi.richiesta;
import classiDao.GroupDao;
import classiDao.UserDao;
import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class NotificationInterface extends JFrame {

    public NotificationInterface(int currentUser, List<notifica> notifiche, List<richiesta> notifichedirichiestaaitg, Controller controller) {
        try {
            String nickname = UserDao.getUserNameById(currentUser);

            setTitle("Notifiche per " + nickname);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(800, 600);
            setLocationRelativeTo(null);

            JPanel contentPane = new JPanel(new BorderLayout());

   
            JLabel titleLabel = new JLabel("Notifiche per " + nickname);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
            contentPane.add(titleLabel, BorderLayout.NORTH);

            // pannello per le notifiche
            JPanel notificationPanel = new JPanel();
            notificationPanel.setLayout(new BoxLayout(notificationPanel, BoxLayout.Y_AXIS));
            notificationPanel.setBackground(new Color(213, 220, 233));

            JScrollPane scrollPane = new JScrollPane(notificationPanel);
            scrollPane.setBackground(new Color(213, 220, 233));
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            for (richiesta r : notifichedirichiestaaitg) {
                JLabel notificationLabel = new JLabel(UserDao.getUserNameById(r.getIdUtente()) + " ti ha chiesto di essere aggiunto a " + GroupDao.GetGroupNameFromId(r.getIdGruppo()));
                JButton accettaButton = new JButton("Accetta");

                JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                rowPanel.setBackground(new Color(213, 220, 233));
                rowPanel.add(notificationLabel);
                
                rowPanel.add(Box.createHorizontalStrut(20));
                
                rowPanel.add(accettaButton);
                rowPanel.setBackground(new Color(213, 220, 233));
                notificationPanel.add(Box.createVerticalStrut(20));
                rowPanel.setBackground(new Color(213, 220, 233));
                notificationPanel.add(rowPanel);
                rowPanel.setBackground(new Color(213, 220, 233));
                
                
                accettaButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Elimino richiesta ed accetto utente inserendolo negli iscritti al gruppo
                        GroupDao.AcceptUtenteAGruppo(r.getIdUtente(), r.getIdGruppo());
                        
                        // Rimuovo il pannello riga dalla notifica
                        notificationPanel.remove(rowPanel);
                        JOptionPane.showMessageDialog(NotificationInterface.this, "Richiesta Accettata!", "Conferma", JOptionPane.INFORMATION_MESSAGE);
                        // Aggiorno il layout 
                        notificationPanel.revalidate();
                        notificationPanel.repaint();
                    
                    }});
            }
                
            
            contentPane.add(scrollPane, BorderLayout.CENTER);
            
            JButton backButton = new JButton("Torna Indietro");
            
            
            
            
            
            

            
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose(); 
                    controller.showHomePage();
                }
            });
            contentPane.add(backButton, BorderLayout.SOUTH);

            setContentPane(contentPane);
            setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
