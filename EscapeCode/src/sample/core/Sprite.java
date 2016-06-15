package sample.core;

import javafx.geometry.Rectangle2D;
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
    private ResizableCanvas currentCanvas;
    private double height;

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Sprite(ImageView image, ResizableCanvas canvas) {
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

                imageView.setLayoutX(imageView.getLayoutX() + 1 > rightBound ?
                        rightBound : imageView.getLayoutX() + 1);

                imageView.setImage(RIGHT_IMAGE_VIEW);

            } else {

                double leftBound = currentCanvas.getLayoutX();

                imageView.setLayoutX(imageView.getLayoutX() - 1 < leftBound ?
                        leftBound : imageView.getLayoutX() - 1);

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

            } else {

                double upBound = currentCanvas.getLayoutY();

                imageView.setLayoutY(imageView.getLayoutY() - 1 < upBound ?
                        upBound : imageView.getLayoutY() - 1);

            }
        }
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
