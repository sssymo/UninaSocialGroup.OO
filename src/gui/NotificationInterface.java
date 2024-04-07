
	package gui;

	import classi.notifica;
import controller.Controller;

import javax.swing.*;
	import java.awt.*;
	import java.util.List;

	public class NotificationInterface extends JFrame {

	    public NotificationInterface(int currentUser, List<notifica> notifiche,Controller controller) {
	        setTitle("Notifiche per " + currentUser);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        setSize(400, 300);
	        setLocationRelativeTo(null);

	        JPanel contentPane = new JPanel(new GridBagLayout());
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.fill = GridBagConstraints.HORIZONTAL;
	        gbc.insets = new Insets(5, 5, 5, 5);

	        JLabel titleLabel = new JLabel("Notifiche per " + currentUser);
	        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
	        gbc.gridx = 0;
	        gbc.gridy = 0;
	        gbc.gridwidth = 2;
	        contentPane.add(titleLabel, gbc);

	        gbc.gridwidth = 1;
	        gbc.gridy++;

	        for (notifica notifica : notifiche) {
	            JLabel notificationLabel = new JLabel(notifica.getTesto());
	            contentPane.add(notificationLabel, gbc);
	            gbc.gridy++;
	        }

	        setContentPane(contentPane);
	        setVisible(true);
	    }
	}
