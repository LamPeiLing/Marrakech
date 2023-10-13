package comp1110.ass2;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void gameToString() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/game_to_string.txt")));
        Stream<String> testLines = fr.lines();
        for(String line : testLines.toList()){
            String[] splitLine = line.split("@");
            String[] players = splitLine[1].split("!");
            Game game = new Game();
            List<Players> playersList = new ArrayList<>();
            for (String s : players) {
                Players player = new Players();
                playersList.add(player.StringToPlayer(s));
            }
            Assam assam = new Assam().StringToAssam(splitLine[2]);
            Board board = new Board().StringToBoard(splitLine[3]);
            game.setPlayersList(playersList);
            game.setAssam(assam);
            game.setBoard(board);
            assertEquals(splitLine[0], game.GameToString());
        }
    }

    @Test
    void stringToGame() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/string_to_game.txt")));
        Stream<String> testLines = fr.lines();
        Game game = new Game();
        for(String line : testLines.toList()){
            String[] splitLine = line.split("@");
            String[] players =  splitLine[1].split("!");
            List<Players> playersList = game.StringToGame(splitLine[0]).getPlayersList();

           for(int i = 0; i < playersList.size(); i++){
               assertEquals(players[i], playersList.get(i).PlayerToString(), splitLine[4]);
           }
           assertEquals(splitLine[2], game.StringToGame(splitLine[0]).getAssam().AssamToString(), splitLine[4]);
           assertEquals(splitLine[3], game.StringToGame(splitLine[0]).getBoard().BoardToString(), splitLine[4]);
        }
    }
}