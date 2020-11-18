package gui;

import controller.AngajatController;
import main.Main;
import model.Angajat;

import javax.swing.*;
import java.awt.*;

public class MainPage extends JFrame {

    JPanel jPanel = new JPanel(new GridLayout(1,1));
    JTextField angajatText = new JTextField();
    AngajatController angajatController = new AngajatController();

    MainPage(){
        setTitle("Login Page");
        setSize(1000, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Angajat angajat = angajatController.findAngajatById(Main.getCurrentUser().getIdAngajat());
        angajatText.setText(angajat.toString());
        jPanel.add(angajatText);
        add(jPanel);
        setVisible(true);
    }
}
