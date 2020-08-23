package jeopardy;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.List;

public class SceneGenerator {
    private Controller _controller = Controller.getInstance();
    private List<Category> _questionData = _controller.getQuestionData();

    private SceneGenerator(){}

    private static SceneGenerator _sceneGenerator;

    public static SceneGenerator getInstance() {
        if (_sceneGenerator == null) {
            _sceneGenerator = new SceneGenerator();
        }
        return _sceneGenerator;
    }

    public Scene getStartScene(Stage stage, Scene scene) {
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

    public Scene getQuestionBoardScene(Stage stage, Scene scene) {
        int margin = 70;

        StackPane root = new StackPane();
        GridPane questionBoard = new GridPane();
        scene = new javafx.scene.Scene(root, scene.getWidth(), scene.getHeight());
        Label prompt = new Label("Choose a question");
        root.getChildren().add(questionBoard);

        Button backButton = new Button("Back");
        backButton.setOnAction(new backButtonHandler(stage, scene));
        backButton.getStyleClass().add("back-button");
        StackPane.setAlignment(backButton, Pos.TOP_LEFT);
        StackPane.setMargin(backButton, new Insets(20, 20, 20, 20));
        root.getChildren().add(backButton);

        // Set gaps between cells
        questionBoard.setHgap(20);
        questionBoard.setVgap(20);
        questionBoard.add(prompt, 0,0, _questionData.size(),1);
        questionBoard.setAlignment(Pos.CENTER);

        GridPane.setHalignment(prompt, HPos.CENTER);
        prompt.getStyleClass().add("prompt");

        // Display category
        int colIdx = 0;
        for (Category category : _questionData) {
            Label categoryLabel = new Label(category.getCategoryName().toUpperCase());
            GridPane.setHalignment(categoryLabel, HPos.CENTER);
            categoryLabel.getStyleClass().add("category-label");

            questionBoard.add(categoryLabel, colIdx, 1, 1, 1);

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
                questionButton.setMaxWidth(300);
                questionButton.setMaxHeight(70);
                GridPane.setHalignment(questionButton, HPos.CENTER);

                // Disable question button if question already answered
                if (question.isCompleted()) {
                    questionButton.setDisable(true);
                }
                questionBoard.add(questionButton, colIdx, rowIdx, 1, 1);
                rowIdx++;
            }
            colIdx++;
        }
        root.setId("background");
        scene.getStylesheets().add("style.css");

        return scene;
    }

    public Scene getAskQuestionScene(Stage stage, Scene scene, Question question ) {
        return new Scene(new Pane(), 700, 700);
    }
}
