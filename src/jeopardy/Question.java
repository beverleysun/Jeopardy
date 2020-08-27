package jeopardy;

public class Question {
    private final String _question;
    private final String _answer;
    private final int _value;
    private boolean _completed;

    public Question(String question, String answer, int value, boolean completed) {
        _question = question;
        _answer = answer;
        _value = value;
        _completed = completed;
    }

    public String getQuestion() {
        return _question;
    }

    public String getAnswer() {
        return _answer;
    }

    public int getValue() {
        return _value;
    }

    public String getValueString() {
        return Integer.toString(_value);
    }

    public boolean isCompleted() {
        return _completed;
    }

    public void setCompleted(boolean completed) {
        _completed = completed;
    }
}