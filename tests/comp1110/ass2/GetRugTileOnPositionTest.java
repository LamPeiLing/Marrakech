package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)

public class GetRugTileOnPositionTest {

    IntPair position = new IntPair();

    Board board = new Board();

    /**
     * testing for there is no rug placed on the target position
     */
    @Test
    public void checkRugDoesNotExist() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/rug_does_not_exist.txt")));
        Stream<String> testLines = fr.lines();

        for (String line : testLines.toList()) {
            String output = "";
            String[] splitLine = line.split("@");

            position = position.StringToIntPair(splitLine[0]);
            board = board.StringToBoard(splitLine[1].substring(1));
            RugTile rugTile = new RugTile();
            rugTile = board.getRugTileOnPosition(position);
            if(rugTile == null){
                output = "null";
            } else {
                output = rugTile.RugTileToString();
            }
            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[2], output, splitLine[3]);
        }
    }

    /**
     * testing for there exists rug placed on the target position
     */
    @Test
    public void checkRugExist() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/rug_exists_on_board.txt")));
        Stream<String> testLines = fr.lines();

        for (String line : testLines.toList()) {
            String output = "";
            String[] splitLine = line.split("@");

            position = position.StringToIntPair(splitLine[0]);
            board = board.StringToBoard(splitLine[1].substring(1));
            RugTile rugTile = new RugTile();
            rugTile = board.getRugTileOnPosition(position);
            if(rugTile == null){
                output = "null";
            } else {
                output = rugTile.RugTileToString();
            }
            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[2], output, splitLine[3]);
        }
    }
}
