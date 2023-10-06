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
     *
     * @author u7754892 Yaohui Hou
     * @author u7754637 Pei Ling Lam (supporting author)
     */
    public static boolean isRugValid(String gameString, String rug) {
        Game game = new Game();
        game = game.StringToGame(gameString);


        Rug rug1 = new Rug();
        rug1 = rug1.StringToRug(rug);

        if(rug.length() != 7) {
            return false;
        }

        // check whether the colour exists
        if(rug1.getColor() == null) return false;

        // check whether rug has fully covered another rug
        for (int i = 0; i < game.getBoard().getBoardPosition().size(); i++) {
            if(game.getBoard().getBoardPosition().get(i) != null) {
                if (rug1.getRugID() == game.getBoard().getBoardPosition().get(i).getId() && rug1.getColor() == game.getBoard().getBoardPosition().get(i).getColor()) {
                    return false;
                }
            }
        }

        // check whether the rug has gone off the board
        for (int i = 0; i < rug1.getRelativePositions().length; i++) {
            if(rug1.getRelativePositions()[i].getX() > Viewer.BOARD_WIDTH - 1 || rug1.getRelativePositions()[i].getY() > Viewer.BOARD_HEIGHT - 1) {
                return false;
            }
        }

        return true;
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
     *
     * @author u7754892 Yaohui Hou
     */
    public static int rollDie() {
        int[] die = new int[]{1, 2, 2, 3, 3, 4};
        int index = (int)(Math.random() * die.length);
        return die[index];
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
     *
     * @author u7770276 ZeXin Tang
     * @author u7754637 Pei Ling Lam (supporting author)
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
     *
     * @author u7770276 ZeXin Tang
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
     *
     * @author u7770276 ZeXin Tang
     * @author u7754637 Pei Ling Lam (supporting author)
     */

    public static boolean isPlacementValid(String gameState, String rug) {

        Game game = new Game().StringToGame(gameState);

        if (rug.length() != 7) return false;

        Rug rug1 = new Rug().StringToRug(rug);

        IntPair rugTile1 = rug1.getRelativePositions()[0];
        IntPair rugTile2 = rug1.getRelativePositions()[1];

        List<IntPair> assamAdjacent = game.getAssam().adjacentEdge();
        boolean flag = false;

        // check whether it is placed with adjacent to Assam
        // check for first segment of the rug
        for (int i = 0; i < assamAdjacent.size(); i++) {
            if (rugTile1.getX() == assamAdjacent.get(i).getX() && rugTile1.getY() == assamAdjacent.get(i).getY()) {
                flag = true;
            }
        }

        // if first segment does not place at the adjacent of assam, check the second segment
        if (!flag) {
            int i = 0;
            for (i = 0; i < assamAdjacent.size(); i++) {
                if (rugTile2.getX() == assamAdjacent.get(i).getX() && rugTile2.getY() == assamAdjacent.get(i).getY()) {
                    break;
                }
            }

            if (i == assamAdjacent.size()) {
                return false;
            }
        }

        // check if the rug fully cover another rug
        RugTile boardTile1 = game.getBoard().getRugTileOnPosition(rugTile1);
        RugTile boardTile2 = game.getBoard().getRugTileOnPosition(rugTile2);

        if (boardTile1 != null && boardTile2 != null) {
            if (boardTile1.getColor() == boardTile2.getColor() && boardTile1.getId() == boardTile2.getId())
                return false;
        }

        // check if the rug place under assam
        for (int i = 0; i < rug1.getRelativePositions().length; i++) {
            if (rug1.getRelativePositions()[i].getX() == game.getAssam().getAbsolutePosition().getX() && rug1.getRelativePositions()[i].getY() == game.getAssam().getAbsolutePosition().getY())
                return false;
        }

        return true;
    }

    /**
     * Determine the amount of payment required should another player land on a square.
     * For this method, you may assume that Assam has just landed on the square he is currently placed on, and that
     * the player who last moved Assam is not the player who owns the rug landed on (if there is a rug on his current
     * square). Recall that the payment owed to the owner of the rug is equal to the number of connected squares showing
     * on the board that are of that colour. Similarly to the placement rules, two squares are only connected if they
     * share an entire edge -- diagonals do not count.
     * @param  //String representation of the current state of the game.
     * @return The amount of payment due, as an integer.
     *
     * @author u7770276 ZeXin Tang
     */
    public static int getPaymentAmount(String gameString) {
        // Create a new Game object and parse the gameString
        Game game = new Game().StringToGame(gameString);

        // Now, you can use the game object to calculate the payment amount
        Assam assam = game.getAssam();
        List<RugTile> board = game.getBoard().getBoardPosition();
        int boardSize = 7; // 固定的棋盘大小

        Color rugColor = board.get(assam.getAbsolutePosition().getX() * boardSize + assam.getAbsolutePosition().getY()).getColor();

        int payment;

        // Create a boolean array to keep track of visited squares
        boolean[][] visited = new boolean[boardSize][boardSize];


        // Perform a depth-first search to find connected squares of the same color
        payment = dfs(board, rugColor, assam.getAbsolutePosition().getX(), assam.getAbsolutePosition().getY(), visited, boardSize);

        return payment;
    }

    private static int dfs(List<RugTile> board, Color color, int x, int y, boolean[][] visited, int boardSize) {
        // Base case: If the square is out of bounds or has a different color, stop searching.
        if (x < 0 || x >= boardSize || y < 0 || y >= boardSize || visited[x][y] || board.get(x * boardSize + y).getColor() != color) {
            return 0;
        }

        visited[x][y] = true;
        int connectedSquares = 1; // Count the current square

        // Define four possible directions (up, down, left, right)
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            connectedSquares += dfs(board, color, newX, newY, visited, boardSize);
        }

        return connectedSquares;
    }



    /**
     * Depth-first search to count connected squares of the same color.
     * @param board The game board represented as a list of RugTiles.
     * @param color The color of the rug to count.
     * @param x The x-coordinate of the current square.
     * @param y The y-coordinate of the current square.
     * @param visited A boolean array to keep track of visited squares.
     * @return The number of connected squares of the same color.
     *
     * @author u7770276 ZeXin Tang
     */
    private static int dfsHelper(List<RugTile> board, Color color, int x, int y, boolean[][] visited) {
        int boardSize = 7;

        if (x < 0 || x >= boardSize || y < 0 || y >= boardSize || visited[x][y] || board.get(x * boardSize + y).getColor() != color) {
            return 0;
        }

        visited[x][y] = true;
        int connectedSquares = 1;

        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            connectedSquares += dfsHelper(board, color, newX, newY, visited);
        }

        return connectedSquares;
    }


    // 解析Assam的颜色
    private static Color parseAssamColor(String gameString) {
        // 根据游戏状态字符串的格式解析Assam的颜色并返回
        // 请根据实际格式实现
        return Color.RED; // 示例
    }

    // 解析Assam的位置
    private static IntPair parseAssamPosition(String gameString) {
        // 根据游戏状态字符串的格式解析Assam的位置并返回
        // 请根据实际格式实现
        return new IntPair(3, 3); // 示例
    }

    // 深度优先搜索计算连接区域的大小
    private static int dfshelper(List<RugTile> gameString, Color visited, int x, int y, boolean[][] targetColor, int boardSize) {
        // 请实现深度优先搜索的逻辑
        return 0; // 示例
    }

    // 判断坐标是否在游戏板内
    private static boolean isValidPosition(int x, int y) {
        // 根据实际游戏板的大小判断坐标是否合法
        return x >= 0 && x < 7 && y >= 0 && y < 7;
    }

    // 判断指定位置的方格是否与目标颜色相同
    private static boolean isSameColor(String gameString, int x, int y, Color targetColor) {
        // 根据游戏状态字符串的格式判断指定位置的方格颜色是否与目标颜色相同
        // 请根据实际格式实现
        return true; // 示例
    }

        // FIXME: Task 11



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
     *
     * @author u7754637 Pei Ling Lam
     */
    public static char getWinner(String gameState) {
        Game game = new Game();
        game = game.StringToGame(gameState);

        // check whether the game is over
        if(!isGameOver(gameState)) {
            return 'n'; // when the game is still ongoing
        } else { // when the game has over
            //get number of visible rugs of each color
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
            // if there are two players with same highest score, the one has more dirhams wins the game
            // if there are 2 players with same highest score and same number of dirhams, then it's a tie
            for (Players player: game.getPlayersList()) {
                if(player.getColor() != winner.getColor() && player.getScores().getTotalScore() == max_score) {
                    if(player.getNumDirham() > winner.getNumDirham()) {
                        winner = player;
                    } else {
                        return 't';
                    }
                }
            }
            return winner.getColor().value;

        }
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
     *
     * @author u7754637 Pei Ling Lam
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
     *
     * @author u7754637 Pei Ling Lam
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

        return currentGame;
    }

}
