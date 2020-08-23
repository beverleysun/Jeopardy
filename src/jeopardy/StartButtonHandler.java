package jeopardy;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartButtonHandler extends ButtonHandler implements EventHandler {

    public StartButtonHandler(Stage stage, Scene scene) {
        super(stage, scene);
    }

    @Override
    public void handle(Event event) {
        Scene questionBoardScene = _sceneGenerator.getQuestionBoardScene(_stage, _scene);
        _controller.showScene(_stage, questionBoardScene);
    }
}