package controller;

import classiDao.GroupDao;
import classiDao.NotificaDAO;
import classiDao.UserDao;
import gui.*;
import javax.swing.*;

import classi.notifica;
import classiDao.richiestaDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Controller {

    private UserDao userDao;
    private GroupDao groupDao;
    private NotificaDAO notificationDao;
    private richiestaDAO richiestaDao;
    private JFrame currentFrame;
    private int currentUser;
    private String Username;
    private Connection connection;

    public Controller(Connection connection) {
        this.connection = connection;
        userDao = new UserDao(connection);
        groupDao = new GroupDao(connection);
        notificationDao = new NotificaDAO(connection);
        richiestaDao = new richiestaDAO(connection);
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
    
    private void showHomePage() {
        currentFrame.dispose();
        currentFrame = new home(currentUser,Username,groupDao, notificationDao, richiestaDao, this);
    }
    public void showNotificationsInterface(List<notifica> notifications) {
        currentFrame.dispose();
        currentFrame = new NotificationInterface(currentUser, notifications, this);
    }
   
}

