package comp1110.ass2;

import comp1110.ass2.IntPair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntPairTest {

    @Test
    public void testAdd() {
        // Test the addition of IntPair objects
        // Verify that the add method of the IntPair class correctly performs element-level summing and compares it to the expected value

        // Scenario:
        // 1. Initialise two IntPair objects:
        // - pair1 with coordinates (5, 0).
        // - pair2 has coordinates (0, 4).
        // 2. Call the add method, passing pair2 as an argument to pair1.

        // Desired result: the addition should result in an IntPair object with coordinates (5, 4).

        // Use assertions to verify that the result is correct:
        IntPair pair1 = new IntPair(5, 0);
        IntPair pair2 = new IntPair(0, 4);
        IntPair result = pair1.add(pair2);

        assertEquals(5, result.getX()); // Verify that the X-coordinate of the result matches the expected value (5)
        assertEquals(4, result.getY()); // Verify that the resultant Y coordinate matches the expected value (4)
    }

    @Test
    public void testIsValidPosition() {
        // Test the validity of the IntPair object
        // Verify that the isValidPosition method of the IntPair class correctly determines if the coordinates are within the valid range of the 7x7 board

        // Scenario:
        // 1. Create a valid IntPair object with coordinates (3, 4).
        // 2. Create an invalid IntPair object with coordinates (-1, 7).

        // Expected result:
        // - The valid IntPair object should be treated as valid.
        // - An invalid IntPair object should be treated as invalid.

        // Use assertions to verify the correctness of the method:
        IntPair validPosition = new IntPair(3, 4);
        IntPair invalidPosition = new IntPair(-1, 7);

        assertTrue(validPosition.isValidPosition()); // Verify that a valid IntPair object is considered valid
        assertFalse(invalidPosition.isValidPosition()); // Verify that invalid IntPair objects are treated as invalid
    }

    @Test
    public void testIsAdjacentTo() {
        // Test whether IntPair objects are adjacent to each other
        // Verify that the isAdjacentTo method of the IntPair class correctly determines whether two coordinates are adjacent or not

        // Scenario:
        // 1. Create two adjacent IntPair objects with coordinates (3, 4) and (3, 5).
        // 2. Create two non-adjacent IntPair objects with coordinates (2, 2) and (5, 6).

        // Expected result:
        // - Adjacent IntPair objects should be treated as adjacent.
        // - Non-adjacent IntPair objects should be treated as non-adjacent.

        // Use assertions to verify the correctness of the methods:
        IntPair adjacent1 = new IntPair(3, 4);
        IntPair adjacent2 = new IntPair(3, 5);
        IntPair notAdjacent1 = new IntPair(2, 2);
        IntPair notAdjacent2 = new IntPair(5, 6);

        assertTrue(adjacent1.isAdjacentTo(adjacent2)); // Verify that adjacent IntPair objects are treated as neighbouring
        assertFalse(adjacent1.isAdjacentTo(notAdjacent1)); // Verify that non-adjacent IntPair objects are treated as non-adjacent
        assertFalse(adjacent1.isAdjacentTo(notAdjacent2)); // Verify that non-adjacent IntPair objects are treated as non-adjacent
    }

    @Test
    public void testCalculateVectorTo() {
        // Test calculating a vector from one IntPair to another IntPair
        // Verify that the calculateVectorTo method of the IntPair class correctly calculates a vector from one coordinate to another

        // Scenario:
        // 1. Create two IntPair objects with coordinates (3, 4) and (6, 2).

        // Expected results:
        // - Calculate that the vector from (3, 4) to (6, 2) should be (3, -2).

        // Use assertions to verify the correctness of the method:
        IntPair from = new IntPair(3, 4);
        IntPair to = new IntPair(6, 2);
        IntPair vector = from.calculateVectorTo(to);

        assertEquals(3, vector.getX()); // Verify that the X coordinate is correct
        assertEquals(-2, vector.getY()); // Verify that the Y coordinate is correct
    }
}


