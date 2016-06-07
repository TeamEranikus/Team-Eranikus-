package sample.scenes.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class MenuController {

    public Button newGameButton;
    public Button howToPlayButton;
    public Button quitButton;


    public void onNewGameClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage)newGameButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../demoLevel/demoScene.fxml"));
        stage.setTitle("Demo Level");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void onHowToPlayClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage)howToPlayButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../howToPlay/howToPlayScene.fxml"));
        stage.setTitle("How to play");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void onQuitClicked(ActionEvent event) {
        Stage stage = (Stage)quitButton.getScene().getWindow();
        stage.close();
        // should call confirm box? "Are you sure?"
    }
}
