package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class RotateAssamTest {
    /**
     * Check that rotations of Assam that are legal according to the rules result in the correct eventual facing
     */
    @Test
    public void checkLegalRotations() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/assam_rotation_legal.txt")));
        Stream<String> testLines = fr.lines();
        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");
            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[2], Marrakech.rotateAssam(splitLine[0], Integer.parseInt(splitLine[1])), splitLine[3]);
        }
    }

    /**
     * Check that rotations of Assam that are *not* legal according to the rules result in no change to Assam's state
     */
    @Test
    public void checkIllegalRotations() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/assam_rotation_illegal.txt")));
        Stream<String> testLines = fr.lines();
        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");
            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[2], Marrakech.rotateAssam(splitLine[0], Integer.parseInt(splitLine[1])), splitLine[3]);
        }
    }
}
