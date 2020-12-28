package dao;

import model.Medicament;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicamentDao {

    Connection connection;

    PreparedStatement insertQuery;
    PreparedStatement updateQuery;
    PreparedStatement deleteQuery;
    PreparedStatement selectQuery;

    public MedicamentDao (Connection connection){
        this.connection =connection;

        try {
            insertQuery = connection.prepareStatement("INSERT INTO Medicament VALUES (null, ?,?,?,?,?)");
            updateQuery = connection.prepareStatement("UPDATE Medicament " +
                    "SET NumeMedicament=?, Valabilitate =?, IDCategorie=?, NumeProducator=?, Gramaj=?" +
                    "WHERE IDMedicament =?");
            deleteQuery = connection.prepareStatement("DELETE FROM Medicament WHERE IDMedicament =?");
            selectQuery = connection.prepareStatement("SELECT * FROM Medicament");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public boolean insertMedicament(Medicament medicament){

        try {
            insertQuery.setString(1,medicament.getNumeMedicament());
            insertQuery.setDate(2,medicament.getValabitilate());
            insertQuery.setInt(3, medicament.getIdCategorie());
            insertQuery.setString(4, medicament.getNumeProducator());
            insertQuery.setInt(5, medicament.getGramaj());

            return insertQuery.executeUpdate()!=0;
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

    public boolean update(Medicament medicament){

        try {
            updateQuery.setString(1,medicament.getNumeMedicament());
            updateQuery.setDate(2,medicament.getValabitilate());
            updateQuery.setInt(3, medicament.getIdCategorie());
            updateQuery.setString(4, medicament.getNumeProducator());
            updateQuery.setInt(5, medicament.getGramaj());
            updateQuery.setInt(6,medicament.getIdMedicament());

            return updateQuery.executeUpdate()!=0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public List<Medicament> selectAll(){

        try {
            ResultSet results = selectQuery.executeQuery();
            List<Medicament> listaMedicamente = new ArrayList<>();
            while(results.next()){
                Medicament medicament = new Medicament(results.getInt("IDMedicament"),
                        results.getString("NumeMedicament"),
                        results.getDate("Valabilitate"),
                        results.getInt("IDCategorie"),
                        results.getString("NumeProducator"),
                        results.getInt("Gramaj"));
                listaMedicamente.add(medicament);
            }

            return listaMedicamente;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return new ArrayList<>();
    }

}
