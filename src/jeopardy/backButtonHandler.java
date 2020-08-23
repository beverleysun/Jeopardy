package jeopardy;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class backButtonHandler implements EventHandler {
    private final Stage _stage;
    private final Scene _scene;

    public backButtonHandler(Stage stage, Scene scene) {
        _stage = stage;
        _scene = scene;
    }

    @Override
    public void handle(Event event) {
        SceneGenerator sceneGenerator = SceneGenerator.getInstance();
        Scene startScene = sceneGenerator.getStartScene(_stage, _scene);
        Controller.showScene(_stage, startScene);
    }
}
