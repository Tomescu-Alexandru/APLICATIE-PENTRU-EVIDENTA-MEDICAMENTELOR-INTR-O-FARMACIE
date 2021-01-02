package gui;

import controller.AngajatController;
import controller.MedicamentController;
import model.Angajat;
import model.Medicament;

import javax.swing.*;
import java.util.List;

public class QueryHandler {
    static MedicamentController medicamentController = new MedicamentController();
    static AngajatController angajatController = new AngajatController();

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
        List<Angajat> angajati = angajatController.selectAngajatByPunctLucru("Farmacia Tei");
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
}
