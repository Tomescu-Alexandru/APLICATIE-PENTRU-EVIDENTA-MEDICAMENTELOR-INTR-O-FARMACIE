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
    JButton complexQuery1, complexQuery2, complexQuery3, complexQuery4;
    JPanel simpleQuery3Panel;
    JTextField simpleQuery3Parameter;


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

        initComplex();
        add(jPanel);
        setVisible(true);


    }
    //verific daca utilizatorul este sau nu administrator pentru a stabili la ce query-uri are acces
    private int getRanduri(){
        if(Main.getCurrentUser().getRol().equals("admin"))
            return 4;
        else return 3;
    }
    //am adaugat un panou cu butoanele de insert,update,delete si un tabel cu medicamentele existente
    private void initMedicamente() {
        insertM= new JButton("Insert/Update");
        insertM.setBorder(border);
        deleteM= new JButton("Delete");
        deleteM.setBorder(border);

        medicamentPanel = new JPanel(new GridLayout(1,2,10,10));
        butoaneM = new JPanel(new GridLayout(4,1,10,10));

        butoaneM.add(insertM);
        butoaneM.add(deleteM);

        JTextField deleteIdM= new JTextField();
        butoaneM.add(deleteIdM);

        medicamentPanel.add(butoaneM);

        insertM.addActionListener(e->{
            MedicamentPage medicamentPage = new MedicamentPage();
        });

        deleteM.addActionListener(e->{
            medicamentController.deleteMedicament(Integer.parseInt(deleteIdM.getText()));
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

    //am adaugat un panou cu butoanele de insert,update,delete si un tabel cu angajatii existenti
    private void initAngajat() {
        insertA= new JButton("Insert/Update");
        insertA.setBorder(border);
        deleteA= new JButton("Delete");
        deleteA.setBorder(border);

        angajatPanel = new JPanel(new GridLayout(1,2,10,10));
        butoaneA = new JPanel(new GridLayout(4,1,10,10));

        butoaneA.add(insertA);
        butoaneA.add(deleteA);

        JTextField deleteIdA= new JTextField();
        butoaneA.add(deleteIdA);

        angajatPanel.add(butoaneA);

        insertA.addActionListener(e->{
            AngajatPage angajatPage =new AngajatPage();
        });

        deleteA.addActionListener(e->{
            angajatController.deleteAngajat(Integer.parseInt(deleteIdA.getText()));
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

    //am adaugat un panou cu butoane pentru fiecare cerere simpla si text pentru a arata ce face fiecare
    //iar pentru cele care au un parametru variabil am adaugat si o caseta de text pentru a-l introduce
    //toate cererile vor afisa un tabel cu rezultatele
    void initSimple(){
        simplePanel = new JPanel(new FlowLayout(FlowLayout.CENTER,100,100));


        simpleQuery1 = new JButton("Medicament Simple Query 1");
        simpleQuery1.setToolTipText("Selecteaza medicamentele din categoria Durere");
        simpleQuery1.setBorder(border);

        simplePanel.add(simpleQuery1);
        simpleQuery1.addActionListener(e-> QueryHandler.query1());

        simpleQuery2 = new JButton("Medicament Simple Query 2");
        simpleQuery2.setToolTipText("Selecteaza medicamentele de la furnizorul Pharmafarm");
        simpleQuery2.setBorder(border);

        simplePanel.add(simpleQuery2);
        simpleQuery2.addActionListener(e-> QueryHandler.query2());

        simpleQuery3Panel = new JPanel(new GridLayout(2,1));
        simpleQuery3 = new JButton("Medicament Simple Query 3");
        simpleQuery3.setToolTipText("Selecteaza medicamentele din categoria Durere care au o valabilitate pana in anul dorit");
        simpleQuery3.setBorder(border);
        simpleQuery3Parameter = new JTextField();
        simpleQuery3Parameter.setToolTipText("An");
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
            simpleQuery4 = new JButton("Angajat Simple Query 1");
            simpleQuery4.setBorder(border);
            simpleQuery4.setToolTipText("Selecteaza toti angajatii de la punctul de lucru 'Farmacia Denis'");
            simplePanel.add(simpleQuery4);

            simpleQuery4.addActionListener(e->QueryHandler.query4());

            simpleQuery5 = new JButton("Angajat Simple Query 2");
            simpleQuery5.setToolTipText("Selecteaza toti angajatii care au utilizatori pentru aplicatie");
            simpleQuery5.setBorder(border);
            simplePanel.add(simpleQuery5);

            simpleQuery5.addActionListener(e->QueryHandler.query5());

            simpleQuery6 = new JButton("Angajat Simple Query 3");
            simpleQuery6.setBorder(border);
            simpleQuery6.setToolTipText("Selecteaza toti angajatii de la punctul de lucru 'Farmacia Tei', care au salariul mai mare sau egal cu o valoare dorita");
            JPanel query6Variabil = new JPanel(new GridLayout(2,1));
            query6Variabil.add(simpleQuery6);
            JTextField query6Text= new JTextField();
            query6Text.setToolTipText("Salariu");
            query6Variabil.add(query6Text);

            simplePanel.add(query6Variabil);

            simpleQuery6.addActionListener(e->{
                if(query6Text.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Salariu Invalid");
                    query6Text.setText("");
                    query6Text.requestFocus();
                }
                else QueryHandler.guery6(Integer.parseInt(query6Text.getText()));
            });
        }


        jPanel.add(simplePanel);
    }
    //am adaugat un panou cu butoane pentru fiecare cerere complexa si text pentru a arata ce face fiecare
    //iar pentru cele care au un parametru variabil am adaugat si o caseta de text pentru a-l introduce
    //toate cererile vor afisa un tabel cu rezultatele
    void initComplex(){
        complexPanel= new JPanel(new FlowLayout(FlowLayout.CENTER,100,100));

        complexQuery1 = new JButton("Medicament Complex Query 1");
        complexQuery1.setToolTipText("Selecteaza toate medicamentele de la punctul de lucru la care lucreaza angajatul curent, al caror pret/bucata e mai mare decat media tuturor medicamentelor");
        complexQuery1.setBorder(border);
        complexPanel.add(complexQuery1);

        complexQuery1.addActionListener(e->QueryHandler.complexQuery1());

        complexQuery2 = new JButton("Medicament Complex Query 2");
        complexQuery2.setToolTipText("Selecteaza toate medicamentele, cat si stocul lor, in ordine descrescatoare, de la farmacia cu cel mai mare numar de medicamente");
        complexQuery2.setBorder(border);

        complexPanel.add(complexQuery2);
        complexQuery2.addActionListener(e-> QueryHandler.complexQuery2());

        if(getRanduri()==4) {
            complexQuery3 = new JButton("Angajat Complex Query 1");
            complexQuery3.setToolTipText("Selecteaza punctele de lucru pentru care media salariilor angajatilor este mai mare decat media salariului pe intreaga companie");
            complexQuery3.setBorder(border);
            complexQuery3.addActionListener(e -> QueryHandler.complexQuery3());

            complexPanel.add(complexQuery3);

            complexQuery4 = new JButton("Angajat Complex Query 2");
            complexQuery4.setToolTipText("Selecteaza toti angajatii de la punctul de lucru care are cel mai mare stoc din medicamentul selectat");
            complexQuery4.setBorder(border);

            JTextField complexQuery4Text = new JTextField();
            complexQuery4Text.setToolTipText("Nume Medicament");
            JPanel complexQuery4Panel = new JPanel(new GridLayout(2,1));
            complexQuery4Panel.add(complexQuery4);
            complexQuery4Panel.add(complexQuery4Text);
            complexPanel.add(complexQuery4Panel);

            complexQuery4.addActionListener(e->{
                if(complexQuery4Text.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Medicament Invalid");
                    complexQuery4Text.setText("");
                    complexQuery4Text.requestFocus();
                }
                else QueryHandler.complexQuery4(complexQuery4Text.getText());
            });
        }

        jPanel.add(complexPanel);
    }
}
