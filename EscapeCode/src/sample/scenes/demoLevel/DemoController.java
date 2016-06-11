package sample.scenes.demoLevel;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.core.interfaces.Controller;

import java.net.URL;
import java.util.ResourceBundle;

public class DemoController implements Initializable, Controller{

    @FXML
    private ImageView imagePlayer;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Canvas mainCanvas;

    public Canvas getMainCanvas() {
        return mainCanvas;
    }

    public ImageView getImagePlayer() {
        return imagePlayer;
    }

    public void onMouseClickKeyItem(Event event) {

    }

    public void onMouseClickPapierItem(Event event) {

    }

    public void onMouseClickBookItem(Event event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //Try to use Hbox with buttons for the bottom bar
    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

}
