/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlinetestingfacility;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a person in the online testing facility
 */
public class Person {
    // Attributes corresponding to the person table in the database
    private int personId;
    private String username;
    private String password;
    private boolean isTestCreator;

    // List to store tests taken by the person
    private List<Test> testsTaken;

    // Default constructor
    public Person() {
        this.testsTaken = new ArrayList<>();
    }

    // Parameterized constructor
    public Person(int personId, String username, String password, boolean isTestCreator) {
        this.personId = personId;
        this.username = username;
        this.password = password;
        this.isTestCreator = isTestCreator;
        this.testsTaken = new ArrayList<>();
    }

    // Getters and setters
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isTestCreator() {
        return isTestCreator;
    }

    public void setTestCreator(boolean testCreator) {
        isTestCreator = testCreator;
    }

    public List<Test> getTestsTaken() {
        return testsTaken;
    }

    public void setTestsTaken(List<Test> testsTaken) {
        this.testsTaken = testsTaken;
    }

    // Method to add a test to taken tests
    public void addTestTaken(Test test) {
        this.testsTaken.add(test);
    }

    // Method to get highest score for a specific test
    public int getHighestScoreForTest(Test test) {
        return testsTaken.stream()
                .filter(t -> t.getTestId() == test.getTestId())
                .mapToInt(Test::getTotalScore)
                .max()
                .orElse(0);
    }

    // toString method for easy printing and debugging
    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", username='" + username + '\'' +
                ", isTestCreator=" + isTestCreator +
                ", testsTaken=" + testsTaken +
                '}';
    }
}