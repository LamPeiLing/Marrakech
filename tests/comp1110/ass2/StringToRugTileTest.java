package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)

public class StringToRugTileTest {

    RugTile rugTile = new RugTile();

    /**
     * testing by checking the color when converting string to RugTile
     */
    @Test
    public void checkColor() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/string_to_rugtile_color.txt")));
        Stream<String> testLines = fr.lines();

        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");
            rugTile.StringToRugTile(splitLine[0],0,0); // position will put 0 first because string representation of rugtile does not have information about the position
            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[1], String.valueOf(rugTile.getColor().value), splitLine[2]);
        }
    }

    /**
     * testing by checking the id when converting string to RugTile
     */
    @Test
    public void checkId() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/string_to_rugtile_id.txt")));
        Stream<String> testLines = fr.lines();
        IntPair position = new IntPair();
        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");
            rugTile.StringToRugTile(splitLine[0],0,0); // position will put 0 first because string representation of rugtile does not have information about the position
            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[1], String.valueOf(rugTile.getId()), splitLine[2]);
        }
    }
}
