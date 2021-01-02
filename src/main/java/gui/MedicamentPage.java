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

    MedicamentController medicamentController= new MedicamentController();

    public MedicamentPage(){
        initDefaults();
        initForm();
        add(panel);
        setVisible(true);
    }


    private void initDefaults() {
        setTitle("Medicament Page");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

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

        insert.addActionListener(e-> {
            Medicament medicament=formToMedicament();
            medicamentController.insertMedicament(medicament);
            dispose();
            Main.currentPage.dispose();
            MainPage mainPage = new MainPage();
            Main.setCurrentPage(mainPage);
        });

        update = new JButton("Update");
        panel.add(update);

        update.addActionListener(e-> {
            medicamentController.updateMedicament(formToMedicament());
            dispose();
            Main.currentPage.dispose();
            MainPage mainPage = new MainPage();
            Main.setCurrentPage(mainPage);
        });

    }

    Medicament formToMedicament(){
        Medicament medicamentCurent =medicamentController.findById(Integer.parseInt(idMedicamentText.getText()));
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
