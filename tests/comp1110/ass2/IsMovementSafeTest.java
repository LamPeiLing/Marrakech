package comp1110.ass2;

import comp1110.ass2.Assam;
import comp1110.ass2.Direction;
import comp1110.ass2.IntPair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class IsMovementSafeTest {
    Assam assam = new Assam();
    IntPair position = new IntPair();

    /**
     * testing when Assam is safe to move forward
     */
    @Test
    public void checkIsMovementSafe() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/movement_safe.txt")));
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
            Assertions.assertEquals(splitLine[2], String.valueOf(assam.isMovementSafe()), splitLine[3]);
        }
    }

    /**
     * testing when Assam is not safe to move forward
     */
    @Test
    public void checkIsMovementNotSafe() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/movement_not_safe.txt")));
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
            Assertions.assertEquals(splitLine[2], String.valueOf(assam.isMovementSafe()), splitLine[3]);
        }
    }
}
