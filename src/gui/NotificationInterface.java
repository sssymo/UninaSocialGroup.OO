
	package gui;

	import classi.notifica;
import classi.richiesta;
import classiDao.GroupDao;
import classiDao.UserDao;
import controller.Controller;

import javax.swing.*;
	import java.awt.*;
import java.sql.SQLException;
import java.util.List;

	public class NotificationInterface extends JFrame {

	    public NotificationInterface(int currentUser, List<notifica> notifiche,List<richiesta> notifichedirichiestaaitg,Controller controller){
	        JPanel contentPane;
			try {
				String nickname=UserDao.getUserNameById(currentUser);
				
					setTitle("Notifiche per " + nickname);

				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				setSize(400, 300);
				setLocationRelativeTo(null);

				contentPane = new JPanel(new GridBagLayout());
				GridBagConstraints gbc = new GridBagConstraints();
				gbc.fill = GridBagConstraints.HORIZONTAL;
				gbc.insets = new Insets(5, 5, 5, 5);
				JLabel titleLabel = new JLabel("Notifiche per " + nickname);
				titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
				gbc.gridx = 0;
				gbc.gridy = 0;
				gbc.gridwidth = 2;
				contentPane.add(titleLabel, gbc);

				gbc.gridwidth = 1;
				gbc.gridy++;

				for (richiesta  r : notifichedirichiestaaitg) {
				    JLabel notificationLabel;

				
							notificationLabel = new JLabel(UserDao.getUserNameById(r.getIdUtente())+"ti ha chiesto di essere aggiunto a "+GroupDao.GetGroupNameFromId(r.getIdGruppo()));
				            contentPane.add(notificationLabel, gbc);
				            gbc.gridy++;
		
						}




		        setContentPane(contentPane);
		        setVisible(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        

	    }
	}
