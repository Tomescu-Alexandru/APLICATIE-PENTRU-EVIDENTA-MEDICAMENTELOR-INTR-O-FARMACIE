package dao;

import gui.ResultsPage;
import model.Medicament;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicamentDao {

    Connection connection;

    PreparedStatement insertQuery;
    PreparedStatement updateQuery;
    PreparedStatement deleteQuery;
    PreparedStatement selectQuery;
    PreparedStatement selectById;
    PreparedStatement selectAllFromCategorie;
    PreparedStatement selectAllFromFurnizor;
    PreparedStatement selectMedicamentWithYear;

    public MedicamentDao (Connection connection){
        this.connection =connection;

        try {
            insertQuery = connection.prepareStatement("INSERT INTO Medicament VALUES (?,?,?,?,?)");
            updateQuery = connection.prepareStatement("UPDATE Medicament SET NumeMedicament=?, Valabilitate =?, IDCategorie=?, NumeProducator=?, Gramaj=? WHERE IDMedicament =?");
            deleteQuery = connection.prepareStatement("DELETE FROM Medicament WHERE IDMedicament =?");
            selectQuery = connection.prepareStatement("SELECT * FROM Medicament");
            selectById = connection.prepareStatement("SELECT * FROM Medicament WHERE IDMedicament=?");
            selectAllFromCategorie = connection.prepareStatement("SELECT M.NumeMedicament FROM Medicament M JOIN Categorie C ON C.IDCategorie =M.IDCategorie WHERE C.NumeCategorie = ?");
            selectAllFromFurnizor = connection.prepareStatement("SELECT M.NumeMedicament FROM Medicament M JOIN FurnizorMedicament FM ON FM.IDMedicament=M.IDMedicament JOIN Furnizor F ON F.IDFurnizor =FM.IDFurnizor WHERE F.NumeFurnizor = ?");
            selectMedicamentWithYear = connection.prepareStatement("SELECT M.NumeMedicament, M.Valabilitate FROM Medicament M JOIN Categorie C ON C.IDCategorie=M.IDCategorie  WHERE C.NumeCategorie = ? AND YEAR(Valabilitate)< ?");
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

    public Medicament findbyId(int id){
        try {
            selectById.setInt(1,id);
            ResultSet resultSet = selectById.executeQuery();

            while(resultSet.next()){
                return new Medicament(
                       resultSet.getInt("IDMedicament"),
                       resultSet.getString("NumeMedicament"),
                       resultSet.getDate("Valabilitate"),
                       resultSet.getInt("IDCategorie"),
                       resultSet.getString("NumeProducator"),
                       resultSet.getInt("Gramaj")
                );
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Medicament();
    }

    public List<String> selectFromCategorie(String numeCategorie){
        try {
            selectAllFromCategorie.setString(1,numeCategorie);
            ResultSet resultSet = selectAllFromCategorie.executeQuery();
            List<String> medicamente = new ArrayList<>();

            while(resultSet.next()){
                medicamente.add(resultSet.getString("NumeMedicament"));
            }
            return medicamente;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<String> selectFromFrunizor(String numeFurnizor){
        try {
            selectAllFromFurnizor.setString(1,numeFurnizor);
            ResultSet resultSet = selectAllFromFurnizor.executeQuery();
            List<String> medicamente = new ArrayList<>();

            while(resultSet.next()){
                medicamente.add(resultSet.getString("NumeMedicament"));
            }
            return medicamente;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Medicament> selectWithDate(String numeCategorie, int an){
        try {
            selectMedicamentWithYear.setString(1,numeCategorie);
            selectMedicamentWithYear.setInt(2, an);
            List<Medicament> medicamente= new ArrayList<>();

            ResultSet resultSet = selectMedicamentWithYear.executeQuery();
            while (resultSet.next()){
                Medicament medicament = new Medicament();
                medicament.setNumeMedicament(resultSet.getString("NumeMedicament"));
                medicament.setValabitilate(resultSet.getDate("Valabilitate"));

                medicamente.add(medicament);
            }

            return medicamente;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }

}
