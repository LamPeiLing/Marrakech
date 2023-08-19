package comp1110.ass2;

public class Players {

    //Integers that define the number of coins every player have during the game
    private int currentNumOfCoin_5;
    private int currentNumOfCoin_1;

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

}
