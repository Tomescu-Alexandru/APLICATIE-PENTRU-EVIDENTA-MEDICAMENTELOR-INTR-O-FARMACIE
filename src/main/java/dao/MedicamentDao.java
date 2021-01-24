package dao;

import model.Categorie;
import model.Medicament;
import model.MedicamentStoc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    PreparedStatement selectMedicamentFromPunctLucru;
    PreparedStatement selectMaximMedicament;
    PreparedStatement selectCategorie;

    public MedicamentDao (Connection connection){
        this.connection =connection;

        try {
            //aici am scris toate cererile care sunt folosite de aplicatie si au legatura cu tabela Medicamente
            insertQuery = connection.prepareStatement("INSERT INTO Medicament VALUES (?,?,?,?,?)");
            updateQuery = connection.prepareStatement("UPDATE Medicament SET NumeMedicament=?, Valabilitate =?, IDCategorie=?, NumeProducator=?, Gramaj=? WHERE IDMedicament =?");
            deleteQuery = connection.prepareStatement("DELETE FROM Medicament WHERE IDMedicament =?");
            selectQuery = connection.prepareStatement("SELECT * FROM Medicament");
            selectById = connection.prepareStatement("SELECT * FROM Medicament WHERE IDMedicament=?");
            selectAllFromCategorie = connection.prepareStatement("SELECT M.NumeMedicament FROM Medicament M JOIN Categorie C ON C.IDCategorie =M.IDCategorie WHERE C.NumeCategorie = ?");
            selectAllFromFurnizor = connection.prepareStatement("SELECT M.NumeMedicament FROM Medicament M JOIN FurnizorMedicament FM ON FM.IDMedicament=M.IDMedicament JOIN Furnizor F ON F.IDFurnizor =FM.IDFurnizor WHERE F.NumeFurnizor = ?");
            selectMedicamentWithYear = connection.prepareStatement("SELECT M.NumeMedicament, M.Valabilitate FROM Medicament M JOIN Categorie C ON C.IDCategorie=M.IDCategorie  WHERE C.NumeCategorie = ? AND YEAR(Valabilitate)< ?");
            selectMedicamentFromPunctLucru = connection.prepareStatement("SELECT M.NumeMedicament, MP.Stoc, FM.PretBucata FROM Medicament M  JOIN MedicamentPunctLucru MP ON MP.IDMedicament=M.IDMedicament  JOIN FurnizorMedicament FM ON FM.IDMedicament=M.IDMedicament WHERE MP.IDPunctLucru =  (SELECT A.IDPunctLucru FROM Angajat A WHERE A.IDAngajat=?)  AND FM.PretBucata > (SELECT AVG(FM1.PretBucata) FROM FurnizorMedicament FM1)");
            selectMaximMedicament = connection.prepareStatement("SELECT M.NumeMedicament, MP.Stoc, P.NumePunctLucru FROM Medicament M JOIN MedicamentPunctLucru MP ON MP.IDMedicament=M.IDMedicament JOIN PunctLucru P ON P.IDPunctLucru=MP.IDPunctLucru WHERE P.NumePunctLucru =  (SELECT TOP 1 P1.NumePunctLucru FROM PunctLucru P1 JOIN MedicamentPunctLucru MP1 ON MP1.IDPunctLucru=P1.IDPunctLucru GROUP BY P1.NumePunctLucru ORDER BY SUM(MP1.Stoc) DESC) ORDER BY MP.Stoc DESC");
            selectCategorie = connection.prepareStatement("SELECT * FROM Categorie");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    // pentru fiecare cerere am creat o metoda care o executa, salveaza rezultatul si il returneaza pentru a fi folosit mai departe
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

    public List<MedicamentStoc> selectMedicamentPunctLucru(int id){
        try {
            selectMedicamentFromPunctLucru.setInt(1,id);
            ResultSet resultSet = selectMedicamentFromPunctLucru.executeQuery();
            List<MedicamentStoc> medicamente = new ArrayList<>();
            while(resultSet.next()){
                MedicamentStoc medicament = new MedicamentStoc();
                medicament.setNumeMedicament(resultSet.getString("NumeMedicament"));
                medicament.setStoc(resultSet.getInt("Stoc"));

                medicamente.add(medicament);
            }
            return medicamente;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<MedicamentStoc> selectMaxim(){
        try {
            ResultSet resultSet = selectMaximMedicament.executeQuery();
            List<MedicamentStoc> medicamente= new ArrayList<>();
            while(resultSet.next()){
                MedicamentStoc medicamentStoc = new MedicamentStoc();
                medicamentStoc.setNumeMedicament(resultSet.getString("NumeMedicament"));
                medicamentStoc.setStoc(resultSet.getInt("Stoc"));
                medicamentStoc.setNumePunctLucru(resultSet.getString("NumePunctLucru"));

                medicamente.add(medicamentStoc);
            }

            return medicamente;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return new ArrayList<>();
    }

    public List<Categorie> selectAllCategorie(){
        try {
            ResultSet resultSet = selectCategorie.executeQuery();
            List<Categorie> categorii= new ArrayList<>();

            while(resultSet.next()){
                categorii.add(new Categorie(
                        resultSet.getInt("IDCategorie"),
                        resultSet.getString("NumeCategorie"),
                        resultSet.getString("Descriere")
                ));
            }
            return categorii;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }

}
