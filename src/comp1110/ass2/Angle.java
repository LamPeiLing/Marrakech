package comp1110.ass2;

/**
 * Enum to represent angles in degrees
 */
public enum Angle {
    DEG_0(0),          // Represents 0 degrees
    DEG_90(90),        // Represents 90 degrees
    DEG_180(180),      // Represents 180 degrees
    DEG_270(270);      // Represents 270 degrees

    // Angle value in degrees
    public final int value;

    /**
     * Constructor: creates a new instance of the Angle enum
     * @param angle Angle value in degrees
     */
    Angle(int angle) {
        this.value = angle;
    }

    /**
     * Convert angle value to a cardinal direction.
     * It ensures that the angle value is between 0 and 359.
     * If the angle is divisible by 90, returns the corresponding direction (DEG_0, DEG_90, DEG_180, or DEG_270),
     * otherwise returns null.
     *
     * @param value Angle value in degrees
     * @return Corresponding angle or null
     */
    public static Angle getAngleFromValue(int value) {
        assert value >= 0;
        value = value % 360;
        if (value == 0) {
            return DEG_0;
        } else if (value == 90) {
            return DEG_90;
        } else if (value == 180) {
            return DEG_180;
        } else if (value == 270) {
            return DEG_270;
        }
        return null;
    }

    /**
     * Adds another angle to this angle.
     *
     * @param other The other angle
     * @return New angle that is the sum of both
     */
    public Angle add(Angle other) {
        int sum = this.value + other.value;
        return getAngleFromValue(sum);
    }

    /**
     * Converts the angle value to radians.
     *
     * @return Angle value in radians
     */
    public double getRad() {
        return Math.toRadians(value);
    }

    /**
     * Converts Angle enum to a string for printing.
     *
     * @return String representation of the angle
     */
    @Override
    public String toString() {
        return "Angle{" +
                "value=" + value +
                '}';
    }
}
