package sample.scenes.demoLevel.puzzles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class PuzzlesController implements Initializable {
    @FXML
    Button hintButton;
    @FXML
    Label hintText;
    @FXML
    Label description;
    @FXML
    Label nextClue;
    @FXML
    ImageView puzzleImage;
    @FXML
    TextField userAnswer;

    public void giveHint(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
