package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)

public class StringToAssamTest {
    Assam assam = new Assam();

    /**
     * testing by checking the position when converting string to Assam
     */
    @Test
    public void checkPosition() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/string_to_assam_position.txt")));
        Stream<String> testLines = fr.lines();
        IntPair position = new IntPair();
        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");
            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[1], position.IntPairToString(assam.StringToAssam(splitLine[0]).getAbsolutePosition()), splitLine[2]);
        }
    }

    /**
     * testing by checking the direction when converting string to Assam
     */
    @Test
    public void checkDirection() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/string_to_assam_direction.txt")));
        Stream<String> testLines = fr.lines();
        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");
            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[1], String.valueOf(assam.StringToAssam(splitLine[0]).getCurrentDirection().value), splitLine[2]);
        }
    }
}
