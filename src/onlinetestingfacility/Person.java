/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlinetestingfacility;

import java.sql.*;

/**
 *
 * @author aiden
 */
public class Person {
    
    private int person_id;
    private String username;
    private String password;
    private boolean is_test_creator;
    
    public Person () {}
    
    public Person (int id, String username, String password, boolean is_test_creator) {
        this.person_id = id;
        this.username = username;
        this.password = password;
        this.is_test_creator = is_test_creator;
    }
    
    public static Person getPersonByUsername (String person_username) throws SQLException {
        String select = "SELECT * FROM person WHERE username = ?";
        
        PreparedStatement stmtSelect = DatabaseManager.getConnection().prepareStatement(select);
        stmtSelect.setString(1, person_username);
        ResultSet rsOne = stmtSelect.executeQuery();
        
        Person person = null;
        while (rsOne.next()) {
            person = new Person(rsOne.getInt("person_id"),
                rsOne.getString("username"),
                rsOne.getString("passwrd"),
                rsOne.getBoolean("is_test_creater"));
        }
        return person;
    }
    
    public int insert() throws SQLException{
        String insertPerson = "INSERT INTO person (username, passwrd, is_test_creator) "
                + "values (?, ?, ?)";
        
        PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(insertPerson);
        stmt.setString(1, this.getUsername());
        stmt.setString(2, this.getPassword());
        stmt.setBoolean(3, this.getTestCreator());
        
        int count = stmt.executeUpdate();

        return count;
    }

    /**
     * @return the person_id
     */
    public int getPerson_id() {
        return person_id;
    }

    /**
     * @param person_id the person_id to set
     */
//    public void setPerson_id(int person_id) {
//        this.person_id = person_id;
//    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the is_test_creator
     */
    public Boolean getTestCreator() {
        return is_test_creator;
    }

    /**
     * @param is_test_creator the is_test_creator to set
     */
    public void setTestCreator(Boolean is_test_creator) {
        this.is_test_creator = is_test_creator;
    }
    
    public void deletePerson() throws SQLException{
        String insertPerson = "DELETE FROM person WHERE person_id = ?";
        
        PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(insertPerson);
        stmt.setInt(1, this.getPerson_id());
        
        stmt.executeUpdate();
    }
}
