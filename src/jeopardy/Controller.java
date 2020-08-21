package jeopardy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Controller {
    private final File _saveFolder = new File("./.save");
    private final File _answeredFolder = new File("./.save/answered");
    private final File _winningsFile = new File("./.save/winnings");
    private final File _categoriesFolder = new File("./categories");
    private final File[] _categoryFiles = _categoriesFolder.listFiles();

    public void loadQuestions() throws IOException {

        // Create file structure if it doesn't exist
        if(!_saveFolder.exists()) {
            _answeredFolder.mkdirs();
            _winningsFile.createNewFile();

            // Create folders for each category in the save folder
            for (String name : _categoriesFolder.list()) {
                new File("./.save/answered/" + name).mkdir();
            }
        }

        for(File categoryFile : _categoryFiles) {
            BufferedReader reader = new BufferedReader(new FileReader(categoryFile));
            String questionLine;

            while((questionLine = reader.readLine()) != null) {
                String[] questionData = questionLine.split(",");
                int value = Integer.parseInt(questionData[0].trim());
                String question = questionData[1].trim();
                String answer = questionData[2].trim();
            }
        }
    }
}