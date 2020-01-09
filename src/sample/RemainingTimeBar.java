package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RemainingTimeBar extends Application {
    private final static double RECTANGLE_WIDTH = 400.0;
    private final static double RECTANGLE_HEIGHT = 15.0;

    private Rectangle timeBar;
    private Timeline guessingSongTimeline;

    public RemainingTimeBar(Timeline timeline){
        timeBar = new Rectangle(RECTANGLE_WIDTH, RECTANGLE_HEIGHT, Color.RED);
        guessingSongTimeline = timeline;

    }

    @Override
    public void start(Stage stage) {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2),
                actionEvent -> {
                    updateTimeBar();
                }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
    }

    private void updateTimeBar(){
        timeBar.setWidth(RECTANGLE_WIDTH * ((SongSettings.getTimeForGuessingSong() - guessingSongTimeline.getCurrentTime().toSeconds())/ SongSettings.getTimeForGuessingSong()));
    }

    public Rectangle getTimeBar() {
        return timeBar;
    }
}
