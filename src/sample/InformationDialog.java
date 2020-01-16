package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class InformationDialog {

    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private Optional<ButtonType> result;
    private String textMessage;


    public InformationDialog(String message){
        textMessage = message;
    }

    public void showInformationDialog(){
        customiseInformationDialog();
        result = alert.showAndWait();
    }

    public void customiseInformationDialog(){
        alert.getDialogPane().getStylesheets().add("/resources/informationDialogStyle.css");
        alert.setContentText(textMessage);
        alert.setTitle("Game Info");
        alert.setHeaderText(null);
    };

    public void closeWindows(Stage stage){
        ButtonType buttonType = result.orElse(ButtonType.CLOSE);
        if(buttonType == ButtonType.OK || buttonType == ButtonType.CLOSE){
            stage.close();
        }
    }

}
