package sample.utils;

public class Constants {
    public static final String MENU_FXML_PATH = "menu/menuScene.fxml";
    public static final String TITLE = "Escape code";
    public static final String DEMO_LEVEL_FXML_PATH = "demoLevel/demoScene.fxml";
    public static final String HOW_TO_PLAY_FXML_PATH = "howToPlay/howToPlayScene.fxml";
    public static final String PUZZLE_FILE_PATH = (System.getProperty("user.dir").contains("EscapeCode") ?
            System.getProperty("user.dir") : System.getProperty("user.dir") + "/EscapeCode") +
            "/src/sample/resources/templates/Puzzles.txt";
    public static final String PUZZLE_FXML_PATH = "demoLevel/puzzles/puzzles.fxml";
    //public static final String PUZZLE_FILE = System.getProperty("user.dir") + "/src/sample/resources/templates/Puzzles.txt";

}
