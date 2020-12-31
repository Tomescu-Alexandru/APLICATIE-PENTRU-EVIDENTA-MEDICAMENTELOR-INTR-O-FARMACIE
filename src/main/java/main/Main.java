package main;


import gui.LoginPage;
import gui.MedicamentPage;
import model.User;


public class Main {
    public static User currentUser;

    public static void main(String[] args) {
        LoginPage loginPage =  new LoginPage();
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Main.currentUser = currentUser;
    }
}
