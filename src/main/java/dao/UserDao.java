package dao;

import dbConnection.DBConnection;

import java.sql.*;

import model.Angajat;
import model.User;


public class UserDao {

    Connection connection;

    PreparedStatement insertQuery;
    PreparedStatement selectQueryUserName;

    public UserDao(Connection connection){

        this.connection = connection;

        try {
            //aici am scris toate cererile care sunt folosite de aplicatie si au legatura cu tabela Users
            insertQuery = connection.prepareStatement("INSERT INTO Utilizator VALUES(?,?,?,?)");
            selectQueryUserName = connection.prepareStatement("SELECT * FROM Utilizator WHERE Username = ?");
         } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // pentru fiecare cerere am creat o metoda care o executa, salveaza rezultatul si il returneaza pentru a fi folosit mai departe
    public boolean insert(User user){
        try {
            insertQuery.setString(1,user.getUsername());
            insertQuery.setString(2,user.getParola());
            insertQuery.setInt(3,user.getIdAngajat());
            insertQuery.setString(4,user.getRol());

            return insertQuery.executeUpdate()!=0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public User findByUsername(String username){
        try {
            selectQueryUserName.setString(1,username);

            ResultSet resultSet = selectQueryUserName.executeQuery();

            while(resultSet.next()){
                User user = new User();
                user.setUsername(resultSet.getString("Username"));
                user.setParola(resultSet.getString("Parola"));
                user.setIdAngajat(resultSet.getInt("IDAngajat"));
                user.setRol(resultSet.getString("Rol"));
                System.out.println(user.toString());
                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return new User();
    }

}
