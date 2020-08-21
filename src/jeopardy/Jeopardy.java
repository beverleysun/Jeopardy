package jeopardy;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Jeopardy extends Application {

    public static void main(String[] args) throws IOException {
        Controller controller = new Controller();
        controller.loadQuestions();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Jeopardy");
        Group root = new Group();
        Scene scene = new Scene(root, 600, 400);

        Text jeopardyLabel = new Text("Welcome to Jeopardy!");
        Button startButton = new Button("Start");
        startButton.setPrefWidth(100);

        jeopardyLabel.layoutYProperty().bind(scene.heightProperty().divide(2).subtract(startButton.prefHeight(-1)+50));
        jeopardyLabel.layoutXProperty().bind(scene.widthProperty().divide(2).subtract(jeopardyLabel.prefWidth(-1)/2));

        startButton.layoutYProperty().bind(scene.heightProperty().divide(2));
        startButton.layoutXProperty().bind(scene.widthProperty().divide(2).subtract(startButton.prefWidth(-1)/2));

        root.getChildren().addAll(jeopardyLabel, startButton);

        stage.setScene(scene);

        stage.show();
    }
}