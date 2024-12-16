package onlinetestingfacility;

import java.sql.*;
import java.util.Scanner;

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
}