package gui;


import controller.MedicamentController;
import main.Main;
import model.Medicament;


import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class MedicamentPage extends JFrame{

    JPanel panel;
    JLabel idMedicament, numeMedicament, valabilitate, idCategorie, numeProducator, gramaj;
    JTextField idMedicamentText, numeMedicamentText, valabilitateText, idCategorieText, numeProducatorText, gramajText;
    JButton insert,update;
    private int option;
    MedicamentController medicamentController= new MedicamentController();

    public MedicamentPage(){
        initDefaults();
        initForm();
        add(panel);
        QueryHandler.categorie();
        setVisible(true);
    }

    //dimensiunile si varianta de close
    private void initDefaults() {
        setTitle("Medicament Page");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    //am creat un formular cu toate campurile pe care le are medicamentul in baza de date
    void initForm(){
        panel= new JPanel(new GridLayout(7,2));

        idMedicament= new JLabel("IDMedicament");
        idMedicamentText= new JTextField();
        panel.add(idMedicament);
        panel.add(idMedicamentText);

        numeMedicament= new JLabel("Nume Medicament");
        numeMedicamentText = new JTextField();
        panel.add(numeMedicament);
        panel.add(numeMedicamentText);

        valabilitate = new JLabel("Valabilitate");
        valabilitateText = new JTextField();
        panel.add(valabilitate);
        panel.add(valabilitateText);

        idCategorie = new JLabel("IDCategorie");
        idCategorieText = new JTextField();
        panel.add(idCategorie);
        panel.add(idCategorieText);

        numeProducator = new JLabel("Nume Producator");
        numeProducatorText = new JTextField();
        panel.add(numeProducator);
        panel.add(numeProducatorText);

        gramaj = new JLabel("Gramaj");
        gramajText = new JTextField();
        panel.add(gramaj);
        panel.add(gramajText);

        insert = new JButton("Insert");
        panel.add(insert);
        //daca utiliatorul vrea sa insereze transformama datele si apelam cererea de insert si in functie de rezultatul ei afisam daca randul a fost inserat sau nu
        insert.addActionListener(e-> {
            option=1;
            Medicament medicament=formToMedicament();
            if(medicamentController.insertMedicament(medicament))
                JOptionPane.showMessageDialog(null, "Rand inserat");
            else
                JOptionPane.showMessageDialog(null, "Nu a reusit inserarea");

            Main.currentPage.dispose();
            MainPage mainPage = new MainPage();
            Main.setCurrentPage(mainPage);
            dispose();

        });

        update = new JButton("Update");
        panel.add(update);
        //daca utilizatorul vrea sa faca update la date verificam daca acesta a introdus un id si in functie de rezultatul ei afisam daca randul a fost modificat sau nu
        update.addActionListener(e-> {
            option=2;
            if(!idMedicamentText.getText().isEmpty()) {

                if (medicamentController.updateMedicament(formToMedicament()))
                    JOptionPane.showMessageDialog(null, "Rand actualizat");
                else
                    JOptionPane.showMessageDialog(null, "Actualizarea nu a reusit");
                dispose();
                Main.currentPage.dispose();
                MainPage mainPage = new MainPage();
                Main.setCurrentPage(mainPage);
            }
            else JOptionPane.showMessageDialog(null, "Trebuie adaugat id-ul");
        });

    }
    //in aceasta metoda citesc toate campurile si creez o instanta de Medicament cu acestea
    Medicament formToMedicament(){
        Medicament medicamentCurent;
        //in cazul in care facem update pentru campurile pe care nu le-am completat se vor adauga cele existente in baza de date
        if(option==2)
            medicamentCurent =medicamentController.findById(Integer.parseInt(idMedicamentText.getText()));
        else
            medicamentCurent = new Medicament();

        Medicament medicament = new Medicament();

        medicament.setIdMedicament(medicamentCurent.getIdMedicament());

        if(numeMedicamentText.getText().isEmpty())
            medicament.setNumeMedicament(medicamentCurent.getNumeMedicament());
        else
            medicament.setNumeMedicament(numeMedicamentText.getText());

        if(valabilitateText.getText().isEmpty())
            medicament.setValabitilate(medicamentCurent.getValabitilate());
        else
            medicament.setValabitilate(Date.valueOf(valabilitateText.getText()));

        if(idCategorieText.getText().isEmpty())
            medicament.setIdCategorie(medicamentCurent.getIdCategorie());
        else
            medicament.setIdCategorie(Integer.parseInt(idCategorieText.getText()));

        if(numeProducatorText.getText().isEmpty())
            medicament.setNumeProducator(medicamentCurent.getNumeProducator());
        else
            medicament.setNumeProducator(numeProducatorText.getText());

        if(gramajText.getText().isEmpty())
            medicament.setGramaj(medicamentCurent.getGramaj());
        else
            medicament.setGramaj(Integer.parseInt(gramajText.getText()));



        return medicament;
    }
}
