package jeopardy;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class SceneGenerator {
    private static Controller _controller = new Controller();
    private static List<Category> _questionData = _controller.getQuestionData();

    public static Scene getStartScene(Stage stage, Scene scene) {
        GridPane root = new GridPane();
        scene = new Scene(root, scene.getWidth(), scene.getHeight());

        // Components
        Label jeopardyLabel = new Label("JEOPARDY");
        Button startButton = new Button("Start");
        Button resetButton = new Button("Reset");

        // Alignment of content
        root.setAlignment(Pos.CENTER);
        root.setVgap(20);
        GridPane.setHalignment(startButton, HPos.CENTER);
        GridPane.setHalignment(resetButton, HPos.CENTER);
        GridPane.setHalignment(jeopardyLabel, HPos.CENTER);

        // Buttons
        startButton.setPrefWidth(100);
        startButton.setOnAction(new StartButtonHandler(stage, scene));

        resetButton.setPrefWidth(100);

        // Set styles
        scene.getStylesheets().add("style.css");
        jeopardyLabel.setId("start-jeopardy");
        startButton.setId("start-button");
        resetButton.setId("start-button");
        root.setId("background");

        // Add components to the scene
        root.addColumn(0, jeopardyLabel, startButton, resetButton);

        return scene;
    }

    public static Scene getQuestionBoardScene(Stage stage, Scene scene) {
        int margin = 70;

        GridPane root = new GridPane();
        scene = new javafx.scene.Scene(root, scene.getWidth(), scene.getHeight());
        Label prompt = new Label("Choose a question");

        // Set gaps between cells
        root.setHgap(20);
        root.setVgap(20);
        root.add(prompt, 0,0, _questionData.size(),1);
        root.setAlignment(Pos.CENTER);

        prompt.prefWidthProperty().bind(scene.widthProperty().subtract(margin).add(20));
        prompt.setPrefHeight(50);
        prompt.getStyleClass().add("prompt");

        // Display category
        int colIdx = 0;
        for (Category category : _questionData) {
            Label categoryLabel = new Label(category.getCategoryName().toUpperCase());
            categoryLabel.prefWidthProperty().bind(scene.widthProperty().subtract(margin).divide(_questionData.size()));
            categoryLabel.getStyleClass().add("category-label");

            root.add(categoryLabel, colIdx, 1, 1, 1);

            int rowIdx = 2;

            // Display question values under category name
            for (Question question : category.getQuestions()) {
                Button questionButton = new Button("$" + question.getValueString());
                questionButton.setOnAction(new QuestionButtonHandler(stage, scene));

                // Set unique IDs
                questionButton.setId(category.getCategoryName() + "," + question.getValue());
                questionButton.getStyleClass().add("question-button");

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
