package comp1110.ass2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayersTest {

    @Test
    void setIsInGame() {
        Players player = new Players();
        player.setIsInGame(true);
        assertEquals(true, player.isInGame());
    }

    @Test
    void playerToString() {
        Players player = new Players();
        player.setColor(Color.RED);
        player.setNumDirham(10);
        player.setNumRug(6);
        player.setIsInGame(true);
        assertEquals("Pr01006i", player.PlayerToString());
    }

    @Test
    void stringToPlayer() {
        Players player = new Players();
        String playerString = "Pr01006i";
        player.StringToPlayer(playerString);
        assertEquals("r", String.valueOf(player.StringToPlayer(playerString).getColor().value));
        assertEquals(10, player.StringToPlayer(playerString).getNumDirham());
        assertEquals(6, player.StringToPlayer(playerString).getNumRug());
        assertEquals(true, player.StringToPlayer(playerString).isInGame());
    }
}