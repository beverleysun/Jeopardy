package jeopardy;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class QuestionButtonHandler extends ButtonHandler implements EventHandler {


    public QuestionButtonHandler(Stage stage, Scene scene) {
        super(stage, scene);
    }

    @Override
    public void handle(Event event) {

        // Extract information
        Button questionButton = (Button) event.getSource();
        String[] questionInfo = questionButton.getId().split(",");
        String category = questionInfo[0];
        String value = questionInfo[1];
        Question questionToAsk = _controller.findQuestion(category, value);

    }
}
