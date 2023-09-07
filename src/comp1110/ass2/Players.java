package comp1110.ass2;

public class Players {

    //Integers that define the number of coins every player have during the game
    private int currentNumOfCoin_5;
    private int currentNumOfCoin_1;

    // current position on the board
    private IntPair absolutePosition;

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
     * @param color color of the rugs
     */
    public Players(int numDirham, int numRug, Color color) {
        this.color = color;
        this.numDirham = numDirham;
        this.numRug = numRug;
    }

    /**
     * Constructor that does not initialize anything
     */
    public Players() {}

    public int getCurrentNumOfCoin_5(){
        return currentNumOfCoin_5;
    }

    public int getCurrentNumOfCoin_1(){
        return currentNumOfCoin_1;
    }


    /**
     * Check if the player who moves the Assam has enough changes to pay the fee
     * @return true if the changes are enough, otherwise false
     */
    public boolean needGiveChange(){
        return false;
    }

    /**
     * Calculate how much change does the player need
     * @return the amount of change
     */
    public int giveChange(){
        if(needGiveChange()){
            return 0;
        }else{
            return 0;
        }
    }

    public void setIsInGame(char representation) {
        if(representation == 'i') {
            isInGame = true;
        } else  {
            isInGame = false;
        }
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
        scores.setRugScore(n);
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


    // convert Players to String
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

    // convert String to Players
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
        setIsInGame(stringPlayer.charAt(7));

        return this;
    }

}
