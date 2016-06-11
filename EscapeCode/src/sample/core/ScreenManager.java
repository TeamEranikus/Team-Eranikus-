package sample.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.utils.Constants;

import java.io.IOException;


public class ScreenManager {
    private AnchorPane root;

    public void setPrimaryStage(Stage primaryStage, String FXMLPath) throws IOException {
        primaryStage.setTitle(Constants.TITLE);
        root = FXMLLoader.load(getClass().getResource("../scenes/" + FXMLPath));
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    public <T> T loadSceneToPrimaryStage(Stage currentStage, String FXMLPath) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../scenes/" + FXMLPath));
        try {
            root = fxmlLoader.load();
            currentStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentStage.show();
        return fxmlLoader.getController();
    }

}

