package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)

public class BoardToStringTest {

    Board board = new Board();

    /**
     * testing Board converts to string when board is empty
     */
    @Test
    public void checkEmptyBoard() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/board_to_string_empty.txt")));
        Stream<String> testLines = fr.lines();

        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");

            String[] splitRugTile = splitLine[0].split("-");
            for (String piece: splitRugTile) {
                RugTile rugTile = new RugTile();
                if(!piece.equals("null")) {
                    switch (piece.charAt(0)) {
                        case 'y':
                            rugTile.setColor(Color.YELLOW);
                            break;
                        case 'r':
                            rugTile.setColor(Color.RED);
                            break;
                        case 'c':
                            rugTile.setColor(Color.CYAN);
                            break;
                        case 'p':
                            rugTile.setColor(Color.PURPLE);
                            break;
                    }
                    rugTile.setId(Integer.parseInt(piece.substring(1)));
                }
                board.getBoardPosition().add(rugTile);
            }

            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[1], board.BoardToString(), splitLine[2]);
        }
    }

    /**
     * testing Board converts to string when board has only one piece of rug
     */
    @Test
    public void checkTrivialBoard() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/board_to_string_trivial.txt")));
        Stream<String> testLines = fr.lines();

        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");

            String[] splitRugTile = splitLine[0].split("-");
            List<RugTile> rugTileList = new ArrayList<>();
            int x = 0;
            int y = 0;

            for (int i = 0; i < splitRugTile.length; i++) {
                RugTile rugTile = new RugTile();
                if(!splitRugTile[i].equals("null")) {
                    switch (splitRugTile[i].charAt(0)) {
                        case 'y':
                            rugTile.setColor(Color.YELLOW);
                            break;
                        case 'r':
                            rugTile.setColor(Color.RED);
                            break;
                        case 'c':
                            rugTile.setColor(Color.CYAN);
                            break;
                        case 'p':
                            rugTile.setColor(Color.PURPLE);
                            break;
                    }
                    rugTile.setId(Integer.parseInt(splitRugTile[i].substring(1)));
                }
                if(i % 7 == 0 && i != 0) {
                    x++;
                    y=0;
                }
                rugTile.setAbsolutePosition(new IntPair(x,y++));
                rugTileList.add(rugTile);
            }

            board.setBoardPosition(rugTileList);

            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[1], board.BoardToString(), splitLine[2]);
        }
    }

    /**
     * testing Board converts to string when board has few piece of rugs
     */
    @Test
    public void checkSimpleBoard() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/board_to_string_simple.txt")));
        Stream<String> testLines = fr.lines();

        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");

            String[] splitRugTile = splitLine[0].split("-");
            List<RugTile> rugTileList = new ArrayList<>();
            int x = 0;
            int y = 0;

            for (int i = 0; i < splitRugTile.length; i++) {
                RugTile rugTile = new RugTile();
                if(!splitRugTile[i].equals("null")) {
                    switch (splitRugTile[i].charAt(0)) {
                        case 'y':
                            rugTile.setColor(Color.YELLOW);
                            break;
                        case 'r':
                            rugTile.setColor(Color.RED);
                            break;
                        case 'c':
                            rugTile.setColor(Color.CYAN);
                            break;
                        case 'p':
                            rugTile.setColor(Color.PURPLE);
                            break;
                    }
                    rugTile.setId(Integer.parseInt(splitRugTile[i].substring(1)));
                }
                if(i % 7 == 0 && i != 0) {
                    x++;
                    y=0;
                }
                rugTile.setAbsolutePosition(new IntPair(x,y++));
                rugTileList.add(rugTile);
            }

            board.setBoardPosition(rugTileList);

            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[1], board.BoardToString(), splitLine[2]);
        }
    }

    /**
     * testing Board converts to string when board has many rugs on it
     */
    @Test
    public void checkComplexBoard() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/board_to_string_complex.txt")));
        Stream<String> testLines = fr.lines();

        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");

            String[] splitRugTile = splitLine[0].split("-");
            List<RugTile> rugTileList = new ArrayList<>();
            int x = 0;
            int y = 0;

            for (int i = 0; i < splitRugTile.length; i++) {
                RugTile rugTile = new RugTile();
                if(!splitRugTile[i].equals("null")) {
                    switch (splitRugTile[i].charAt(0)) {
                        case 'y':
                            rugTile.setColor(Color.YELLOW);
                            break;
                        case 'r':
                            rugTile.setColor(Color.RED);
                            break;
                        case 'c':
                            rugTile.setColor(Color.CYAN);
                            break;
                        case 'p':
                            rugTile.setColor(Color.PURPLE);
                            break;
                    }
                    rugTile.setId(Integer.parseInt(splitRugTile[i].substring(1)));
                }
                if(i % 7 == 0 && i != 0) {
                    x++;
                    y=0;
                }
                rugTile.setAbsolutePosition(new IntPair(x,y++));
                rugTileList.add(rugTile);
            }

            board.setBoardPosition(rugTileList);

            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(splitLine[1], board.BoardToString(), splitLine[2]);
        }
    }

}
