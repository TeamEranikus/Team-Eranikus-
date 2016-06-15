package sample.core;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite {

    private final Image RIGHT_IMAGE_VIEW = new Image(getClass().getResource("../resources/templates/emoticon_right.png").toExternalForm());
    private final Image LEFT_IMAGE_VIEW = new Image(getClass().getResource("../resources/templates/emoticon_left.png").toExternalForm());

    private ImageView imageView;
    private double positionX;
    private double positionY;
    private double width;
    private Canvas currentCanvas;
    private double height;

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Sprite(ImageView image, Canvas canvas) {
        this.imageView = image;
        this.currentCanvas = canvas;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;

    }

    public void moveX(int x) {

        boolean right = x > 0 ? true : false;
        for (int i = 0; i < Math.abs(x); i++) {
            if (right) {
                double rightBound = currentCanvas.getLayoutX() + currentCanvas.getWidth() - (imageView.getX() + imageView.getFitWidth());
                //System.out.println(imageView.getLayoutY() + " " + imageView.getLayoutX());

                imageView.setLayoutX(imageView.getLayoutX() + 1 > rightBound ?
                        rightBound : imageView.getLayoutX() + 1);

                if (tryToMove(imageView.getLayoutX(), imageView.getLayoutY())) {

                    imageView.setLayoutX(imageView.getLayoutX() - 2);
                }

                imageView.setImage(RIGHT_IMAGE_VIEW);

            } else {

                double leftBound = currentCanvas.getLayoutX();
                //System.out.println(imageView.getLayoutY() + " " + imageView.getLayoutX());

                imageView.setLayoutX(imageView.getLayoutX() - 1 < leftBound ?
                        leftBound : imageView.getLayoutX() - 1);

                if (tryToMove(imageView.getLayoutX(), imageView.getLayoutY())) {

                    imageView.setLayoutX(imageView.getLayoutX() + 2);
                }

                imageView.setImage(LEFT_IMAGE_VIEW);
            }
        }
    }

    public void moveY(int y) {

        boolean down = y > 0 ? true : false;
        for (int i = 0; i < Math.abs(y); i++) {
            if (down) {

                double downBound = currentCanvas.getLayoutY() + currentCanvas.getHeight() - (imageView.getY() + imageView.getFitHeight());

                imageView.setLayoutY(imageView.getLayoutY() + 1 > downBound ?
                        downBound : imageView.getLayoutY() + 1);

                if (tryToMove(imageView.getLayoutX(), imageView.getLayoutY())) {

                    imageView.setLayoutY(imageView.getLayoutY() - 2);
                }

                //System.out.println(imageView.getLayoutY() + " " + imageView.getLayoutX());

            } else {

                double upBound = currentCanvas.getLayoutY();

                imageView.setLayoutY(imageView.getLayoutY() - 1 < upBound ?
                        upBound : imageView.getLayoutY() - 1);

                if (tryToMove(imageView.getLayoutX(), imageView.getLayoutY())) {

                    imageView.setLayoutY(imageView.getLayoutY() + 2);
                }

                //System.out.println(imageView.getLayoutY() + " " + imageView.getLayoutX());
            }
        }
    }

    private boolean tryToMove(double layoutX, double layoutY) {
        boolean desk = false;
        boolean bottomRightObj = false;
        boolean computer = false;
        boolean piano = false;
        boolean library = false;
        if (layoutY < 167 && (layoutX > 368 && layoutX < 597)) {
            desk = true;
        }
        if (layoutY > 425 && layoutX > 766) {
            bottomRightObj = true;
        }
        if (layoutX < 260 && layoutY < 167) {
            computer = true;
        }
        if (layoutY > 369 && layoutX < 265) {
            piano = true;
        }
        if (layoutY < 220 && layoutX > 700 &&((layoutY - 135) + (800 - layoutX) < 100)) {
            library = true;
        }
        return desk || bottomRightObj || computer || piano || library;
    }

    public void render(GraphicsContext gc) {
        //gc.drawImage(image, positionX, positionY);
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(positionX, positionY, width, height);
    }

    public boolean checkCollision(Sprite spr) {
        return spr.getBoundary().intersects(this.getBoundary());
    }
}
