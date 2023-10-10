package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Players{

    /**
     * Constructor that does not initialize anything
     *
     * @author u7754892 Yaohui Hou
     */
    public ComputerPlayer() {}
    public ComputerPlayer(int numDirham, int numRug, boolean inGame){
        super(numDirham, numRug, inGame);
    }


    /**
     * Get all valid movement for computer players when dragging rugs
     * @param currentGame A game string representing the current state of the game
     * @param rug A rug string representing the candidate rug which the computer player want to drag
     * @return A list that contains all valid movements
     *
     * @author Yaohui Hou
     */
    public List<String> getPossiblePositions(String currentGame, String rug) {
        List<String> possiblePositions = new ArrayList<>();
        Game game = new Game().StringToGame(currentGame);
        Rug rug1 = new Rug().StringToRug(rug);
        int x = game.getAssam().getAbsolutePosition().getX();
        int y = game.getAssam().getAbsolutePosition().getY();
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int new_x = x + dx[i];
            int new_y = y + dy[i];
            for (int j = 0; j < 4; j++) {
                int new_new_x = new_x + dx[j];
                int new_new_y = new_y + dy[j];
                String rugPos = rug1.getColor() + String.valueOf(rug1.getRugID()) + new_x + new_y + new_new_x + new_new_y;
                possiblePositions.add(rugPos);
            }
        }

        for (String rugPos : possiblePositions) {
            if (!Marrakech.isPlacementValid(currentGame, rugPos)) {
                possiblePositions.remove(rugPos);
            }
        }
        return possiblePositions;
    }

    /**
     * Get a valid move at random
     * @param possiblePositions A list that contains all valid movements
     * @return A string in the list
     *
     * @author Yaohui Hou
     */
    public String dragRandomly(List<String> possiblePositions){
        Random random = new Random();
        int randomIndex = random.nextInt(possiblePositions.size());

        return possiblePositions.get(randomIndex);
    }

}

