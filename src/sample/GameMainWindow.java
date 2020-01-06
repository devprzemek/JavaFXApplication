package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;


public class GameMainWindow {
    private static final int SCENE_WIDTH = 950;
    private static final int SCENE_HEIGHT = 700;

    private GameMainWindowButtons chooseCategoryButton;
    private GameMainWindowButtons randomCategoryButton;
    private GameMainWindowButtons settingsButton;
    private GameMainWindowButtons startButton;
    private GameMainWindowButtons backToMenuButton;
    private Label categoryLabel;

    private Stage gameMainWindow;
    private Scene gameMainScene;
    private BorderPane layout;

    private GameWindow gameWindow;

    public GameMainWindow(){
        gameMainWindow = new Stage();
        gameMainWindow.setTitle("Czółko Game");

        chooseCategoryButton = new GameMainWindowButtons("Choose categories");
        randomCategoryButton = new GameMainWindowButtons("Random category");
        settingsButton = new GameMainWindowButtons("");
        startButton = new GameMainWindowButtons("");
        backToMenuButton = new GameMainWindowButtons("");
        categoryLabel = new Label();
        configureCategoryLabel();

        chooseCategoryButton.configureCategoryButton();
        randomCategoryButton.configureCategoryButton();
        settingsButton.configureSettingsButton();
        startButton.configureStartButton();
        backToMenuButton.configureBackToMenuButton();

        chooseCategoryButton.setOpacityAnimation();
        randomCategoryButton.setOpacityAnimation();
        settingsButton.setColorAnimation();
        backToMenuButton.setColorAnimation();
        displayMainGameWindow();


        //obsługa zdarzeń przycisków
        settingsButton.setOnAction(eventAction -> {
            GameSettingWindow.getInstance().displayGameSettingsWindow();
        });

        chooseCategoryButton.setOnAction(actionEvent -> {
            categoryLabel.setText("");
            CategoryChooseWindow.getInstance().displayCategoryChooseWindow();
            startButton.setDisable(false);
        });

        randomCategoryButton.setOnAction(actionEvent -> {
            String randomCategory = RandomCategoryGenerator.generateRandomCategory();
            categoryLabel.setText("Category: \n" + randomCategory);
            layout.setCenter(categoryLabel);
            layout.setAlignment(categoryLabel, Pos.CENTER);
            SongSettings.setSongCountry("all songs");
            SongSettings.setSongGenre(randomCategory);
            startButton.setDisable(false);
        });

        startButton.setOnAction(actionEvent -> {
            String sqlQuery = SongSettings.makeSQLQuery();
            DatabaseConnector.connectWithSongDatebas();
            ResultSet rs = DatabaseConnector.executeMyQuery(sqlQuery);

            try{
                ResultSetMetaData rsmd = rs.getMetaData();
                int numberOfColumns = rsmd.getColumnCount();
                String[] columnValues = new String[numberOfColumns];
                List<SongFlashCard> flashCards = new ArrayList<>(SongSettings.getNumberOfSongsInRound());
                while (rs.next()) {
                    for (int i = 0; i < numberOfColumns; i++) {
                        columnValues[i] = rs.getString(i + 1);
                    }
                    flashCards.add(new SongFlashCard(columnValues[0], columnValues[1], Integer.parseInt(columnValues[2])));
                }
                gameWindow = new GameWindow();
                gameWindow.initialiseSongFlashCardsSet(flashCards);
            }
            catch (Exception e){
                e.printStackTrace();
            }

            DatabaseConnector.closeConnectionWithSongDatabase();

            gameWindow.displayGameWindow();
            gameMainWindow.close();
        });

        backToMenuButton.setOnAction(actionEvent -> {
            gameMainWindow.close();
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

        HBox hBox = new HBox(650);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(backToMenuButton, startButton);

        layout.setPadding(new Insets(10,10,10,10));

        layout.setLeft(vbox);
        layout.setAlignment(vbox, Pos.TOP_LEFT);
        layout.setRight(settingsButton);
        layout.setBottom(hBox);

        gameMainWindow.setScene(gameMainScene);
        gameMainWindow.setResizable(false);
        gameMainWindow.show();
    }

    private void configureCategoryLabel(){
        categoryLabel.getStylesheets().add("/resources/labels/categoryLabelStyle.css");
    }

}
