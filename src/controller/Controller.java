package controller;

import classiDao.GroupDao;
import classiDao.NotificaDAO;
import classiDao.UserDao;
import gui.*;
import javax.swing.*;

import classi.notifica;

import java.sql.Connection;
import java.util.List;

public class Controller {

    private UserDao userDao;
    private GroupDao groupDao;
    private NotificaDAO notificationDao;
    private JFrame currentFrame;
    private String currentUser;
    private Connection connection;

    public Controller(Connection connection) {
        this.connection = connection;
        userDao = new UserDao(connection);
        groupDao = new GroupDao(connection);
        notificationDao = new NotificaDAO(connection);
        showLoginInterface();
    }

    public void showLoginInterface() {
    
        currentFrame = new LoginInterface(userDao, this);
    }

    public void loginSuccessful(String username) {
        currentUser = username;
        showHomePage();
    }
    
    public void showRegistrationInterface() {
    	currentFrame.dispose();
    	currentFrame = new RegistrationInterface(userDao, this);
    }
    
    private void showHomePage() {
        currentFrame.dispose();
        currentFrame = new home(currentUser, groupDao, notificationDao, this);
    }
    public void showNotificationsInterface(List<notifica> notifications) {
        currentFrame.dispose();
        //currentFrame = new NotificationInterface(currentUser, notifications, this);
    }
   
}

