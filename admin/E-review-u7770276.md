## Code Review

Reviewed by: Zexin TANG, u7770276

Reviewing code written by: Pei ling Lam <other uid>

Component:
package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * check if Assam is safe to move forward a step according to its direction
     * @return false if his next step is out of the board, true otherwise
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
     */
    public List<IntPair> adjacentEdge () {
        List<IntPair> adjacents = new ArrayList<>();

        if(!isBorder()) {
            adjacents.add(new IntPair(absolutePosition.getX() - 1, absolutePosition.getY()));
            adjacents.add(new IntPair(absolutePosition.getX() + 1, absolutePosition.getY()));
            adjacents.add(new IntPair(absolutePosition.getX(), absolutePosition.getY() - 1));
            adjacents.add(new IntPair(absolutePosition.getX(), absolutePosition.getY() + 1));
        } else {
            if(getAbsolutePosition().getY() == 0) {
                if(getAbsolutePosition().getX() == 0) {
                    adjacents.add(new IntPair(absolutePosition.getX() + 1, absolutePosition.getY()));
                } else if (getAbsolutePosition().getX() == 6) {
                    adjacents.add(new IntPair(absolutePosition.getX() - 1, absolutePosition.getY()));
                } else {
                    adjacents.add(new IntPair(absolutePosition.getX() - 1, absolutePosition.getY()));
                    adjacents.add(new IntPair(absolutePosition.getX() + 1, absolutePosition.getY()));
                }
                adjacents.add(new IntPair(absolutePosition.getX(), absolutePosition.getY() + 1));
            } else if(getAbsolutePosition().getY() == 6) {
                if(getAbsolutePosition().getX() == 0) {
                    adjacents.add(new IntPair(absolutePosition.getX() + 1, absolutePosition.getY()));
                } else if (getAbsolutePosition().getX() == 6) {
                    adjacents.add(new IntPair(absolutePosition.getX() - 1, absolutePosition.getY()));
                } else {
                    adjacents.add(new IntPair(absolutePosition.getX() - 1, absolutePosition.getY()));
                    adjacents.add(new IntPair(absolutePosition.getX() + 1, absolutePosition.getY()));
                }
                adjacents.add(new IntPair(absolutePosition.getX(), absolutePosition.getY() - 1));
            } else if (getAbsolutePosition().getX() == 0) {
                if(getAbsolutePosition().getY() == 0) {
                    adjacents.add(new IntPair(absolutePosition.getX(), absolutePosition.getY() + 1));
                } else if (getAbsolutePosition().getY() == 6) {
                    adjacents.add(new IntPair(absolutePosition.getX(), absolutePosition.getY() - 1));
                } else {
                    adjacents.add(new IntPair(absolutePosition.getX(), absolutePosition.getY() - 1));
                    adjacents.add(new IntPair(absolutePosition.getX(), absolutePosition.getY() + 1));
                }
                adjacents.add(new IntPair(absolutePosition.getX() + 1, absolutePosition.getY()));
            } else {
                if(getAbsolutePosition().getY() == 0) {
                    adjacents.add(new IntPair(absolutePosition.getX(), absolutePosition.getY() + 1));
                } else if (getAbsolutePosition().getY() == 6) {
                    adjacents.add(new IntPair(absolutePosition.getX(), absolutePosition.getY() - 1));
                } else {
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


### Comments 

This code defines a Java class named "Assam" to represent a character or object in the game. The following is an evaluation of this code:

1. Code structure and naming: The structure of the code is relatively clear, with appropriate comments to explain the roles of individual methods and variables. The naming is also relatively expressive, e.g., meaningful variable and method names are used, which contributes to the readability and maintenance of the code.

2. Class members: The class contains two private member variables, `absolutePosition` and `currentDirection`, which represent the position and direction of Assam, respectively. This is a reasonable design to encapsulate the state information of Assam inside the class and provide appropriate access methods.

3. Constructors: The class has two constructors, one of which accepts a position parameter to initialise the position of the Assam, while the other constructor is empty. This provides some flexibility, but may also result in the need to manually set the position and orientation of an Assam object after it has been created, and further consideration may need to be given to how to optimise the design of the constructor.

4. Method implementation: The class contains a number of methods to manipulate and query the state of an Assam. The functionality of these methods is relatively clear, and they are properly annotated to explain what they do. For example, the `isBorder` method is used to check if the Assam is on the border of the game board, the `isMovementSafe` method is used to check if it is safe to move one step, the `adjacentEdge` method is used to get a list of adjacent positions, etc.

5. Type conversion methods: The class also provides two methods `AssamToString` and `StringToAssam` for converting Assam objects to strings and from strings back to Assam objects. These methods may be useful in game logic, but further improvements could be considered to ensure correctness and robustness.

Overall, the code looks good, but could be further optimised and improved, especially when dealing with conversions between strings and objects. Additionally, it is important to ensure that the code will work and be maintained in real game use cases.
*** Translated with www.DeepL.com/Translator (free version) ***




