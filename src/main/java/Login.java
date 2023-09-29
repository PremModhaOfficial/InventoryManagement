package main.java;

import java.util.Scanner;

public class Login {
    Scanner sc = new Scanner(System.in);
    String username, email_id;
    String password;
    int remainingTrys;

    boolean login() {
        System.out.println("---------------");
        System.out.println("Welcome to Inventory System");
        System.out.println("Enter Your Username : ");
        this.username = sc.nextLine();

        System.out.println("Enter Your Password : ");
        password = sc.nextLine();

        System.out.println("Enter your Email id");
        email_id = sc.nextLine();

        if (isValidPassword(password) && isValidEmail(email_id)) {
            System.out.println("Welcome " + username + "!");
            return true;
        } else {
            System.out.println("Invalid Details");
            return false;
        }
    }

    // Function to validate the password based on conditions
    boolean isValidPassword(String passwordString) {
        if (passwordString.length() >= 8
                && passwordString.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>?].*")
                && passwordString.matches(".*[a-z].*")
                && passwordString.matches(".*[A-Z].*")) {
            return true;
        } else if (remainingTrys > 0) {
            System.out.println("INVALID! \nRemaining Tries: " + remainingTrys--);
            return isValidPassword(sc.nextLine());
        }
        return false;
    }

    // Function to validate the email
    boolean isValidEmail(String email) {
        return email.contains("@");
    }

    public static void main(String[] args) {
        Login loginSystem = new Login();
        loginSystem.remainingTrys = 3;
        if (loginSystem.login()) {
            InventoryManagementSystem.manage();
        } else {
            System.out.println("Exited due to invalid login");
        }
    }
}