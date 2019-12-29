package sample;

import javafx.scene.control.Button;

public class GameMainWindowButtons extends Button {
    public GameMainWindowButtons(String buttonLabel){
        super(buttonLabel);
    }

    public void configureCategoryButton(){
        setOpacity(0.75);
        getStylesheets().add("/resources/categoryButtons/chooseCategoryButtonStyle.css");
        setWrapText(true);
        autosize();
    }

    public void configureSettingsButton(){
        setGraphic(ImageLoader.resizeImage(ImageLoader.loadImageFromFile("res/buttonIcons/settingsIcon.png"),100,100));
        setStyle("-fx-background-color: transparent");
    }

    public void setOpacityAnimation(){
        setOnMouseMoved(actionEvent -> {
            setOpacity(1);
        });
        setOnMouseExited(actionEvent -> {
            setOpacity(0.75);
        });
    }

    public void setColorAnimation(){
        setOnMouseMoved(actionEvent -> {
            setStyle("-fx-background-color: #B03A2E ");
        });
        setOnMouseExited(actionEvent -> {
            setStyle("-fx-background-color: transparent");
        });
    }

    public void configureStartButton(){
        setDisable(true);
        setGraphic(ImageLoader.resizeImage(ImageLoader.loadImageFromFile("res/buttonIcons/startIcon.png"),200,150));
        setStyle("-fx-background-color: transparent");
    }


    public void configureBackToMenuButton(){
        setGraphic(ImageLoader.resizeImage(ImageLoader.loadImageFromFile("res/buttonIcons/cancelIcon.png"),100,100));
        setStyle("-fx-background-color: #F9E79F");
        setMinWidth(200);
    }
}
