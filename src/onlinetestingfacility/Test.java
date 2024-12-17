package onlinetestingfacility;

import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents a test in the online testing facility
 */
public class Test {
    // Creates a test with a name and inserts it into the database
    public static void createTest(int creator_id) throws SQLException {
        System.out.println("Enter a name for the test: ");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
        
        String insertTest = "INSERT INTO test (creator_id, test_name) "
                + "VALUES (?, ?)";
        PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(insertTest);
        stmt.setInt(1, creator_id);
        stmt.setString(2, name);
        stmt.executeUpdate();
    }
    
    // Updates the name of a specific test
    public static void updateTestName() throws SQLException {
        System.out.println("Enter the id of the test: ");
        Scanner scan = new Scanner(System.in);
        int id = Integer.parseInt(scan.nextLine());
        
        System.out.println("Enter the new name for the test: ");
        Scanner scan2 = new Scanner(System.in);
        String name = scan2.nextLine();
        
        String changeName = "UPDATE test SET test_name = ? WHERE test_id = ?";
        PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(changeName);
        stmt.setString(1, name);
        stmt.setInt(2, id);
        stmt.executeUpdate();
    }
    
    // Deletes a specific test
    public static void deleteTest() throws SQLException {
        System.out.println("Enter the id of the test: ");
        Scanner scan = new Scanner(System.in);
        int id = Integer.parseInt(scan.nextLine());
        
        String deleteTest = "DELETE FROM test WHERE test_id = ?";
        PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(deleteTest);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
    
    // Links a specific question to a specific test
    public static void addQuestion() throws SQLException {
        System.out.println("Enter the id of the test: ");
        Scanner scan = new Scanner(System.in);
        int test_id = Integer.parseInt(scan.nextLine());
        
        System.out.println("Enter the id of the question: ");
        Scanner scan2 = new Scanner(System.in);
        int question_id = Integer.parseInt(scan2.nextLine());
        
        String addQuestion = "INSERT INTO test_questions (test_id, question_id) "
                + "VALUES (?, ?)";
        PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(addQuestion);
        stmt.setInt(1, test_id);
        stmt.setInt(2, question_id);
        stmt.executeUpdate();
    }
    
    // Displays all the tests
    public static void viewTests() throws SQLException {
        String viewTests = "SELECT * FROM test";
        
        PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(viewTests);
        ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                int test_id = rs.getInt("test_id");
                String test_name = rs.getString("test_name");
                System.out.println(test_id + " - " + test_name);
            }
    }
    
    // Allows a user to take a test and adds their score to the database
    public static void takeTest(int person_id) throws SQLException {
        int score_count = 0;
        int question_count = 1;
        
        viewTests();
            
        System.out.println("Enter the id of the test you wish to take: ");
        Scanner scan = new Scanner(System.in);
        int test_id = Integer.parseInt(scan.nextLine());
        
        System.out.println("");
        
        String viewQuestions = "SELECT question_id, score, question_text FROM test_with_questions WHERE test_id = ?";
        PreparedStatement stmt2 = DatabaseManager.getConnection().prepareStatement(viewQuestions);
        stmt2.setInt(1, test_id);
        ResultSet rs2 = stmt2.executeQuery();
        while(rs2.next()) {
                int question_id = rs2.getInt("question_id");
                int score = rs2.getInt("score");
                String question_text = rs2.getString("question_text");
                
                System.out.println(question_count + ". " + question_text);
                
                int choice_count = 1;
                ArrayList<String> choice_texts = new ArrayList<String>();
                
                String viewChoices = ("SELECT choice_text FROM questions_with_choices WHERE question_id = ?");
                PreparedStatement stmt3 = DatabaseManager.getConnection().prepareStatement(viewChoices);
                stmt3.setInt(1, question_id);
                ResultSet rs3 = stmt3.executeQuery();
                while(rs3.next()) {
                    String choice_text = rs3.getString("choice_text");
                    System.out.println(" " + choice_count + ". " + choice_text);
                    choice_count++;
                    choice_texts.add(choice_text);
                }
                
                System.out.println("Enter the number of your choice: ");
                Scanner scan2 = new Scanner(System.in);
                int chosen_choice = Integer.parseInt(scan2.nextLine());
                
                String chosen_choice_text = choice_texts.get(chosen_choice-1);
                String viewChoiceCorrectness = ("SELECT is_correct FROM questions_with_choices "
                        + "WHERE question_id = ? AND choice_text = ?");
                PreparedStatement stmt4 = DatabaseManager.getConnection().prepareStatement(viewChoiceCorrectness);
                stmt4.setInt(1, question_id);
                stmt4.setString(2, chosen_choice_text);
                ResultSet rs4 = stmt4.executeQuery();
                boolean is_correct = false;
                while(rs4.next()) {
                    is_correct = rs4.getBoolean("is_correct");
                }
                if(is_correct) {
                    score_count += score;
                }
                question_count++;
                System.out.println("");
            }
        
        int total_score = totalScore(test_id);
        System.out.println("You got " + score_count + " points out of " + total_score + ".");
        
        String insertScore = "INSERT INTO person_tests (person_id, test_id, test_score) "
                + "VALUES (?, ?, ?)";
        PreparedStatement stmt5 = DatabaseManager.getConnection().prepareStatement(insertScore);
        stmt5.setInt(1, person_id);
        stmt5.setInt(2, test_id);
        stmt5.setInt(3, total_score);
        stmt5.executeUpdate();
    }
    
    // Returns the total score of a specific test
    private static int totalScore(int test_id) throws SQLException {
        int score = 0;
        
        String viewScores = "SELECT score FROM test_with_questions WHERE test_id = ?";
        PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(viewScores);
        stmt.setInt(1, test_id);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            int question_score = rs.getInt("score");
            score += question_score;
        }
        
        return score;
    }
}