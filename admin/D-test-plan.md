
# Test plan

## List of classes
//AngleTest code (enums)

### Test Case 1: testGetAngleFromValue - Testing Angle Enumeration from Value

Description:

This test case meticulously evaluates the getAngleFromValue method of the Angle enum. It verifies that the enum correctly associates angle values with corresponding enum values within the range of 0 to 359 degrees.

Scenario:

    Calling the getAngleFromValue method with various angle values within the range of 0 to 359 degrees.

Expected Result:

The expected outcome is that the method should return the correct Angle enum value corresponding to each angle value within the valid range. For example, an angle value of 90 degrees should correspond to Angle.DEG_90.

Assertions:

Employing assertions to validate that the method correctly maps angle values to Angle enum values:

    assertEquals(Angle.DEG_0, Angle.getAngleFromValue(0)): Verifies that an angle value of 0 degrees corresponds to Angle.DEG_0.
    assertEquals(Angle.DEG_90, Angle.getAngleFromValue(90)): Ensures that an angle value of 90 degrees corresponds to Angle.DEG_90.
    (Similar assertions for other angles within the valid range)

### Test Case 2: testAdd - Testing Angle Addition

Description:

This test case assesses the add method of the Angle enum, which adds two angle values together and returns the corresponding enum value. 
It ensures that angle addition is correctly handled by the enum.

Scenario:

    Creating Angle enum values representing angles such as Angle.DEG_0, Angle.DEG_90, and Angle.DEG_180.
    Using the add method to add these enum values together.

Expected Result:

The expected outcome is that the add method should correctly perform angle addition and return the corresponding Angle enum value.

Assertions:

Employing assertions to validate the correctness of angle addition:

    assertEquals(Angle.DEG_90, Angle.DEG_0.add(Angle.DEG_90)): Checks that adding Angle.DEG_0 and Angle.DEG_90 results in Angle.DEG_90.
    (Similar assertions for other angle additions)

### Test Case 3: testGetRad - Testing Angle to Radians Conversion

Description:

This test case rigorously examines the getRad method of the Angle enum, which converts angle values to radians. It ensures that angle values are accurately converted to radians.

Scenario:

    Creating Angle enum values representing angles such as Angle.DEG_0, Angle.DEG_90, and Angle.DEG_180.
    Using the getRad method to convert these enum values to radians.

Expected Result:

The expected outcome is that the getRad method should correctly convert angle values to radians. For example, Angle.DEG_90 should convert to π/2 radians.

Assertions:

Employing assertions to validate the correctness of radians conversion:

    assertEquals(Math.toRadians(0), Angle.DEG_0.getRad()): Checks that Angle.DEG_0 converts to 0 radians.
    assertEquals(Math.toRadians(90), Angle.DEG_90.getRad()): Ensures that Angle.DEG_90 converts to π/2 radians.
    (Similar assertions for other angle conversions)

By providing these detailed descriptions, scenarios, expected results, and assertions, these test cases comprehensively validate the behavior of the Angle enum, covering angle mapping, addition, and radians conversion.

2.testAdd:to see whether adding the two Angle enumerations correctly and returns the expected result.

3.testGetRad: to see if it converts angles to radians correctly and returns the expected result.


//IntpairTest code

### Test Case 1: `testAdd` - Testing Addition of Two IntPairs

**Description:**

This test case rigorously examines the `add` method of the `IntPair` class. 
It focuses on the element-wise addition of two `IntPair` objects and ensures the correctness of the result by comparing it to the expected values.

**Scenario:**

1. Initializing two `IntPair` objects:
  - `pair1` with coordinates (5, 0).
  - `pair2` with coordinates (0, 4).

2. Invoking the `add` method on `pair1` by passing `pair2` as an argument.

**Expected Result:**

The expected outcome is an `IntPair` object with coordinates (5, 4) as the result of the addition.

**Assertion:**

Employing assertions to validate the correctness of the result:
- `assertEquals(5, result.getX())`: Verifies that the X-coordinate of the result matches the expected value (5).
- `assertEquals(4, result.getY())`: Ensures that the Y-coordinate of the result matches the expected value (4).

### Test Case 2: `testAddOutOfBounds` - Testing Out-of-Bounds Addition

**Description:**

This test case scrutinizes the behavior of the `IntPair` class when attempting to add two `IntPair` objects, the result of which falls outside the bounds of a 7x7 board. 
It evaluates whether the class correctly marks such a result as invalid.

**Scenario:**

1. Initializing two `IntPair` objects:
  - `pair1` with coordinates (6, 6).
  - `pair2` with coordinates (2, 2).

2.  Invoking the `add` method on `pair1` by passing `pair2` as an argument.

**Expected Result:**

As a consequence of the addition, the resulting coordinates become (8, 8), which are beyond the valid bounds of a 7x7 board. Therefore, the result should be considered invalid.

**Assertion:**

To confirm the correctness of the result, we assert that:
- `assertFalse(result.isValidPosition())`: Verifies that the `isValidPosition` method returns `false` for the result, 
- indicating that it is not a valid position on a 7x7 board.

By providing these detailed descriptions, scenarios, expected results, and assertions, 
these test cases aim to comprehensively validate the behavior of the `IntPair` class, including both typical and boundary scenarios.

//RugTest code

### Test Case 1: `testConstructorAndGetters` - Testing Rug Initialization and Getters

**Description:**

This test case meticulously examines the constructor and getter methods of the `Rug` class. It ensures that a `Rug` object can be correctly initialized with specified properties, and that the getter methods retrieve these properties accurately.

**Scenario:**

1. Creating a `Rug` object:
  - `color` is set to `Color.RED`.
  - `rugID` is set to `1`.
  - `relativePositions` contain two `IntPair` objects: (0, 0) and (1, 1).

**Expected Result:**

The expected outcome is that the `Rug` object should have its properties initialized as specified.

**Assertions:**

Employing assertions to validate the correctness of the `Rug` object's properties:
- `assertEquals(Color.RED, rug.getColor())`: Checks that the color property matches the expected value (`Color.RED`).
- `assertEquals(1, rug.getRugID())`: Ensures that the `rugID` property matches the expected value (`1`).
- `assertArrayEquals(expectedPositions, rug.getRelativePositions())`: Verifies that the `relativePositions` property contains the expected `IntPair` objects.

### Test Case 2: `testGetRugNumber` - Testing Calculation of Rug Numbers

**Description:**

This test case evaluates the `getRugNumber` static method of the `Rug` class, which calculates the number of rugs based on the number of players in the game. It verifies if the method accurately calculates the number of rugs for different player counts.

**Scenario:**

1. Calling the `getRugNumber` method with different player counts, such as `3` and `4`.

**Expected Result:**

The expected outcomes are:
- For `3` players, the method should return `15` rugs.
- For `4` players, the method should return `12` rugs.

**Assertions:**

Employing assertions to validate that the method returns the expected rug numbers:
- `assertEquals(15, Rug.getRugNumber(3))`: Checks that the method returns `15` for 3 players.
- `assertEquals(12, Rug.getRugNumber(4))`: Ensures that the method returns `12` for 4 players.

### Test Case 3: `testEquals` - Testing Rug Equality

**Description:**

This test case assesses the `equals` method of the `Rug` class, which compares two `Rug` objects for equality. It ensures that the method correctly determines whether two rugs are equal or not.

**Scenario:**

1. Creating three `Rug` objects:
  - `rug1` with the same properties as `rug2`.
  - `rug2` with the same properties as `rug1`.
  - `rug3` with different properties.

**Expected Result:**

The expected outcomes are:
- `rug1` and `rug2` should be considered equal.
- `rug1` and `rug3` should not be considered equal.

**Assertions:**

Employing assertions to validate the correctness of the `equals` method:
- `assertTrue(rug1.equals(rug2))`: Verifies that `rug1` and `rug2` are considered equal.
- `assertFalse(rug1.equals(rug3))`: Ensures that `rug1` and `rug3` are not considered equal.

//ScoreTest

### Test Case 1: testSetAndGetDirhamScore - Testing Dirham Score Setter and Getter

Description:

This test case thoroughly evaluates the setDirhamScore and getDirhamScore methods of the Scores class. It focuses on ensuring that the dirham score can be correctly set and retrieved.

Scenario:

    We create a Scores object.
    We use the setDirhamScore method to set the dirham score to 10.

Expected Result:

The expected outcome is that the dirham score should be set to 10 and can be accurately retrieved using the getDirhamScore method.

Assertions:

Employing assertions to validate the correctness of the dirham score:

    assertEquals(10, scores.getDirhamScore()): Checks that the dirham score matches the expected value (10).

### Test Case 2: testSetAndGetRugScore - Testing Rug Score Setter and Getter

Description:

This test case rigorously assesses the setRugScore and getRugScore methods of the Scores class. It focuses on ensuring that the rug score can be correctly set and retrieved.

Scenario:

    We create a Scores object.
    We use the setRugScore method to set the rug score to 5.

Expected Result:

The expected outcome is that the rug score should be set to 5 and can be accurately retrieved using the getRugScore method.

Assertions:

Employing assertions to validate the correctness of the rug score:

    assertEquals(5, scores.getRugScore()): Ensures that the rug score matches the expected value (5).

### Test Case 3: testGetTotalScore - Testing Calculation of Total Score

Description:

This test case evaluates the getTotalScore method of the Scores class, which calculates the total score by summing the dirham and rug scores. It ensures that the method correctly calculates the total score.

Scenario:

    Creating a Scores object.
    Setting the dirham score to 10 and the rug score to 5.

Expected Result:

The expected outcome is that the total score should be calculated as 10 + 5 = 15.

Assertions:

Employing assertions to validate that the method returns the expected total score:

    assertEquals(15, scores.getTotalScore()): Checks that the method returns the correct total score (15).

### Test Case 4: testUpdateDirhamScore - Testing Dirham Score Update

Description:

This test case assesses the updateDirhamScore method of the Scores class. 
It evaluates how the dirham score is updated when adding or deducting dirhams from the score.

Scenario:

    Creating a Scores object.
    Using the updateDirhamScore method to add 5 dirhams.
    Then use the same method to deduct 3 dirhams.

Expected Result:

The expected outcome is that the dirham score should be updated correctly. 
It should increase by 5 when adding dirhams and decrease by 3 when deducting dirhams.

Assertions:

Employing assertions to validate the correctness of the dirham score:

    assertEquals(5, scores.getDirhamScore()): Checks that the dirham score is updated correctly after adding dirhams.
    assertEquals(2, scores.getDirhamScore()): Ensures that the dirham score is updated correctly after deducting dirhams.

### Test Case 5: testUpdateRugScore - Testing Rug Score Update

Description:

This test case evaluates the updateRugScore method of the Scores class, 
which updates the rug score based on whether a rug is placed on the board or overlapped by other rugs. 
It ensures that the method correctly updates the rug score.

Scenario:

    Creating a Scores object.
    Using the updateRugScore method to indicate that a rug is placed on the board (false).
    Using then use the same method to indicate that a rug is overlapped by others (true).

Expected Result:

The expected outcome is that the rug score should be updated correctly. 
It should increase by 2 when a rug is placed on the board and decrease by 1 when a rug is overlapped by others.

Assertions:

Employing assertions to validate the correctness of the rug score:

    assertEquals(2, scores.getRugScore()): Checks that the rug score is updated correctly after placing a rug on the board.
    assertEquals(1, scores.getRugScore()): Ensures that the rug score is updated correctly after a rug is overlapped by others.

By providing these detailed descriptions, scenarios, expected results, and assertions, 
these test cases comprehensively validate the behavior of the Scores class, 
covering various aspects of score calculation and update.

* List below all classes in your implementation that should have unit tests.
* For each class, list methods that can be tested in isolation.
* For each class, if there are conditions on the class' behaviour that cannot
  be tested by calling one method in isolation, give at least one example of
  a test for such a condition.


Do **not** include in your test plan the `Marrakech` class or the predefined
static methods for which we have already provided unit tests.
