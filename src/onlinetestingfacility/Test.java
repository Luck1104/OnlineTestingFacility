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
    
    /**
     * Returns the number of users who have taken a specific test
     * @param testId ID of the test
     * @throws SQLException if database operation fails
     */
    public static int getTestTakerCount(int testId) throws SQLException {
        if (testId == -1) return -1;
        String selectTakerCount = "SELECT t.test_name, COUNT(DISTINCT pt.person_id) as total_takers " +
                "FROM person_tests pt " +
                "JOIN test t ON pt.test_id = t.test_id " +
                "WHERE pt.test_id = ? " +
                "GROUP BY t.test_name";
        
        PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(selectTakerCount);
        stmt.setInt(1, testId);
        
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) return rs.getInt("total_takers");
        return -1;
    }
    
    /**
     * Returns the highest score for a specific test
     * @param testId ID of the test
     * @throws SQLException if database operation fails
     */
    public static int getHighestTestScore(int testId) throws SQLException {
        if (testId == -1) return -1;
        String selectHighestScore = "SELECT MAX(test_score) as highest_score " +
                "FROM person_tests " +
                "WHERE test_id = ? ";
        
        PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(selectHighestScore);
        stmt.setInt(1, testId);
        
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) return rs.getInt("highest_score");
        return -1;
    }
    
    /**
     * Returns the username of who has the highest score for a specific test
     * @param testId ID of the test
     * @throws SQLException if database operation fails
     */
    public static String getHighestTestScoreTaker(int testId) throws SQLException {
        if (testId == -1) return null;
        String selectHighestScore = "SELECT p.username FROM person_tests pt " +
                "JOIN person p ON pt.person_id = p.person_id " +
                "WHERE pt.test_score = (SELECT MAX(test_score) "
                + "FROM person_tests) AND pt.test_id = ?";
        
        PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(selectHighestScore);
        stmt.setInt(1, testId);
        
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) return rs.getString("username");
        return null;
    }
    
    /**
     * Returns the average score for a specific test
     * @param testId ID of the test
     * @throws SQLException if database operation fails
     */
    public static int getAverageTestScore(int testId) throws SQLException {
        if (testId == -1) return -1;
        String selectAverageScore = "SELECT AVG(test_score) as average_score " +
                "FROM person_tests " +
                "WHERE test_id = ? ";
        
        PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(selectAverageScore);
        stmt.setInt(1, testId);
        
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) return rs.getInt("average_score");
        return -1;
    }
    
    /**
     * Returns score for a specific user and test
     * @param personId ID of the person
     * @param testId ID of the test
     * @throws SQLException if database operation fails
     */
    public static int getUserTestScore(int personId, int testId) throws SQLException {
        if (testId == -1) return -1;
        String selectScore = "SELECT p.username, t.test_name, pt.test_score " +
                "FROM person_tests pt " +
                "JOIN person p ON pt.person_id = p.person_id " +
                "JOIN test t ON pt.test_id = t.test_id " +
                "WHERE pt.person_id = ? AND pt.test_id = ?";
        
        PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(selectScore);
        stmt.setInt(1, personId);
        stmt.setInt(2, testId);
        
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) return rs.getInt("test_score");
        return -1;
    }
}