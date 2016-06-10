package sample.scenes.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.scenes.demoLevel.DemoController;

import java.io.IOException;

public class MenuController {

    // Fields must be private with FXML annotations
    @FXML
    private Button newGameButton;

    @FXML
    private Button howToPlayButton;

    @FXML
    private Button quitButton;

    private DemoController controller;

    public void onNewGameClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage)newGameButton.getScene().getWindow();
        //Parent root = FXMLLoader.load(getClass().getResource("../demoLevel/demoScene.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../demoLevel/demoScene.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        Scene scene = new Scene(root);

        stage.setTitle("Demo Level");
        stage.setScene(scene);
        stage.show();
        controller.moveCharacter(scene);
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
