package gui;

import classi.Gruppo;
import classi.Notifica;
import classi.Richiesta;
import classiDao.GroupDao;
import classiDao.NotificaDAO;
import classiDao.PostDao;
import classiDao.UserDao;
import classiDao.RichiestaDAO;
import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class NotificationInterface extends JFrame {

    private int currentUser;
    private GroupDao groupDao;
    private RichiestaDAO richiestaDAO;
    private NotificaDAO notificationDao;
    private Controller controller;
    private PostDao postDao;

	public NotificationInterface(int currentUser, List<Notifica> notifiche, List<Richiesta> notifichedirichiestaaitg, Controller controller,NotificaDAO notificationDao,RichiestaDAO richiestaDAO) {
        String nickname = UserDao.getUserNameById(currentUser);
        this.controller=controller;this.currentUser = currentUser;
       

		setTitle("UninaSocialNetwork - Notifiche " + nickname);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
        ImageIcon IconaFrame = new ImageIcon("./src/img/UNINASOCIALGROPICON.png");
     Image imgIconaFrame = IconaFrame.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        setIconImage(imgIconaFrame);
		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(new Color(137, 156, 196));
		contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

   
		JLabel titleLabel = new JLabel("Notifiche per " + nickname);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
		contentPane.add(titleLabel, BorderLayout.NORTH);

		
		JPanel notificationPanel = new JPanel();
		notificationPanel.setLayout(new BoxLayout(notificationPanel, BoxLayout.Y_AXIS));
		notificationPanel.setBackground(new Color(213, 220, 233));

		JScrollPane scrollPane = new JScrollPane(notificationPanel);
		scrollPane.setBackground(new Color(213, 220, 233));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		 //s
		for (Richiesta r : notifichedirichiestaaitg) {
		    JLabel notificationLabel = new JLabel(r.getDataRichiesta()+" "+UserDao.getUserNameById(r.getIdUtente()) + " ti ha chiesto di essere aggiunto a " + GroupDao.GetGroupNameFromId(r.getIdGruppo()));
		 
		    
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
		    
		    accettaButton.setToolTipText("Accetta Richiesta");
		    accettaButton.addMouseListener((MouseListener) new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                	accettaButton.setBackground(new Color(160, 196, 156)); 
                }

                @Override
                public void mouseExited(MouseEvent e) {
                	accettaButton.setBackground(UIManager.getColor("Button.background")); 
                }
            });
		    accettaButton.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		           GroupDao.AcceptUtenteAGruppo(r.getIdUtente(), r.getIdGruppo(),currentUser);
		           
		            	richiestaDAO.deleteRequest(r.getIdUtente(),r.getIdGruppo());
		            
		            notificationPanel.remove(rowPanel);
	

		            notificationPanel.revalidate();
		            notificationPanel.repaint();
		        
		        }});
		}
		for (Notifica n : notifiche) {
		     
		    if (n.getIdpost() != 0) {
		    	
		        Gruppo link = PostDao.getGroupFromPost(n.getIdpost());

	                 
		        JLabel notificationLabel = new JLabel(
		        		n.getData_notifica()+" "+n.getOrario_notifica().toString().substring(11,11+5)+" "+n.getDesc_notifica());

		        JButton VAIALGRUPPO = new JButton(link.getNomeGruppo());

		        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		        rowPanel.setBackground(new Color(213, 220, 233));
		        rowPanel.add(notificationLabel);
		        rowPanel.add(VAIALGRUPPO);
		        rowPanel.add(Box.createHorizontalStrut(20));
		        notificationPanel.add(Box.createVerticalStrut(20));
		        notificationPanel.add(rowPanel);

		        VAIALGRUPPO.setToolTipText("Accedi Al Gruppo");
			    VAIALGRUPPO.addMouseListener((MouseListener) new MouseAdapter() {
	                @Override
	                public void mouseEntered(MouseEvent e) {
	                	VAIALGRUPPO.setBackground(new Color(160, 196, 156)); 
	                }

	                @Override
	                public void mouseExited(MouseEvent e) {
	                	VAIALGRUPPO.setBackground(UIManager.getColor("Button.background")); 
	                }
	            });
		        VAIALGRUPPO.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                dispose();
		                controller.showGroupInterface(currentUser, GroupDao.GetGroupDataFromId(link.getIdGruppo()));
		                notificationPanel.remove(rowPanel);
		                notificationPanel.revalidate();
		                notificationPanel.repaint();
		            }
		        });
		    }
		    if (n.getIdGruppo() != 0) {
		    	
		    	Gruppo link2 = GroupDao.GetGroupDataFromId(n.getIdGruppo());
		        JLabel notificationLabel = new JLabel(
		        		n.getData_notifica()+" "+n.getOrario_notifica().toString().substring(11,11+5)+" "+n.getDesc_notifica());

		        JButton VAIALGRUPPO = new JButton(link2.getNomeGruppo());

		        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		        rowPanel.setBackground(new Color(213, 220, 233));
		        rowPanel.add(notificationLabel);
		        rowPanel.add(VAIALGRUPPO);
		        rowPanel.add(Box.createHorizontalStrut(20));
		        notificationPanel.add(Box.createVerticalStrut(20));
		        notificationPanel.add(rowPanel);
		        VAIALGRUPPO.setToolTipText("Accedi Al Gruppo");
			    VAIALGRUPPO.addMouseListener((MouseListener) new MouseAdapter() {
	                @Override
	                public void mouseEntered(MouseEvent e) {
	                	VAIALGRUPPO.setBackground(new Color(160, 196, 156)); 
	                }

	                @Override
	                public void mouseExited(MouseEvent e) {
	                	VAIALGRUPPO.setBackground(UIManager.getColor("Button.background")); 
	                }
	            });
		        VAIALGRUPPO.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                dispose();
		                controller.showGroupInterface(currentUser, GroupDao.GetGroupDataFromId(link2.getIdGruppo()));
		                notificationPanel.remove(rowPanel);
		                notificationPanel.revalidate();
		                notificationPanel.repaint();
		            }
		        });
		    }
		}
		
		    
		
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JButton backButton = new JButton("Torna Indietro");		
		backButton.setToolTipText("Torna Indietro");
		backButton.addMouseListener((MouseListener) new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	backButton.setBackground(new Color(160, 196, 156)); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	backButton.setBackground(UIManager.getColor("Button.background")); 
            }
        });
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
    }
}
