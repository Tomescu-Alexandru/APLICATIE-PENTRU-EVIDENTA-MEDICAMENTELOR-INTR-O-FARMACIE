package main;

import gui.AngajatPage;
import gui.LoginPage;
import model.Angajat;
import model.User;


public class Main {
    public static User currentUser;

    public static void main(String[] args) {
     // LoginPage loginPage =  new LoginPage();
        AngajatPage angajatPage = new AngajatPage();
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Main.currentUser = currentUser;
    }
}
