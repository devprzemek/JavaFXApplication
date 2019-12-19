package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class GameSettingWindow {
    private static final int SCENE_WIDTH = 400;
    private static final int SCENE_HEIGHT = 400;

    private Stage settingsWindowStage;
    private Scene settingsWindowScene;
    private StackPane layout;

    private final Slider numberOfSongsSlider;
    Label numberOfSongsLabel;

    public GameSettingWindow(){
        settingsWindowStage = new Stage();
        layout = new StackPane();
        layout.styleProperty().set("-fx-background-color: #5D6D7E");

        numberOfSongsSlider = new Slider ();
        numberOfSongsLabel = new Label("Liczba piosenek:");
        numberOfSongsLabel.getStylesheets().add("/resources/categoryLabel/categoryLabelStyle.css");
        configureNumberOfSongsSlider();

        VBox root = new VBox();
        root.setPadding(new Insets(20));
        root.setSpacing(10);
        root.getChildren().addAll(numberOfSongsLabel, numberOfSongsSlider);

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
        numberOfSongsSlider.setMin(1);
        numberOfSongsSlider.setMax(15);
        numberOfSongsSlider.setValue(10);
        numberOfSongsSlider.setBlockIncrement(1);
        numberOfSongsSlider.setMajorTickUnit(2);
        numberOfSongsSlider.setShowTickLabels(true);
        numberOfSongsSlider.setShowTickMarks(true);
        numberOfSongsSlider.getStylesheets().add("/resources/sliderStyle.css");
    }

}
