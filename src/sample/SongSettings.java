package sample;

public class SongSettings {
    private static String songCountry;
    private static String songGenre;

    private static int numberOfSongsInRound;

    public static void setSongCountry(String songC) {
        songCountry = songC;
    }

    public static void setSongGenre(String songG){
        songGenre = songG;
    }

    public static void setNumberOfSongsInRound(int number){
        numberOfSongsInRound = number;
    }
}
