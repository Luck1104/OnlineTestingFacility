package onlinetestingfacility;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a question in the online testing facility
 */
public class Question {
    // Attributes corresponding to the question table in the database
    private int questionId;
    private String questionText;
    private int score;

    // List to store choices for this question
    private List<Choice> choices;

    // Default constructor
    public Question() {
        this.choices = new ArrayList<>();
    }

    // Parameterized constructor
    public Question(int questionId, String questionText, int score) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.score = score;
        this.choices = new ArrayList<>();
    }

    // Getters and setters
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    // Method to add a choice to the question
    public void addChoice(Choice choice) {
        this.choices.add(choice);
    }

    // Method to get the correct choice
    public Choice getCorrectChoice() {
        return choices.stream()
                .filter(Choice::isCorrect)
                .findFirst()
                .orElse(null);
    }

    // toString method for easy printing and debugging
    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", questionText='" + questionText + '\'' +
                ", score=" + score +
                ", choices=" + choices +
                '}';
    }
}