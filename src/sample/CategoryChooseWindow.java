package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CategoryChooseWindow {
    private static final int SCENE_WIDTH = 500;
    private static final int SCENE_HEIGHT = 450;

    private Stage categoryChooseWindow;
    private Scene categoryChooseScene;
    private TilePane categoryChooseLayout;

    private Button okButton;
    private Button cancelButton;

    public CategoryChooseWindow(){
        categoryChooseWindow = new Stage();

        okButton = new Button("Save", ImageLoader.resizeImage(ImageLoader.loadImageFromFile("res/buttonIcons/acceptIcon.png"),64,64));
        okButton.setStyle("-fx-background-color: #F9E79F");
        cancelButton = new Button("Cancel", ImageLoader.resizeImage(ImageLoader.loadImageFromFile("res/buttonIcons/cancelIcon.png"),64,64));
        cancelButton.setStyle("-fx-background-color: #F9E79F");

        okButton.setMinSize(64, 64);
        cancelButton.setMinSize(64, 64);

        Label countryLabel = new Label("Country");
        Label genreLabel = new Label("Genre");
        countryLabel.getStylesheets().add("resources/categoryLabel/categoryLabelStyle.css");
        genreLabel.getStylesheets().add("resources/categoryLabel/categoryLabelStyle.css");

        //radio buttons which allow to choose songs' origin
        final ToggleGroup group = new ToggleGroup();
        RadioButton polishSongs = new RadioButton("polish songs");
        polishSongs.setToggleGroup(group);

        RadioButton foreignSongs = new RadioButton("foreign songs");
        foreignSongs.setToggleGroup(group);

        RadioButton allSongs = new RadioButton("all songs");
        allSongs.setToggleGroup(group);
        allSongs.setSelected(true);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(polishSongs, foreignSongs, allSongs);
        hBox.setSpacing(8);
        hBox.getStylesheets().add("/resources/categoryButtons/chooseCategoryRadioButtnsStyle.css");

        //radio buttons which allow to choose songs' genre
        final ToggleGroup groupGenre = new ToggleGroup();
        RadioButton popSongs = new RadioButton("pop");
        popSongs.setToggleGroup(groupGenre);

        RadioButton rockSongs = new RadioButton("rock");
        rockSongs.setToggleGroup(groupGenre);

        RadioButton discopoloSongs = new RadioButton("disco-polo");
        discopoloSongs.setToggleGroup(groupGenre);

        RadioButton metalSongs = new RadioButton("metal");
        metalSongs.setToggleGroup(groupGenre);

        RadioButton reggaeSongs = new RadioButton("reggae");
        reggaeSongs.setToggleGroup(groupGenre);

        RadioButton allGenreSongs = new RadioButton("all");
        allGenreSongs.setToggleGroup(groupGenre);
        allGenreSongs.setSelected(true);

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

        cancelButton.setOnAction(actionEvent -> {
            categoryChooseWindow.close();
        });

        okButton.setOnAction(actionEvent -> {
            categoryChooseWindow.close();
        });
    }

    public void displayChooseCategoryWindow(){
        categoryChooseScene = new Scene(categoryChooseLayout, SCENE_WIDTH, SCENE_HEIGHT);
        categoryChooseWindow.setScene(categoryChooseScene);
        categoryChooseWindow.initModality(Modality.APPLICATION_MODAL);

        categoryChooseWindow.showAndWait();
    }
}
