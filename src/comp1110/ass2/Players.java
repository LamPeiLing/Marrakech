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

    // number of rugs player owned
    private int numRug;
    private boolean isInGame;

    private Scores scores = new Scores();

    // Constructor
    public Players(int numDirham, int numRug, Color color) {
        this.color = color;
        this.numDirham = numDirham;
        this.numRug = numRug;
    }

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

    public Color getColor() {
        return color;
    }

    public int getNumDirham() {
        return numDirham;
    }

    public int getNumRug() {
        return numRug;
    }


    // convert Players to String
    public String PlayerToString(Players players) {
        String stringPlayer = "P";

        stringPlayer += players.getColor().value;

        String numDirham = String.valueOf(players.getNumDirham());
        if(numDirham.length() == 3) {
            stringPlayer += numDirham;
        } else if(numDirham.length() == 2) {
            stringPlayer += "0" + numDirham;
        } else {
            stringPlayer += "00" + numDirham;
        }

        String numRug = String.valueOf(players.getNumRug());
        if(numRug.length() == 2) {
            stringPlayer += numRug;
        } else {
            stringPlayer += "0" + numRug;
        }

        if(players.isInGame()){
            stringPlayer += "i";
        } else {
            stringPlayer += "o";
        }

        return stringPlayer;
    }

    // convert String to Players
    public Players StringToPlayer(String stringPlayer) {
        // get rug color of player
        Color rugColor;
        if(stringPlayer.charAt(1) == 'c') {
            rugColor = Color.CYAN;
        } else if(stringPlayer.charAt(1) == 'y') {
            rugColor = Color.YELLOW;
        } else if(stringPlayer.charAt(1) == 'r') {
            rugColor = Color.RED;
        } else {
            rugColor = Color.PURPLE;
        }

        int numDirham = Integer.parseInt(stringPlayer.substring(2,4));
        scores.setDirhamScore(numDirham);
        int numRug = Integer.parseInt(stringPlayer.substring(5,6));
        scores.setRugScore(numRug);

        Players player = new Players(numDirham, numRug, rugColor);
        player.setIsInGame(stringPlayer.charAt(7));

        return player;
    }

}
