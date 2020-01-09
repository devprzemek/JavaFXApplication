package sample;


import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

public class SongFlashCard extends Label {
    private String songTitle;
    private String songPerformer;
    private int releaseYear;

    public SongFlashCard(String title, String performer, int year){
        super();
        songTitle = title;
        songPerformer = performer;
        releaseYear = year;

        setTextAlignment(TextAlignment.CENTER);
        createSongFlashCard();
    }

    @Override
    public String toString() {
        return songTitle + "\n" + songPerformer + "\n" + releaseYear;
    }

    private void createSongFlashCard(){
        getStylesheets().add("/resources/labels/flashCardLabelStyle.css");
        setTextAlignment(TextAlignment.CENTER);
        setText(toString());
    }
}
