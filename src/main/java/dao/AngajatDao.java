package dao;

import model.Angajat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AngajatDao {

    Connection connection;

    PreparedStatement insertQuery;
    PreparedStatement updateQuery;
    PreparedStatement deleteQuery;
    PreparedStatement selectQueryById;
    PreparedStatement selectAllQuery;

    public AngajatDao (Connection connection){
        this.connection = connection;

        try {
            selectQueryById = connection.prepareStatement("SELECT * FROM Angajat WHERE IDAngajat = ?");
            insertQuery= connection.prepareStatement("INSERT INTO Angajat VALUES(?,?,?,?,?,?,?,?)");
            updateQuery= connection.prepareStatement("UPDATE Angajat SET Nume=?, Prenume=?, CNP=?, Adresa=?, Sex=?, DataNasterii=?, Salariu=?, IDPunctLucru=? WHERE IDAngajat=?");
            deleteQuery= connection.prepareStatement("DELETE FROM Angajat WHERE IDAngajat=?");
            selectAllQuery = connection.prepareStatement("SELECT * FROM Angajat");
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
                        resultSet.getInt("IDAngajat"),
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

    public List<Angajat> selectAll(){
        try {
            ResultSet resultSet = selectAllQuery.executeQuery();
            List<Angajat> angajati = new ArrayList<>();
            while(resultSet.next()){
                Angajat angajat = new Angajat(
                        resultSet.getInt("IDAngajat"),
                        resultSet.getString("Nume"),
                        resultSet.getString("Prenume"),
                        resultSet.getString("CNP"),
                        resultSet.getString("Adresa"),
                        resultSet.getString("Sex"),
                        resultSet.getDate("DataNasterii"),
                        resultSet.getInt("Salariu"),
                        resultSet.getInt("IDPunctLucru")
                );
                angajati.add(angajat);
            }
            return angajati;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }

    public boolean insert(Angajat angajat){
        try {
            insertQuery.setString(1,angajat.getNume());
            insertQuery.setString(2, angajat.getPrenume());
            insertQuery.setString(3, angajat.getCnp());
            insertQuery.setString(4, angajat.getAdresa());
            insertQuery.setString(5, angajat.getSex());
            insertQuery.setDate(6, angajat.getDataNasterii());
            insertQuery.setInt(7, angajat.getSalariu());
            insertQuery.setInt(8, angajat.getIdPunctLucru());

            return insertQuery.executeUpdate()!=0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean update(Angajat angajat){

        try {
            updateQuery.setString(1,angajat.getNume());
            updateQuery.setString(2, angajat.getPrenume());
            updateQuery.setString(3, angajat.getCnp());
            updateQuery.setString(4, angajat.getAdresa());
            updateQuery.setString(5, angajat.getSex());
            updateQuery.setDate(6, angajat.getDataNasterii());
            updateQuery.setInt(7, angajat.getSalariu());
            updateQuery.setInt(8, angajat.getIdPunctLucru());
            updateQuery.setInt(9, angajat.getIdAngajat());

            return updateQuery.executeUpdate()!=0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id){
        try {
            deleteQuery.setInt(1,id);

            return deleteQuery.executeUpdate()!=0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
            return false;
    }
}
