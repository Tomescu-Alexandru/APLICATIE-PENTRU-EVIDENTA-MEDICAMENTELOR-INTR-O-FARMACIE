package gui;

import controller.AngajatController;
import main.Main;
import model.Angajat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Date;

public class AngajatPage extends JFrame {

    JPanel panel = new JPanel(new GridLayout(10, 2));

    JLabel idAngajat, nume,prenume, cnp,adresa, sex, dataNasterii, salariu, idPunctLucru;
    JTextField idAngajatText, numeText,prenumeText, cnpText,adresaText, sexText, dataNasteriiText, salariuText, idPunctLucruText;
    EmptyBorder border = new EmptyBorder(0, 10, 0, 0);

    JButton insert, update;
    AngajatController angajatController;

    public AngajatPage() {
        initDefaults();
        initForm();
        add(panel);

        setVisible(true);
    }


    private void initDefaults() {
        setTitle("Login Page");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initForm()
    {
        idAngajat = new JLabel("IDAngajat");
        idAngajatText = new JTextField();


        panel.add(idAngajat);
        panel.add(idAngajatText);

        nume = new JLabel("Nume");
        numeText = new JTextField();


        panel.add(nume);
        panel.add(numeText);

        prenume = new JLabel("Prenume");
        prenumeText = new JTextField();

        panel.add(prenume);
        panel.add(prenumeText);

        cnp = new JLabel("CNP");
        cnpText = new JTextField();

        panel.add(cnp);
        panel.add(cnpText);

        adresa = new JLabel("Adresa");
        adresaText = new JTextField();

        panel.add(adresa);
        panel.add(adresaText);

        sex = new JLabel("Sex");
        sexText = new JTextField();

        panel.add(sex);
        panel.add(sexText);

        dataNasterii = new JLabel("Data Nasterii");
        dataNasteriiText = new JTextField();

        panel.add(dataNasterii);
        panel.add(dataNasteriiText);

        salariu = new JLabel("Salariu");
        salariuText = new JTextField();

        panel.add(salariu);
        panel.add(salariuText);

        idPunctLucru = new JLabel("IDPunctLucru");
        idPunctLucruText = new JTextField();

        panel.add(idPunctLucru);
        panel.add(idPunctLucruText);

        insert = new JButton("Insert");
        update = new JButton("Update");

        panel.add(insert);
        panel.add(update);

        insert.addActionListener(e->{
            Angajat angajat= fromTextToAngajat();
            angajatController.insertAngajat(angajat);
        });

        update.addActionListener(e->{
            Angajat angajat= fromTextToAngajat();
            angajatController.updateAngajat(angajat);
        });
    }

    private Angajat fromTextToAngajat(){
        Angajat angajat = new Angajat(
                Integer.getInteger(idAngajatText.getText()),
                numeText.getText(),
                prenumeText.getText(),
                cnpText.getText(),
                adresaText.getText(),
                sexText.getText(),
                Date.valueOf(dataNasteriiText.getText()),
                Integer.getInteger(salariuText.getText()),
                Integer.getInteger(idPunctLucruText.getText())
        );

        return angajat;
    }

}