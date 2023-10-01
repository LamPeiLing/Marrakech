package comp1110.ass2;

/**
 * Class that defines a rug in the Marrakech game.
 * @author u7770276 ZeXin Tang
 * @author u7754637 Pei Ling Lam (supporting author)
 */
public class Rug {
    private Color color;  // Color of the rug
    private IntPair[] relativePositions;  // Relative positions of the rug segments

    private int rugID;

    /**
     * Constructor: creates a new instance of the Rug class.
     * @param color Color of the rug (one of the color constants)
     * @param relativePositions Relative positions of the rug segments
     *
     * @author u7754637 Pei Ling Lam
     */
    public Rug(Color color, int id, IntPair[] relativePositions) {
        this.color = color;
        this.rugID = id;
        this.relativePositions = relativePositions;
    }

    /**
     * Constructor that does nothing
     * Declare Rug
     *
     * @author u7754637 Pei Ling Lam
     */
    public Rug(){}

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Gets the color of the rug.
     * @return Color of the rug
     */
    public Color getColor() {
        return color;
    }

    public void setRugID(int rugID) {
        this.rugID = rugID;
    }

    public int getRugID() {
        return rugID;
    }

    public void setRelativePositions(IntPair[] relativePositions) {
        this.relativePositions = relativePositions;
    }

    public IntPair[] getRelativePositions() {
        return relativePositions;
    }


    /**
     * Get the number of rugs based on the number of players.
     * @param numPlayers Number of players in the game
     * @return Number of rugs for the given number of players
     *
     * @author u7770276 ZeXin Tang
     */
    public static int getRugNumber(int numPlayers) {
        if (numPlayers == 3) {
            return 15;
        } else if (numPlayers == 4) {
            return 12;
        } else {
            throw new IllegalArgumentException("Invalid number of players");
        }
    }

    /**
     * Overrides the equals method to compare two rug objects for equality.
     * @param o Other object to compare
     * @return True if the objects are equal, false otherwise
     *
     * @author u7770276 ZeXin Tang
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rug rug = (Rug) o;
      /* return Objects.equals(color, rug.color) &&
                Arrays.equals(relativePositions, rug.relativePositions);*/
        return true;
    }

    /**
     * Overrides the toString method to convert the rug object to a printable string.
     * @return String representation of the rug object
     */
    @Override
    public String toString() {
        return null;
    }

    /**
     * Convert string representation of rug to Rug class type
     * @param rugString string representation of rug
     * @return rug as Rug class type
     *
     * @author u7754637 Pei Ling Lam
     */
    public Rug StringToRug(String rugString) {

        if(rugString.charAt(0) == Color.RED.value) {
            setColor(Color.RED);
        } else if(rugString.charAt(0) == Color.CYAN.value) {
            setColor(Color.CYAN);
        } else if(rugString.charAt(0) == Color.YELLOW.value) {
            setColor(Color.YELLOW);
        } else if(rugString.charAt(0) == Color.PURPLE.value) {
            setColor(Color.PURPLE);
        } else {
            setColor(null);
        }

        setRugID(Integer.parseInt(rugString.substring(1, 3)));

        IntPair position = new IntPair();
        IntPair[] positionList = new IntPair[2];
        positionList[0] = position.StringToIntPair(rugString.substring(3, 5));
        positionList[1] = position.StringToIntPair(rugString.substring(5));
        setRelativePositions(positionList);

        return this;
    }
}
