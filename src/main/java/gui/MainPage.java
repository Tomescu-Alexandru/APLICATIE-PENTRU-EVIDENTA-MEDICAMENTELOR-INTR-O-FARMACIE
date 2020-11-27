package gui;

import controller.AngajatController;
import model.Angajat;
import main.Main;

import javax.swing.*;
import java.awt.*;

public class MainPage extends JFrame {

    JPanel jPanel = new JPanel(new GridLayout(7,2));
    AngajatController angajatController = new AngajatController();
    JLabel [] Campuri = new JLabel[7];
    JLabel [] Atribute = new JLabel[7];


    public MainPage(){
        setTitle("Login Page");
        setSize(300, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initPanel();
        add(jPanel);
        setVisible(true);
    }
  
    private void initPanel(){
        Angajat angajat = angajatController.findAngajatById(Main.getCurrentUser().getIdAngajat());

        Campuri[0]= new JLabel("Nume: ");
        Atribute[0]= new JLabel(angajat.getNume());
        jPanel.add(Campuri[0]);
        jPanel.add(Atribute[0]);

        Campuri[1]= new JLabel("Prenume: ");
        Atribute[1]= new JLabel(angajat.getPrenume());
        jPanel.add(Campuri[1]);
        jPanel.add(Atribute[1]);

        Campuri[2]= new JLabel("CNP: ");
        Atribute[2]= new JLabel(angajat.getCnp());
        jPanel.add(Campuri[2]);
        jPanel.add(Atribute[2]);

        Campuri[3]= new JLabel("Adresa: ");
        Atribute[3]= new JLabel(angajat.getAdresa());
        jPanel.add(Campuri[3]);
        jPanel.add(Atribute[3]);

        Campuri[4]= new JLabel("Sex: ");
        Atribute[4]= new JLabel(angajat.getSex());
        jPanel.add(Campuri[4]);
        jPanel.add(Atribute[4]);

        Campuri[5]= new JLabel("Data nasterii: ");
        Atribute[5]= new JLabel(angajat.getDataNasterii().toString());
        jPanel.add(Campuri[5]);
        jPanel.add(Atribute[5]);

        Campuri[6]= new JLabel("Salariu: ");
        Atribute[6]= new JLabel(Integer.toString(angajat.getSalariu()));
        jPanel.add(Campuri[6]);
        jPanel.add(Atribute[6]);

    }
}
