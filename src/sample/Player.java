package sample;

public class Player {
    private String playerNickname;
    private int playerScore;

    public Player(){
        playerScore = 0;
    }

    public void setPlayerNickname(String playerNickname) {
        this.playerNickname = playerNickname;
    }

    public void increaseScore(){
        playerScore += 100;
    }

    public int getPlayerScore(){
        return playerScore;
    }
}
