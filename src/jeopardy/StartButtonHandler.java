package jeopardy;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class StartButtonHandler implements EventHandler {
    private final Stage _stage;
    private final List<Category> _questionData;

    public StartButtonHandler(Stage stage) {
        _stage = stage;

        Controller controller = new Controller();
        controller.loadQuestions();

        _questionData = controller.getQuestionData();
    }

    @Override
    public void handle(Event event) {
        int margin = 70;

        GridPane root = new GridPane();
        root.setAlignment(Pos.TOP_CENTER);
        Scene scene = new Scene(root, _stage.getWidth(), _stage.getHeight());
        Label prompt = new Label("Choose a question");

        // Set gaps between cells
        root.setHgap(20);
        root.setVgap(20);

        prompt.prefWidthProperty().bind(_stage.widthProperty().subtract(margin));

        root.add(prompt, 0,0, _questionData.size(),1);

        int colIdx = 0;
        // Display category
        for (Category category : _questionData) {
            Label categoryLabel = new Label(category.getCategoryName().toUpperCase());
            categoryLabel.prefWidthProperty().bind(_stage.widthProperty().subtract(margin).divide(_questionData.size()));
            root.add(categoryLabel, colIdx, 1, 1, 1);

            int rowIdx = 2;

            // Display question values under category name
            for (Question question : category.getQuestions()) {
                Button questionButton = new Button("$" + question.getValueString());

                // Bind sizes of buttons to window size
                questionButton.prefWidthProperty().bind(_stage.widthProperty().subtract(margin).divide(_questionData.size()));
                questionButton.prefHeightProperty().bind(_stage.heightProperty().subtract(margin).divide(_questionData.get(0).getQuestions().size()+2));

                // Disable question button if question already answered
                if (question.isCompleted()) {
                    questionButton.setDisable(true);
                }
                root.add(questionButton, colIdx, rowIdx, 1, 1);
                rowIdx++;
            }
            colIdx++;
        }

        _stage.setScene(scene);
        _stage.show();
    }
}
