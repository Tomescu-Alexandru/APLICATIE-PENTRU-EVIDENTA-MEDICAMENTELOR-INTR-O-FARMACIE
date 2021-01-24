package controller;

import dao.UserDao;
import dbConnection.DBConnection;
import model.User;

import java.sql.Connection;

public class UserController {
    //aceasta clasa este folosita pentru a face legatura intre actiunea realizata de utilizator si raspunsul pe care trebuie sa il ofere aplicatia pentru cererei legate de tabela Utilizatori
    private UserDao userDao;

    public UserController(){
        Connection connection = DBConnection.getConnection();
        userDao = new UserDao(connection);
    }

    public boolean addUser(User user){ return userDao.insert(user); }

    public User getUserByName(String username) { return userDao.findByUsername(username); }
}
