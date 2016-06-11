package sample.models;

import java.util.ArrayDeque;
import java.util.Deque;

import javafx.scene.image.Image;
import sample.userInterface.Reader;
import java.util.LinkedList;

public class PuzzleManager {

    private Deque<Puzzle> puzzleQue;
    private Reader reader;


    public PuzzleManager(Reader reader) {
        this.puzzleQue = new ArrayDeque<>();
        this.reader = reader;
    }

    public void load(){
        LinkedList<String> puzzleImages = new LinkedList<String>(){{
            add("../resources/templates/ComputerTaskWhite.png");
            add("../resources/templates/PianoTask.jpg");
            add("../resources/templates/LibraryWithJoker.jpg");
        }};
        LinkedList<String> puzzles = reader.readPuzzleFile();
        for (String puzzle : puzzles) {
            String[] line = puzzle.split("\\**");
            String description = line[0];
            String answer = line[1];
            String hint = line[2];
            String nextClue = line[3];
            Puzzle currentPuzzle = new Puzzle(description,answer, hint,puzzleImages.pop(), nextClue);
            puzzleQue.push(currentPuzzle);
        }

    }

}
