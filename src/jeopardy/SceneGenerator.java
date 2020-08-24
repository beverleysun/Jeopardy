package jeopardy;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
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
        resetButton.setOnAction(new ResetButtonHandler(stage, scene));

        // Set styles
        scene.getStylesheets().add("style.css");
        jeopardyLabel.setId("start-jeopardy");
        startButton.setId("start-button");
        resetButton.setId("reset-button");
        root.setId("background");

        // Add components to the scene
        root.addColumn(0, jeopardyLabel, startButton, resetButton);

        return scene;
    }

    public Scene getQuestionBoardScene(Stage stage, Scene scene) {
        int margin = 100;

        StackPane root = new StackPane();
        GridPane questionBoard = new GridPane();
        scene = new javafx.scene.Scene(root, scene.getWidth(), scene.getHeight());
        Label prompt = new Label("Choose a question");
        root.getChildren().add(questionBoard);

        // Add back button at top right corner
        Button backButton = new Button("Back");
        backButton.setOnAction(new BackButtonHandler(stage, scene));
        backButton.getStyleClass().add("back-button");
        StackPane.setAlignment(backButton, Pos.TOP_LEFT);
        StackPane.setMargin(backButton, new Insets(20, 20, 20, 20));
        root.getChildren().add(backButton);

        // Add winnings label at top right corner
        int winnings = _controller.getWinnings();
        Label winningsLabel = new Label("$" + winnings);

        if (winnings > 0) {
            winningsLabel.getStyleClass().add("winnings-pos");
        } else if (winnings < 0){
            winningsLabel.getStyleClass().add("winnings-neg");
        }
        winningsLabel.getStyleClass().add("winnings-label");
        StackPane.setAlignment(winningsLabel, Pos.TOP_RIGHT);
        StackPane.setMargin(winningsLabel, new Insets(20, 20, 20, 20));
        root.getChildren().add(winningsLabel);


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

                questionButton.setOnAction(new QuestionButtonHandler(stage, scene));

                // Bind sizes of buttons to window size
                questionButton.prefWidthProperty().bind(scene.widthProperty().subtract(margin).divide(_questionData.size()));
                questionButton.prefHeightProperty().bind(scene.heightProperty().subtract(margin).divide(_questionData.get(0).getQuestions().size()+2));
                questionButton.setMaxWidth(150);
                questionButton.setMaxHeight(50);
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

    public Scene getResetScene(Stage stage, Scene scene) {
        GridPane root = new GridPane();
        scene = new Scene(root, scene.getWidth(), scene.getHeight());

        // Components
        Label prompt = new Label("Are you sure?");
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        // Alignment of content
        root.setAlignment(Pos.CENTER);
        root.setVgap(20);
        root.setHgap(20);
        GridPane.setHalignment(yesButton, HPos.CENTER);
        GridPane.setHalignment(noButton, HPos.CENTER);
        GridPane.setHalignment(prompt, HPos.CENTER);

        // Buttons
        yesButton.setPrefWidth(100);
        yesButton.setOnAction(new ResetButtonHandler(stage, scene));

        noButton.setPrefWidth(100);
        noButton.setOnAction(new ResetButtonHandler(stage, scene));

        // Set styles
        scene.getStylesheets().add("style.css");
        prompt.getStyleClass().add("prompt");
        yesButton.setId("yes-button");
        noButton.setId("no-button");
        root.setId("background");

        // Add components to the scene
        root.add(prompt, 0,0,2,1);
        root.add(yesButton, 0, 1, 1, 1);
        root.add(noButton, 1, 1, 1, 1);

        return scene;
    }

    public Scene getAskQuestionScene(Stage stage, Scene scene, String categoryStr, Question question) {
        String questionStr = question.getQuestion();
        String value = question.getValueString();
        String answer = question.getAnswer();

        GridPane root = new GridPane();
        scene = new Scene(root, scene.getWidth(), scene.getHeight());




        return scene;
    }
}
