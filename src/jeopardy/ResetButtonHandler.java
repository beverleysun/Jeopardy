package jeopardy;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class ResetButtonHandler extends ButtonHandler implements EventHandler {

    public ResetButtonHandler(Stage stage, Scene scene) {
        super(stage, scene);
    }

    @Override
    public void handle(Event event) {
        Button button = (Button) event.getSource();
        if (button.getId().equals("reset-button")) {
            Scene resetScene = SceneGenerator.getInstance().getResetScene(_stage, _scene);
            Controller.getInstance().showScene(_stage, resetScene);
        } else if (button.getId().equals("yes-button")) {
            Controller.getInstance().reset();
            Scene startScene = SceneGenerator.getInstance().getStartScene(_stage, _scene);
            Controller.getInstance().showScene(_stage, startScene);
        } else {
            Scene startScene = SceneGenerator.getInstance().getStartScene(_stage, _scene);
            Controller.getInstance().showScene(_stage, startScene);
        }
    }
}