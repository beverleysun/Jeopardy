package jeopardy;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Jeopardy extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Jeopardy");
        showStartScene(stage);
    }

    public void showStartScene(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 900, 600);

        Label welcomeLabel = new Label("Welcome to");
        Label jeopardyLabel = new Label("Jeopardy");
        Button startButton = new Button("Start");
        startButton.setPrefWidth(100);
        startButton.setOnAction(new StartButtonHandler(stage));

        // Bind position to the window dimensions
        welcomeLabel.layoutYProperty().bind(scene.heightProperty().divide(2).subtract(startButton.prefHeight(-1)+80));
        welcomeLabel.minWidthProperty().bind(scene.widthProperty());

        jeopardyLabel.layoutYProperty().bind(scene.heightProperty().divide(2).subtract(startButton.prefHeight(-1)+70));
        jeopardyLabel.minWidthProperty().bind(scene.widthProperty());

        startButton.layoutYProperty().bind(scene.heightProperty().divide(2).add(25));
        startButton.layoutXProperty().bind(scene.widthProperty().divide(2).subtract(startButton.prefWidth(-1)/2));

        // Set styles
        scene.getStylesheets().add("style.css");
        welcomeLabel.setId("start-welcome");
        jeopardyLabel.setId("start-jeopardy");
        startButton.setId("start-button");
        root.setId("start-background");

        root.getChildren().addAll(welcomeLabel, jeopardyLabel, startButton);

        stage.setScene(scene);
        stage.show();
    }
}