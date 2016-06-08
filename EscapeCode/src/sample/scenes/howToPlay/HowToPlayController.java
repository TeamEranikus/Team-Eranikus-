package sample.scenes.howToPlay;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.scenes.menu.MenuController;

import java.io.IOException;

public class HowToPlayController {
    @FXML
    private Button StartGame;
    public void backToMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage)StartGame.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../demoLevel/demoScene.fxml"));
        stage.setTitle("Demo Level");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
