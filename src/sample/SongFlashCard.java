package sample;

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

        createSongFlashCard();
    }

    @Override
    public String toString() {
        return "[" + songTitle + songPerformer + releaseYear + "]";
    }

    public void createSongFlashCard(){
        getStylesheets().add("/resources/labels/flashCardLabelStyle.css");
        setText(toString());
    }
}
