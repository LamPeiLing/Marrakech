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

This code creates a Java class called "Assam" to stand in for a gaming character or item. This code's evaluation is as follows:

1. Code organization and naming: The code is organized in a reasonably obvious manner, and relevant comments are included to describe the functions of the various methods and variables. Additionally, the naming is rather expressive; for example, relevant variable and method names are employed, which improves the code's readability and maintenance.

2. Class members: The class has two private member variables called "absolutePosition" and "currentDirection" that, respectively, describe Assam's position and orientation. This is a reasonable design that provides adequate access methods and encapsulates the state information of Assam inside the class.

3. Constructors: There are two constructors in the class, one of which takes a position parameter to initialize the position of the Assam and the other of which is empty. This offers considerable flexibility, but it may also necessitate manually setting the position and orientation of an Assam object after it has been formed, necessitating extra thought about how to optimize the constructor's design.

4. Method implementation: A number of methods are included in the class to manipulate and query an Assam's state. These methods' functionality is quite obvious, and they are appropriately documented to describe what they do. The 'isBorder' method, for instance, is used to determine whether the Assam is on the game board's boundary, the 'isMovementSafe' method determines whether it is safe to move one step, the 'adjacentEdge' method returns a list of adjacent positions, etc.

5. Type conversion methods: The class additionally offers the 'AssamToString' and 'StringToAssam' methods for changing Assam objects into strings and back from strings to Assam objects. These techniques might be helpful in game logic, however other advancements might be thought about to guarantee accuracy and robustness.

The code appears to be excellent overall, but it could still be optimized and improved, especially when it comes to object and string conversions. Furthermore, it is crucial to guarantee that the code will function and be maintained in real-world game use situations.

