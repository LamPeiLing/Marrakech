package comp1110.ass2.gui;

public class IntPair {
    private final int x;
    private final int y;

    public IntPair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Create a new IntPair by adding another IntPair element wise
    public IntPair add(IntPair other) {
        int newX = this.x + other.x;
        int newY = this.y + other.y;
        return new IntPair(newX, newY);
    }

    // Get the x coordinate
    public int getX() {
        return x;
    }

    // Get the y coordinate
    public int getY() {
        return y;
    }

    // Check if this position is valid on a 7x7 board
    public boolean isValidPosition() {
        return x >= 0 && x < 7 && y >= 0 && y < 7;
    }

    // Check if this position is adjacent to another position
    public boolean isAdjacentTo(IntPair other) {
        int dx = Math.abs(this.x - other.x);
        int dy = Math.abs(this.y - other.y);
        return (dx == 1 && dy == 0) || (dx == 0 && dy == 1);
    }

    // Check if this position is within a given range of another position
    public boolean isWithinRange(IntPair other, int range) {
        int dx = Math.abs(this.x - other.x);
        int dy = Math.abs(this.y - other.y);
        return dx <= range && dy <= range;
    }

    // Calculate the vector to move from this position to another position
    public IntPair calculateVectorTo(IntPair other) {
        int dx = other.x - this.x;
        int dy = other.y - this.y;
        return new IntPair(dx, dy);
    }

    // Other methods can be added here based on your requirements
}
