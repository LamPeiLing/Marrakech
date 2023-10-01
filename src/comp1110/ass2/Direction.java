package comp1110.ass2;

/**
 * @author u7754637 Pei Ling Lam
 */
public enum Direction {
    NORTH('N'),
    SOUTH('S'),
    WEST('W'),
    EAST('E');

    public final char value;

    Direction(char value) {
        this.value = value;
    }
}
