package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.core.Engine;

public class AppMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Engine engine = new Engine(primaryStage);
        engine.run();
        // TODO

        for (int i = 0; i < ; i++) {
            
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
