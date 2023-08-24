package comp1110.ass2;

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
