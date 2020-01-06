package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

public class GameWindow {
    private final Button nextSongButton;
    private final Button passSongButton;
    private final Label scoreLabel;

    private static final int SCENE_WIDTH = 550;
    private static final int SCENE_HEIGHT = 500;

    private  static Stage gameWindowStage;
    private Scene gameWindowScene;
    private BorderPane layout;

    private List<? extends Label> songFlashCards;
    private int flashCardIndex = 1;

    private Timeline guessingSongTimeline;

    public GameWindow(){
        scoreLabel = new Label("Points : 0");
        scoreLabel.setAlignment(Pos.CENTER);
        scoreLabel.getStylesheets().add("/resources/labels/scoreLabelStyle.css");

        nextSongButton = new Button("next");
        nextSongButton.getStylesheets().add("/resources/buttons/gameWindowButtons.css");
        passSongButton = new Button("pass");
        passSongButton.getStylesheets().add("/resources/buttons/gameWindowButtons.css");

        gameWindowStage = new Stage();
        layout = new BorderPane();
        layout.setStyle("-fx-background-color: #2E4053");
        layout.setPadding(new Insets(30, 30,30,30));
        gameWindowScene = new Scene(layout, SCENE_WIDTH, SCENE_HEIGHT);

        gameWindowStage.setScene(gameWindowScene);
        gameWindowStage.setResizable(false);
        gameWindowStage.initModality(Modality.APPLICATION_MODAL);

        displayGameWindow();

        guessingSongTimeline = new Timeline(new KeyFrame(
                Duration.seconds(SongSettings.getTimeForGuessingSong()),
                event -> {
                    layout.setCenter(getNextFlashCard());
                    modifyScoreLabel();
                }));;

        //obsługa zdarzeń przycisków
        nextSongButton.setOnAction(actionEvent -> {
            layout.setCenter(getNextFlashCard());
            PlayerDataWindow.playerInfo.increaseScore();
            modifyScoreLabel();
            guessingSongTimeline.playFromStart();
        });

        passSongButton.setOnAction(actionEvent -> {
            layout.setCenter(getNextFlashCard());
            guessingSongTimeline.playFromStart();
        });
    }

    public void displayGameWindow(){
        FXTimer timerAnimation = new FXTimer();
        timerAnimation.start(gameWindowStage);
        gameWindowStage.show();
    }

     void initialiseSongFlashCardsSet(List<? extends Label> list){
        if(list == null){
            new NullPointerException();
        }
        songFlashCards = list;
    }

    private Label getNextFlashCard(){
        flashCardIndex += 1;
        if(flashCardIndex < songFlashCards.size()){
            return songFlashCards.get(flashCardIndex);
        }
        return new Label("KONIEC");
    }

    private void modifyScoreLabel(){
        if(flashCardIndex < songFlashCards.size()){
            StringBuffer scoreLabelText = new StringBuffer("Points : ");
            scoreLabelText.append(PlayerDataWindow.playerInfo.getPlayerScore());
            scoreLabel.setAlignment(Pos.CENTER);
            scoreLabel.setText(scoreLabelText.toString());
        }
    }

    //wewnętrzna klasa animacji
    private class FXTimer extends Application {

        private final Integer START_TIME = 3;
        private Timeline timeline;
        private Label timerLabel = new Label();
        private IntegerProperty timeSeconds = new SimpleIntegerProperty(START_TIME);


        @Override
        public void start(Stage primaryStage) {
            // Bind the timerLabel text property to the timeSeconds property
            timerLabel.textProperty().bind(timeSeconds.asString());
            timerLabel.setTextFill(Color.RED);
            timerLabel.setStyle("-fx-font-size: 25em;");

            layout.setCenter(timerLabel);
            displayCountdownAnimation();
        }

        public void displayCountdownAnimation(){
            if (timeline != null) {
                timeline.stop();
            }
            timeSeconds.set(START_TIME);
            timeline = new Timeline();
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(START_TIME+1),
                            new KeyValue(timeSeconds, 0)));
            timeline.playFromStart();

            timeline.setOnFinished(actionEvent -> {
                layout.setTop(scoreLabel);
                layout.setCenter(songFlashCards.get(flashCardIndex));

                HBox hBox = new HBox(100);
                hBox.setAlignment(Pos.CENTER);
                hBox.getChildren().addAll(passSongButton, nextSongButton);

                layout.setBottom(hBox);
                layout.setAlignment(hBox, Pos.BOTTOM_CENTER);

                guessingSongTimeline.setCycleCount(Animation.INDEFINITE);
                guessingSongTimeline.play();
            });

        }
    }
}
