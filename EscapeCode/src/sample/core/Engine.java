package sample.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Engine {

    private Stage primaryStage;

    public Engine(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void run() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../scenes/menu/menuScene.fxml"));
        primaryStage.setTitle("Escape code");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
