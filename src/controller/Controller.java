package controller;

import classiDao.GroupDao;
import classiDao.NotificationDao;
import classiDao.UserDao;
import gui.Home;
import gui.LoginInterface;

import javax.swing.*;
import java.sql.Connection;

public class Controller {

    private UserDao userDao;
    private GroupDao groupDao;
    private NotificationDao notificationDao;
    private JFrame currentFrame;
    private String currentUser;
    private Connection connection;

    public Controller(Connection connection) {
        this.connection = connection;
        userDao = new UserDao(connection);
        groupDao = new GroupDao(connection);
        notificationDao = new NotificationDao(connection);
        showLoginInterface();
    }

    private void showLoginInterface() {
        currentFrame = new LoginInterface(userDao, this);
    }

    public void loginSuccessful(String username) {
        currentUser = username;
        showHomePage();
    }

    private void showHomePage() {
        currentFrame.dispose();
        currentFrame = new Home(currentUser, groupDao, notificationDao, this);
    }

   
}

