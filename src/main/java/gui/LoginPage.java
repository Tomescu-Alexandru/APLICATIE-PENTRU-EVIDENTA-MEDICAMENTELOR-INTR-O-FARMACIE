package gui;

import controller.UserController;
import main.Main;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class LoginPage extends JFrame {

    private JButton loginButton, cancelButton;
    private JLabel userLabel, passwordLabel;

    private JTextField usernameField;
    private JPasswordField passwordField;
    UserController userController = new UserController();

    EmptyBorder border = new EmptyBorder(0, 10, 0, 0);

    public LoginPage() {

        initDefaults();


        initButtonsText();

        loginButton.addActionListener(event -> loginMethod());

        passwordField.addActionListener(event -> loginMethod());


        initPanel();

        setVisible(true);

    }

    private void loginMethod() {
        User user = userController.getUserByName(usernameField.getText());
        if (!user.equals(new User())) {
            //verificam daca datele introduse de utilizator sunt corecte
            if(user.getParola().equals(new String(passwordField.getPassword()))){
                //in caz afirmativ salvam utilizatorul si trecem la pagina principala
            Main.setCurrentUser(user);
            MainPage mainPage =new MainPage();
            Main.setCurrentPage(mainPage);
            dispose();
            } else {
                //in caz contrar cerem utilizatorului sa le introduca din nou
                JOptionPane.showMessageDialog(null, "Credentiale invalide");
                usernameField.setText("");
                passwordField.setText("");
                usernameField.requestFocus();
            }
        }
    }
    //dimensiunile si varianta de close
    private void initDefaults() {
        setTitle("Login Page");
        setSize(300, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 // am creat cele 2 campuri si butonele
    private void initButtonsText() {
        userLabel = new JLabel("Username:");
        userLabel.setBorder(border);
        passwordLabel = new JLabel("Parola:");
        passwordLabel.setBorder(border);


        usernameField = new JTextField();
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");
    }
        //am adaugat cele de mai sus in interfata
    private void initPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(cancelButton);
        add(panel);
    }

}
