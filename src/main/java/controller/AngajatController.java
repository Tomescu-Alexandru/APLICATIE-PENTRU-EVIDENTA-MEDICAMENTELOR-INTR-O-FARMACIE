package controller;

import dao.AngajatDao;
import dbConnection.DBConnection;
import model.Angajat;

import java.sql.Connection;

public class AngajatController {

    private AngajatDao angajatDao;

    public AngajatController(){
        Connection connection = DBConnection.getConnection();
        angajatDao = new AngajatDao(connection);
    }

    public Angajat findAngajatById(int id) {return angajatDao.findbyId(id); }
}
