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
            insertQuery = connection.prepareStatement("INSERT INTO Utilizator VALUES(?,?,?,?)");
            selectQueryUserName = connection.prepareStatement("SELECT * FROM Utilizator WHERE Username = ?");
         } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

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
                User user = new User(
                        resultSet.getString("Username"),
                        resultSet.getString("Parola"),
                        resultSet.getInt("IDAngajat"),
                        resultSet.getString("Rol")
                );
                System.out.println(user.toString());
                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return new User();
    }

}
