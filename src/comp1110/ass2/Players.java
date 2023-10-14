package comp1110.ass2;

/**
 * @author u7754892 Yaohui Hou
 * @author u7754637 Pei Ling Lam (supporting author)
 */
public class Players {

    // color of rug the player owned
    private Color color;

    // number of dirham player owned
    private int numDirham;

    // number of rugs player left
    private int numRug;
    private boolean isInGame;

    private Scores scores = new Scores();

    /**
     * Constructor that initialize a player when a new game starts
     * @param numDirham initial number of dirham
     * @param numRug initial number of rugs
     * @param inGame boolean to check whether player is in or out the game
     *
     * @author u7754637 Pei Ling Lam
     */
    public Players(int numDirham, int numRug, boolean inGame) {
        this.numDirham = numDirham;
        this.numRug = numRug;
        this.isInGame = inGame;
    }

    /**
     * Constructor that does not initialize anything
     *
     * @author u7754637 Pei Ling Lam
     */
    public Players() {}

    /*
    setters and getters
     */

    public void setIsInGame(boolean isInGame) {
       this.isInGame = isInGame;
    }

    public boolean isInGame() {
        return isInGame;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setNumDirham(int n) {
        this.numDirham = n;
        scores.setDirhamScore(n);
    }

    public int getNumDirham() {
        return numDirham;
    }

    public void setNumRug(int n) {
        this.numRug = n;
    }

    public int getNumRug() {
        return numRug;
    }

    public void setScores(Scores scores) {
        this.scores = scores;
    }

    public Scores getScores() {
        return scores;
    }


    /**
     * method to update the number of remaining rug everytime a rug is placed
     * also update the score of the rug
     *
     * @author u7754637 Pei Ling Lam
     */
    public void updateNumRug() {
        this.numRug--;
        scores.updateRugScore(false);
    }

    /**
     * update number of dirhams of the player
     * update also the score of dirhams
     * @param n value that should be added or subtracted
     * @param isSubtract true if pay to someone else, false if get paid
     *
     * @author u7754637 Pei Ling Lam
     */
    public void updateNumDirham(int n, boolean isSubtract) {
        if(isSubtract) {
            this.numDirham -= n;
            scores.updateDirhamScore(n,!isSubtract);
        } else {
            this.numDirham += n;
            scores.updateDirhamScore(n, !isSubtract);
        }
    }


    /**
     * convert Players to String
     * @return Players type
     *
     * @author u7754637 Pei Ling Lam
     */
    public String PlayerToString() {
        String stringPlayer = "P";

        stringPlayer += getColor().value;

        String numDirham = String.valueOf(getNumDirham());
        if(numDirham.length() == 3) {
            stringPlayer += numDirham;
        } else if(numDirham.length() == 2) {
            stringPlayer += "0" + numDirham;
        } else {
            stringPlayer += "00" + numDirham;
        }

        String numRug = String.valueOf(getNumRug());
        if(numRug.length() == 2) {
            stringPlayer += numRug;
        } else {
            stringPlayer += "0" + numRug;
        }

        if(isInGame()){
            stringPlayer += "i";
        } else {
            stringPlayer += "o";
        }

        return stringPlayer;
    }

    /**
     * convert string to Players
     * @param stringPlayer string of player representation
     * @return Players type of the string
     *
     * @author u7754637 Pei Ling Lam
     */
    public Players StringToPlayer(String stringPlayer) {
        // get rug color of player
        if(stringPlayer.charAt(1) == 'c') {
            setColor(Color.CYAN);
        } else if(stringPlayer.charAt(1) == 'y') {
            setColor(Color.YELLOW);
        } else if(stringPlayer.charAt(1) == 'r') {
            setColor(Color.RED);
        } else if(stringPlayer.charAt(1) == 'p'){
            setColor(Color.PURPLE);
        } else {
            setColor(null);
        }

        setNumDirham(Integer.parseInt(stringPlayer.substring(2,5)));
        setNumRug(Integer.parseInt(stringPlayer.substring(5,7)));
        if(stringPlayer.charAt(7) == 'i') {
            setIsInGame(true);
        } else {
            setIsInGame(false);
        }

        return this;
    }

}
