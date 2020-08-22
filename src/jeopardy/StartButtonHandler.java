package jeopardy;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class StartButtonHandler implements EventHandler {
    private Stage _stage;
    private Controller _controller = new Controller();

    public StartButtonHandler(Stage stage) {
        _stage = stage;
        _controller.loadQuestions();
    }

    @Override
    public void handle(Event event) {
        Pane root = new Pane();
        Scene scene = new Scene(root, _stage.getWidth(), _stage.getHeight());

        _stage.setScene(scene);
        _stage.show();
    }
}
