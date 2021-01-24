package main;


import gui.LoginPage;
import model.User;

import javax.swing.*;

//am folosit aceasta clasa pentru a porni interfata grafica si a retine atat Utilizatorul curent, cat si Pagina curenta
public class Main {
    public static User currentUser;
    public static JFrame currentPage;

    public static void main(String[] args) {
        LoginPage loginPage =  new LoginPage();

    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Main.currentUser = currentUser;
    }

    public static JFrame getCurrentPage(){ return currentPage;}

    public static void setCurrentPage(JFrame page){ Main.currentPage = page;}
}
