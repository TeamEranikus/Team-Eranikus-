package game.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Engine {

    Stage primaryStage;

    public Engine(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void run() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../scenes/menu/menuScene.fxml"));
        primaryStage.setTitle("Escape code");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
    }
}
