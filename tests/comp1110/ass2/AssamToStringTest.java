package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class AssamToStringTest {
    Assam assam = new Assam();
    IntPair position = new IntPair();

    /**
     * testing the method that converts Assam to String
     */
    @Test
    public void checkPositionAndDirection() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/assam_to_string.txt")));
        Stream<String> testLines = fr.lines();

        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");

            assam.setAbsolutePosition(position.StringToIntPair(splitLine[0]));
            switch (splitLine[1]) {
                case "S":
                    assam.setCurrentDirection(Direction.SOUTH);
                    break;
                case "N":
                    assam.setCurrentDirection(Direction.NORTH);
                    break;
                case "W":
                    assam.setCurrentDirection(Direction.WEST);
                    break;
                case "E":
                    assam.setCurrentDirection(Direction.EAST);
                    break;
            }
            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[2], assam.AssamToString(), splitLine[3]);
        }
    }
}
