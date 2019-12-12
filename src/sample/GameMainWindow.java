package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class GameMainWindow {
    private static final int SCENE_WIDTH = 950;
    private static final int SCENE_HEIGHT = 700;

    private GameMainWindowButtons chooseCategoryButton;
    private GameMainWindowButtons randomCategoryButton;
    private GameMainWindowButtons settingsButton;

    private Stage gameMainWindow;
    private Scene gameMainScene;
    private GridPane layout;

    private CategoryComboBox categoryComboBox;

    public GameMainWindow(){
        gameMainWindow = new Stage();
        gameMainWindow.setTitle("Czółko");

        chooseCategoryButton = new GameMainWindowButtons("Wybierz kategorię z listy");
        randomCategoryButton = new GameMainWindowButtons("Wylosuj kategorię");
        settingsButton = new GameMainWindowButtons("");

        categoryComboBox = new CategoryComboBox();

        chooseCategoryButton.configureCategoryButton();
        randomCategoryButton.configureCategoryButton();
        settingsButton.configureSettingsButton();

        chooseCategoryButton.setOpacityAnimation();
        randomCategoryButton.setOpacityAnimation();
        settingsButton.setColorAnimation();

        displayMainGameWindow();

        settingsButton.setOnAction(eventAction -> {
            GameSettingWindow settingWindow = new GameSettingWindow();
        });

    }

    public void displayMainGameWindow(){

        layout = new GridPane ();
        layout.setId("pane");

        gameMainScene = new Scene(layout, SCENE_WIDTH, SCENE_HEIGHT);
        gameMainScene.getStylesheets().addAll(this.getClass().getResource("/resources/backgroundStyle.css").toExternalForm());

//        HBox hbox = new HBox(50);
//        hbox.setAlignment(Pos.CENTER);
//        hbox.getChildren().addAll(randomCategoryButton, chooseCategoryButton);

        layout.setPadding(new Insets(10,10,10,10));

        layout.setVgap(10);
        layout.setHgap(10);
        //layout.add(hbox,3,2);
        layout.add(settingsButton,45,0);
        layout.add(chooseCategoryButton,0,0);
        layout.add(randomCategoryButton,0,1);


        gameMainWindow.setScene(gameMainScene);
        gameMainWindow.setResizable(false);
        gameMainWindow.show();
    }



    private class CategoryComboBox {

        private final ComboBox<String> comboBox;

        ObservableList<String> availableCategories = FXCollections.observableArrayList(
                "Polskie piosenki", "Zagraniczne piosenki"
        );

        public CategoryComboBox(){
            comboBox = new ComboBox<>(availableCategories);
            comboBox.setPromptText("choose category");
            configureCategoryComboBox();
        }

        public void configureCategoryComboBox(){
            AnchorPane.setLeftAnchor(chooseCategoryButton, 100d);
            comboBox.getStylesheets().add("/resources/categoryComboBox/categoryComboBoxStyle.css");
        }

    }
}
