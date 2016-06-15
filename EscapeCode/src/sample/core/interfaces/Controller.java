package sample.core.interfaces;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.core.ResizableCanvas;

public interface Controller {
    ResizableCanvas getMainCanvas();

    ImageView getImagePlayer();

    AnchorPane getAnchorPane();
}
