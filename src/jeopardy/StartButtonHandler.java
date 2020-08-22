package jeopardy;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartButtonHandler implements EventHandler {
    private final Stage _stage;
    private final Scene _scene;
    private final Controller _controller = new Controller();

    public StartButtonHandler(Stage stage, Scene scene) {
        _stage = stage;
        _scene = scene;
        _controller.loadQuestions();

    }

    @Override
    public void handle(Event event) {
        Scene questionBoardScene = _controller.getQuestionBoardScene(_scene);
        _stage.setScene(questionBoardScene);
        _stage.show();
    }
}
