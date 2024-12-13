/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlinetestingfacility;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a test in the online testing facility
 */
public class Test {
    // Attributes corresponding to the test table in the database
    private int testId;
    private int creatorId;
    private String testName;
    private int totalScore;

    // List to store questions in the test
    private List<Question> questions;

    // Default constructor
    public Test() {
        this.questions = new ArrayList<>();
    }

    // Parameterized constructor
    public Test(int testId, int creatorId, String testName, int totalScore) {
        this.testId = testId;
        this.creatorId = creatorId;
        this.testName = testName;
        this.totalScore = totalScore;
        this.questions = new ArrayList<>();
    }

    // Getters and setters
    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    // Method to add a question to the test
    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    // Method to calculate total score from questions
    public int calculateTotalScore() {
        return questions.stream()
                .mapToInt(Question::getScore)
                .sum();
    }

    // toString method for easy printing and debugging
    @Override
    public String toString() {
        return "Test{" +
                "testId=" + testId +
                ", creatorId=" + creatorId +
                ", testName='" + testName + '\'' +
                ", totalScore=" + totalScore +
                ", questions=" + questions +
                '}';
    }
}
