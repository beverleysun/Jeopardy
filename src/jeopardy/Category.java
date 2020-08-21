package jeopardy;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String _categoryName;
    private List<Question> _questions = new ArrayList<Question>();

    public Category(String categoryName){
        _categoryName = categoryName;
    }

    public void addQuestion(Question question) {
        _questions.add(question);
    }
}