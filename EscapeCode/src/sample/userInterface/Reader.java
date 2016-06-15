package sample.userInterface;

import sample.utils.Constants;

import java.io.*;
import java.util.LinkedList;

public class Reader {

    public LinkedList<String> readPuzzleFile() {
        LinkedList<String> lineFromFile = new LinkedList<>();
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(new File(Constants.PUZZLE_FILE_PATH)))) { //src/sample/resources/templates/Puzzles.txt
            String line;
            while ((line = reader.readLine()) != null){
                lineFromFile.add(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Reader wrong parameters");
        }

        return lineFromFile;
    }
    
}



