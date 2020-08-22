package jeopardy;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
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

    public void loadQuestions() {

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

    public Scene getQuestionBoardScene(Scene scene) {
        int margin = 70;

        GridPane root = new GridPane();
        scene = new Scene(root, scene.getWidth(), scene.getHeight());
        Label prompt = new Label("Choose a question");

        // Set gaps between cells
        root.setHgap(20);
        root.setVgap(20);
        root.add(prompt, 0,0, _questionData.size(),1);
        root.setAlignment(Pos.TOP_CENTER);

        prompt.prefWidthProperty().bind(scene.widthProperty().subtract(margin));

        // Display category
        int colIdx = 0;
        for (Category category : _questionData) {
            Label categoryLabel = new Label(category.getCategoryName().toUpperCase());
            categoryLabel.prefWidthProperty().bind(scene.widthProperty().subtract(margin).divide(_questionData.size()));
            root.add(categoryLabel, colIdx, 1, 1, 1);

            int rowIdx = 2;

            // Display question values under category name
            for (Question question : category.getQuestions()) {
                Button questionButton = new Button("$" + question.getValueString());

                // Bind sizes of buttons to window size
                questionButton.prefWidthProperty().bind(scene.widthProperty().subtract(margin).divide(_questionData.size()));
                questionButton.prefHeightProperty().bind(scene.heightProperty().subtract(margin).divide(_questionData.get(0).getQuestions().size()+2));

                // Disable question button if question already answered
                if (question.isCompleted()) {
                    questionButton.setDisable(true);
                }
                root.add(questionButton, colIdx, rowIdx, 1, 1);
                rowIdx++;
            }
            colIdx++;
        }
        root.setId("background");
        scene.getStylesheets().add("style.css");

        return scene;
    }
}