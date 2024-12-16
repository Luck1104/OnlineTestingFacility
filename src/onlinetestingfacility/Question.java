package onlinetestingfacility;

import java.sql.*;
import java.util.Scanner;

/**
 * Represents a question in the online testing facility
 */
public class Question {
    // Creates a question with text and a score and inserts it into the database
    public static void createQuestion() throws SQLException {
        System.out.println("Enter the text for the question: ");
        Scanner scan = new Scanner(System.in);
        String text = scan.nextLine();
        
        System.out.println("Enter the amount of points the question is worth: ");
        Scanner scan2 = new Scanner(System.in);
        int score = Integer.parseInt(scan2.nextLine());
        
        String insertQuestion = "INSERT INTO question (question_text, score) "
                + "VALUES (?, ?)";
        PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(insertQuestion);
        stmt.setString(1, text);
        stmt.setInt(2, score);
        stmt.executeUpdate();
    }
    
    // Updates the text of a specific question
    public static void updateQuestionText() throws SQLException {
        System.out.println("Enter the id of the question: ");
        Scanner scan = new Scanner(System.in);
        int id = Integer.parseInt(scan.nextLine());
        
        System.out.println("Enter the new text for the question: ");
        Scanner scan2 = new Scanner(System.in);
        String text = scan2.nextLine();
        
        String changeText = "UPDATE question SET question_text = ? WHERE question_id = ?";
        PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(changeText);
        stmt.setString(1, text);
        stmt.setInt(2, id);
        stmt.executeUpdate();
    }
    
    // Updates the score of a specific question
    public static void updateQuestionScore() throws SQLException {
        System.out.println("Enter the id of the question: ");
        Scanner scan = new Scanner(System.in);
        int id = Integer.parseInt(scan.nextLine());
        
        System.out.println("Enter the new score for the question: ");
        Scanner scan2 = new Scanner(System.in);
        int score = Integer.parseInt(scan2.nextLine());
        
        String changeScore = "UPDATE question SET score = ? WHERE question_id = ?";
        PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(changeScore);
        stmt.setInt(1, score);
        stmt.setInt(2, id);
        stmt.executeUpdate();
    }
    
    // Deletes a specific question
    public static void deleteQuestion() throws SQLException {
        System.out.println("Enter the id of the question: ");
        Scanner scan = new Scanner(System.in);
        int id = Integer.parseInt(scan.nextLine());
        
        String deleteQuestion = "DELETE FROM question WHERE question_id = ?";
        PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(deleteQuestion);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
    
    // Adds a specific choice to a specific question
    public static void addChoice() throws SQLException {
        System.out.println("Enter the id of the question: ");
        Scanner scan = new Scanner(System.in);
        int question_id = Integer.parseInt(scan.nextLine());
        
        System.out.println("Enter the id of the choice: ");
        Scanner scan2 = new Scanner(System.in);
        int choice_id = Integer.parseInt(scan2.nextLine());
        
        String addChoice = "UPDATE choice SET question_id = ? WHERE choice_id = ?";
        PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(addChoice);
        stmt.setInt(1, question_id);
        stmt.setInt(2, choice_id);
        stmt.executeUpdate();
    }
}