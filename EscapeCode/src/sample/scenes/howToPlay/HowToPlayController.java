package sample.scenes.howToPlay;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.core.Engine;
import sample.core.ScreenManager;
import sample.core.Sprite;
import sample.core.interfaces.Controller;
import sample.scenes.menu.MenuController;
import sample.utils.Constants;

import java.io.IOException;

public class HowToPlayController {
    @FXML
    private Button StartGame;

    @FXML
    private Button BackTogame;

    private Stage currentStage;
    private Controller currentController;
    private ScreenManager screenManager;

    public void startGame(ActionEvent event) throws IOException {
        currentStage = (Stage) StartGame.getScene().getWindow();
        screenManager = new ScreenManager();
        currentController = screenManager.loadSceneToPrimaryStage(currentStage, Constants.DEMO_LEVEL_FXML_PATH);
        Sprite sprite = new Sprite(currentController.getImagePlayer(),currentController.getMainCanvas());
        Engine engine = new Engine(sprite);
        engine.loadRectanglesPuzzles(currentController.getAnchorPane());
        engine.loadRectanglesCollision(currentController.getAnchorPane());
        engine.run(currentStage.getScene());
    }
    public void  backToMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage)StartGame.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../menu/menuScene.fxml"));
        stage.setTitle("Demo Level");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
