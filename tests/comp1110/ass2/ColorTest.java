package comp1110.ass2;

import comp1110.ass2.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class ColorTest {

    /**
     * testing for valid colors
     */
    @Test
    public void checkValidColor() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/color_valid.txt")));
        Stream<String> testLines = fr.lines();

        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");
            Color color = null;
            switch (splitLine[0]) {
                case "y":
                    color = Color.YELLOW;
                    break;
                case "r":
                    color = Color.RED;
                    break;
                case "c":
                    color = Color.CYAN;
                    break;
                case "p":
                    color = Color.PURPLE;
                    break;
            }
            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[0], String.valueOf(color.value), splitLine[1]);
        }
    }

    /**
     * testing for invalid colors
     */
    @Test
    public void checkInValidColor() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/color_invalid.txt")));
        Stream<String> testLines = fr.lines();

        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");
            Color color = null;
            switch (splitLine[0]) {
                case "y":
                    color = Color.YELLOW;
                    break;
                case "r":
                    color = Color.RED;
                    break;
                case "c":
                    color = Color.CYAN;
                    break;
                case "p":
                    color = Color.PURPLE;
                    break;
            }
            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[1], String.valueOf(color), splitLine[2]);
        }
    }
}
