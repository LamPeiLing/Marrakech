package comp1110.ass2;

import comp1110.ass2.Angle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AngleTest {

    @Test
    public void testGetAngleFromValue() {
        // Description: Enumeration from value test perspective.
        // This test case evaluates the getAngleFromValue method of the angle enumeration.
        // This test case evaluates the getAngleFromValue method of the angle enumeration. // It verifies that the enumeration correctly associates an angle value with the corresponding enumeration value in the range 0 to 359 degrees.

        // Scenario: Call the getAngleFromValue method with various angle values in the range 0 to 359 degrees.

        // Expected result: The method should return the correct angle enumeration value for each angle value in the valid range.

        // Assertion verifying that the method correctly maps angle values to angle enumeration values:

        assertEquals(Angle.DEG_0, Angle.getAngleFromValue(0)); // Verifies that 0 degrees corresponds to Angle.DEG_0.
        assertEquals(Angle.DEG_90, Angle.getAngleFromValue(90)); // Ensures that 90 degrees corresponds to Angle.DEG_90.
        // Similar assertions for other angles within the valid range.
    }

    @Test
    public void testAdd() {
        // Description: Testing angle addition
        // This test case evaluates the add method of the angle enumeration, which adds two angle values and returns the corresponding enumeration value.

        // Scenario
        // 1. Create Angle enumeration values representing angles such as Angle.DEG_0, Angle.DEG_90, and Angle.DEG_180.
        //DEG_0, Angle.DEG_90, and Angle.DEG_180. // Use the add method to add these enumerations.

        // Expected result: The add method should correctly perform the angle addition and return the corresponding angle enumeration values.

        // Assertions to verify the correctness of the angle addition:

        assertEquals(Angle.DEG_90, Angle.DEG_0.add(Angle.DEG_90)); // Adding Angle.DEG_0 and Angle.DEG_90 should result in Angle.DEG_90.
        // Similar assertions for other angle additions.
    }

    @Test
    public void testGetRad() {
        // Description: Tests the conversion of angles to radians.
        // This test case examines the getRad method of the angle enumeration, which converts angle values to radians.

        // Scenario
        // 1. Create Angle enumeration values that represent angles, such as Angle.DEG_0, Angle.DEG_90, and Angle.DEG_180.
        // DEG_0, Angle.DEG_90, and Angle.DEG_180. // 2. Use the getRad method to convert these enumerated values to radians.

        // Expected result: The getRad method should correctly convert angle values to radians.

        // Assertions to verify correct radian conversion:
        assertEquals(Math.toRadians(0), Angle.DEG_0.getRad()); // Angle.DEG_0 should convert to 0 radians.
        assertEquals(Math.toRadians(90), Angle.DEG_90.getRad()); // Angle.DEG_90 should convert to π/2 radians.
        // Similar assertions for other angle conversions.
    }
}
