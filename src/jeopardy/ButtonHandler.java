package jeopardy;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class ButtonHandler implements EventHandler {

    protected final Stage _stage;
    protected final Scene _scene;
    protected final SceneGenerator _sceneGenerator = SceneGenerator.getInstance();

    public ButtonHandler(Stage stage, Scene scene) {
        _stage = stage;
        _scene = scene;
    }

}
