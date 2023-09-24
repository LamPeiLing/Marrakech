package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)

public class AdjacentEdgeTest {

    Assam assam = new Assam();
    IntPair position = new IntPair();

    /**
     * testing for position that has only exists two adjacents
     */
    @Test
    public void checkTwoAdjacent() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/two_adjacent.txt")));
        Stream<String> testLines = fr.lines();

        for (String line : testLines.toList()) {
            String output = "";
            String[] splitLine = line.split("@");
            position = position.StringToIntPair(splitLine[0]);
            assam.setAbsolutePosition(position);

            for(IntPair intPair: assam.adjacentEdge()) {
                output += intPair.IntPairToString(intPair);
            }
            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[1], output, splitLine[2]);
        }
    }

    /**
     * testing for position that exists three adjacents
     */
    @Test
    public void checkThreeAdjacent() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/three_adjacent.txt")));
        Stream<String> testLines = fr.lines();

        for (String line : testLines.toList()) {
            String output = "";
            String[] splitLine = line.split("@");
            position = position.StringToIntPair(splitLine[0]);
            assam.setAbsolutePosition(position);

            for(IntPair intPair: assam.adjacentEdge()) {
                output += intPair.IntPairToString(intPair);
            }
            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[1], output, splitLine[2]);
        }
    }

    /**
     * testing for position that exists all adjacents
     */
    @Test
    public void checkFourAdjacent() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/four_adjacent.txt")));
        Stream<String> testLines = fr.lines();

        for (String line : testLines.toList()) {
            String output = "";
            String[] splitLine = line.split("@");
            position = position.StringToIntPair(splitLine[0]);
            assam.setAbsolutePosition(position);

            for(IntPair intPair: assam.adjacentEdge()) {
                output += intPair.IntPairToString(intPair);
            }
            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[1], output, splitLine[2]);
        }
    }
}
