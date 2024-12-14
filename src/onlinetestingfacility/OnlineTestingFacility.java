/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package onlinetestingfacility;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author David
 */
public class OnlineTestingFacility {

    Person person = null;
    
    public void start() {
        DatabaseManager.getConnection();
        
        String anotherRequest = "yes";
        
        while (anotherRequest.equalsIgnoreCase("yes")) {
            
            Scanner scanChoice = new Scanner(System.in);
            System.out.println("Enter 1 to create an account, "
                + "2 to login");
            int choice = scanChoice.nextInt();
            
            switch (choice) {
                case 1 -> this.createPerson();
                case 2 -> this.login();
                default -> System.out.println("Invalid operation.");
            }
            
            System.out.println("Another request? yes or no");
            Scanner scanRequest = new Scanner(System.in);
            anotherRequest = scanRequest.next();
        }
    }
    
    private void createPerson() {
        System.out.println("Please enter your username:");
        Scanner scan = new Scanner(System.in);
        String newUsername = scan.next();
        
        System.out.println("Please enter your password:");
        scan = new Scanner(System.in);
        String newPassword = scan.next();
        
        System.out.println("Please enter true if you would like to be a test creator, enter false if you don't:");
        scan = new Scanner(System.in);
        Boolean newTestCreator = scan.nextBoolean();
        
        try {
            person = new Person(newUsername, newPassword, newTestCreator);
        }
        catch(SQLException e) {
            System.out.println("new Person threw SQLException");
            e.printStackTrace();
        }
        
        System.out.println("Account successfully created!");
        this.mainMenu();
    }
    
    private void login() {
        System.out.println("Please enter your username:");
        Scanner scan = new Scanner(System.in);
        String newUsername = scan.next();
        
        System.out.println("Please enter your password:");
        scan = new Scanner(System.in);
        String newPassword = scan.next();
        
        try {
            person = new Person().getPersonByUsernameAndPassword(newUsername, newPassword);
        }
        catch(SQLException e) {
            System.out.println("getPersonByUsernameAndPassword threw SQLException");
            e.printStackTrace(); //COMMENT THIS OUT FOR RELEASE
        }
        
        if (person == null) {
            System.out.println("Username and Password did not match, enter 1 to try again");
            Scanner scanChoice = new Scanner(System.in);
            int choice = scanChoice.nextInt();
            if (choice == 1) this.login();
        }
        else {
            System.out.println("Successfully logged in!");
            this.mainMenu();
        }
    }
    
    private void mainMenu() {
        if (person.getTestCreator()) {
            System.out.println("Enter 1 to take a test, 2 to create a test, "
                    + "3 to edit an existing test, or 4 to review account information");
            Scanner scanChoice = new Scanner(System.in);
            int choice = scanChoice.nextInt();
            
            switch (choice) {
                case 1 -> this.takeTest();
                case 2 -> this.createTest();
                case 3 -> this.editTest();
                case 4 -> this.viewInformation();
                default -> System.out.println("Invalid operation.");
            }
        }
        else {
            System.out.println("Enter 1 to take a test or 2 to review account information");
            Scanner scanChoice = new Scanner(System.in);
            int choice = scanChoice.nextInt();
            
            switch (choice) {
                case 1 -> this.takeTest();
                case 2 -> this.viewInformation();
                default -> System.out.println("Invalid operation.");
            }
        }
    }
    
    //TODO, added so mainMenu() does't throw an error
    private void takeTest() {
        System.out.println("takeTest() Chosen");
    }
    
    //TODO, added so mainMenu() does't throw an error
    private void createTest() {
        System.out.println("createTest() Chosen");
        
    }
    
    //TODO, added so mainMenu() does't throw an error
    private void editTest() {
        System.out.println("editTest() Chosen");
        
    }
    
    //TODO, added so mainMenu() does't throw an error
    private void viewInformation() {
        System.out.println("viewInformation() Chosen");
        
    }
    
    public static void main(String[] args) throws SQLException {
        new OnlineTestingFacility().start();
    }
}
