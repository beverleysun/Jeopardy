package jeopardy;

import java.io.File;
import java.io.IOException;

public class Controller {
    private final File _saveFolder = new File("./.save");
    private final File _answeredFolder = new File("./.save/answered");
    private final File _winningsFile = new File("./.save/winnings");
    private final File _categoriesFolder = new File("./categories");

    public void loadQuestions() throws IOException {
        if(!_saveFolder.exists()) {
            // Create file structure
            _answeredFolder.mkdirs();
            _winningsFile.createNewFile();

            // Create folders for each category in the save folder
            for (String name : _categoriesFolder.list()) {
                new File("./.save/answered/" + name).mkdir();
            }
        }
    }
}