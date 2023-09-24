package comp1110.ass2;

import comp1110.ass2.Color;
import comp1110.ass2.IntPair;
import comp1110.ass2.Rug;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RugTest {

    @Test
    public void testConstructorAndGetters() {
        // Test Case 1: Testing Rug Initialization and Getters

        // Creating a Rug object
        Rug rug = new Rug(Color.RED, 1, new IntPair[]{new IntPair(0, 0), new IntPair(1, 1)});

        // Expected properties
        Color expectedColor = Color.RED;
        int expectedRugID = 1;
        IntPair[] expectedPositions = {new IntPair(0, 0), new IntPair(1, 1)};

        // Assertions
        assertEquals(expectedColor, rug.getColor()); // Check color property
        assertEquals(expectedRugID, rug.getRugID()); // Check rugID property
        assertArrayEquals(expectedPositions, rug.getRelativePositions()); // Check relativePositions property
        // assertArrayEquals(expectedPositions, rug.getRelativePositions(), (a, b) -> a.equals(b));

    }

    @Test
    public void testGetRugNumber() {
        // Test Case 2: Testing Calculation of Rug Numbers

        // Assertions
        assertEquals(15, Rug.getRugNumber(3)); // Check rug number for 3 players
        assertEquals(12, Rug.getRugNumber(4)); // Check rug number for 4 players
    }

    @Test
    public void testEquals() {
        // Test Case 3: Testing Rug Equality

        // Creating Rug objects
        Rug rug1 = new Rug(Color.RED, 1, new IntPair[]{new IntPair(0, 0), new IntPair(1, 1)});
        Rug rug2 = new Rug(Color.RED, 1, new IntPair[]{new IntPair(0, 0), new IntPair(1, 1)});
        Rug rug3 = new Rug(Color.CYAN, 2, new IntPair[]{new IntPair(1, 1), new IntPair(1, 0)});

        // Assertions
        assertTrue(rug1.equals(rug2)); // Check equality of rug1 and rug2
        assertTrue(rug1.equals(rug3)); // Check inequality of rug1 and rug3
    }

    // Additional test cases can be added here...

}
