package controller;

import classiDao.GroupDao;
import classiDao.NotificaDAO;
import classiDao.PostDao;
import classiDao.TagDao;
import classiDao.UserDao;
import gui.*;
import javax.swing.*;

import classi.Gruppo;
import classi.Notifica;
import classi.Richiesta;
import classiDao.RichiestaDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Controller {

    private UserDao userDao;
    private TagDao Tagdao;
    private GroupDao groupDao;
    private NotificaDAO notificationDao;
    private RichiestaDAO richiestaDAO;
    private JFrame currentFrame;
    private int currentUser;
    private String Username;
    private Connection connection;
	private PostDao PostDao;

    public Controller(Connection connection) {
        this.connection = connection;
        userDao = new UserDao(connection);
        groupDao = new GroupDao(connection);
        Tagdao=new TagDao(connection);
        notificationDao = new NotificaDAO(connection);
        richiestaDAO = new RichiestaDAO(connection);
        PostDao= new PostDao(connection);
        showLoginInterface();
    }

    public void showLoginInterface() {
    
        currentFrame = new LoginInterface(userDao, this);
    }

    public void loginSuccessful(String username,String password) {
        try {
            currentUser = userDao.getUserIdByUsername(username,password);
            Username=username;
            showHomePage();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(currentFrame, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void showRegistrationInterface() {
    	currentFrame.dispose();
    	currentFrame = new RegistrationInterface(userDao, this);
    }
    
    public void showHomePage() {
        currentFrame.dispose();
        currentFrame = new HomeInterface(currentUser,Username,groupDao, notificationDao, richiestaDAO, this,PostDao,Tagdao);
    }
    public void showNotificationsInterface(List<Notifica> notifications,List<Richiesta> richieste) {
        currentFrame.dispose();
        currentFrame = new NotificationInterface(currentUser, notifications,richieste, this,notificationDao,richiestaDAO);
    }

	public void showGroupInterface(int currentUser,Gruppo group) {
		// TODO Auto-generated method stub
		currentFrame.dispose();
		//notificationDao.SendNotificaForAccesso(currentUser, group.getIdGruppo());
		currentFrame = new GroupInterface(currentUser, group,this,PostDao,notificationDao);
		
	}

public void showReportInterface(int currentUser) {
	currentFrame.dispose();
	//???
	List<Gruppo> g = GroupDao.getGroupsByCreatore(currentUser);
	
		

	currentFrame=new ReportInterface(currentUser,this,g);

}
   
public void ShowGroupInfo(int currentUser,Gruppo group) {
	currentFrame.dispose();
	currentFrame=new GroupInfoInterface(currentUser,group,this,PostDao);
}
}

