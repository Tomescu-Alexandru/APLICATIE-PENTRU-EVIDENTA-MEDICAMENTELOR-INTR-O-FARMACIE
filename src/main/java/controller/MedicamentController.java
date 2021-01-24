package controller;

import dao.MedicamentDao;
import dbConnection.DBConnection;
import model.Categorie;
import model.Medicament;
import model.MedicamentStoc;

import java.sql.Connection;
import java.util.List;

public class MedicamentController {

    //aceasta clasa este folosita pentru a face legatura intre actiunea realizata de utilizator si raspunsul pe care trebuie sa il ofere aplicatia pentru cererei legate de tabela Medicament
    private MedicamentDao medicamentDao;

    public MedicamentController(){
        Connection connection = DBConnection.getConnection();
        medicamentDao = new MedicamentDao(connection);
    }

    public boolean insertMedicament (Medicament medicament) {return medicamentDao.insertMedicament(medicament);}

    public boolean updateMedicament (Medicament medicament) {return medicamentDao.update(medicament);}

    public boolean deleteMedicament (int id) {return medicamentDao.delete(id);}

    public List<Medicament> selectMedicamente (){return medicamentDao.selectAll();}

    public List<String> selectFromCategorie(String numeCategorie){return medicamentDao.selectFromCategorie(numeCategorie);}

    public Medicament findById(int id){return medicamentDao.findbyId(id);}

    public List<String> selectFromFurnizor(String numeFurnizor){return medicamentDao.selectFromFrunizor(numeFurnizor);}

    public List<Medicament> selectWithDate(String numeCategorie, int an){return medicamentDao.selectWithDate(numeCategorie, an);}

    public List<MedicamentStoc> selectMedicamentPunctLucru(int id){return medicamentDao.selectMedicamentPunctLucru(id);}

    public List<MedicamentStoc> selectMaxim(){return  medicamentDao.selectMaxim();}

    public List<Categorie> selectAllCategorie(){return  medicamentDao.selectAllCategorie();}

}
