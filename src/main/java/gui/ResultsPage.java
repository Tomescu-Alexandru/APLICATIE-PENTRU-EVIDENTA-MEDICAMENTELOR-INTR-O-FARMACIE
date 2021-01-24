package gui;

import javax.swing.*;
//aceasta clasa este folosita pentru a afisa pe ecran un tabel cu rezultatele cererilor
public class ResultsPage extends JFrame {

    public ResultsPage(JScrollPane table){

        setTitle("Results Page");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(table);
        setVisible(true);

    }
}
