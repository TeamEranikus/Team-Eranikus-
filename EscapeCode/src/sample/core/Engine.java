package sample.core;

import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sample.models.PuzzleManager;
import sample.userInterface.Reader;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

import javafx.scene.media.AudioClip;

import java.net.URL;

public class Engine {

    private PuzzleManager puzzleManager;
    private Reader reader;
    private HashMap<KeyCode, Boolean> keys;
    private LinkedList<Rectangle> rectangles;
    private Rectangle currentPuzzle;
    private boolean hasCol = false;
    private AnimationTimer timeline;
    private Sprite sprite;

    private AudioClip iSound0, iSound1, iSound2, iSound3;
    private URL iAudioFile0, iAudioFile1, iAudioFile2, iAudioFile3;


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
        puzzleManager.load();


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

            //TODO try to write new method to pop new window
            //TODO check for correct answer and set the constants of paths
            if (!currentPuzzle.getId().contains("door")) {
                try {
                    puzzleManager.setPuzzle();
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("../scenes/demoLevel/puzzles/puzzles.fxml"));
                    Scene scene = new Scene(root);
                    stage.initOwner(currentPuzzle.getScene().getWindow());
                    stage.setScene(scene);
                    stage.show();
                    currentPuzzle = getCurrentPuzzleRectangle();
                } catch (IOException e) {

                }
            }else {
                //TODO show win message

            }


        }
    }


    private void playAudioClip() {
        if (isPressed(KeyCode.LEFT)) {
            playiSound0();
        }
        if (isPressed(KeyCode.RIGHT)) {
            playiSound1();
        }
        if (isPressed(KeyCode.UP)) {
            playiSound2();
        }
        if (isPressed(KeyCode.DOWN)) {
            playiSound3();
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

    private void loadAudioAssets() {
        iAudioFile0 = getClass().getResource("../data/sounds/leftmono.wav");
        iSound0 = new AudioClip(iAudioFile0.toString());
        iAudioFile1 = getClass().getResource("../data/sounds/rightmono.wav");
        iSound1 = new AudioClip(iAudioFile1.toString());
        iAudioFile2 = getClass().getResource("../data/sounds/upmono.wav");
        iSound2 = new AudioClip(iAudioFile2.toString());
        iAudioFile3 = getClass().getResource("../data/sounds/downmono.wav");
        iSound3 = new AudioClip(iAudioFile3.toString());
    }

    public void playiSound0() {
        iSound0.play();
    }

    public void playiSound1() {
        this.iSound1.play();
    }

    public void playiSound2() {
        this.iSound2.play();
    }

    public void playiSound3() {
        this.iSound3.play();
    }


}
