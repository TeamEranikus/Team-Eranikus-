package sample.userInterface;

import javafx.scene.image.Image;

import java.io.*;
import java.util.LinkedList;

public class Reader {

    public LinkedList<String> readPuzzleFile() {
        LinkedList<String> lineFromFile = new LinkedList<>();
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(new File("EscapeCode/src/sample/resources/templates/Puzzles.txt")))) { //src/sample/resources/templates/Puzzles.txt
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

    public LinkedList<String> loadImages() {
        LinkedList<String> lineFromFile = new LinkedList<>();
        try (BufferedReader reader =
                     new BufferedReader(new FileReader("../resources/templates/Puzzles.txt"))) {
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



