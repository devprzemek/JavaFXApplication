package sample;


import javafx.scene.control.Button;

public class MenuButton extends Button {
    public MenuButton(String buttonLabel){
        setText(buttonLabel);
        configureButtonStyle();
        addSizeAnimationToButton();
    }

    public void configureButtonStyle(){
        this.setMinSize(400, 100);
        this.setStyle("-fx-background-color: #2E4053; -fx-font: 40 Halvetica;-fx-font-weight: bold; -fx-text-fill: #117864 ");
    }

    public void addSizeAnimationToButton(){
        this.setOnMouseMoved(actionEvent -> {
            this.setMinSize(420, 120);
            this.setStyle("-fx-background-color: #2E4053; -fx-font: 50 Halvetica;-fx-font-weight: bold; -fx-text-fill: #117864 ");
        });

        this.setOnMouseExited(actionEvent -> {
            this.setMinSize(400, 100);
            this.setStyle("-fx-background-color: #2E4053; -fx-font: 40 Halvetica;-fx-font-weight: bold; -fx-text-fill: #117864 ");
        });
    }

}
