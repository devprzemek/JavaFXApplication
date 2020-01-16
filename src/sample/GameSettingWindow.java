package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class GameSettingWindow {
    private static final GameSettingWindow gameSettingWindow = new GameSettingWindow();

    private static final int SCENE_WIDTH = 500;
    private static final int SCENE_HEIGHT = 350;

    private Stage settingsWindowStage;
    private Scene settingsWindowScene;
    private TilePane layout;

    private Button okButton;
    private Button cancelButton;

    private final Slider numberOfSongsSlider;
    private Label numberOfSongsLabel;
    private final Slider timeForGuessingSongSlider;
    private Label timeForGuessingSongLabel;

    private GameSettingWindow(){
        settingsWindowStage = new Stage();
        settingsWindowStage.setTitle("Settings");
        settingsWindowStage.setResizable(false);

        layout = new TilePane();
        layout.styleProperty().set("-fx-background-color: #5D6D7E");

        okButton = new Button("Save", ImageLoader.resizeImage(ImageLoader.loadImageFromFile("res/buttonIcons/acceptIcon.png"),64,64));
        okButton.setStyle("-fx-font-weight: bold; -fx-background-color: #F9E79F");
        cancelButton = new Button("Cancel", ImageLoader.resizeImage(ImageLoader.loadImageFromFile("res/buttonIcons/cancelIcon.png"),64,64));
        cancelButton.setStyle("-fx-font-weight: bold; -fx-background-color: #F9E79F");

        okButton.setMinSize(64, 64);
        cancelButton.setMinSize(64, 64);

        numberOfSongsSlider = new Slider ();
        numberOfSongsLabel = new Label();
        numberOfSongsLabel.getStylesheets().add("/resources/labels/categoryLabelStyle.css");
        configureSlider(numberOfSongsSlider, 1, 15, 10);
        numberOfSongsLabel.setText("Number of songs: " + (int) numberOfSongsSlider.getValue());

        timeForGuessingSongSlider = new Slider();
        timeForGuessingSongLabel = new Label();
        timeForGuessingSongLabel.getStylesheets().add("/resources/labels/categoryLabelStyle.css");
        timeForGuessingSongSlider.getStylesheets().add("/resources/labels/categoryLabelStyle.css");
        configureSlider(timeForGuessingSongSlider, 10, 60, 30);
        timeForGuessingSongLabel.setText("Time for guessing each song: " + (int) timeForGuessingSongSlider.getValue() + " s");

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(5));
        vBox.setSpacing(10);
        vBox.getChildren().addAll(numberOfSongsLabel, numberOfSongsSlider, timeForGuessingSongLabel, timeForGuessingSongSlider);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(okButton, cancelButton);
        hBox.setAlignment(Pos.TOP_CENTER);
        hBox.setSpacing(25);

        layout.getChildren().addAll(vBox, hBox);
        layout.setVgap(10);
        layout.setAlignment(Pos.TOP_CENTER);
        settingsWindowScene = new Scene(layout, SCENE_WIDTH, SCENE_HEIGHT);
        settingsWindowStage.setScene(settingsWindowScene);
        settingsWindowStage.initModality(Modality.APPLICATION_MODAL);

        displayGameSettingsWindow();

        numberOfSongsSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int number = Math.round(newValue.floatValue());
            numberOfSongsLabel.setText("Number of songs: " + number);
            SongSettings.changeNumberOfSongsInRound(number);
        });

        timeForGuessingSongSlider.valueProperty().addListener((observable, oldValue, newValue )-> {
            int number = Math.round(newValue.floatValue());
            timeForGuessingSongLabel.setText("Time for guessing each song: " + number + " s");
            SongSettings.changeTimeForGuessingSong(number);
        });

        okButton.setOnAction(actionEvent -> {
            settingsWindowStage.close();
        });

        cancelButton.setOnAction(actionEvent -> {
            settingsWindowStage.close();
        });
    }


    private void configureSlider(Slider slider, int min, int max, int defaultValue){
        slider.setMin(min);
        slider.setMax(max);
        slider.setValue(defaultValue);
        slider.setBlockIncrement(1);
        slider.setMajorTickUnit(2);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.getStylesheets().add("/resources/sliderStyle.css");
        slider.setStyle("-fx-font-size: 25px;\n" +
                "    -fx-font-family: 'Helvetica';\n" +
                "    -fx-font-weight: bold");
    }

    public void displayGameSettingsWindow(){
        settingsWindowStage.show();
    }

    public static GameSettingWindow getInstance(){
        return gameSettingWindow;
    }

}
