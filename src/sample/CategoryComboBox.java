package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.ComboBox;

public class CategoryComboBox {

    private final ComboBox<String> comboBox;

    ObservableList<String> availableCategories = FXCollections.observableArrayList(
            "Polskie piosenki", "Zagraniczne piosenki"
    );

    public CategoryComboBox(){
        comboBox = new ComboBox<>(availableCategories);
        comboBox.setPromptText("choose category");
        System.out.println(comboBox.toString());
    }



}
