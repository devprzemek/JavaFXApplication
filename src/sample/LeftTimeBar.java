package sample;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LeftTimeBar {
    private Rectangle timeBar = new Rectangle(100.0, 20.0, Color.RED);
    private DoubleProperty timePercentage = new SimpleDoubleProperty(1.0);
    private DoubleBinding timeBinding = timeBar.widthProperty().multiply(timePercentage);
}
