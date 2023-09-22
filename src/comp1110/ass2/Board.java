package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<RugTile> boardPosition = new ArrayList<>(); // a list of rug on each position on board
    private final int BOARD_HEIGHT = 7;
    private final int BOARD_WIDTH = 7;

    /**
     * Constructor
     * does nothing at this moment
     */
    public Board(){}

    public void setBoardPosition(List<RugTile> boardPosition) {
        this.boardPosition = boardPosition;
    }

    public List<RugTile> getBoardPosition() {
        return boardPosition;
    }

    /**
     * update the rug tile at a specific position
     * @param rugTile new rug tile that is going to replace the old one
     */
    public void updateRugTile(RugTile rugTile) {

        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if(i == rugTile.getAbsolutePosition().getX() && j == rugTile.getAbsolutePosition().getY()) {
                    getBoardPosition().get((i * 7) + j).setAbsolutePosition(rugTile.getAbsolutePosition());
                    getBoardPosition().get((i * 7) + j).setId(rugTile.getId());
                    getBoardPosition().get((i * 7) + j).setColor(rugTile.getColor());
                }
            }

        }
    }

    /**
     * method that find the rug tile given a specific position
     * @param position the position that we need for the rug tile
     * @return target rug tile
     */
    public RugTile getRugTileOnPosition (IntPair position) {
        for (RugTile rugTile: boardPosition) {
            if(rugTile.getAbsolutePosition() != null) {
                if (rugTile.getAbsolutePosition().getX() == position.getX() && rugTile.getAbsolutePosition().getY() == position.getY())
                    return rugTile;
            }
        }
        return null;
    }

    /**
     * Convert Board type to its string representation
     * @return string representation of Board class
     */
    public String BoardToString() {
        String boardString = "B";

        for (RugTile rugTile: this.boardPosition) {
            boardString += rugTile.RugTileToString();
        }

        return boardString;
    }


    /**
     * Convert String representation to Board class
     * @param boardString string representation of board
     * @return Board class data
     */
    public Board StringToBoard(String boardString) {
        List<RugTile> rugPositions = new ArrayList<>();
        int k = 0;

        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                // get substring of rug
                String subRug = boardString.substring(k, k+3);

                if(subRug.charAt(0) == 'n'){
                    rugPositions.add(new RugTile());
                } else {
                    RugTile rugTile = new RugTile();
                    rugTile = rugTile.StringToRugTile(subRug, i, j);
                    rugPositions.add(rugTile);
                }
                k+=3;
            }
        }

        Board board = new Board();
        board.setBoardPosition(rugPositions);

        return board;
    }
}
