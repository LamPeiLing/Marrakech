package comp1110.ass2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoinTest {

    @Test
    void updateNumOfCoin_5() {
        Coin coin1 = new Coin();
        Coin coin2 = new Coin();
        coin1.updateNumOfCoin_5(6, true);
        coin2.updateNumOfCoin_5(6, false);
        assertEquals(6, coin1.getCurrentNumOfCoin_5());
        assertEquals(4, coin2.getCurrentNumOfCoin_5());
    }

    @Test
    void updateNumOfCoin_1() {
        Coin coin1 = new Coin();
        Coin coin2 = new Coin();
        coin1.updateNumOfCoin_1(3, true);
        coin2.updateNumOfCoin_1(6, false);
        assertEquals(8, coin1.getCurrentNumOfCoin_1());
        assertEquals(4, coin2.getCurrentNumOfCoin_1());
    }
}