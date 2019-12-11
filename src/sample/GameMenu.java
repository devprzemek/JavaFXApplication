package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class GameMenu  {

    private static final int SCENE_WIDTH = 450;
    private static final int SCENE_HEIGHT = 450;

    private Button playButton;
    private Button scoresButton;
    private Button optionsButton;
    private Button exitButton;

    private VBox menuButtons;
    private StackPane layout;


    public GameMenu(){
        playButton = new MenuButton("Zagraj");
        scoresButton = new MenuButton("Wyniki");
        optionsButton = new MenuButton("Opcje");
        exitButton = new MenuButton("Zakończ");

        menuButtons  = new VBox(5);
        layout = new StackPane ();

    }

    public void createMenu(Stage menuStage){

        playButton.setOnAction(actionEvent -> {
            PlayerDataWindow dataWindow = new PlayerDataWindow();
            dataWindow.displayDataWindow();
        });


        optionsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

        exitButton.setOnAction(actionEvent -> System.exit(0));


        menuButtons.setAlignment(Pos.CENTER);
        menuButtons.getChildren().addAll(playButton, scoresButton, optionsButton, exitButton);

        configureWindowLayout();

        menuStage.setScene(new Scene(layout, SCENE_WIDTH, SCENE_HEIGHT));
        menuStage.show();

    }

    public void configureWindowLayout(){
        layout.getChildren().add(menuButtons);
        layout.setAlignment(menuButtons , Pos.CENTER);
        layout.styleProperty().set("-fx-background-color: #BFC9CA");
    }
}
