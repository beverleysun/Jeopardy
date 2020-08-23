package jeopardy;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartButtonHandler implements EventHandler {
    private final Stage _stage;
    private final Scene _scene;

    public StartButtonHandler(Stage stage, Scene scene) {
        _stage = stage;
        _scene = scene;
    }

    @Override
    public void handle(Event event) {
        SceneGenerator sceneGenerator = SceneGenerator.getInstance();
        Scene questionBoardScene = sceneGenerator.getQuestionBoardScene(_stage, _scene);
        Controller.showScene(_stage, questionBoardScene);
    }
}