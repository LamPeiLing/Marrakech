package comp1110.ass2;

public class Assam {
    private IntPair absolutePosition; // current position of Assam on the board
    private Direction currentDirection; // current direction of Assam is facing


    /**
     * Constructor that initialize the position of Assam when a new game starts
     * @param position the position of the middle of the board
     */
    public Assam(IntPair position) {
        this.absolutePosition = position;
    }

    /**
     * Constructor that does not do anything
     */
    public Assam() {}

    public void setAbsolutePosition(IntPair position) {
        this.absolutePosition = position;
    }

    /**
     * get the current position of Assam
     * @return the absolute position on the board
     */
    public IntPair getAbsolutePosition() {
        return this.absolutePosition;
    }

    public void setCurrentDirection(Direction direction) {
        this.currentDirection = direction;
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

    public String AssamToString(Assam assam) {
        String stringAssam = "A";
        stringAssam += assam.getAbsolutePosition().IntPairToString(getAbsolutePosition());
        stringAssam += assam.getCurrentDirection().value;
        return stringAssam;
    }

    public Assam StringToAssam(String stringAssam) {
        Assam assam = new Assam();

        IntPair position = new IntPair();
        assam.setAbsolutePosition(position.StringToIntPair(stringAssam.substring(1, 3)));

        // set direction
        if(stringAssam.charAt(3) == 'N') {
            assam.setCurrentDirection(Direction.NORTH);
        } else if(stringAssam.charAt(3) == 'S') {
            assam.setCurrentDirection(Direction.SOUTH);
        } else if(stringAssam.charAt(3) == 'W') {
            assam.setCurrentDirection(Direction.WEST);
        } else {
            assam.setCurrentDirection(Direction.EAST);
        }
        return assam;
    }
}
