package onlinetestingfacility;

import java.sql.*;
import java.util.Scanner;

/**
 * Represents a choice for a question in the online testing facility
 */
public class Choice {
    // Creates a question with text and a score and inserts it into the database
    public static void createQuestion() throws SQLException {
        System.out.println("Enter the text for the choice: ");
        Scanner scan = new Scanner(System.in);
        String text = scan.nextLine();
        
        System.out.println("Is this the correct choice? Type Y for yes, or type anything else for no.");
        Scanner scan2 = new Scanner(System.in);
        String str = scan2.nextLine();
        boolean correct = false;
        if (str.equals("Y"))
        {
            correct = true;
        }
        
        String insertChoice = "INSERT INTO choice (question_id, choice_text, is_correct) "
                + "VALUES (null, ?, ?)";
        PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(insertChoice);
        stmt.setString(1, text);
        stmt.setBoolean(2, correct);
        stmt.executeUpdate();
    }
    
    // Updates the text of a specific choice
    public static void updateChoiceText() throws SQLException {
        System.out.println("Enter the id of the choice: ");
        Scanner scan = new Scanner(System.in);
        int id = Integer.parseInt(scan.nextLine());
        
        System.out.println("Enter the new text for the choice: ");
        Scanner scan2 = new Scanner(System.in);
        String text = scan2.nextLine();
        
        String changeText = "UPDATE choice SET choice_text = ? WHERE choice_id = ?";
        PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(changeText);
        stmt.setString(1, text);
        stmt.setInt(2, id);
        stmt.executeUpdate();
    }
    
    // Updates the correctness of a specific choice
    public static void updateChoiceCorrectness() throws SQLException {
        System.out.println("Enter the id of the choice: ");
        Scanner scan = new Scanner(System.in);
        int id = Integer.parseInt(scan.nextLine());
        
        System.out.println("Is this the correct choice? Type Y for yes, or type anything else for no.");
        Scanner scan2 = new Scanner(System.in);
        String str = scan2.nextLine();
        boolean correct = false;
        if (str.equals("Y"))
        {
            correct = true;
        }
        
        String changeCorrectness = "UPDATE choice SET is_correct = ? WHERE choice_id = ?";
        PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(changeCorrectness);
        stmt.setBoolean(1, correct);
        stmt.setInt(2, id);
        stmt.executeUpdate();
    }
    
    // Deletes a specific choice
    public static void deleteChoice() throws SQLException {
        System.out.println("Enter the id of the choice: ");
        Scanner scan = new Scanner(System.in);
        int id = Integer.parseInt(scan.nextLine());
        
        String deleteChoice = "DELETE FROM choice WHERE choice_id = ?";
        PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(deleteChoice);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}