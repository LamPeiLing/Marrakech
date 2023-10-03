package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Timeout(value = 1300, unit = TimeUnit.MILLISECONDS)
public class IsPlacementValidTest {
    /**
     * These tests check for simple situations which should be valid -- specifically where a placement is made onto
     * two empty squares and is thus valid, or where a placement is invalid because it is not adjacent to Assam.
     */
    @Test
    public void simplePlacements() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/is_placement_valid_simple.txt")));
        Stream<String> testLines = fr.lines();
        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");
            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(Boolean.valueOf(splitLine[2]), Marrakech.isPlacementValid(splitLine[0], splitLine[1]), splitLine[3]);
        }
    }

    /**
     * These tests check for more complex situations which ought to be valid -- specifically the invalid cases are
     * ones where the placement is invalid because it totally covers another rug, and the valid cases are ones where
     * the placement is valid despite partially covering other rugs.
     */
    @Test
    public void complexPlacements() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/is_placement_valid_complex.txt")));
        Stream<String> testLines = fr.lines();
        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");
            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(Boolean.valueOf(splitLine[2]), Marrakech.isPlacementValid(splitLine[0], splitLine[1]), splitLine[3]);
        }
    }
}