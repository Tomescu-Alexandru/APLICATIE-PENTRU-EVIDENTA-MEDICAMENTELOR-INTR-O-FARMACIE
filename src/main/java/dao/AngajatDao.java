package dao;

import model.Angajat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AngajatDao {

    Connection connection;

    PreparedStatement insertQuery;
    PreparedStatement selectQueryById;

    public AngajatDao (Connection connection){
        this.connection = connection;

        try {
            selectQueryById = connection.prepareStatement("SELECT * FROM Angajat WHERE IDAngajat = ?");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Angajat findbyId(int id){
        try {
            selectQueryById.setInt(1,id);
            ResultSet resultSet = selectQueryById.executeQuery();

            while(resultSet.next()){
                Angajat angajat = new Angajat(
                        resultSet.getString("Nume"),
                        resultSet.getString("Prenume"),
                        resultSet.getString("CNP"),
                        resultSet.getString("Adresa"),
                        resultSet.getString("Sex"),
                        resultSet.getDate("DataNasterii"),
                        resultSet.getInt("Salariu"),
                        resultSet.getInt("IDPunctLucru")
                );
                return angajat;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Angajat();
    }
}
