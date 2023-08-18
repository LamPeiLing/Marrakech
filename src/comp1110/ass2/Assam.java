package comp1110.ass2;

public class Assam {
    private IntPair absolutePosition; // current position of Assam on the board
    private Direction currentDirection; // current direction of Assam is facing


    /**
     * Constructor: initialize assam to the middle of board and facing south
     */
    public Assam() {

    }

    /**
     * get the current position of Assam
     * @return the absolute position on the board
     */
    public IntPair getAbsolutePosition() {
        return this.absolutePosition;
    }

    /**
     * get the direction where Assam is facing currently
     * @return the current direction
     */
    public Direction getCurrentDirection() {
        return this.currentDirection;
    }

    /**
     * check if Assam is already at the border of the board
     * use absolutePosition to check
     * @return true if already at the border of the board, false otherwise
     */
    public boolean isBorder() {
        return false;
    }


    /**
     * update Assam's direction when players are rotating Assam
     * @param transform new transformation from players
     */
    public void updateDirection(Transform transform) {

    }

    /**
     * update Assam's absolute position after moving Assam
     * @param newPosition new absolute position
     */
    public void updatePosition(IntPair newPosition) {

    }
}
