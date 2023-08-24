package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class GetWinnerTest {
    @Test
    public void getWinnerSimple() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/get_winner_simple.txt")));
        Stream<String> testLines = fr.lines();
        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");
            // For this test, there's one argument needed to the function
            Assertions.assertEquals(splitLine[1], String.valueOf(Marrakech.getWinner(splitLine[0])), splitLine[2]);
        }
    }

    @Test
    public void getWinnerOutOfGame() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/get_winner_complex.txt")));
        Stream<String> testLines = fr.lines();
        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");
            // For this test, there's one argument needed to the function
            Assertions.assertEquals(splitLine[1], String.valueOf(Marrakech.getWinner(splitLine[0])), splitLine[2]);
        }
    }
}
