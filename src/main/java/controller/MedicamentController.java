package controller;

import dao.MedicamentDao;
import dbConnection.DBConnection;
import model.Medicament;

import java.sql.Connection;
import java.util.List;

public class MedicamentController {

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

}
