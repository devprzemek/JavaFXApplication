package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

public class GameWindow {
    private static final GameWindow gameWindow = new GameWindow();

    private static final int SCENE_WIDTH = 550;
    private static final int SCENE_HEIGHT = 500;

    private  static Stage gameWindowStage;
    private Scene gameWindowScene;
    private static BorderPane layout;

    private static List<SongFlashCard> songFlashCards;

    private GameWindow(){
        gameWindowStage = new Stage();
        layout = new BorderPane();
        layout.setStyle("-fx-background-color: #2E4053");
        gameWindowScene = new Scene(layout, SCENE_WIDTH, SCENE_HEIGHT);

        gameWindowStage.setScene(gameWindowScene);
        gameWindowStage.initModality(Modality.APPLICATION_MODAL);

        displayGameWindow();
    }

    public void displayGameWindow(){
        gameWindowStage.show();
    }

    public static GameWindow getInstance(){
        FXTimer timerAnimation = new FXTimer();
        timerAnimation.start(gameWindowStage);
        return gameWindow;
    }

    public static void initialiseSongFlashCardsSet(List<SongFlashCard> list){
        songFlashCards = list;
    }

    //wewnętrzna klasa animacji

    private static class FXTimer extends Application {

        private final Integer START_TIME = 5;
        private Timeline timeline;
        private Label timerLabel = new Label();
        private IntegerProperty timeSeconds = new SimpleIntegerProperty(START_TIME);


        @Override
        public void start(Stage primaryStage) {
            // Bind the timerLabel text property to the timeSeconds property
            timerLabel.textProperty().bind(timeSeconds.asString());
            timerLabel.setTextFill(Color.RED);
            timerLabel.setStyle("-fx-font-size: 20em;");

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
                layout.setCenter(songFlashCards.get(1));
            });

        }
    }
}
