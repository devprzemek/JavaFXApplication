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
            setStyle("-fx-background-color: black");
        });
        setOnMouseExited(actionEvent -> {
            setStyle("-fx-background-color: transparent");
        });
    }

}
