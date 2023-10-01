package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class IsRugValidTest {
    @Test
    public void checkRugValidBadPlayer() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/is_rug_valid_badplayer.txt")));
        Stream<String> testLines = fr.lines();
        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");
            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[2], String.valueOf(Marrakech.isRugValid(splitLine[0], splitLine[1])), splitLine[3]);
        }
    }
    @Test
    public void checkRugValidOffboard() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/is_rug_valid_offboard.txt")));
        Stream<String> testLines = fr.lines();
        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");
            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[2], String.valueOf(Marrakech.isRugValid(splitLine[0], splitLine[1])), splitLine[3]);
        }
    }

    @Test
    public void checkRugValid() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/is_rug_valid_valid.txt")));
        Stream<String> testLines = fr.lines();
        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");
            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[2], String.valueOf(Marrakech.isRugValid(splitLine[0], splitLine[1])), splitLine[3]);
        }
    }

    @Test
    public void checkRugValidDuplicate() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/is_rug_valid_duplicate.txt")));
        Stream<String> testLines = fr.lines();
        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");
            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[2], String.valueOf(Marrakech.isRugValid(splitLine[0], splitLine[1])), splitLine[3]);
        }
    }
}
