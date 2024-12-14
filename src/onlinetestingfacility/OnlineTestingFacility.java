package onlinetestingfacility;

import java.sql.*;
import java.util.Scanner;

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
            e.printStackTrace();
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
                    + "3 to edit an existing test, 4 to review account information, or 5 to logout");
            Scanner scanChoice = new Scanner(System.in);
            int choice = scanChoice.nextInt();
            
            switch (choice) {
                case 1 -> this.listTests();
                case 2 -> this.createTest();
                case 3 -> this.editTest();
                case 4 -> this.viewInformation();
                case 5 -> this.start();
                default -> System.out.println("Invalid operation.");
            }
        }
        else {
            System.out.println("Enter 1 to take a test or 2 to review account information");
            Scanner scanChoice = new Scanner(System.in);
            int choice = scanChoice.nextInt();
            
            switch (choice) {
                case 1 -> this.listTests();
                case 2 -> this.viewInformation();
                default -> System.out.println("Invalid operation.");
            }
        }
    }
    
    //TODO, added so mainMenu() does't throw an error
    private void listTests() {
        System.out.println("\nAll tests:");
        String select = "SELECT * FROM creator_with_tests";
        try {
            //List out all the tests
            PreparedStatement stmtSelect = DatabaseManager.getConnection().prepareStatement(select);
            ResultSet rsOne = stmtSelect.executeQuery();
            while (rsOne.next()) {
                System.out.println(rsOne.getString("test_name") + 
                        " {Creator: " + rsOne.getString("username") + ", Total Score: " 
                        + rsOne.getInt("total_score") + "}");
            }
            
            //Enter test choice
            System.out.println("Enter the name of the test you would like to take, or enter 1 to return to the main menu");
            Scanner scanChoice = new Scanner(System.in);
            String choice = scanChoice.next();
        
            //Check if input is a valid test - TODO Not Working
            rsOne = stmtSelect.executeQuery();
            boolean isValidTest = false;
            while (rsOne.next()) {
                if (rsOne.getString("test_name").equals(choice)) isValidTest = true;
            }
            
            //Do the option they chose, only let them take the test if they chose a valid test otherwise restart
            if (choice.equals("1")) this.mainMenu();
            else if (isValidTest) this.takeTest(choice);
            else {
                System.out.println("Invalid operation.");
                this.listTests();
            }
        }
        catch(SQLException e) {
            System.out.println("listTests() threw SQLException");
            e.printStackTrace();
        }
    }
    
    //TODO, added so mainMenu() does't throw an error
    private void takeTest(String testName) {
        System.out.println("createTest() Chosen");
        
    }
    
    //TODO, added so mainMenu() does't throw an error
    private void createTest() {
        System.out.println("createTest() Chosen");
        
    }
    
    //TODO, added so mainMenu() does't throw an error
    private void editTest() {
        System.out.println("editTest() Chosen");
        
    }
    
    private void viewInformation() {
            System.out.println("Account Information: " + person.toString() + 
                    "\nEnter 1 go back to the main menu, 2 to update your username, 3 to update your password"
                    + ", 4 to change test creator status, or 5 to delete your account");
            Scanner scanChoice = new Scanner(System.in);
            int choice = scanChoice.nextInt();
            
            switch (choice) {
                case 1 -> this.mainMenu();
                case 2 -> this.changeUsername();
                case 3 -> this.changePassword();
                case 4 -> this.changeTestCreatorStatus();
                case 5 -> this.deleteAccount();
                default -> System.out.println("Invalid operation.");
            }
    }
    
    private void changeUsername() {
        System.out.println("Enter your new username");
        Scanner scan = new Scanner(System.in);
        
        try {
            person = new Person(scan.next(), person.getPassword(), person.getTestCreator());
        }
        catch(SQLException e) {
            System.out.println("changeUsername() threw SQLException");
            e.printStackTrace();
        }
        
        System.out.println("Username successfully changed!");
        this.viewInformation();
    }
    
    private void changePassword() {
        System.out.println("Enter your new password");
        Scanner scan = new Scanner(System.in);
        
        try {
            person = new Person(person.getUsername(), scan.next(), person.getTestCreator());
        }
        catch(SQLException e) {
            System.out.println("changePassword() threw SQLException");
            e.printStackTrace();
        }
        
        System.out.println("Password successfully changed!");
        this.viewInformation();
    }
    
    //TODO, need to make it delete all their tests, questions, and choices
    private void changeTestCreatorStatus() {
        if (person.getTestCreator()) {
            System.out.println("WARNING! This will deleted all created tests! Enter yes to confirm");
            Scanner scan = new Scanner(System.in);
            
            if (scan.next().toLowerCase().contains("yes")) person.setTestCreator(false);
            
            //TODO, need to make it delete all their tests, questions, and choices
            
            System.out.println("Creator status and tests successfully removed");
        }
        else { 
            person.setTestCreator(true);
            System.out.println("You're now a test creator!");
        }
        this.viewInformation();
    }
    
    //TODO, need to make it delete all their tests, questions, and choices
    private void deleteAccount() {
        System.out.println("Are you sure you want to delete your account? "
                + "This will also delete all your tests. yes or no");
        Scanner scan = new Scanner(System.in);
        if (scan.next().toLowerCase().contains("yes")) {
            try {
                person.delete();
            }
            catch(SQLException e) {
                System.out.println("person.delete() threw SQLException");
                e.printStackTrace();
            }
        }
        
    }
    
    public static void main(String[] args) throws SQLException {
        new OnlineTestingFacility().start();
    }
}
