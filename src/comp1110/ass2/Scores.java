package comp1110.ass2;

public class Scores {

    private int dirhamScore; // total points for the coin

    private int rugScore; // total points for the rug

    private Coin coin = new Coin();

    /**
     * Constructor: count the score of each game state
     */
    public Scores() {}

    public void setDirhamScore(int score) {
        this.dirhamScore = score;
    }

    /**
     * get the coin score
     * @return score of coin itself
     */
    public int getDirhamScore() {
        return this.dirhamScore;
    }

    public void setRugScore(int score) {
        this.rugScore = score;
    }

    /**
     * get the rug score
     * @return number of visible rug itself
     */
    public int getRugScore() {
        return this.rugScore;
    }

    /**
     * get the total score when the game ends
     * @return the total number of scores the player get in total
     */
    public int getTotalScore() {
        return this.dirhamScore + this.rugScore;
    }


    /**
     * update the coin score by checking whether it is getting coins from other players or give coins to other players
     * @param score the score to update
     * @param isGet true if get from other players, false otherwise
     */
    public void updateDirhamScore(int score, boolean isGet) {
        if(isGet) this.dirhamScore += score;
        else this.dirhamScore -= score;
    }

    /**
     * update the rug score by checking whether it is put on it or overlapped by other rugs
     * if player put rug on the board, +2 points
     * if overlapped by others, -1 point (since only can be overlapped half rug at a point)
     * @param isOverlap true if overlapped, false if player put the rug
     */
    public void updateRugScore(boolean isOverlap) {
        if(isOverlap) this.rugScore --;
        else this.rugScore += 2;
    }
}
