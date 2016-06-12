package sample.core;

import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import sample.models.PuzzleManager;
import sample.userInterface.Reader;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;
import javafx.scene.media.AudioClip;

public class Engine{

    private PuzzleManager puzzleManager;
    private Reader reader;
    private HashMap<KeyCode, Boolean> keys;
    private LinkedList<Rectangle> rectangles;
    private Rectangle currentPuzzle;
    private boolean hasCol = false;
    private AnimationTimer timeline;
    private Sprite sprite;

    public Engine(Sprite sprite) {
        this.reader = new Reader();
        this.puzzleManager = new PuzzleManager(reader);
        this.keys = new HashMap<>();
        this.sprite = sprite;
        this.rectangles = new LinkedList<>();
    }

    public void run(Scene scene) throws IOException {
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));


        timeline = new AnimationTimer() {
            @Override
            public void handle(long now) {
               update();
                checkForCol(currentPuzzle);
            }
        };
        timeline.start();
    }

    private void update() {
        if (isPressed(KeyCode.UP)) {
            sprite.moveY(-2);
        } else if (isPressed(KeyCode.DOWN)) {
            sprite.moveY(2);
        } else if (isPressed(KeyCode.RIGHT)) {
            sprite.moveX(2);
        } else if (isPressed(KeyCode.LEFT)) {
            sprite.moveX(-2);
        }

        if (checkForCol(currentPuzzle)) {
            currentPuzzle.setVisible(false);
            currentPuzzle.setDisable(true);
            hasCol = true;

            currentPuzzle = getCurrentPuzzleRectangle();
        }
    }

    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);

    }

    public void loadRectangles(Pane pane) {

        ObservableList<Node> listOfAllElements = pane.getChildren();
        for (Node element : listOfAllElements) {
            if (element != null && element.getId().endsWith("Puzzle")) {
                Rectangle current = (Rectangle) element;
                current.setDisable(true);
                current.setVisible(false);
                rectangles.add(current);
            }
        }
        rectangles = rectangles.stream()
                .sorted((a, b) -> a.getId().compareTo(b.getId()))
                .collect(Collectors.toCollection(LinkedList<Rectangle>::new));
        currentPuzzle = getCurrentPuzzleRectangle();
    }

    private Rectangle getCurrentPuzzleRectangle() {
        if (rectangles.size() > 0) {
            Rectangle current = rectangles.removeFirst();
            current.setVisible(true);
            current.setDisable(false);
            return current;
        }
        return new Rectangle();
    }

    private boolean checkForCol(Rectangle current) {
        if (current.isDisabled()) {
            return false;
        }
        return current.getBoundsInParent().intersects(sprite.getImageView().getBoundsInParent());
    }
}
