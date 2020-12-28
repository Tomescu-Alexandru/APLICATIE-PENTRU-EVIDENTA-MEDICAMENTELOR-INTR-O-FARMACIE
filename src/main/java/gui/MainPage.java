package gui;

import controller.AngajatController;
import controller.MedicamentController;
import model.Angajat;
import main.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainPage extends JFrame {

    JPanel jPanel = new JPanel(new GridLayout(getRanduri(),1));
    JPanel angajatPanel;
    JPanel  medicamentPanel;
    JPanel  simplePanel;
    JPanel complexPanel;
    AngajatController angajatController = new AngajatController();
    EmptyBorder border = new EmptyBorder(0, 10, 0, 0);
    MedicamentController medicamentController = new MedicamentController();

    public static boolean fromUpdate;

    public MainPage(){
        setTitle("Login Page");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initPanel();
        setVisible(true);
    }
  
    private void initPanel(){


    }

    private int getRanduri(){
        if(Main.getCurrentUser().getRol()=="admin")
            return 4;
        else return 3;
    }

    private void initMedicamente()
    {
        JButton insert= new JButton("Insert");
        insert.setBorder(border);
        JButton update= new JButton("Update");
        insert.setBorder(border);
        JButton delete= new JButton("Delete");
        delete.setBorder(border);

        medicamentPanel = new JPanel(new GridLayout(1,2));
        JPanel butoane = new JPanel(new GridLayout(4,1));

        butoane.add(insert);
        butoane.add(update);
        butoane.add(delete);

        JTextField deleteId= new JTextField();
        butoane.add(deleteId);

        medicamentPanel.add(butoane);

        insert.addActionListener(e->{
            new MedicamentPage();
        });

        update.addActionListener(e->{
            fromUpdate=true;
            new MedicamentPage();
        });

        delete.addActionListener(e->{
            medicamentController.deleteMedicament(Integer.getInteger(deleteId.getText()));
        });

        JTable tabelAngajati;



    }

    private void initAngajat()
    {
        JButton insert= new JButton("Insert");
        insert.setBorder(border);
        JButton update= new JButton("Update");
        insert.setBorder(border);
        JButton delete= new JButton("Delete");
        delete.setBorder(border);

        angajatPanel = new JPanel(new GridLayout(1,2));
        JPanel butoane = new JPanel(new GridLayout(4,1));

        butoane.add(insert);
        butoane.add(update);
        butoane.add(delete);

        JTextField deleteId= new JTextField();
        butoane.add(deleteId);

        medicamentPanel.add(butoane);

        insert.addActionListener(e->{
            new AngajatPage();
        });

        update.addActionListener(e->{
            fromUpdate=true;
            new MedicamentPage();
        });

        delete.addActionListener(e->{
            angajatController.deleteAngajat(Integer.getInteger(deleteId.getText()));
        });




    }
}
