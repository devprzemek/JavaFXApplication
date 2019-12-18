package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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

    private CategoryComboBox categoryComboBox;

    public GameMainWindow(){
        gameMainWindow = new Stage();
        gameMainWindow.setTitle("Czółko");

        chooseCategoryButton = new GameMainWindowButtons("Wybierz kategorię z listy");
        randomCategoryButton = new GameMainWindowButtons("Wylosuj kategorię");
        settingsButton = new GameMainWindowButtons("");
        startButton = new GameMainWindowButtons("");
        categoryLabel = new Label();
        configureCategoryLabel();

        categoryComboBox = new CategoryComboBox();

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
            GameSettingWindow settingWindow = new GameSettingWindow();
        });

        chooseCategoryButton.setOnAction(actionEvent -> {
            layout.setCenter(categoryComboBox.comboBox);
            layout.setAlignment(categoryComboBox.comboBox, Pos.BOTTOM_RIGHT);
            chooseCategoryButton.setDisable(true);
        });

        randomCategoryButton.setOnAction(actionEvent -> {
            categoryLabel.setText("Kategoria: \n" + RandomCategoryGenerator.generateRandomCategory());
            layout.setCenter(categoryLabel);
            layout.setAlignment(categoryLabel, Pos.BOTTOM_RIGHT);

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

    public boolean categoryChosen(){
        return true;
    }

    public void configureCategoryLabel(){
        System.out.println("ok");
        categoryLabel.getStylesheets().add("/resources/labelStyle.css");
    }



    private class CategoryComboBox {

        private  final ComboBox<String> comboBox;

        ObservableList<String> availableCategories = FXCollections.observableArrayList(
                "Polskie piosenki", "Zagraniczne piosenki"
        );

        public CategoryComboBox(){
            comboBox = new ComboBox<>(availableCategories);
            comboBox.setPromptText("...");
            configureCategoryComboBox();

            comboBox.setOnAction(actionEvent -> {
                categoryLabel.setText("Kategoria: \n" + comboBox.getValue());
                layout.setCenter(categoryLabel);
                layout.setAlignment(categoryLabel, Pos.TOP_RIGHT);
                chooseCategoryButton.setDisable(false);
            });
        }

        public void configureCategoryComboBox(){
            AnchorPane.setLeftAnchor(chooseCategoryButton, 100d);
            comboBox.getStylesheets().add("/resources/categoryComboBox/categoryComboBoxStyle.css");
        }
    }
}
