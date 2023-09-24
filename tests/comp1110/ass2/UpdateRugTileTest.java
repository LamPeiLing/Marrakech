package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class UpdateRugTileTest {

    Board board = new Board();
    RugTile rugTile = new RugTile();
    IntPair position = new IntPair();

    /**
     * testing whether the updates of rug tile have been done correctly
     */
    @Test
    public void checkUpdate() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/update_rugtile.txt")));
        Stream<String> testLines = fr.lines();

        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");
            board = board.StringToBoard(splitLine[3].substring(1));

            rugTile.setAbsolutePosition(position.StringToIntPair(splitLine[0]));

            switch (splitLine[1]) {
                case "y":
                    rugTile.setColor(Color.YELLOW);
                    break;
                case "r":
                    rugTile.setColor(Color.RED);
                    break;
                case "c":
                    rugTile.setColor(Color.CYAN);
                    break;
                case "p":
                    rugTile.setColor(Color.PURPLE);
                    break;
            }
            rugTile.setId(Integer.parseInt(splitLine[2]));

            board.updateRugTile(rugTile); // main method to test here
            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[3], board.BoardToString(), splitLine[4]);
        }
    }
}
