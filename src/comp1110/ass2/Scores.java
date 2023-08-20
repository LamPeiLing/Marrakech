package comp1110.ass2;

public class Scores {

    private int coinScore; // total points for the coin

    private int rugScore; // total points for the rug

    private Coin coin;

    /**
     * Constructor: creates a new instance of the Rug class.
     * initialize coin score to the coins that allocated to all players from Coin class, then reduce or increase according to the game
     * initialize rug score to 0, then increase or reduce
     */
    public Scores() {
        this.coinScore = coin.getCoin();
        this.rugScore = 0;
    }

    /**
     * get the coin score
     * @return score of coin itself
     */
    public int getCoinScore() {
        return this.coinScore;
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
        return getCoinScore() + getRugScore();
    }


    /**
     * update the coin score by checking whether it is getting coins from other players or give coins to other players
     * @param score the score to update
     * @param isGet true if get from other players, false otherwise
     */
    public void updateCoinScore(int score, boolean isGet) {
        if(isGet) this.coinScore += score;
        else this.coinScore -= score;
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
