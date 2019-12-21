package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.ResultSet;


public class GameMainWindow {
    private static final int SCENE_WIDTH = 950;
    private static final int SCENE_HEIGHT = 700;

    private GameMainWindowButtons chooseCategoryButton;
    private GameMainWindowButtons randomCategoryButton;
    private GameMainWindowButtons settingsButton;
    private GameMainWindowButtons startButton;
    private Label categoryLabel;

    private Stage gameMainWindow;
    private Scene gameMainScene;
    private BorderPane layout;


    public GameMainWindow(){
        gameMainWindow = new Stage();
        gameMainWindow.setTitle("Czółko Game");

        chooseCategoryButton = new GameMainWindowButtons("Choose categories");
        randomCategoryButton = new GameMainWindowButtons("Random category");
        settingsButton = new GameMainWindowButtons("");
        startButton = new GameMainWindowButtons("");
        categoryLabel = new Label();
        configureCategoryLabel();

        chooseCategoryButton.configureCategoryButton();
        randomCategoryButton.configureCategoryButton();
        settingsButton.configureSettingsButton();
        startButton.configureStartButton();

        chooseCategoryButton.setOpacityAnimation();
        randomCategoryButton.setOpacityAnimation();
        settingsButton.setColorAnimation();

        displayMainGameWindow();

        //obsługa zdarzeń przycisków
        settingsButton.setOnAction(eventAction -> {
            GameSettingWindow.getInstance().displayGameSettingsWindow();
        });

        chooseCategoryButton.setOnAction(actionEvent -> {
            categoryLabel.setText("");
            CategoryChooseWindow.getInstance().displayChooseCategoryWindow();
        });

        randomCategoryButton.setOnAction(actionEvent -> {
            categoryLabel.setText("Category: \n" + RandomCategoryGenerator.generateRandomCategory());
            layout.setCenter(categoryLabel);
            layout.setAlignment(categoryLabel, Pos.CENTER);
        });

        startButton.setOnAction(actionEvent -> {
            String sqlQuery = "select songs.songName from songs where country = 'Polska' ";
            DatabaseConnector.connectWithSongDatebas();
            ResultSet resultSet = DatabaseConnector.executeMyQuery(sqlQuery);
        });
    }

    public void displayMainGameWindow(){
        layout = new BorderPane();
        layout.setId("pane");

        gameMainScene = new Scene(layout, SCENE_WIDTH, SCENE_HEIGHT);
        gameMainScene.getStylesheets().addAll(this.getClass().getResource("/resources/backgroundStyle.css").toExternalForm());

        VBox vbox = new VBox(50);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(randomCategoryButton, chooseCategoryButton);

        layout.setPadding(new Insets(10,10,10,10));

        layout.setLeft(vbox);
        layout.setAlignment(vbox, Pos.TOP_LEFT);
        layout.setRight(settingsButton);
        layout.setBottom(startButton);
        layout.setAlignment(startButton, Pos.BOTTOM_RIGHT);


        gameMainWindow.setScene(gameMainScene);
        gameMainWindow.setResizable(false);
        gameMainWindow.show();
    }

    private void configureCategoryLabel(){
        categoryLabel.getStylesheets().add("/resources/categoryLabel/categoryLabelStyle.css");
    }
}
