package gui;

import controller.AngajatController;
import controller.MedicamentController;
import main.Main;
import model.*;

import javax.swing.*;
import java.util.List;

public class QueryHandler {
    static MedicamentController medicamentController = new MedicamentController();
    static AngajatController angajatController = new AngajatController();
  // aceasta clasa este folosita pentru a transfera datele de la o anumita cerere SQL intr-un tabel folosind ResultsPage
    public static void query1(){
        List<String> medicamente = medicamentController.selectFromCategorie("Durere");
        String [] collumName={" ","Nume Medicament"};
        Object[][] medicamenteData= new Object[medicamente.size()][2];
        for (int i=0;i<medicamente.size();i++) {
            medicamenteData[i][0]=i+1;
            medicamenteData[i][1] = medicamente.get(i);
        }

        JTable results = new JTable(medicamenteData,collumName);

        new ResultsPage(new JScrollPane(results));

    }

    public static void query2(){
        List<String> medicamente = medicamentController.selectFromFurnizor("Pharmafarm");
        String [] collumName={" ","Nume Medicament"};
        Object[][] medicamenteData= new Object[medicamente.size()][2];
        for (int i=0;i<medicamente.size();i++) {
            medicamenteData[i][0]=i+1;
            medicamenteData[i][1] = medicamente.get(i);
        }

        JTable results = new JTable(medicamenteData,collumName);

         new ResultsPage(new JScrollPane(results));

    }

    public static void query3(int an){
        List<Medicament> medicamente = medicamentController.selectWithDate("Durere",an);
        String [] collumName={" ","Nume Medicament","Valabilitate"};
        Object[][] medicamenteData= new Object[medicamente.size()][3];
        for (int i=0;i<medicamente.size();i++) {
            medicamenteData[i][0]=i+1;
            medicamenteData[i][1] = medicamente.get(i).getNumeMedicament();
            medicamenteData[i][2] = medicamente.get(i).getValabitilate();
        }

        JTable results = new JTable(medicamenteData,collumName);

        new ResultsPage(new JScrollPane(results));

    }

    public static void query4(){
        List<Angajat> angajati = angajatController.selectAngajatByPunctLucru("Farmacia Denis");
        String [] collumName={" ","Nume","Prenume"};
        Object [][]angajatiData = new Object[angajati.size()][3];

        for (int i=0;i<angajati.size();i++) {
            angajatiData[i][0]=i+1;
            angajatiData[i][1]=angajati.get(i).getNume();
            angajatiData[i][2]=angajati.get(i).getPrenume();
        }
        JTable results = new JTable(angajatiData,collumName);
        new ResultsPage(new JScrollPane(results));

    }

    public static void query5(){
        List<User> users = angajatController.selectAngajatByUser();
        String [] collumName={" ","Nume","Prenume","Username","Rol","Nume Punct Lucru"};
        Object [][]usersData = new Object[users.size()][6];

        for (int i=0;i<users.size();i++){
            usersData[i][0]=i+1;
            usersData[i][1]=users.get(i).getNume();
            usersData[i][2]=users.get(i).getPrenume();
            usersData[i][3]=users.get(i).getUsername();
            usersData[i][4]=users.get(i).getRol();
            usersData[i][5]=users.get(i).getNumePunctLucru();
        }
        JTable results = new JTable(usersData,collumName);
        new ResultsPage(new JScrollPane(results));


    }

    public static void guery6(int salariu){
        List<Angajat> angajati = angajatController.selectAngajatBySalariu("Farmacia Tei", salariu);
        String [] collumName={" ","Nume","Prenume","Salariu"};
        Object [][]angajatiData = new Object[angajati.size()][4];

        for (int i=0;i<angajati.size();i++) {
            angajatiData[i][0]=i+1;
            angajatiData[i][1]=angajati.get(i).getNume();
            angajatiData[i][2]=angajati.get(i).getPrenume();
            angajatiData[i][3]=angajati.get(i).getSalariu();
        }
        JTable results = new JTable(angajatiData,collumName);
        new ResultsPage(new JScrollPane(results));
    }

    public static void complexQuery1(){
        List<MedicamentStoc> medicamente = medicamentController.selectMedicamentPunctLucru(Main.getCurrentUser().getIdAngajat());
        String [] collumName={" ","Nume Medicament","Stoc"};
        Object [][]medicamenteData = new Object[medicamente.size()][3];

        for (int i=0;i<medicamente.size();i++) {
            medicamenteData[i][0]=i+1;
            medicamenteData[i][1] = medicamente.get(i).getNumeMedicament();
            medicamenteData[i][2] = medicamente.get(i).getStoc();
        }

        JTable results = new JTable(medicamenteData,collumName);

        new ResultsPage(new JScrollPane(results));
    }

    public static void complexQuery2(){
        List<MedicamentStoc> medicamente = medicamentController.selectMaxim();
        String [] collumName={" ","Nume Medicament","Stoc","Nume Punct Lucru"};
        Object [][]medicamenteData = new Object[medicamente.size()][4];

        for (int i=0;i<medicamente.size();i++) {
            medicamenteData[i][0]=i+1;
            medicamenteData[i][1] = medicamente.get(i).getNumeMedicament();
            medicamenteData[i][2] = medicamente.get(i).getStoc();
            medicamenteData[i][3] = medicamente.get(i).getNumePunctLucru();
        }

        JTable results = new JTable(medicamenteData,collumName);

        new ResultsPage(new JScrollPane(results));
    }

    public static void complexQuery3(){
        List<AngajatPunctLucru> punctLucru = angajatController.selectPunctLucru();
        String [] collumName={" ","Nume Punct Lucru","Medie Salariu"};
        Object [][]punctLucruData = new Object[punctLucru.size()][3];

        for(int i=0;i<punctLucru.size();i++){
            punctLucruData[i][0]=i+1;
            punctLucruData[i][1]=punctLucru.get(i).getNumePunctLucru();
            punctLucruData[i][2]=punctLucru.get(i).getSalariu();
        }

        JTable results = new JTable(punctLucruData, collumName);

        new ResultsPage(new JScrollPane(results));
    }

    public static void complexQuery4(String numeMedicament){
        List<AngajatPunctLucru> angajati = angajatController.selectAngajatByMedicament(numeMedicament);
        String [] collumName={" ","Nume","Prenume","Nume Punct Lucru"};
        Object [][] angajatiData = new Object[angajati.size()][4];

        for(int i=0;i<angajati.size();i++){
            angajatiData[i][0]=i+1;
            angajatiData[i][1]=angajati.get(i).getNume();
            angajatiData[i][2]=angajati.get(i).getPrenume();
            angajatiData[i][3]=angajati.get(i).getNumePunctLucru();
        }

        JTable results = new JTable(angajatiData, collumName);

        new ResultsPage(new JScrollPane(results));
    }

    public static void punctLucru(){
        List<AngajatPunctLucru> punctLucru = angajatController.selectAllPunctLucru();
        String [] collumName={"IDPunctLucru","NumePunctLucru","Adresa","Categorie"};
        Object [][]punctLucruData = new Object[punctLucru.size()][4];

        for(int i=0;i<punctLucru.size();i++){
            punctLucruData[i][0]=punctLucru.get(i).getIdPunctLucru();
            punctLucruData[i][1]=punctLucru.get(i).getNumePunctLucru();
            punctLucruData[i][2]=punctLucru.get(i).getAdresa();
            punctLucruData[i][3]=punctLucru.get(i).getTelefon();
        }

        JTable results = new JTable(punctLucruData, collumName);

        new ResultsPage(new JScrollPane(results));
    }

    public static void categorie(){
        List<Categorie> categorii = medicamentController.selectAllCategorie();
        String [] collumName={"IDCategorie","NumeCategorie","Descriere"};
        Object [][] categoriiData = new Object[categorii.size()][3];

        for(int i=0;i<categorii.size();i++){
            categoriiData[i][0]=categorii.get(i).getIdCategorie();
            categoriiData[i][1]=categorii.get(i).getNumeCategorie();
            categoriiData[i][2]=categorii.get(i).getDescriere();
        }

        JTable results = new JTable(categoriiData, collumName);

        new ResultsPage(new JScrollPane(results));
    }
}
