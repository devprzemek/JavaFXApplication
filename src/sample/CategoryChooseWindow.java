package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CategoryChooseWindow {
    private static final CategoryChooseWindow chooseWindow = new CategoryChooseWindow();

    private static final int SCENE_WIDTH = 500;
    private static final int SCENE_HEIGHT = 450;

    private Stage categoryChooseWindow;
    private Scene categoryChooseScene;
    private TilePane categoryChooseLayout;

    private Button okButton;
    private Button cancelButton;

    private CategoryChooseWindow(){
        categoryChooseWindow = new Stage();
        categoryChooseWindow.setResizable(false);

        okButton = new Button("Save", ImageLoader.resizeImage(ImageLoader.loadImageFromFile("res/buttonIcons/acceptIcon.png"),64,64));
        okButton.setStyle("-fx-background-color: #F9E79F");
        cancelButton = new Button("Cancel", ImageLoader.resizeImage(ImageLoader.loadImageFromFile("res/buttonIcons/cancelIcon.png"),64,64));
        cancelButton.setStyle("-fx-background-color: #F9E79F");

        okButton.setMinSize(64, 64);
        cancelButton.setMinSize(64, 64);

        Label countryLabel = new Label("Country");
        Label genreLabel = new Label("Genre");
        countryLabel.getStylesheets().add("resources/labels/categoryLabelStyle.css");
        genreLabel.getStylesheets().add("resources/labels/categoryLabelStyle.css");

        //radio buttons which allow to choose songs' origin
        final ToggleGroup countryGroup = new ToggleGroup();
        RadioButton polishSongs = new RadioButton("polish songs");
        polishSongs.setToggleGroup(countryGroup);

        RadioButton foreignSongs = new RadioButton("foreign songs");
        foreignSongs.setToggleGroup(countryGroup);

        RadioButton allSongs = new RadioButton("all songs");
        allSongs.setToggleGroup(countryGroup);
        allSongs.setSelected(true);
        SongSettings.setSongCountry(allSongs.getText());

        HBox hBox = new HBox();
        hBox.getChildren().addAll(polishSongs, foreignSongs, allSongs);
        hBox.setSpacing(8);
        hBox.getStylesheets().add("/resources/categoryButtons/chooseCategoryRadioButtnsStyle.css");

        //radio buttons which allow to choose songs' genre
        final ToggleGroup genreGroup = new ToggleGroup();
        RadioButton popSongs = new RadioButton("pop");
        popSongs.setToggleGroup(genreGroup);

        RadioButton rockSongs = new RadioButton("rock");
        rockSongs.setToggleGroup(genreGroup);

        RadioButton discopoloSongs = new RadioButton("disco-polo");
        discopoloSongs.setToggleGroup(genreGroup);

        RadioButton metalSongs = new RadioButton("metal");
        metalSongs.setToggleGroup(genreGroup);

        RadioButton reggaeSongs = new RadioButton("reggae");
        reggaeSongs.setToggleGroup(genreGroup);

        RadioButton allGenreSongs = new RadioButton("all");
        allGenreSongs.setToggleGroup(genreGroup);
        allGenreSongs.setSelected(true);
        SongSettings.setSongGenre(allGenreSongs.getText());

        HBox hBox2 = new HBox();
        hBox2.getChildren().addAll(popSongs, rockSongs, discopoloSongs, metalSongs);
        hBox2.setAlignment(Pos.TOP_CENTER);
        hBox2.setSpacing(8);
        hBox2.getStylesheets().add("/resources/categoryButtons/chooseCategoryRadioButtnsStyle.css");

        HBox hBox3 = new HBox();
        hBox3.getChildren().addAll(reggaeSongs, allGenreSongs);
        hBox3.setAlignment(Pos.TOP_CENTER);
        hBox3.setSpacing(6);
        hBox3.getStylesheets().add("/resources/categoryButtons/chooseCategoryRadioButtnsStyle.css");

        categoryChooseLayout = new TilePane();
        categoryChooseLayout.styleProperty().set("-fx-background-color: #5D6D7E");

        HBox hBox4 = new HBox();
        hBox4.getChildren().addAll(okButton, cancelButton);
        hBox4.setAlignment(Pos.TOP_CENTER);
        hBox4.setSpacing(15);

        categoryChooseLayout.setAlignment(Pos.TOP_CENTER);
        categoryChooseLayout.setVgap(10);
        categoryChooseLayout.getChildren().addAll(countryLabel, hBox, genreLabel,hBox2, hBox3, hBox4);

        categoryChooseScene = new Scene(categoryChooseLayout, SCENE_WIDTH, SCENE_HEIGHT);
        categoryChooseWindow.setScene(categoryChooseScene);
        categoryChooseWindow.initModality(Modality.APPLICATION_MODAL);

        countryGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        {
            public void changed(ObservableValue<? extends Toggle> ob,
                                Toggle o, Toggle n)
            {
                RadioButton radioButton = (RadioButton) countryGroup.getSelectedToggle();

                if (radioButton != null) {
                    SongSettings.setSongCountry(radioButton.getText());
                }
            }
        });

        genreGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        {
            public void changed(ObservableValue<? extends Toggle> ob,
                                Toggle o, Toggle n)
            {
                RadioButton radioButton = (RadioButton) genreGroup.getSelectedToggle();

                if (radioButton != null) {
                    SongSettings.setSongGenre(radioButton.getText());
                }
            }
        });


        cancelButton.setOnAction(actionEvent -> {
            categoryChooseWindow.close();
        });

        okButton.setOnAction(actionEvent -> {
            categoryChooseWindow.close();
        });
    }

    public static CategoryChooseWindow getInstance(){
        return chooseWindow;
    }

    public void displayCategoryChooseWindow(){
        categoryChooseWindow.showAndWait();
    }
}
