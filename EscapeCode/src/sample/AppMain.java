package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.core.ScreenManager;
import sample.utils.Constants;

public class AppMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       ScreenManager screenManager = new ScreenManager();
        screenManager.setPrimaryStage(primaryStage, Constants.MENU_FXML_PATH);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
