package controller;

import dao.AngajatDao;
import dbConnection.DBConnection;
import model.Angajat;
import model.AngajatPunctLucru;
import model.User;

import java.sql.Connection;
import java.util.List;

//aceasta clasa este folosita pentru a face legatura intre actiunea realizata de utilizator si raspunsul pe care trebuie sa il ofere aplicatia pentru cererei legate de tabela Angajat
public class AngajatController {

    private AngajatDao angajatDao;

    public AngajatController(){
        Connection connection = DBConnection.getConnection();
        angajatDao = new AngajatDao(connection);
    }

    public Angajat findAngajatById(int id) {return angajatDao.findbyId(id); }

    public boolean insertAngajat(Angajat angajat){ return angajatDao.insert(angajat);}

    public boolean updateAngajat(Angajat angajat){return angajatDao.update(angajat);}

    public boolean deleteAngajat(int id){return angajatDao.delete(id);}

    public List<Angajat> selectAll(){return angajatDao.selectAll();}

    public List<Angajat> selectAngajatByPunctLucru(String punctLucru){return angajatDao.selectAngajatByPunctLucru(punctLucru);}

    public List<Angajat> selectAngajatBySalariu(String punctLucru, int salariu){ return angajatDao.selectAngajatBySalariu(punctLucru,salariu);}

    public List<AngajatPunctLucru> selectPunctLucru(){return angajatDao.selectPunctLucru();}

    public List<AngajatPunctLucru> selectAngajatByMedicament(String numeMedicament){return angajatDao.selectAngajatByMedicament(numeMedicament);}

    public List<User> selectAngajatByUser(){return angajatDao.selectAngajatiUsers();}

    public List<AngajatPunctLucru> selectAllPunctLucru(){return angajatDao.selectAllPunctLucru();}
}
