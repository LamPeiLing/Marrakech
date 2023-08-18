package comp1110.ass2.gui;

/**
 * Class that defines a rug in the Marrakech game.
 */
public class Rug {
    // Colors of the rugs
    public static final String YELLOW = "Yellow";
    public static final String BLUE = "Blue";
    public static final String RED = "Red";
    public static final String PURPLE = "Purple";

    private final String color;  // Color of the rug
    private final IntPair[] relativePositions;  // Relative positions of the rug segments

    /**
     * Constructor: creates a new instance of the Rug class.
     * @param color Color of the rug (one of the color constants)
     * @param relativePositions Relative positions of the rug segments
     */
    public Rug(String color, IntPair[] relativePositions) {
        this.color = color;
        this.relativePositions = relativePositions;
    }

    /**
     * Converts relative positions to absolute positions using the given transform.
     * @param transform Transformation to be applied
     * @return Array of absolute positions
     */
    public IntPair[] getAbsolutePositions(Transform transform) {
        return Piece.calcNewAbsolutePositions(relativePositions, transform);
    }

    /**
     * Gets the color of the rug.
     * @return Color of the rug
     */
    public String getColor() {
        return color;
    }

    /**
     * Get the number of rugs based on the number of players.
     * @param numPlayers Number of players in the game
     * @return Number of rugs for the given number of players
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
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rug rug = (Rug) o;
        return Objects.equals(color, rug.color) &&
                Arrays.equals(relativePositions, rug.relativePositions);
    }

    /**
     * Overrides the toString method to convert the rug object to a printable string.
     * @return String representation of the rug object
     */
    @Override
    public String toString() {
        return "Rug{" +
                "color='" + color + '\'' +
                ", relativePositions=" + Arrays.toString(relativePositions) +
                '}';
    }
}
