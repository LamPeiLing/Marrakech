package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
public class GetPaymentAmountTest {
    @Test
    public void checkSmallPayments() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/get_payment_small.txt")));
        Stream<String> testLines = fr.lines();
        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");
            // For this test, there's one argument needed to the function
            Assertions.assertEquals(splitLine[1], String.valueOf(Marrakech.getPaymentAmount(splitLine[0])), splitLine[2]);
        }
    }

    @Test
    public void checkMediumPayments() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/get_payment_medium.txt")));
        Stream<String> testLines = fr.lines();
        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");
            // For this test, there's one argument needed to the function
            Assertions.assertEquals(splitLine[1], String.valueOf(Marrakech.getPaymentAmount(splitLine[0])), splitLine[2]);
        }
    }

    @Test
    public void checkLargePayments() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/get_payment_large.txt")));
        Stream<String> testLines = fr.lines();
        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");
            // For this test, there's one argument needed to the function
            Assertions.assertEquals(splitLine[1], String.valueOf(Marrakech.getPaymentAmount(splitLine[0])), splitLine[2]);
        }
    }
}
