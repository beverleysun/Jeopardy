package jeopardy;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartButtonHandler extends ButtonHandler {

    public StartButtonHandler(Stage stage, Scene scene) {
        super(stage, scene);
    }

    @Override
    public void handle(ActionEvent event) {
        Scene questionBoardScene = _sceneGenerator.getQuestionBoardScene(_stage, _scene);
        _controller.showScene(_stage, questionBoardScene);
    }
}