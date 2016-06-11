package sample.scenes.demoLevel;

import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class DemoController implements Initializable{

    @FXML
    private ImageView imagePlayer;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Canvas mainCanvas;
    private GraphicsContext gc;
    private Image image;
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    private boolean hasCol = false;
    private AnimationTimer animationTimer;
    private LinkedList<Rectangle> rectangles;
    private Rectangle currentPuzzle;

    //Try to use Hbox with buttons for the bottom bar

    public void onMouseClickKeyItem(Event event) {

    }

    public void onMouseClickPapierItem(Event event) {

    }

    public void onMouseClickBookItem(Event event) {

    }

    public void initGraphics() {

        gc = mainCanvas.getGraphicsContext2D();
//        image = new Image(getClass().getResource("../../resources/templates/2.png").toExternalForm());
//        gc.drawImage(image, 10, 10);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initGraphics();
        rectangles = new LinkedList<>();
        loadRectangles();
    }

    public void moveCharacter(Scene scene) {

        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));


        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                checkForCol(currentPuzzle);
            }
        };
        animationTimer.start();
    }

    private void loadRectangles() {

        ObservableList<Node> listOfAllElements = anchorPane.getChildren();
        for (Node element : listOfAllElements) {
            if (element != null && element.getId().endsWith("Puzzle")) {
                Rectangle current = (Rectangle) element;
                current.setDisable(true);
                current.setVisible(false);
                rectangles.add(current);
            }
        }
        rectangles = rectangles.stream()
                .sorted((a,b) -> a.getId().compareTo(b.getId()))
                .collect(Collectors.toCollection(LinkedList<Rectangle> :: new));
        currentPuzzle = getCurrentPuzzleRectangle();
    }

    private Rectangle getCurrentPuzzleRectangle() {
        if (rectangles.size() > 0){
            Rectangle current = rectangles.removeFirst();
            current.setVisible(true);
            current.setDisable(false);
            return current;
        }
        return new Rectangle();
    }

    private boolean checkForCol(Rectangle current) {
        if (current.isDisabled()){
            return false;
        }
        return current.getBoundsInParent().intersects(imagePlayer.getBoundsInParent());
    }

    private void update() {
        if (isPressed(KeyCode.UP)) {
            moveY(-2);
        } else if (isPressed(KeyCode.DOWN)) {
            moveY(2);
        } else if (isPressed(KeyCode.RIGHT)) {
            moveX(2);
        } else if (isPressed(KeyCode.LEFT)) {
            moveX(-2);
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

    private void moveX(int x) {

        boolean right = x > 0 ? true : false;
        for (int i = 0; i < Math.abs(x); i++) {
            if (right) {
                double rightBound = mainCanvas.getLayoutX() + mainCanvas.getWidth() - (imagePlayer.getX()+imagePlayer.getFitWidth());
                imagePlayer.setLayoutX(imagePlayer.getLayoutX() + 1 > rightBound ?
                        rightBound : imagePlayer.getLayoutX() + 1);
                Image img = new Image(getClass().getResource("../../resources/templates/emoticon_right.png").toExternalForm());
                imagePlayer.setImage(img);

            } else {

                double leftBound = mainCanvas.getLayoutX();
                imagePlayer.setLayoutX(imagePlayer.getLayoutX() - 1 < leftBound ?
                        leftBound : imagePlayer.getLayoutX() - 1);
                Image img = new Image(getClass().getResource("../../resources/templates/emoticon_left.png").toExternalForm());
                imagePlayer.setImage(img);
            }
        }
    }

    private void moveY(int y) {

        boolean down = y > 0 ? true : false;
        for (int i = 0; i < Math.abs(y); i++) {
            if (down) {

                double downBound = mainCanvas.getLayoutY() + mainCanvas.getHeight() - (imagePlayer.getY()+imagePlayer.getFitHeight());
                imagePlayer.setLayoutY(imagePlayer.getLayoutY() + 1 > downBound ?
                        downBound : imagePlayer.getLayoutY() + 1);
            } else {

                double upBound = mainCanvas.getLayoutY();
                imagePlayer.setLayoutY(imagePlayer.getLayoutY() - 1 < upBound ?
                        upBound : imagePlayer.getLayoutY() - 1);
            }
        }
    }

}
