package jeopardy;

import javafx.event.Event;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BackButtonHandler extends ButtonHandler {

    public BackButtonHandler(Stage stage, Scene scene) {
        super(stage, scene);
    }

    @Override
    public void handle(Event event) {
        Scene startScene = _sceneGenerator.getStartScene(_stage, _scene);
        _controller.showScene(_stage, startScene);
    }
}