package sample.core.interfaces;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public interface Controller {
    Canvas getMainCanvas();

    ImageView getImagePlayer();

    AnchorPane getAnchorPane();
}
