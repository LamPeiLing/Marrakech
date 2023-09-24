package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)

public class StringToBoardTest {

    Board board = new Board();

    /**
     * This test is checking when there is no rugs on the board
     * should return empty list
     */
    @Test
    public void checkEmptyBoard() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/string_to_board_empty.txt")));
        Stream<String> testLines = fr.lines();

        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");

            String output = "";

            board = board.StringToBoard(splitLine[0].substring(1));
            for (RugTile r : board.getBoardPosition()) {
                if(r.getColor() != null && r.getAbsolutePosition() != null) {
                    output += r.getColor().value + String.valueOf(r.getId());
                }
            }

            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[1], output, splitLine[2]);
        }
    }

    /**
     * This test is checking when there is only one segment of rug on the board
     * should return size one of list
     */
    @Test
    public void checkTrivialRugTileOnBoard() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/string_to_board_trivial.txt")));
        Stream<String> testLines = fr.lines();

        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");

            String output = "";

            board = board.StringToBoard(splitLine[0].substring(1));
            for (RugTile r : board.getBoardPosition()) {
                if(r.getColor() != null && r.getAbsolutePosition() != null) {
                    output += r.getColor().value + String.valueOf(r.getId());
                }
            }

            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[1], output, splitLine[2]);
        }
    }

    /**
     * This test is checking when there is one rug on the board i.e. two segments of rug
     * should return size two of list
     */
    @Test
    public void checkTrivialRugOnBoard() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/string_to_board_trivial_rug.txt")));
        Stream<String> testLines = fr.lines();

        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");

            String output = "";

            board = board.StringToBoard(splitLine[0].substring(1));
            for (RugTile r : board.getBoardPosition()) {
                if(r.getColor() != null && r.getAbsolutePosition() != null) {
                    output += r.getColor().value + "0" + String.valueOf(r.getId());
                }
            }

            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[1], output, splitLine[2]);
        }
    }

    /**
     * This test is checking when the board in such a complex situation where it has many different rug tile on it
     */
    @Test
    public void checkComplexBoard() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/string_to_board_trivial_rug.txt")));
        Stream<String> testLines = fr.lines();

        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");

            String output = "";

            board = board.StringToBoard(splitLine[0].substring(1));
            for (RugTile r : board.getBoardPosition()) {
                if(r.getColor() != null && r.getAbsolutePosition() != null) {
                    output += r.getColor().value;
                    if(String.valueOf(r.getId()).length() == 1) {
                        output += "0";
                    }
                    output += String.valueOf(r.getId());
                }
            }

            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[1], output, splitLine[2]);
        }
    }
}
