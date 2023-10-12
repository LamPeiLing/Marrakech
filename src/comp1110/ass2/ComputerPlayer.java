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
    public List<Rug> getPossiblePositions(Game currentGame, Rug rug) {
        List<Rug> possiblePositions = new ArrayList<>();
        List<IntPair> p = currentGame.getAssam().adjacentEdge();
        List<Rug> validRugs = new ArrayList<>();

        for (IntPair tmpp: p) {
            IntPair[] posi = new IntPair[2];
            posi[0] = new IntPair(0,0);
            posi[1] = new IntPair(0,0);

            // set horizontally
            // set for right of the adjacent
            posi[0] = tmpp;
            posi[1].setX(tmpp.getX() + 1);
            posi[1].setY(tmpp.getY());
            possiblePositions.add(new Rug(rug.getColor(), rug.getRugID(), posi));

            // set for left of the adjacent
            posi[1].setX(tmpp.getX() - 1);
            possiblePositions.add(new Rug(rug.getColor(), rug.getRugID(), posi));

            // set vertically
            // set for bottom of the adjacent
            posi[0] = tmpp;
            posi[1].setX(tmpp.getX());
            posi[1].setY(tmpp.getY() + 1);
            possiblePositions.add(new Rug(rug.getColor(), rug.getRugID(), posi));

            // set for up of the adjacent
            posi[1].setY(tmpp.getY() - 1);
            possiblePositions.add(new Rug(rug.getColor(), rug.getRugID(), posi));
        }

        // make sure that the placement is valid
        for (int i = 0; i < possiblePositions.size(); i++) {
            if(Marrakech.isPlacementValid(currentGame.GameToString(), possiblePositions.get(i).RugToString())) {
                validRugs.add(possiblePositions.get(i));
            }
        }

        return validRugs;
    }

    /**
     * Get a valid move at random
     * @param possiblePositions A list that contains all valid movements
     * @return A string in the list
     *
     * @author Yaohui Hou
     */
    public Rug dragRandomly(List<Rug> possiblePositions){
        int max = possiblePositions.size() - 1;
        int min = 0;
        int randomIndex = (int)Math.floor(Math.random() * (max - min + 1) + min);

        return possiblePositions.get(randomIndex);
    }

    /**
     * method to decide whether assam is rotated randomly
     * @return random number
     *
     * @author u7754637 Pei Ling Lam
     */
    public int rotateAssamRandomly() {
        int max = 2;
        int min = 0;
        int rand = (int)Math.floor(Math.random() * (max - min + 1) + min);

        return rand;
    }

}

