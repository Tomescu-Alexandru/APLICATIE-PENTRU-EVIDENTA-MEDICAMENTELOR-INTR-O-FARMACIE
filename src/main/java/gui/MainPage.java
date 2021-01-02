package gui;

import controller.AngajatController;
import controller.MedicamentController;
import model.Angajat;
import main.Main;
import model.Medicament;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class MainPage extends JFrame {

    JPanel jPanel = new JPanel(new GridLayout(getRanduri(),1));
    JPanel angajatPanel;
    JPanel  medicamentPanel;
    JPanel  simplePanel;
    JPanel complexPanel;
    JPanel butoaneM,butoaneA;
    AngajatController angajatController = new AngajatController();
    EmptyBorder border = new EmptyBorder(0, 10, 0, 0);
    MedicamentController medicamentController = new MedicamentController();
    JTable medicamentTable,angajatTable;
    JButton insertM,deleteM,insertA, deleteA;
    JButton simpleQuery1, simpleQuery2, simpleQuery3, simpleQuery4, simpleQuery5, simpleQuery6;
    JPanel simpleQuery3Panel;
    JTextField simpleQuery3Parameter;

    public static boolean fromUpdate;

    public MainPage(){
        setTitle("Main Page");
        setSize(1920, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println(getRanduri());

        initMedicamente();

        if(getRanduri()==4)
        initAngajat();

        initSimple();

        add(jPanel);

        setVisible(true);

    }

    private int getRanduri(){
        if(Main.getCurrentUser().getRol().equals("admin"))
            return 4;
        else return 3;
    }

    private void initMedicamente() {
        insertM= new JButton("Insert/Update");
        insertM.setBorder(border);
        deleteM= new JButton("Delete");
        deleteM.setBorder(border);

        medicamentPanel = new JPanel(new GridLayout(1,2,10,10));
        butoaneM = new JPanel(new GridLayout(4,1,10,10));

        butoaneM.add(insertM);
        butoaneM.add(deleteM);

        JTextField deleteId= new JTextField();
        butoaneM.add(deleteId);

        medicamentPanel.add(butoaneM);

        insertM.addActionListener(e->{
            MedicamentPage medicamentPage = new MedicamentPage();
        });

        deleteM.addActionListener(e->{
            medicamentController.deleteMedicament(Integer.parseInt(deleteId.getText()));
            dispose();
            MainPage mainPage = new MainPage();
            Main.setCurrentPage(mainPage);
        });

        String []collumNames ={"ID Medicament","Nume Medicament", "Valabilitate", "IDCategorie","Nume Producator", "Gramaj"};
        List<Medicament> medicamentList = medicamentController.selectMedicamente();
        Object [][] medicamentData= new Object[medicamentList.size()][6];
        for(int i=0;i<medicamentList.size();i++){
            Medicament medicament = medicamentList.get(i);
            medicamentData[i][0]=medicament.getIdMedicament();
            medicamentData[i][1]=medicament.getNumeMedicament();
            medicamentData[i][2]=medicament.getValabitilate();
            medicamentData[i][3]=medicament.getIdCategorie();
            medicamentData[i][4]=medicament.getNumeProducator();
            medicamentData[i][5]=medicament.getGramaj();
        }
        medicamentTable = new JTable(medicamentData, collumNames);

        medicamentPanel.add(new JScrollPane(medicamentTable));
        jPanel.add(medicamentPanel);
    }

    private void initAngajat() {
        insertA= new JButton("Insert/Update");
        insertA.setBorder(border);
        deleteA= new JButton("Delete");
        deleteA.setBorder(border);

        angajatPanel = new JPanel(new GridLayout(1,2,10,10));
        butoaneA = new JPanel(new GridLayout(4,1,10,10));

        butoaneA.add(insertA);
        butoaneA.add(deleteA);

        JTextField deleteId= new JTextField();
        butoaneA.add(deleteId);

        angajatPanel.add(butoaneA);

        insertA.addActionListener(e->{
            AngajatPage angajatPage =new AngajatPage();
        });

        deleteA.addActionListener(e->{
            angajatController.deleteAngajat(Integer.getInteger(deleteId.getText()));
            dispose();
           MainPage mainPage= new MainPage();
           Main.setCurrentPage(mainPage);
        });

        String [] collumNames ={"ID Angajat","Nume","Prenume","CNP","Adresa","Sex","Data Nasterii", "Salariu","ID Punct Lucru"};
        List<Angajat> angajati = angajatController.selectAll();
        Object [][] angajatiData = new Object[angajati.size()][9];
        for(int i=0;i<angajati.size();i++){
            Angajat angajat = angajati.get(i);
            angajatiData[i][0]= angajat.getIdAngajat();
            angajatiData[i][1]=angajat.getNume();
            angajatiData[i][2]=angajat.getPrenume();
            angajatiData[i][3]=angajat.getCnp();
            angajatiData[i][4]=angajat.getAdresa();
            angajatiData[i][5]=angajat.getSex();
            angajatiData[i][6]=angajat.getDataNasterii();
            angajatiData[i][7]=angajat.getSalariu();
            angajatiData[i][8]=angajat.getIdPunctLucru();
        }
        angajatTable= new JTable(angajatiData,collumNames);
        angajatPanel.add(new JScrollPane(angajatTable));

        jPanel.add(angajatPanel);
    }

    void initSimple(){
        if(getRanduri()==4)
        simplePanel = new JPanel(new GridLayout(1,6,10,10));
        else   simplePanel = new JPanel(new GridLayout(1,3,10,10));


        simpleQuery1 = new JButton("Simple Query 1");
        simpleQuery1.setBorder(border);

        simplePanel.add(simpleQuery1);
        simpleQuery1.addActionListener(e-> QueryHandler.query1());

        simpleQuery2 = new JButton("Simple Query 2");
        simpleQuery2.setBorder(border);

        simplePanel.add(simpleQuery2);
        simpleQuery2.addActionListener(e-> QueryHandler.query2());

        simpleQuery3Panel = new JPanel(new GridLayout(2,1));
        simpleQuery3 = new JButton("Simple Query 3");
        simpleQuery3.setBorder(border);
        simpleQuery3Parameter = new JTextField();
        simpleQuery3Panel.add(simpleQuery3);
        simpleQuery3Panel.add(simpleQuery3Parameter);

        simpleQuery3.addActionListener(e-> {
            if(simpleQuery3Parameter.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "An invalid");
                simpleQuery3Parameter.setText("");
                simpleQuery3Parameter.requestFocus();
            }
            else QueryHandler.query3(Integer.parseInt(simpleQuery3Parameter.getText()));
        });

        simplePanel.add(simpleQuery3Panel);

        if(getRanduri()==4){
            simpleQuery4 = new JButton("Simple Query 4");
            simpleQuery4.setBorder(border);

            simplePanel.add(simpleQuery4);

            simpleQuery4.addActionListener(e->QueryHandler.query4());
        }

        jPanel.add(simplePanel);
    }
}
