package comp1110.ass2;

import comp1110.ass2.Color;
import comp1110.ass2.RugTile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)

public class RugTileToStringTest {

    RugTile rugTile = new RugTile();

    /**
     * testing when converting rug tile to string
     */
    @Test
    public void checkPositionAndDirection() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/rugtile_to_string.txt")));
        Stream<String> testLines = fr.lines();

        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");

            switch (splitLine[0]) {
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
            rugTile.setId(Integer.parseInt(splitLine[1]));

            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[2], rugTile.RugTileToString(), splitLine[3]);
        }
    }
}
