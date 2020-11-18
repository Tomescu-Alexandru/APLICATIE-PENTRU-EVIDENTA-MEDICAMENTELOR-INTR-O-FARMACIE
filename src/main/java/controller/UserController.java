package controller;

import dao.UserDao;
import dbConnection.DBConnection;
import model.User;

import java.sql.Connection;

public class UserController {

    private UserDao userDao;

    public UserController(){
        Connection connection = DBConnection.getConnection();
        userDao = new UserDao(connection);
    }

    public boolean addUser(User user){ return userDao.insert(user); }

    public User getUserByName(String username) { return userDao.findByUsername(username); }
}
