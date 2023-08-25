package comp1110.ass2;

public class IntPair {
    private int x;
    private int y;


    /**
     * Constructor that does not do anything
     */
    public IntPair(){}

    /**
     * Constructor that initialize intPair by x and y coordinate
     * @param x x coordinate
     * @param y y coordinate
     */
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

    public void setX(int x) {
        this.x = x;
    }

    // Get the x coordinate
    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
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

    /**
     * convert string to IntPair
     * @param stringPosition takes a string with length of 2 that represents x and y
     * @return with the IntPair data type
     */
    public IntPair StringToIntPair(String stringPosition) {
        int x = Integer.parseInt(stringPosition.substring(0, 1));
        int y = Integer.parseInt(stringPosition.substring(1));
        IntPair position = new IntPair(x,y);
        return position;
    }

    public String IntPairToString(IntPair position) {
        return String.valueOf(position.getX()) + String.valueOf(position.getY());
    }
}
