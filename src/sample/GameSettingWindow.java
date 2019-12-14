package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class GameSettingWindow {
    private static final int SCENE_WIDTH = 400;
    private static final int SCENE_HEIGHT = 400;

    private Stage settingsWindowStage;
    private Scene settingsWindowScene;
    private StackPane layout;

    private final Slider numberOfSongs;
    Label numberOfSongsLabel;

    public GameSettingWindow(){
        settingsWindowStage = new Stage();
        layout = new StackPane();

        numberOfSongs = new Slider ();
        numberOfSongsLabel = new Label("Liczba piosenek:");
        configureNumberOfSongsSlider();

        VBox root = new VBox();
        root.setPadding(new Insets(20));
        root.setSpacing(10);
        root.getChildren().addAll(numberOfSongsLabel, numberOfSongs);

        layout.getChildren().addAll(root);
        settingsWindowScene = new Scene(layout, SCENE_WIDTH, SCENE_HEIGHT);
        displayWindow();
    }

    private void displayWindow(){
        settingsWindowStage.setScene(settingsWindowScene);
        settingsWindowStage.initModality(Modality.APPLICATION_MODAL);
        settingsWindowStage.show();
    }

    private void configureNumberOfSongsSlider(){
        numberOfSongs.setMin(1);
        numberOfSongs.setMax(15);
        numberOfSongs.setValue(10);
        numberOfSongs.setBlockIncrement(1);
        numberOfSongs.setMajorTickUnit(2);
        numberOfSongs.setShowTickLabels(true);
        numberOfSongs.setShowTickMarks(true);
    }

}
