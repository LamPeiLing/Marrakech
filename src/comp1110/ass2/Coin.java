package comp1110.ass2;

public class Coin {

    //Integers that define the face value of coins
    private final static int coin_5 = 5;
    private final static int coin_1 = 1;

    //Integers that define the number of coins every player have at the beginning of the game
    private final static int startingNumOfCoin_5 = 5;
    private final static int startingNumOfCoin_1 = 5;

    //Integers that define the number of coins every player have during the game
    private int currentNumOfCoin_5;
    private int currentNumOfCoin_1;


    /**
     * Get the initial amount of dirhams every player have.
     * @return the initial amount of dirhams every player have.
     */
    public int getCoin(){
        return coin_5 * startingNumOfCoin_5 + coin_1 * startingNumOfCoin_1;
    }





}
