package jeopardy;

import javafx.event.Event;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ConfirmButtonHandler extends ButtonHandler {

    private String _realAnswer;
    private String _givenAnswer;
    private int _value;

    public ConfirmButtonHandler(Stage stage, Scene scene, String realAnswer, String givenAnswer, int value) {
        super(stage, scene);
        _realAnswer = realAnswer;
        _givenAnswer = givenAnswer;
        _value = value;
    }

    @Override
    public void handle(Event event) {
        validateAnswer();


    }

    public void validateAnswer() {
        Scene rightWrongScene;
        if (_realAnswer.toLowerCase().trim().equals(_givenAnswer.toLowerCase().trim())) {
            _controller.addWinnings(_value);
        } else {
            _controller.addWinnings(_value*-1);

        }
    }
}
