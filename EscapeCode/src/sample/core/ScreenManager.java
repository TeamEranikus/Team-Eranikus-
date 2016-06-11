package sample.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.utils.Constants;

import java.io.IOException;


public class ScreenManager {
    private AnchorPane root;

    public void setPrimaryStage(Stage primaryStage, String FXMLPath) throws IOException {
        primaryStage.setTitle(Constants.TITLE);
        root = FXMLLoader.load(getClass().getResource("../scenes/" + FXMLPath));
        Region contentRootRegion = (Region) FXMLLoader.load(getClass().getResource("../scenes/" + FXMLPath));
        double origW = 1920.0;
        double origH = 1080.0;

        if (contentRootRegion.getPrefWidth() == contentRootRegion.USE_COMPUTED_SIZE){
            contentRootRegion.setPrefWidth(origW);
        } else {
            origW = contentRootRegion.getPrefWidth();
        }

        if (contentRootRegion.getPrefHeight() == contentRootRegion.USE_COMPUTED_SIZE){
            contentRootRegion.setPrefHeight(origH);
        } else {
            origH = contentRootRegion.getPrefHeight();
        }
        Group group = new Group(contentRootRegion);

        //Place the Group in a StackPane, which will keep it centered
        StackPane rootPane = new StackPane();
        rootPane.getChildren().add( group );
        Scene scene = new Scene( rootPane, origW, origH );
        //Bind the scene's width and height to the scaling parameters on the group
        group.scaleXProperty().bind( scene.widthProperty().divide( origW ) );
        group.scaleYProperty().bind( scene.heightProperty().divide( origH ) );
        //Set the scene to the window (stage) and show it
       // primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    public <T> T loadSceneToPrimaryStage(Stage currentStage, String FXMLPath) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../scenes/" + FXMLPath));
        try {
            Region region = FXMLLoader.load(getClass().getResource("../scenes/" + FXMLPath));
            root = fxmlLoader.load();
            //root.getChildren().add(region);
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentStage.show();
        return fxmlLoader.getController();
    }

}

