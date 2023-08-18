package comp1110.ass2;

public enum Direction {
    NORTH(0),
    SOUTH(1),
    WEST(2),
    EAST(3);

    public final int value;

    Direction(int value) {
        this.value = value;
    }
}
