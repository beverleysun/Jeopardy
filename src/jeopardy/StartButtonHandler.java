package jeopardy;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
        FlowPane root = new FlowPane();
        Scene scene = new Scene(root, _stage.getWidth(), _stage.getHeight());

        // Display category
        for (Category category : _questionData) {
            VBox categoryBox = new VBox();
            Label categoryButton = new Label(category.getCategoryName());
            categoryBox.getChildren().add(categoryButton);

            // Display question values under category name
            for (Question question : category.getQuestions()) {
                Button questionButton = new Button("$" + question.getValueString());

                // Disable question button if question already answered
                if (question.isCompleted()) {
                    questionButton.setDisable(true);

                }
                categoryBox.getChildren().add(questionButton);
            }
            root.getChildren().add(categoryBox);
        }

        _stage.setScene(scene);
        _stage.show();
    }
}
