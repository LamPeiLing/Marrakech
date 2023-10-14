package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;

/**
 * class that handle the full game string and control of other classes
 *
 * @author u7754892 Yaohui Hou
 * @author u7754637 Pei Ling Lam (supporting author)
 */
public class Game {

    private List<Players> playersList = new ArrayList<>();

    private Board board = new Board();

    private Assam assam = new Assam();


    /**
     * Constructor
     * do nothing at this moment
     */
    public Game() {

    }

    /*
    setters and getters
     */
    public void setPlayersList(List<Players> playersList) {
        this.playersList = playersList;
    }

    public List<Players> getPlayersList() {
        return playersList;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public void setAssam(Assam assam) {
        this.assam = assam;
    }

    public Assam getAssam() {
        return assam;
    }

    /**
     * method to find corresponding player given color
     * @param color color of the player to find
     * @return player
     *
     * @author u7754637 Pei Ling Lam
     */
    public Players findPlayerFromColor(Color color) {
        if(color == null) return null;

        for (Players p : playersList) {
            if(p.getColor() == color){
                return p;
            }
        }
        return null;
    }

    /**
     * method that update information of single player only to the game state
     * @param player player that need to be updated
     *
     * @author u7754637 Pei Ling Lam
     */
    public void updatePlayer(Players player) {
        for (int i = 0; i < playersList.size(); i++) {
            if(playersList.get(i).getColor() == player.getColor()) {
                playersList.set(i, player);
            }
        }
    }

    /**
     * Convert Game type to String
     * @return String representation of current game state
     *
     * @author u7754637 Pei Ling Lam
     */
    public String GameToString() {
        String gameState = "";
        for (Players player: playersList) {
            gameState += player.PlayerToString();
        }

        gameState += assam.AssamToString();

        gameState += board.BoardToString();

        return gameState;
    }

    /**
     * Convert game string representation to Game class type
     * @param gameState get the string representation of current game state
     * @return Game class type data
     *
     * @author u7754637 Pei Ling Lam
     */
    public Game StringToGame(String gameState) {
        String boardRug = gameState.substring(gameState.length()-147);
        String string_p1 = gameState.substring(0, 8);
        String string_p2 = gameState.substring(8, 16);
        String string_p3 = null;
        String string_p4 = null;
        String string_assam = "";

        if (gameState.charAt(16) == 'P') {
            string_p3 = gameState.substring(16, 24);
            if(gameState.charAt(24) == 'P') {
                string_p4 = gameState.substring(24, 32);
            } else {
                string_assam = gameState.substring(24, 28);
            }
        } else {
            string_assam = gameState.substring(16, 20);
        }
        if(string_assam.length() == 0) {
            string_assam = gameState.substring(32, 36);
        }

        // convert into corresponding data type
        List<Players> playersList = new ArrayList<Players>();

        Players  p1= new Players();
        p1.StringToPlayer(string_p1);
        playersList.add(p1);

        Players p2 = new Players();
        p2.StringToPlayer(string_p2);
        playersList.add(p2);

        if(string_p3 != null) {
            Players p3 = new Players();
            p3.StringToPlayer(string_p3);
            playersList.add(p3);
        }

        if(string_p4 != null) {
            Players p4 = new Players();
            p4.StringToPlayer(string_p4);
            playersList.add(p4);
        }
        setPlayersList(playersList);

        Assam assam = new Assam();
        assam = assam.StringToAssam(string_assam);
        setAssam(assam);

        Board board = new Board();
        board = board.StringToBoard(boardRug);
        setBoard(board);

        return this;
    }

}