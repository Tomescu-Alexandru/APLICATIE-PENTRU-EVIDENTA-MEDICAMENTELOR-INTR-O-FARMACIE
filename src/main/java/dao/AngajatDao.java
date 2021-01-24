package dao;

import model.Angajat;
import model.AngajatPunctLucru;
import model.User;

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
    PreparedStatement selectByPunctLucru;
    PreparedStatement selectBySalariu;
    PreparedStatement selectPunctLucruBySalariu;
    PreparedStatement selectByMedicament;
    PreparedStatement selectUsers;
    PreparedStatement selectPunct;

    public AngajatDao (Connection connection){
        this.connection = connection;

        try {
            //aici am scris toate cererile care sunt folosite de aplicatie si au legatura cu tabela Angajati
            selectQueryById = connection.prepareStatement("SELECT * FROM Angajat WHERE IDAngajat = ?");
            insertQuery= connection.prepareStatement("INSERT INTO Angajat VALUES(?,?,?,?,?,?,?,?)");
            updateQuery= connection.prepareStatement("UPDATE Angajat SET Nume=?, Prenume=?, CNP=?, Adresa=?, Sex=?, DataNasterii=?, Salariu=?, IDPunctLucru=? WHERE IDAngajat=?");
            deleteQuery= connection.prepareStatement("DELETE FROM Angajat WHERE IDAngajat=?");
            selectAllQuery = connection.prepareStatement("SELECT * FROM Angajat");
            selectByPunctLucru = connection.prepareStatement("SELECT A.Nume, A.Prenume FROM Angajat A JOIN PunctLucru P ON A.IDPunctLucru=P.IDPunctLucru WHERE P.NumePunctLucru=?");
            selectBySalariu = connection.prepareStatement("SELECT A.Nume, A.Prenume, A.Salariu FROM Angajat A JOIN PunctLucru P ON P.IDPunctLucru=A.IDPunctLucru WHERE P.NumePunctLucru=? AND A.Salariu>=?");
            selectPunctLucruBySalariu= connection.prepareStatement("SELECT P.NumePunctLucru, AVG(A.Salariu) AS medieSal FROM PunctLucru P  JOIN Angajat A ON A.IDPunctLucru = P.IDPunctLucru GROUP BY A.IDPunctLucru, P.NumePunctLucru HAVING AVG(A.Salariu) > (SELECT AVG(Salariu) FROM Angajat)");
            selectByMedicament = connection.prepareStatement("SELECT DISTINCT A.Nume,A.Prenume, P.NumePunctLucru FROM Angajat A  JOIN PunctLucru P ON P.IDPunctLucru = A.IDPunctLucru JOIN MedicamentPunctLucru MP ON MP.IDPunctLucru=P.IDPunctLucru WHERE P.IDPunctLucru =  (SELECT TOP 1 P1.IDPunctLucru FROM PunctLucru P1  JOIN MedicamentPunctLucru MP1 ON MP1.IDPunctLucru=P1.IDPunctLucru JOIN Medicament M ON M.IDMedicament = MP1.IDMedicament WHERE M.NumeMedicament=?  ORDER BY MP1.Stoc DESC)");
            selectUsers = connection.prepareStatement("SELECT A.Nume, A.Prenume, U.Username, U.Rol,P.NumePunctLucru FROM Utilizator U  JOIN Angajat A ON A.IDAngajat=U.IDAngajat JOIN PunctLucru P ON P.IDPunctLucru=A.IDPunctLucru");
            selectPunct= connection.prepareStatement("SELECT * FROM PunctLucru");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
   // pentru fiecare cerere am creat o metoda care o executa, salveaza rezultatul si il returneaza pentru a fi folosit mai departe
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

    public List<Angajat> selectAngajatByPunctLucru(String punctLucru){
        try {
            selectByPunctLucru.setString(1,punctLucru);

            ResultSet resultSet= selectByPunctLucru.executeQuery();
            List<Angajat> angajati= new ArrayList<>();
            while(resultSet.next()){
                Angajat angajat = new Angajat();
                angajat.setNume(resultSet.getString("Nume"));
                angajat.setPrenume(resultSet.getString("Prenume"));

                angajati.add(angajat);
            }
            return angajati;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return new ArrayList<>();
    }

    public List<Angajat> selectAngajatBySalariu(String punctLucru, int salariu){
        try {
            selectBySalariu.setString(1,punctLucru);
            selectBySalariu.setInt(2,salariu);

            ResultSet resultSet= selectBySalariu.executeQuery();
            List<Angajat> angajati= new ArrayList<>();
            while(resultSet.next()){
                Angajat angajat = new Angajat();
                angajat.setNume(resultSet.getString("Nume"));
                angajat.setPrenume(resultSet.getString("Prenume"));
                angajat.setSalariu(resultSet.getInt("Salariu"));

                angajati.add(angajat);
            }
            return angajati;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return new ArrayList<>();
    }

    public List<AngajatPunctLucru> selectPunctLucru(){
        try {
            ResultSet resultSet = selectPunctLucruBySalariu.executeQuery();
            List<AngajatPunctLucru> angajati = new ArrayList<>();

            while(resultSet.next()){
                AngajatPunctLucru angajatPunctLucru = new AngajatPunctLucru();
                angajatPunctLucru.setNumePunctLucru(resultSet.getString("NumePunctLucru"));
                angajatPunctLucru.setSalariu(resultSet.getInt("medieSal"));

                angajati.add(angajatPunctLucru);
            }

            return angajati;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return new ArrayList<>();
    }

    public List<AngajatPunctLucru> selectAngajatByMedicament(String numeMedicament){
        try {
            selectByMedicament.setString(1,numeMedicament);
            ResultSet resultSet = selectByMedicament.executeQuery();
            List<AngajatPunctLucru> angajati = new ArrayList<>();

            while(resultSet.next()){
                AngajatPunctLucru angajatPunctLucru = new AngajatPunctLucru();
                angajatPunctLucru.setNume(resultSet.getString("Nume"));
                angajatPunctLucru.setPrenume(resultSet.getString("Prenume"));
                angajatPunctLucru.setNumePunctLucru(resultSet.getString("NumePunctLucru"));

                angajati.add(angajatPunctLucru);
            }

            return angajati;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return new ArrayList<>();
    }

    public List<User> selectAngajatiUsers(){
        try {
            ResultSet resultSet = selectUsers.executeQuery();
            List<User> users = new ArrayList<>();

            while(resultSet.next()){
                User user = new User();
                user.setNume(resultSet.getString("Nume"));
                user.setPrenume(resultSet.getString("Prenume"));
                user.setUsername(resultSet.getString("Username"));
                user.setRol(resultSet.getString("Rol"));
                user.setNumePunctLucru(resultSet.getString("NumePunctLucru"));

                users.add(user);
            }
            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<AngajatPunctLucru> selectAllPunctLucru(){
        try {
            ResultSet resultSet = selectPunct.executeQuery();
            List<AngajatPunctLucru> puncte = new ArrayList<>();

            while(resultSet.next()){
                AngajatPunctLucru angajatPunctLucru = new AngajatPunctLucru();
                angajatPunctLucru.setIdPunctLucru(resultSet.getInt("IDPunctLucru"));
                angajatPunctLucru.setNumePunctLucru(resultSet.getString("NumePunctLucru"));
                angajatPunctLucru.setAdresa(resultSet.getString("Adresa"));
                angajatPunctLucru.setTelefon(resultSet.getString("Telefon"));

                puncte.add(angajatPunctLucru);
            }

            return puncte;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return new ArrayList<>();
    }
}
