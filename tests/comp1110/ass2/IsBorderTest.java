package comp1110.ass2;

import comp1110.ass2.Assam;
import comp1110.ass2.IntPair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class IsBorderTest {

    Assam assam = new Assam();
    IntPair position = new IntPair();

    /**
     * testing when Assam is at the border of the board
     */
    @Test
    public void checkIsBorder() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/is_border.txt")));
        Stream<String> testLines = fr.lines();

        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");
            assam.setAbsolutePosition(position.StringToIntPair(splitLine[0]));
            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[1], String.valueOf(assam.isBorder()), splitLine[2]);
        }
    }

    /**
     * testing when Assam is not at the border of the board
     */
    @Test
    public void checkIsNotBorder() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/is_not_border.txt")));
        Stream<String> testLines = fr.lines();

        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");
            assam.setAbsolutePosition(position.StringToIntPair(splitLine[0]));
            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[1], String.valueOf(assam.isBorder()), splitLine[2]);
        }
    }
}
