package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;

public class Marrakech {

    /**
     * Determine whether a rug String is valid.
     * For this method, you need to determine whether the rug String is valid, but do not need to determine whether it
     * can be placed on the board (you will determine that in Task 10 ). A rug is valid if and only if all the following
     * conditions apply:
     *  - The String is 7 characters long
     *  - The first character in the String corresponds to the colour character of a player present in the game
     *  - The next two characters represent a 2-digit ID number
     *  - The next 4 characters represent coordinates that are on the board
     *  - The combination of that ID number and colour is unique
     * To clarify this last point, if a rug has the same ID as a rug on the board, but a different colour to that rug,
     * then it may still be valid. Obviously multiple rugs are allowed to have the same colour as well so long as they
     * do not share an ID. So, if we already have the rug c013343 on the board, then we can have the following rugs
     *  - c023343 (Shares the colour but not the ID)
     *  - y013343 (Shares the ID but not the colour)
     * But you cannot have c014445, because this has the same colour and ID as a rug on the board already.
     * @param gameString A String representing the current state of the game as per the README
     * @param rug A String representing the rug you are checking
     * @return true if the rug is valid, and false otherwise.
     */
    public static boolean isRugValid(String gameString, String rug) {
        String colourAndID = rug.substring(0, 3);
        if(rug.length() != 7){
            return false;
        }else if(gameString.contains(colourAndID)){
            return false;
        }else{
            return true;
        }
        // FIXME: Task 4
        //return true;
    }


    /**
     * Roll the special Marrakech die and return the result.
     * Note that the die in Marrakech is not a regular 6-sided die, since there
     * are no faces that show 5 or 6, and instead 2 faces that show 2 and 3. That
     * is, of the 6 faces
     *  - One shows 1
     *  - Two show 2
     *  - Two show 3
     *  - One shows 4
     * As such, in order to get full marks for this task, you will need to implement
     * a die where the distribution of results from 1 to 4 is not even, with a 2 or 3
     * being twice as likely to be returned as a 1 or 4.
     * @return The result of the roll of the die meeting the criteria above
     */
    public static int rollDie() {
        int[] die = new int[]{1, 2, 2, 3, 3, 4};
        int index = (int)(Math.random() * die.length);
        return die[index];
        // FIXME: Task 6
        //return -1;
    }

    /**
     * Determine whether a game of Marrakech is over
     * Recall from the README that a game of Marrakech is over if a Player is about to enter the rotation phase of their
     * turn, but no longer has any rugs. Note that we do not encode in the game state String whose turn it is, so you
     * will have to think about how to use the information we do encode to determine whether a game is over or not.
     * @param currentGame A String representation of the current state of the game.
     * @return true if the game is over, or false otherwise.
     */
    /**
     * Determine whether a game of Marrakech is over.
     * @param currentGame A String representation of the current state of the game.
     * @return true if the game is over, or false otherwise.
     */
    public static boolean isGameOver(String currentGame) {

        // Possible player colors.
        char[] playerColors = {'c', 'r', 'y', 'b', 'g', 'p'};
        int rugsPerPlayer = 12;  // This is just an example. You need the actual number of rugs each player starts with.

        for (char color : playerColors) {
            int count = 0;
            for (int i = 0; i < currentGame.length(); i++) {
                if (currentGame.charAt(i) == color) {
                    count++;
                }
            }

            // If a player has placed all their rugs, the game is over.
            if (count == rugsPerPlayer) {
                return true;
            }
        }
        return false;  // No player has placed all their rugs yet.
        // FIXME: Task 8
    }

    /**
     * Implement Assam's rotation.
     * Recall that Assam may only be rotated left or right, or left alone -- he cannot be rotated a full 180 degrees.
     * For example, if he is currently facing North (towards the top of the board), then he could be rotated to face
     * East or West, but not South. Assam can also only be rotated in 90 degree increments.
     * If the requested rotation is illegal, you should return Assam's current state unchanged.
     * @param currentAssam A String representing Assam's current state
     * @param rotation The requested rotation, in degrees. This degree reading is relative to the direction Assam
     *                 is currently facing, so a value of 0 for this argument will keep Assam facing in his
     *                 current orientation, 90 would be turning him to the right, etc.
     * @return A String representing Assam's state after the rotation, or the input currentAssam if the requested
     * rotation is illegal.
     */
    public static String rotateAssam(String currentAssam, int rotation) {
        // Assuming the last character in currentAssam represents Assam's facing direction
        char direction = currentAssam.charAt(currentAssam.length() - 1);

        if (rotation == 0) { // If no rotation
            return currentAssam;
        } else if (rotation == 90 || rotation == -270) { // Turn right
            switch (direction) {
                case 'N':
                    direction = 'E';
                    break;
                case 'E':
                    direction = 'S';
                    break;
                case 'S':
                    direction = 'W';
                    break;
                case 'W':
                    direction = 'N';
                    break;
                default:
                    return currentAssam;  // Invalid direction in currentAssam, return unchanged
            }
        } else if (rotation == -90 || rotation == 270) { // Turn left
            switch (direction) {
                case 'N':
                    direction = 'W';
                    break;
                case 'E':
                    direction = 'N';
                    break;
                case 'S':
                    direction = 'E';
                    break;
                case 'W':
                    direction = 'S';
                    break;
                default:
                    return currentAssam;  // Invalid direction in currentAssam, return unchanged
            }
        } else {
            return currentAssam;  // Invalid rotation, return unchanged
        }

        // Return the updated Assam state with the new direction
        return currentAssam.substring(0, currentAssam.length() - 1) + direction;
        // FIXME: Task 9
    }


    /**
     * Determine whether a potential new placement is valid (i.e that it describes a legal way to place a rug).
     * There are a number of rules which apply to potential new placements, which are detailed in the README but to
     * reiterate here:
     *   1. A new rug must have one edge adjacent to Assam (not counting diagonals)
     *   2. A new rug must not completely cover another rug. It is legal to partially cover an already placed rug, but
     *      the new rug must not cover the entirety of another rug that's already on the board.
     * @param gameState A game string representing the current state of the game
     * @param rug A rug string representing the candidate rug which you must check the validity of.
     * @return true if the placement is valid, and false otherwise.
     */
    public static boolean isPlacementValid(String gameState, String rug) {
        // Extract information from the rug string
        String colourAndID = rug.substring(0, 3);
        String startCoords = rug.substring(3, 5);
        String endCoords = rug.substring(5, 7);

        // Check if the rug is already in the gameState
        if (gameState.contains(colourAndID)) {
            return false;
        }

        // Check if the rug does not completely cover another rug.
        for (int i = 0; i < gameState.length(); i += 7) {
            String existingRugStart = gameState.substring(i+3, i+5);
            String existingRugEnd = gameState.substring(i+5, i+7);

            if (startCoords.equals(existingRugStart) && endCoords.equals(existingRugEnd)) {
                return false; // The new rug completely covers an existing rug
            }
        }

        // Here you would add logic to check if the rug is adjacent to Assam (not counting diagonals).

        // If all checks pass, return true
        return true;
        // FIXME: Task 10
    }


    /**
     * Determine the amount of payment required should another player land on a square.
     * For this method, you may assume that Assam has just landed on the square he is currently placed on, and that
     * the player who last moved Assam is not the player who owns the rug landed on (if there is a rug on his current
     * square). Recall that the payment owed to the owner of the rug is equal to the number of connected squares showing
     * on the board that are of that colour. Similarly to the placement rules, two squares are only connected if they
     * share an entire edge -- diagonals do not count.
     * @param gameString A String representation of the current state of the game.
     * @return The amount of payment due, as an integer.
     */
    public static int getPaymentAmount(String gameString) {
        // Assuming Assam's position is represented by the last 7 characters of the game string.
        String assamPosition = gameString.substring(gameString.length() - 7);
        String rugColor = assamPosition.substring(0, 2); // Assuming the color is represented by the first 2 characters.

        // A boolean array to mark which rugs have been visited.
        boolean[] visited = new boolean[gameString.length() / 7];

        int count = 0;

        // For every rug in the game string
        for (int i = 0; i < gameString.length(); i += 7) {
            if (!visited[i/7]) {
                String currentRug = gameString.substring(i, i + 7);
                if (currentRug.startsWith(rugColor)) {
                    count += dfs(gameString, visited, i);
                }
            }
        }

        return count;
    }

    // Depth First Search to count connected rugs of the same color.
    private static int dfs(String gameString, boolean[] visited, int index) {
        if (index < 0 || index >= gameString.length() || visited[index/7]) return 0;

        String currentRug = gameString.substring(index, index + 7);
        String startCoords = currentRug.substring(3, 5);
        String endCoords = currentRug.substring(5, 7);
        // Logic to find neighboring coordinates based on the direction of the rug goes here...

        int count = 1; // Count the current rug
        visited[index/7] = true;

/*        // Check all neighboring coordinates
        for (String neighbor : neighbors) {
            int nextIndex = gameString.indexOf(neighbor);
            if (nextIndex != -1) {
                count += dfs(gameString, visited, nextIndex);
            }
        }*/
        // FIXME: Task 11
        return count;
    }


    /**
     * Determine the winner of a game of Marrakech.
     * For this task, you will be provided with a game state string and have to return a char representing the colour
     * of the winner of the game. So for example if the cyan player is the winner, then you return 'c', if the red
     * player is the winner return 'r', etc...
     * If the game is not yet over, then you should return 'n'.
     * If the game is over, but is a tie, then you should return 't'.
     * Recall that a player's total score is the sum of their number of dirhams and the number of squares showing on the
     * board that are of their colour, and that a player who is out of the game cannot win. If multiple players have the
     * same total score, the player with the largest number of dirhams wins. If multiple players have the same total
     * score and number of dirhams, then the game is a tie.
     * @param gameState A String representation of the current state of the game
     * @return A char representing the winner of the game as described above.
     */
    public static char getWinner(String gameState) {
        // get string representation
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
                System.out.println("assam: "+string_assam);
            }
        } else {
            string_assam = gameState.substring(16, 20);
            System.out.println("assam: "+string_assam);
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

        Assam assam = new Assam();
        assam.StringToAssam(string_assam);

        boolean flag = true;

        for(int i = 0; i < playersList.size(); i++) {
            if(playersList.get(i).getNumRug() != 0) {
                flag = false;
            }
        }

        if(!flag && gameState.contains("n00")) {
            return 'n';
        } else {
            //get number of visible rugs of each color
            //TODO: create a method to count visible rugs on board
            int[] p_rugScore = new int[playersList.size()];
            for(int i = 0; i < playersList.size(); i++) {
                for (int j = 0; j < boardRug.length(); j += 3) {
                    if (boardRug.charAt(j) == playersList.get(i).getColor().value) {
                        p_rugScore[i] += 1;
                    }
                }
            }

            Players winner = new Players();
            int max_score = playersList.get(0).getNumDirham() + p_rugScore[0];
            winner = p1;
            // find max score
            for(int i = 1; i < playersList.size(); i++) {
                if(playersList.get(i).getNumDirham() + p_rugScore[i] > max_score) {
                    max_score = playersList.get(i).getNumDirham() + p_rugScore[i];
                    winner = playersList.get(i);
                } else if(playersList.get(i).getNumDirham() + p_rugScore[i] == max_score) {
                    return 't';
                }
            }
            return winner.getColor().value;

        }
        // FIXME: Task 12
    }

    /**
     * Implement Assam's movement.
     * Assam moves a number of squares equal to the die result, provided to you by the argument dieResult. Assam moves
     * in the direction he is currently facing. If part of Assam's movement results in him leaving the board, he moves
     * according to the tracks diagrammed in the assignment README, which should be studied carefully before attempting
     * this task. For this task, you are not required to do any checking that the die result is sensible, nor whether
     * the current Assam string is sensible either -- you may assume that both of these are valid.
     * @param currentAssam A string representation of Assam's current state.
     * @param dieResult The result of the die, which determines the number of squares Assam will move.
     * @return A String representing Assam's state after the movement.
     */
    public static String moveAssam(String currentAssam, int dieResult){
        Assam assam = new Assam();
        assam = assam.StringToAssam(currentAssam);

        for (int i = 0; i < dieResult; i++) {
            if(!assam.isMovementSafe()) {
                // if the movement requires to turn
                switch (assam.getCurrentDirection()) {
                    case NORTH:
                        if(assam.getAbsolutePosition().getX() % 2 == 0 && (assam.getAbsolutePosition().getX() != 6)) {
                            assam.setAbsolutePosition(new IntPair(assam.getAbsolutePosition().getX() + 1, assam.getAbsolutePosition().getY()));
                            assam.setCurrentDirection(Direction.SOUTH);
                        } else if(assam.getAbsolutePosition().getX() % 2 != 0) {
                            assam.setAbsolutePosition(new IntPair(assam.getAbsolutePosition().getX() - 1, assam.getAbsolutePosition().getY()));
                            assam.setCurrentDirection(Direction.SOUTH);
                        } else {
                            assam.setCurrentDirection(Direction.WEST);
                        }
                        break;
                    case SOUTH:
                        if(assam.getAbsolutePosition().getX() % 2 == 0 && (assam.getAbsolutePosition().getX() != 0)) {
                            assam.setAbsolutePosition(new IntPair(assam.getAbsolutePosition().getX() - 1, assam.getAbsolutePosition().getY()));
                            assam.setCurrentDirection(Direction.NORTH);
                        } else if(assam.getAbsolutePosition().getX() % 2 != 0) {
                            assam.setAbsolutePosition(new IntPair(assam.getAbsolutePosition().getX() + 1, assam.getAbsolutePosition().getY()));
                            assam.setCurrentDirection(Direction.NORTH);
                        } else {
                            assam.setCurrentDirection(Direction.EAST);
                        }
                        break;
                    case EAST:
                        if(assam.getAbsolutePosition().getY() % 2 == 0 && (assam.getAbsolutePosition().getY() != 0)) {
                            assam.setAbsolutePosition(new IntPair(assam.getAbsolutePosition().getX(), assam.getAbsolutePosition().getY() - 1));
                            assam.setCurrentDirection(Direction.WEST);
                        } else if(assam.getAbsolutePosition().getY() % 2 != 0) {
                            assam.setAbsolutePosition(new IntPair(assam.getAbsolutePosition().getX(), assam.getAbsolutePosition().getY() + 1));
                            assam.setCurrentDirection(Direction.WEST);
                        } else {
                            assam.setCurrentDirection(Direction.SOUTH);
                        }
                        break;
                    case WEST:
                        if(assam.getAbsolutePosition().getY() % 2 == 0 && (assam.getAbsolutePosition().getY() != 6)) {
                            assam.setAbsolutePosition(new IntPair(assam.getAbsolutePosition().getX(), assam.getAbsolutePosition().getY() + 1));
                            assam.setCurrentDirection(Direction.EAST);
                        } else if(assam.getAbsolutePosition().getY() % 2 != 0) {
                            assam.setAbsolutePosition(new IntPair(assam.getAbsolutePosition().getX(), assam.getAbsolutePosition().getY() - 1));
                            assam.setCurrentDirection(Direction.EAST);
                        } else {
                            assam.setCurrentDirection(Direction.NORTH);
                        }
                        break;
                }
            } else {
                switch (assam.getCurrentDirection()) {
                    case NORTH:
                        assam.setAbsolutePosition(new IntPair(assam.getAbsolutePosition().getX(), assam.getAbsolutePosition().getY() - 1));
                        break;
                    case SOUTH:
                        assam.setAbsolutePosition(new IntPair(assam.getAbsolutePosition().getX(), assam.getAbsolutePosition().getY() + 1));
                        break;
                    case EAST:
                        assam.setAbsolutePosition(new IntPair(assam.getAbsolutePosition().getX() + 1, assam.getAbsolutePosition().getY()));
                        break;
                    case WEST:
                        assam.setAbsolutePosition(new IntPair(assam.getAbsolutePosition().getX() - 1, assam.getAbsolutePosition().getY()));
                        break;
                }
            }
        }

        // FIXME: Task 13
        //update position to Assam class at the end
        return assam.AssamToString(assam);
    }

    /**
     * Place a rug on the board
     * This method can be assumed to be called after Assam has been rotated and moved, i.e in the placement phase of
     * a turn. A rug may only be placed if it meets the conditions listed in the isPlacementValid task. If the rug
     * placement is valid, then you should return a new game string representing the board after the placement has
     * been completed. If the placement is invalid, then you should return the existing game unchanged.
     * @param currentGame A String representation of the current state of the game.
     * @param rug A String representation of the rug that is to be placed.
     * @return A new game string representing the game following the successful placement of this rug if it is valid,
     * or the input currentGame unchanged otherwise.
     */
    public static String makePlacement(String currentGame, String rug) {
        // FIXME: Task 14
        return "";
    }

}
