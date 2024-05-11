package controller;

import classiDao.GroupDao;
import classiDao.NotificaDAO;
import classiDao.PostDao;
import classiDao.TagDao;
import classiDao.UserDao;
import gui.*;
import javax.swing.*;

import classi.gruppo;
import classi.notifica;
import classi.richiesta;
import classiDao.richiestaDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Controller {

    private UserDao userDao;
    private TagDao Tagdao;
    private GroupDao groupDao;
    private NotificaDAO notificationDao;
    private richiestaDAO richiestaDao;
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
        richiestaDao = new richiestaDAO(connection);
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
        currentFrame = new home(currentUser,Username,groupDao, notificationDao, richiestaDao, this,PostDao,Tagdao);
    }
    public void showNotificationsInterface(List<notifica> notifications,List<richiesta> richieste) {
        currentFrame.dispose();
        currentFrame = new NotificationInterface(currentUser, notifications,richieste, this);
    }

	public void showGroupInterface(int currentUser,gruppo group) {
		// TODO Auto-generated method stub
		currentFrame.dispose();
		notificationDao.SendNotificaForAccesso(currentUser, group.getIdGruppo());
		currentFrame = new GroupInterface(currentUser, group,this,PostDao,notificationDao);
		
	}

public void showReportInterface(int currentUser) {
	currentFrame.dispose();
	//???
	currentFrame=new ReportInterface(currentUser,this);

}
   
public void ShowGroupInfo(int currentUser,gruppo group) {
	currentFrame.dispose();
	currentFrame=new ShowGroupInfo(currentUser,group,this,PostDao);
}
}

