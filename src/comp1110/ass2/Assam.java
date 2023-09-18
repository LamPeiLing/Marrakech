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
        if(absolutePosition.getX() == 0 || absolutePosition.getX() == 6 || absolutePosition.getY() == 0 || absolutePosition.getY() == 6) {
            return true;
        }
        return false;
    }

    public boolean isMovementSafe() {
        switch (this.currentDirection) {
            case NORTH:
                if(this.absolutePosition.getY() == 0 ) {
                    return false;
                }
                break;

            case SOUTH:
                if(this.absolutePosition.getY() == 6 ) {
                    return false;
                }
                break;

            case WEST:
                if(this.absolutePosition.getX() == 0 ) {
                    return false;
                }
                break;

            case EAST:
                if(this.absolutePosition.getX() == 6 ) {
                    return false;
                }
                break;
        }
        return true;
    }

    /**
     * update Assam's absolute position after moving Assam
     * @param newPosition new absolute position
     */
    public void updatePosition(IntPair newPosition) {

    }

    /**
     * Convert Assam class type to String
     * @return string value
     */
    public String AssamToString() {
        String stringAssam = "A";
        stringAssam += getAbsolutePosition().IntPairToString(getAbsolutePosition());
        stringAssam += getCurrentDirection().value;
        return stringAssam;
    }

    /**
     * convert String to Assam class type
     * @param stringAssam string value of assam
     * @return Assam class type value
     */
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
