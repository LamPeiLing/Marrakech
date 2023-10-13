package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class PlayersTest {

    @Test
    public void isInGame() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/player_is_in_game.txt")));
        Stream<String> testLines = fr.lines();
        Players player = new Players();
        for(String line : testLines.toList()){
            String[] splitLine = line.split("@");
            Assertions.assertEquals(splitLine[1], String.valueOf(player.StringToPlayer(splitLine[0]).isInGame()), splitLine[2]);
        }
    }

    @Test
    void playerToString() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/player_to_string.txt")));
        Stream<String> testLines = fr.lines();
        for(String line : testLines.toList()){
            String[] splitLine = line.split("@");
            String[] playerAttribute = splitLine[1].split("!");
            Players player = new Players();
            Color color = Color.RED;
            player.setColor(color.getColorFromValue(playerAttribute[0].toLowerCase().charAt(0)));
            player.setNumDirham(Integer.parseInt(playerAttribute[1]));
            player.setNumRug(Integer.parseInt(playerAttribute[2]));
            player.setIsInGame(Boolean.parseBoolean(playerAttribute[3]));
            assertEquals(splitLine[0], player.PlayerToString());
        }
    }

    @Test
    void stringToPlayer() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/string_to_player.txt")));
        Stream<String> testLines = fr.lines();
        Players player = new Players();
        for (String line : testLines.toList()){
            String[] splitLine = line.split("@");
            Assertions.assertEquals(splitLine[1], player.StringToPlayer(splitLine[0]).getColor().toString(), splitLine[5]);
            Assertions.assertEquals(Integer.parseInt(splitLine[2]), player.StringToPlayer(splitLine[0]).getNumDirham(), splitLine[5]);
            Assertions.assertEquals(Integer.parseInt(splitLine[3]), player.StringToPlayer(splitLine[0]).getNumRug(), splitLine[5]);
            Assertions.assertEquals(splitLine[4],String.valueOf(player.StringToPlayer(splitLine[0]).isInGame()), splitLine[5]);
        }
    }

    @Test
    void testUpdateNumRug() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/update_number_of_rugs.txt")));
        Stream<String> testLines = fr.lines();
        Players player = new Players();
        for(String line : testLines.toList()){
            String[] splitLine = line.split("@");
            player.StringToPlayer(splitLine[0]).updateNumRug();
            Assertions.assertEquals(Integer.parseInt(splitLine[1]), player.getNumRug());
        }
    }
}