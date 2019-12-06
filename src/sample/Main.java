package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage window;

    public static void main(String args) {
        launch(args);
    }

    private GameMenu gameMenu;

    public Main(){
        gameMenu = new GameMenu();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window.setTitle(":) Czółko");

        gameMenu.createMenu(primaryStage);
    }
}
