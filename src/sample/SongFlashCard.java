package sample;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class SongFlashCard extends Label {
    private String songTitle;
    private String songPerformer;
    private int releaseYear;

    public SongFlashCard(String title, String performer, int year){
        super();
        songTitle = title;
        songPerformer = performer;
        releaseYear = year;

        setAlignment(Pos.CENTER);
        createSongFlashCard();
    }

    @Override
    public String toString() {
        return songTitle + "\n" + songPerformer + "\n" + releaseYear;
    }

    public void createSongFlashCard(){
        getStylesheets().add("/resources/labels/flashCardLabelStyle.css");
        setText(toString());
    }
}
