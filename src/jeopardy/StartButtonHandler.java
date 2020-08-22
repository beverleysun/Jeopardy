package jeopardy;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartButtonHandler implements EventHandler {
    private final Stage _stage;
    private final Controller _controller = new Controller();

    public StartButtonHandler(Stage stage) {
        _stage = stage;
        _controller.loadQuestions();

    }

    @Override
    public void handle(Event event) {
        Scene questionBoardScene = _controller.getQuestionBoardScene(_stage);
        _stage.setScene(questionBoardScene);
        _stage.show();
    }
}
