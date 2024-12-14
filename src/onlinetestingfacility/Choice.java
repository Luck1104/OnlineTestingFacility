package onlinetestingfacility;

/**
 * Represents a choice for a question in the online testing facility
 * /**
 * @author aiden 
 */
public class Choice {
    // Attributes corresponding to the choice table in the database
    private int choiceId;
    private int questionId;
    private String choiceText;
    private boolean isCorrect;

    // Default constructor
    public Choice() {
    }

    // Parameterized constructor
    public Choice(int choiceId, int questionId, String choiceText, boolean isCorrect) {
        this.choiceId = choiceId;
        this.questionId = questionId;
        this.choiceText = choiceText;
        this.isCorrect = isCorrect;
    }

    // Getters and setters
    public int getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(int choiceId) {
        this.choiceId = choiceId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getChoiceText() {
        return choiceText;
    }

    public void setChoiceText(String choiceText) {
        this.choiceText = choiceText;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    // toString method for easy printing and debugging
    @Override
    public String toString() {
        return "Choice{" +
                "choiceId=" + choiceId +
                ", questionId=" + questionId +
                ", choiceText='" + choiceText + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}