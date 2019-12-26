package sample;

public class SongSettings {
    private static String songCountry;
    private static String songGenre;

    private static int numberOfSongsInRound;
    private static int timeForGuessingSong;

    private static final String SQLQueryTemplate = "SELECT songName, songPerformer, year FROM songs WHERE %s AND %s;";

    public static void setSongCountry(String songC) {
        songCountry = songC;
    }

    public static void setSongGenre(String songG){
        songGenre = songG;
    }

    public static void setNumberOfSongsInRound(int number){
        numberOfSongsInRound = number;
    }

    public static void setTimeForGuessingSong(int timeInSeconds) {
        timeForGuessingSong = timeInSeconds;
    }

    public static String makeSQLQuery(){
        String countryExpression;
        String genreExpression;

        switch (songCountry){
            case "polish songs" : countryExpression = "country = 'Polska'";
            break;

            case "foreign songs" : countryExpression = "country != 'Polska'";
            break;

            default:
                countryExpression = "country is not null";
        }

        switch (songGenre){
            case "all" : genreExpression = "songGenre is not null";
                break;

            default:
                genreExpression = String.format("songGenre = '%s'", songGenre);
        }

        return String.format(SQLQueryTemplate, countryExpression, genreExpression);
    }
}
