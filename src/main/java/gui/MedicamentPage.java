package gui;


import controller.MedicamentController;
import model.Medicament;


import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLOutput;

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
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
            System.out.println(medicament.toString());
            medicamentController.insertMedicament(medicament);
        });

        update = new JButton("Update");
        panel.add(update);

        update.addActionListener(e-> {
            medicamentController.updateMedicament(formToMedicament());
        });

    }

    Medicament formToMedicament(){
        System.out.println( Integer.parseInt(idMedicamentText.getText()));
        System.out.println( numeMedicamentText.getText());
        System.out.println( Date.valueOf(valabilitateText.getText()));
        System.out.println(Integer.parseInt(idCategorieText.getText()));
        System.out.println(numeProducatorText.getText());
        System.out.println(Integer.parseInt(gramajText.getText()));
        return new Medicament(
                Integer.parseInt(idMedicamentText.getText()),
                numeMedicamentText.getText(),
                Date.valueOf(valabilitateText.getText()),
                Integer.parseInt(idCategorieText.getText()),
                numeProducatorText.getText(),
                Integer.parseInt(gramajText.getText())
        );
    }
}
