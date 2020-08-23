package jeopardy;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Jeopardy extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Pane(), 900, 600);
        SceneGenerator sceneGenerator = SceneGenerator.getInstance();
        Scene startScene = sceneGenerator.getStartScene(stage, scene);
        stage.setTitle("Jeopardy");
        Controller.getInstance().showScene(stage, startScene);
    }
}