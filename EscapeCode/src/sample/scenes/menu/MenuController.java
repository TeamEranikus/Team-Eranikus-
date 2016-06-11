package sample.scenes.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.core.Engine;
import sample.core.ScreenManager;
import sample.core.Sprite;
import sample.core.interfaces.Controller;
import sample.utils.Constants;

import java.io.IOException;

public class MenuController {

    // Fields must be private with FXML annotations
    @FXML
    private Button newGameButton;

    @FXML
    private Button howToPlayButton;

    @FXML
    private Button quitButton;

    private Stage currentStage;
    private Controller currentController;
    private ScreenManager screenManager;

    public void onNewGameClicked(ActionEvent event) throws IOException {
        currentStage = (Stage) newGameButton.getScene().getWindow();
        screenManager = new ScreenManager();
        currentController = screenManager.loadSceneToPrimaryStage(currentStage, Constants.DEMO_LEVEL_FXML_PATH);
        Sprite sprite = new Sprite(currentController.getImagePlayer(),currentController.getMainCanvas());
        Engine engine = new Engine(sprite);
        engine.loadRectangles(currentController.getAnchorPane());
        engine.run(currentStage.getScene());
    }

    public void onHowToPlayClicked(ActionEvent event) throws IOException {
        currentStage = (Stage) howToPlayButton.getScene().getWindow();
        screenManager = new ScreenManager();
        currentController = screenManager.loadSceneToPrimaryStage(currentStage, Constants.HOW_TO_PLAY_FXML_PATH);

    }

    public void onQuitClicked(ActionEvent event) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
        // should call confirm box? "Are you sure?"
    }
}
