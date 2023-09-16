package comp1110.ass2;

import comp1110.ass2.gui.Viewer;

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
        char colour = rug.charAt(0);
        int position1 = Character.getNumericValue(rug.charAt(3));
        int position2 = Character.getNumericValue(rug.charAt(4));
        int position3 = Character.getNumericValue(rug.charAt(5));
        int position4 = Character.getNumericValue(rug.charAt(6));
        if(rug.length() != 7){
            return false;
        }else if(colour != 'r' && colour != 'c' && colour != 'p' && colour != 'y'){
            return false;
        }else return position1 <= 6 && position2 <= 6 && position3 <= 6 && position4 <= 6;
        // FIXME: Task 4
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
        Game game = new Game();
        game = game.StringToGame(currentGame);

        // Game is finished if all players are out of rugs
        boolean isFinished = true;

        for(int i = 0; i < game.getPlayersList().size(); i++) {
            if(game.getPlayersList().get(i).getNumRug() > 0 && game.getPlayersList().get(i).isInGame()) {
                isFinished = false;
                break; // Exit the loop once we find a player who is still in game with rugs
            }
        }

        return isFinished;
    }

    // FIXME: Task 8

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
        if (gameState.length() < 7 || rug.length() != 7) {
            return false; // Invalid input lengths
        }

        // Extract information from the rug string
        String colourAndID = rug.substring(0, 3);
        String startCoords = rug.substring(3, 5);
        String endCoords = rug.substring(5, 7);

        // Check if the rug is already in the gameState
        if (containsRug(gameState, colourAndID)) {
            return false;
        }

        // Check if the rug does not completely cover another rug.
        for (int i = 0; i < gameState.length(); i += 7) {
            if (i + 7 <= gameState.length()) {
                String existingRugID = gameState.substring(i, i + 3);
                if (isRugID(existingRugID)) { // Ensure this segment is a rug
                    String existingRugStart = gameState.substring(i + 3, i + 5);
                    String existingRugEnd = gameState.substring(i + 5, i + 7);
                    if (completelyCovers(rug, existingRugStart, existingRugEnd)) {
                        return false; // The new rug completely covers an existing rug
                    }
                }
            }
        }

        // Check adjacency with Assam
        int assamPositionIndex = gameState.indexOf('A');
        if (assamPositionIndex != -1 && assamPositionIndex + 3 <= gameState.length()) {
            String assamPosition = gameState.substring(assamPositionIndex + 1, assamPositionIndex + 3);

            // Ensure rug does not overlap with Assam's position
            if (coordinatesOverlap(startCoords, assamPosition) || coordinatesOverlap(endCoords, assamPosition)) {
                return false; // The rug overlaps with Assam's position
            }

            if (!(coordinatesAdjacent(startCoords, assamPosition) || coordinatesAdjacent(endCoords, assamPosition))) {
                return false; // The rug is not adjacent to Assam
            }
        } else {
            return false; // Assam's position is missing or invalid
        }

        // If all checks pass, return true
        return true;
    }
    private static boolean coordinatesAdjacent(String coords1, String coords2) {
        if (coords1.length() != 2 || coords2.length() != 2) {
            return false; // Invalid input
        }

        int x1 = Character.getNumericValue(coords1.charAt(0));
        int y1 = Character.getNumericValue(coords1.charAt(1));
        int x2 = Character.getNumericValue(coords2.charAt(0));
        int y2 = Character.getNumericValue(coords2.charAt(1));

        return (x1 == x2 && Math.abs(y1 - y2) == 1) || // Horizontal adjacency
                (y1 == y2 && Math.abs(x1 - x2) == 1);   // Vertical adjacency
    }

    private static boolean containsRug(String gameState, String rugID) {
        for (int i = 0; i <= gameState.length() - 7; i += 7) {
            String existingRugID = gameState.substring(i, i + 3);
            if (existingRugID.equals(rugID)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isRugID(String str) {
        return str.matches("^[a-zA-Z]{3}$");  // Ensure all three characters are alphabets
    }


    private static boolean completelyCovers(String newRug, String existingRugStart, String existingRugEnd) {
        String newRugStart = newRug.substring(3, 5);
        String newRugEnd = newRug.substring(5, 7);

        // For each coordinate string, check if it's numeric before parsing
        if (!isNumeric(newRugStart.substring(0,1)) || !isNumeric(newRugStart.substring(1,2)) ||
                !isNumeric(newRugEnd.substring(0,1)) || !isNumeric(newRugEnd.substring(1,2)) ||
                !isNumeric(existingRugStart.substring(0,1)) || !isNumeric(existingRugStart.substring(1,2)) ||
                !isNumeric(existingRugEnd.substring(0,1)) || !isNumeric(existingRugEnd.substring(1,2))) {
            // One or more of the coordinate strings is not numeric, so return false or handle accordingly
            return false;
        }

        // Convert the coordinate strings to integer values for comparison
        int newRugStartX = Integer.parseInt(newRugStart.substring(0,1));
        int newRugStartY = Integer.parseInt(newRugStart.substring(1,2));
        int newRugEndX = Integer.parseInt(newRugEnd.substring(0,1));
        int newRugEndY = Integer.parseInt(newRugEnd.substring(1,2));

        int existingRugStartX = Integer.parseInt(existingRugStart.substring(0,1));
        int existingRugStartY = Integer.parseInt(existingRugStart.substring(1,2));
        int existingRugEndX = Integer.parseInt(existingRugEnd.substring(0,1));
        int existingRugEndY = Integer.parseInt(existingRugEnd.substring(1,2));

        // Check if the new rug completely covers the existing rug
        return newRugStartX <= existingRugStartX && newRugStartY <= existingRugStartY &&
                newRugEndX >= existingRugEndX && newRugEndY >= existingRugEndY;
    }


    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    private static boolean coordinatesOverlap(String coords1, String coords2) {
        return coords1.equals(coords2);
    }

    // FIXME: Task 10


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
        Game game = new Game();
        game = game.StringToGame(gameState);

        if(!isGameOver(gameState)) {
            return 'n';
        } else {
            //get number of visible rugs of each color
            //TODO: create a method to count visible rugs on board
            int[] p_rugScore = new int[game.getPlayersList().size()];
            for(int i = 0; i < game.getPlayersList().size(); i++) {
                for (RugTile rugTile: game.getBoard().getBoardPosition()) {
                    if(rugTile!= null && rugTile.getColor() == game.getPlayersList().get(i).getColor()) {
                        p_rugScore[i] += 1;
                    }
                    game.getPlayersList().get(i).getScores().setRugScore(p_rugScore[i]);
                }
            }

            Players winner = new Players();
            int max_score = game.getPlayersList().get(0).getScores().getTotalScore();
            winner = game.getPlayersList().get(0);
            // find max score
            for(int i = 1; i < game.getPlayersList().size(); i++) {
                if(game.getPlayersList().get(i).getScores().getTotalScore() > max_score) {
                    max_score = game.getPlayersList().get(i).getScores().getTotalScore();
                    winner = game.getPlayersList().get(i);
                }
            }

            // check whether there are two players with same highest score
            for (Players player: game.getPlayersList()) {
                if(player.getColor() != winner.getColor() && player.getScores().getTotalScore() == max_score) {
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
        return assam.AssamToString();
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
        Game game = new Game();
        Rug rug1 = new Rug();
        List<RugTile> rugTile = new ArrayList<>();

        game = game.StringToGame(currentGame);
        rug1 = rug1.StringToRug(rug);
        for (int i = 0; i < rug1.getRelativePositions().length; i++) {
            RugTile tile = new RugTile(rug1.getColor(), rug1.getRelativePositions()[i], rug1.getRugID());
            rugTile.add(tile);
        }

        if(isPlacementValid(currentGame, rug) && isRugValid(currentGame, rug)) {
            for (RugTile tile: rugTile) {
                if(tile != null) {
                    game.getBoard().updateRugTile(tile);
                }
            }
            for (Players player: game.getPlayersList()) {
                if(player.getColor() == rug1.getColor()) {
                    player.updateNumRug();
                }
            }
            return game.GameToString();
        }
        // FIXME: Task 14
        return currentGame;
    }

}
