package comp1110.ass2;

/**
 * @author u7754892 Yaohui Hou
 */
public class Coin {

    //Integers that define the face value of coins
    private final static int coin_5 = 5;
    private final static int coin_1 = 1;



    //Integers that define the number of coins every player have during the game
    //The starting number of coins for each player is 5 5-dirham coins and 5 1-dirham coins.
    private int currentNumOfCoin_5 = 5;
    private int currentNumOfCoin_1 = 5;

    public Coin() {}

    /**
     * method to get the number of 5 value coin of the player
     * @return number of 5 value coin
     *
     * @author u7754892 Yaohui Hou
     */
    public int getCurrentNumOfCoin_5(){
        return currentNumOfCoin_5;
    }

    /**
     * method to get the number of 1 value coin of the player
     * @return number of 1 value coin
     *
     * @author u7754892 Yaohui Hou
     */
    public int getCurrentNumOfCoin_1(){
        return currentNumOfCoin_1;
    }

    /**
     * update the number of coin_5 and coin_1 by checking whether it is getting coins from other players or give coins to other players
     * @param connectedRugsScore the score of rugs that connect together
     * @param isGet true if get from other players, false otherwise
     *
     * @author u7754892 Yaohui Hou
     */
    public void updateNumOfCoin_5(int connectedRugsScore, boolean isGet){
        int numOfCoin_5 = connectedRugsScore / 5;
        if(isGet) this.currentNumOfCoin_5 += numOfCoin_5;
        else this.currentNumOfCoin_5 -= numOfCoin_5;
    }

    /**
     * update the number of coin_5 and coin_1 by checking whether it is getting coins from other players or give coins to other players
     * @param connectRugsScore the score of rugs that connect together
     * @param isGet true if get from other players, false otherwise
     *
     * @author u7754892 Yaohui Hou
     */
    public void updateNumOfCoin_1(int connectRugsScore, boolean isGet){
        int numOfCoin_1 = connectRugsScore % 5;
        if(isGet) this.currentNumOfCoin_1 += numOfCoin_1;
        else this.currentNumOfCoin_1 -= numOfCoin_1;
    }
}
