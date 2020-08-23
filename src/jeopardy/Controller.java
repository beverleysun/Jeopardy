package jeopardy;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final File _saveFolder = new File("./.save");
    private final File _answeredFolder = new File("./.save/answered");
    private final File _winningsFolder = new File("./.save/winnings");
    private final File _categoriesFolder = new File("./categories");
    private final File[] _categoryFiles = _categoriesFolder.listFiles();

    private final List<Category> _questionData = new ArrayList<>();

    private static Controller _controller;

    private Controller() {
        loadQuestions();
    }

    public static Controller getInstance() {
        if (_controller == null ) {
            _controller = new Controller();
        }

        return _controller;
    }

    private void loadQuestions() {

        try {
            createFileStructure();

            for(File categoryFile : _categoryFiles) {
                // Read every line of category file
                BufferedReader reader = new BufferedReader(new FileReader(categoryFile));
                Category category = new Category(categoryFile.getName());
                String questionLine;

                while((questionLine = reader.readLine()) != null) {
                    String[] questionData = questionLine.split(",");

                    // Extract information from the question line
                    int value = Integer.parseInt(questionData[0].trim());
                    String question = questionData[1].trim();
                    String answer = questionData[2].trim();

                    // Check if question has been answered
                    boolean answered = isAnswered(categoryFile.getName(), value);

                    category.addQuestion(new Question(question, answer, value, answered));
                }

                _questionData.add(category);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFileStructure() throws IOException {
        if(!_saveFolder.exists()) {
            _answeredFolder.mkdirs();
            _winningsFolder.mkdir();
            new File("./.save/winnings/0").createNewFile();

            // Create folders for each category in the save folder
            for (String name : _categoriesFolder.list()) {
                new File("./.save/answered/" + name).mkdir();
            }
        }
    }

    private boolean isAnswered(String category, int value) {
        return new File("./.save/answered/" + category + "/" + value).exists();
    }

    public List<Category> getQuestionData() {
        return _questionData;
    }

    public void showScene(Stage stage, Scene scene) {
        stage.setScene(scene);
        stage.show();
    }

    public int getWinnings() {
        String[] winnings = _winningsFolder.list();
        return Integer.parseInt(winnings[0]);
    }
}