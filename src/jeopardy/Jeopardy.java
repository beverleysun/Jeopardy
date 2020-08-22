package jeopardy;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
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
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 900, 600);

        // Components
        Label jeopardyLabel = new Label("JEOPARDY");
        Button startButton = new Button("Start");
        Button resetButton = new Button("Reset");

        // Alignment of content
        root.setAlignment(Pos.CENTER);
        root.setVgap(20);
        GridPane.setHalignment(startButton, HPos.CENTER);
        GridPane.setHalignment(resetButton, HPos.CENTER);
        GridPane.setHalignment(jeopardyLabel, HPos.CENTER);

        // Buttons
        startButton.setPrefWidth(100);
        startButton.setOnAction(new StartButtonHandler(stage, scene));

        resetButton.setPrefWidth(100);

        // Set styles
        scene.getStylesheets().add("style.css");
        jeopardyLabel.setId("start-jeopardy");
        startButton.setId("start-button");
        resetButton.setId("start-button");
        root.setId("background");

        // Add components to the scene
        root.addColumn(0, jeopardyLabel, startButton, resetButton);

        stage.setScene(scene);
        stage.show();
    }
}