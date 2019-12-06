package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageLoader {
    public static ImageView loadImageFromFile(String filePath){
        FileInputStream input = null;
        try {
            input = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);

        return imageView;
    }

    public static ImageView resizeImage(ImageView imageView, int width, int height){
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);

        return imageView;
    }
}
