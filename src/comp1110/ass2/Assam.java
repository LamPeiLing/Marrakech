package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;

/**
 * class that handles everything regarding Asssam
 *
 * @author u7754637 Pei Ling Lam
 */
public class Assam {
    private IntPair absolutePosition; // current position of Assam on the board
    private Direction currentDirection; // current direction of Assam is facing


    /**
     * Constructor that initialize the position of Assam when a new game starts
     * @param position the position of the middle of the board
     *
     * @author u7754637 Pei Ling Lam
     */
    public Assam(IntPair position) {
        this.absolutePosition = position;
    }

    /**
     * Constructor that does not do anything
     * @author u7754637 Pei Ling Lam
     */
    public Assam() {}

    public void setAbsolutePosition(IntPair position) {
        this.absolutePosition = position;
    }

    /**
     * get the current position of Assam
     * @return the absolute position on the board
     *
     * @author u7754637 Pei Ling Lam
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
     *
     * @author u7754637 Pei Ling Lam
     */
    public Direction getCurrentDirection() {
        return this.currentDirection;
    }

    /**
     * check if Assam is already at the border of the board
     * use absolutePosition to check
     * @return true if already at the border of the board, false otherwise
     *
     * @author u7754637 Pei Ling Lam
     */
    public boolean isBorder() {
        if(absolutePosition.getX() == 0 || absolutePosition.getX() == 6 || absolutePosition.getY() == 0 || absolutePosition.getY() == 6) {
            return true;
        }
        return false;
    }

    /**
     * check if Assam is safe to move forward a step according to its direction
     * @return false if his next step is out of the board, true otherwise
     *
     * @author u7754637 Pei Ling Lam
     */
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
     * method to find the adjacent position of Assam
     * @return a list of adjacent position IntPair
     *
     * @author u7754637 Pei Ling Lam
     */
    public List<IntPair> adjacentEdge () {
        List<IntPair> adjacents = new ArrayList<>();

        if(!isBorder()) { // if assam is in the middle, should have 4 adjecents
            adjacents.add(new IntPair(absolutePosition.getX() - 1, absolutePosition.getY()));
            adjacents.add(new IntPair(absolutePosition.getX() + 1, absolutePosition.getY()));
            adjacents.add(new IntPair(absolutePosition.getX(), absolutePosition.getY() - 1));
            adjacents.add(new IntPair(absolutePosition.getX(), absolutePosition.getY() + 1));
        } else {
            if(getAbsolutePosition().getY() == 0) { // if assam is at top of the board
                if(getAbsolutePosition().getX() == 0) { // if assam at top left corner
                    adjacents.add(new IntPair(absolutePosition.getX() + 1, absolutePosition.getY()));
                } else if (getAbsolutePosition().getX() == 6) { // if assam at top right corner
                    adjacents.add(new IntPair(absolutePosition.getX() - 1, absolutePosition.getY()));
                } else { // if assam at the top border but not the corners
                    adjacents.add(new IntPair(absolutePosition.getX() - 1, absolutePosition.getY()));
                    adjacents.add(new IntPair(absolutePosition.getX() + 1, absolutePosition.getY()));
                }
                adjacents.add(new IntPair(absolutePosition.getX(), absolutePosition.getY() + 1));
            } else if(getAbsolutePosition().getY() == 6) { // if assam is at bottom of the board
                if(getAbsolutePosition().getX() == 0) { // if assam is at bottom left corner
                    adjacents.add(new IntPair(absolutePosition.getX() + 1, absolutePosition.getY()));
                } else if (getAbsolutePosition().getX() == 6) { // if assam is at bottom right corner
                    adjacents.add(new IntPair(absolutePosition.getX() - 1, absolutePosition.getY()));
                } else { // if assam at bottom border but not the corners
                    adjacents.add(new IntPair(absolutePosition.getX() - 1, absolutePosition.getY()));
                    adjacents.add(new IntPair(absolutePosition.getX() + 1, absolutePosition.getY()));
                }
                adjacents.add(new IntPair(absolutePosition.getX(), absolutePosition.getY() - 1));
            } else if (getAbsolutePosition().getX() == 0) { // if assam is at left side of the board
                if(getAbsolutePosition().getY() == 0) { // if assam is at top left corner
                    adjacents.add(new IntPair(absolutePosition.getX(), absolutePosition.getY() + 1));
                } else if (getAbsolutePosition().getY() == 6) { // if assam is at bottom left corner
                    adjacents.add(new IntPair(absolutePosition.getX(), absolutePosition.getY() - 1));
                } else {// if assam at left border but not the corners
                    adjacents.add(new IntPair(absolutePosition.getX(), absolutePosition.getY() - 1));
                    adjacents.add(new IntPair(absolutePosition.getX(), absolutePosition.getY() + 1));
                }
                adjacents.add(new IntPair(absolutePosition.getX() + 1, absolutePosition.getY()));
            } else { // if assam is at right side of the board
                if(getAbsolutePosition().getY() == 0) { // if assam is at top right corner
                    adjacents.add(new IntPair(absolutePosition.getX(), absolutePosition.getY() + 1));
                } else if (getAbsolutePosition().getY() == 6) { // if assam is at bottom right corner
                    adjacents.add(new IntPair(absolutePosition.getX(), absolutePosition.getY() - 1));
                } else { // if assam at right border but not the corners
                    adjacents.add(new IntPair(absolutePosition.getX(), absolutePosition.getY() - 1));
                    adjacents.add(new IntPair(absolutePosition.getX(), absolutePosition.getY() + 1));
                }
                adjacents.add(new IntPair(absolutePosition.getX() - 1, absolutePosition.getY()));
            }
        }

        return adjacents;
    }

    /**
     * Convert Assam class type to String
     * @return string value
     *
     * @author u7754637 Pei Ling Lam
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
     *
     * @author u7754637 Pei Ling Lam
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
