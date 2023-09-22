
# Test plan

## List of classes

* List below all classes in your implementation that should have unit tests.
* For each class, list methods that can be tested in isolation.
* For each class, if there are conditions on the class' behaviour that cannot
  be tested by calling one method in isolation, give at least one example of
  a test for such a condition.


Do **not** include in your test plan the `Marrakech` class or the predefined
static methods for which we have already provided unit tests.


## AngleTest code (enums)

### Test Case 1: testGetAngleFromValue - Testing Angle Enumeration from Value
**Description**

This test case meticulously evaluates the getAngleFromValue method of the Angle enum. 
It verifies that the enum correctly associates angle values with corresponding enum values within the range of 0 to 359 degrees.

**Scenario**

Calling the getAngleFromValue method with various angle values within the range of 0 to 359 degrees.

**Expected Result**

The expected outcome is that the method should return the correct Angle enum value corresponding to each angle value within the valid range. For example, an angle value of 90 degrees should correspond to Angle.DEG_90.

**Assertions**

Employing assertions to validate that the method correctly maps angle values to Angle enum values:
- assertEquals(Angle.DEG_0, Angle.getAngleFromValue(0)): Verifies that an angle value of 0 degrees corresponds to Angle.DEG_0. 
- assertEquals(Angle.DEG_90, Angle.getAngleFromValue(90)): Ensures that an angle value of 90 degrees corresponds to Angle.DEG_90.
(Similar assertions for other angles within the valid range)

### Test Case 2: testAdd - Testing Angle Addition
**Description**

This test case assesses the add method of the Angle enum, which adds two angle values together and returns the corresponding enum value. 
It ensures that angle addition is correctly handled by the enum.

**Scenario**

1. Creating Angle enum values representing angles such as Angle.DEG_0, Angle.DEG_90, and Angle.DEG_180.
2. Using the add method to add these enum values together.

**Expected Result**

The expected outcome is that the add method should correctly perform angle addition and return the corresponding Angle enum value.

**Assertions**

Employing assertions to validate the correctness of angle addition:
- assertEquals(Angle.DEG_90, Angle.DEG_0.add(Angle.DEG_90)): Checks that adding Angle.DEG_0 and Angle.DEG_90 results in Angle.DEG_90.
  (Similar assertions for other angle additions)

### Test Case 3: testGetRad - Testing Angle to Radians Conversion
**Description**

This test case rigorously examines the getRad method of the Angle enum, which converts angle values to radians. It ensures that angle values are accurately converted to radians.

**Scenario**

1. Creating Angle enum values representing angles such as Angle.DEG_0, Angle.DEG_90, and Angle.DEG_180.
2. Using the getRad method to convert these enum values to radians.

**Expected Result**

The expected outcome is that the getRad method should correctly convert angle values to radians. For example, Angle.DEG_90 should convert to π/2 radians.

**Assertions**

Employing assertions to validate the correctness of radians conversion:
- assertEquals(Math.toRadians(0), Angle.DEG_0.getRad()): Checks that Angle.DEG_0 converts to 0 radians.
- assertEquals(Math.toRadians(90), Angle.DEG_90.getRad()): Ensures that Angle.DEG_90 converts to π/2 radians.
    (Similar assertions for other angle conversions)


By providing these detailed descriptions, scenarios, expected results, and assertions, these test cases comprehensively validate the behavior of the Angle enum, covering angle mapping, addition, and radians conversion.

2.testAdd:to see whether adding the two Angle enumerations correctly and returns the expected result.

3.testGetRad: to see if it converts angles to radians correctly and returns the expected result.


## IntpairTest code

### Test Case 1: `testAdd` - Testing Addition of Two IntPairs
**Description**

This test case rigorously examines the `add` method of the `IntPair` class. 
It focuses on the element-wise addition of two `IntPair` objects and ensures the correctness of the result by comparing it to the expected values.

**Scenario**

1. Initializing two `IntPair` objects:
  - `pair1` with coordinates (5, 0).
  - `pair2` with coordinates (0, 4).

2. Invoking the `add` method on `pair1` by passing `pair2` as an argument.

**Expected Result**

The expected outcome is an `IntPair` object with coordinates (5, 4) as the result of the addition.

**Assertion**

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

2. Invoking the `add` method on `pair1` by passing `pair2` as an argument.

**Expected Result:**

As a consequence of the addition, the resulting coordinates become (8, 8), which are beyond the valid bounds of a 7x7 board. Therefore, the result should be considered invalid.

**Assertion:**

To confirm the correctness of the result, we assert that:
- `assertFalse(result.isValidPosition())`: Verifies that the `isValidPosition` method returns `false` for the result, 
- indicating that it is not a valid position on a 7x7 board.

By providing these detailed descriptions, scenarios, expected results, and assertions, 
these test cases aim to comprehensively validate the behavior of the `IntPair` class, including both typical and boundary scenarios.

## RugTest code

### Test Case 1: `testConstructorAndGetters` - Testing Rug Initialization and Getters
**Description**

This test case meticulously examines the constructor and getter methods of the `Rug` class. It ensures that a `Rug` object can be correctly initialized with specified properties, and that the getter methods retrieve these properties accurately.

**Scenario**

1. Creating a `Rug` object:
  - `color` is set to `Color.RED`.
  - `rugID` is set to `1`.
  - `relativePositions` contain two `IntPair` objects: (0, 0) and (1, 1).

**Expected Result**

The expected outcome is that the `Rug` object should have its properties initialized as specified.

**Assertions**

Employing assertions to validate the correctness of the `Rug` object's properties:
- `assertEquals(Color.RED, rug.getColor())`: Checks that the color property matches the expected value (`Color.RED`).
- `assertEquals(1, rug.getRugID())`: Ensures that the `rugID` property matches the expected value (`1`).
- `assertArrayEquals(expectedPositions, rug.getRelativePositions())`: Verifies that the `relativePositions` property contains the expected `IntPair` objects.

### Test Case 2: `testGetRugNumber` - Testing Calculation of Rug Numbers
**Description**

This test case evaluates the `getRugNumber` static method of the `Rug` class, which calculates the number of rugs based on the number of players in the game. It verifies if the method accurately calculates the number of rugs for different player counts.

**Scenario**

1. Calling the `getRugNumber` method with different player counts, such as `3` and `4`.

**Expected Result**

The expected outcomes are:
- For `3` players, the method should return `15` rugs.
- For `4` players, the method should return `12` rugs.

**Assertions**

Employing assertions to validate that the method returns the expected rug numbers:
- `assertEquals(15, Rug.getRugNumber(3))`: Checks that the method returns `15` for 3 players.
- `assertEquals(12, Rug.getRugNumber(4))`: Ensures that the method returns `12` for 4 players.

### Test Case 3: `testEquals` - Testing Rug Equality
**Description**

This test case assesses the `equals` method of the `Rug` class, which compares two `Rug` objects for equality. It ensures that the method correctly determines whether two rugs are equal or not.

**Scenario**

1. Creating three `Rug` objects:
  - `rug1` with the same properties as `rug2`.
  - `rug2` with the same properties as `rug1`.
  - `rug3` with different properties.

**Expected Result**

The expected outcomes are:
- `rug1` and `rug2` should be considered equal.
- `rug1` and `rug3` should not be considered equal.

**Assertions**

Employing assertions to validate the correctness of the `equals` method:
- `assertTrue(rug1.equals(rug2))`: Verifies that `rug1` and `rug2` are considered equal.
- `assertFalse(rug1.equals(rug3))`: Ensures that `rug1` and `rug3` are not considered equal.

## ScoreTest

### Test Case 1: testSetAndGetDirhamScore - Testing Dirham Score Setter and Getter
**Description**

This test case thoroughly evaluates the setDirhamScore and getDirhamScore methods of the Scores class. It focuses on ensuring that the dirham score can be correctly set and retrieved.

**Scenario**

    We create a Scores object.
    We use the setDirhamScore method to set the dirham score to 10.

**Expected Result**

The expected outcome is that the dirham score should be set to 10 and can be accurately retrieved using the getDirhamScore method.

**Assertions**

Employing assertions to validate the correctness of the dirham score:
- assertEquals(10, scores.getDirhamScore()): Checks that the dirham score matches the expected value (10).

### Test Case 2: testSetAndGetRugScore - Testing Rug Score Setter and Getter
**Description**

This test case rigorously assesses the setRugScore and getRugScore methods of the Scores class. It focuses on ensuring that the rug score can be correctly set and retrieved.

**Scenario**

    We create a Scores object.
    We use the setRugScore method to set the rug score to 5.

**Expected Result**

The expected outcome is that the rug score should be set to 5 and can be accurately retrieved using the getRugScore method.

**Assertions**

Employing assertions to validate the correctness of the rug score:
- assertEquals(5, scores.getRugScore()): Ensures that the rug score matches the expected value (5).

### Test Case 3: testGetTotalScore - Testing Calculation of Total Score
**Description**

This test case evaluates the getTotalScore method of the Scores class, which calculates the total score by summing the dirham and rug scores. It ensures that the method correctly calculates the total score.

**Scenario**

    Creating a Scores object.
    Setting the dirham score to 10 and the rug score to 5.

**Expected Result**

The expected outcome is that the total score should be calculated as 10 + 5 = 15.

**Assertions**

Employing assertions to validate that the method returns the expected total score:
- assertEquals(15, scores.getTotalScore()): Checks that the method returns the correct total score (15).

### Test Case 4: testUpdateDirhamScore - Testing Dirham Score Update
**Description**

This test case assesses the updateDirhamScore method of the Scores class. 
It evaluates how the dirham score is updated when adding or deducting dirhams from the score.

**Scenario**

    Creating a Scores object.
    Using the updateDirhamScore method to add 5 dirhams.
    Then use the same method to deduct 3 dirhams.

**Expected Result**

The expected outcome is that the dirham score should be updated correctly. 
It should increase by 5 when adding dirhams and decrease by 3 when deducting dirhams.

**Assertions**

Employing assertions to validate the correctness of the dirham score:
- assertEquals(5, scores.getDirhamScore()): Checks that the dirham score is updated correctly after adding dirhams.
- assertEquals(2, scores.getDirhamScore()): Ensures that the dirham score is updated correctly after deducting dirhams.

### Test Case 5: testUpdateRugScore - Testing Rug Score Update
**Description**

This test case evaluates the updateRugScore method of the Scores class, 
which updates the rug score based on whether a rug is placed on the board or overlapped by other rugs. 
It ensures that the method correctly updates the rug score.

**Scenario**

    Creating a Scores object.
    Using the updateRugScore method to indicate that a rug is placed on the board (false).
    Using then use the same method to indicate that a rug is overlapped by others (true).

**Expected Result**

The expected outcome is that the rug score should be updated correctly. 
It should increase by 2 when a rug is placed on the board and decrease by 1 when a rug is overlapped by others.

**Assertions**

Employing assertions to validate the correctness of the rug score:
- assertEquals(2, scores.getRugScore()): Checks that the rug score is updated correctly after placing a rug on the board.
- assertEquals(1, scores.getRugScore()): Ensures that the rug score is updated correctly after a rug is overlapped by others.

By providing these detailed descriptions, scenarios, expected results, and assertions,
these test cases comprehensively validate the behavior of the Scores class,
covering various aspects of score calculation and update.

## Assam

### StringToAssamTest - Testing the conversion from String to Assam works fine
**Description**

This test will test on converting Assam's string representation to its class type, 
and verified by comparing the direction and position.

**Scenario**

1. The input of string representation: A31N
2. Convert to `Assam` type by calling `StringToAssam("A31N")`
3. verified by comparing `assam.getAbsolutePosition()` and `assam.getCurrentDirection()`

**Expected output**

The expected output of `assam.getAbsolutePosition()` after calling `StringToAssam()` is 31 (IntPair(3,1) in string format)
The expected output of `assam.getCurrentDirection()` after calling `StringToAssam()` is N

**Assertions**

Employing assertions to validate the correctness of the conversion of string representation of assam:
- `assertEquals(String position, String IntPairToString(assam.StringToAssam(stringAssam).getAbsolutePosition()))`: verify that the string we get is the string of position that we first used to convert into `Assam`
- `assertEquals(String direction, String String.valueOf(assam.StringToAssam(stringAssam).getCurrentDirection().value))`: verify that the string we get is the string of direction that we first used to convert into `Assam`
- Examples:
  - A31N `assertEquals("31", "31")` returns true
  - A31N `assertEquals("N", "N")` returns true

### AssamToStringTest - Testing the conversion from Assam to String works fine
**Description**

This test will test on converting `Assam` to its string representation by inputting position and direction
and verified by comparing to the expected string representation.

**Scenario**

1. The input of direction and position: (3,1), N
2. set the direction and position of assam using setter
3. Convert to string type by calling `assam.AssamToString()`
4. verified by comparing the expected string representation `A31N`

**Expected output**

The expected output of Assam with position of (3,1) and direction North after calling `AssamToString` is `A31N`

**Assertions**

Employing assertions to validate the correctness of the conversion of string representation of assam:
- `assertEquals(String stringRepresentation, String assam.AssamToString())`: verify that the string we get is same as the expected output
- Examples:
  - (3,1) N `assertEquals("A31N", "A31N")` returns true
  - (3,1) N `assertEquals("A31N", "A00N")` returns false

### IsBorderTest - Testing the judgement on whether the position is a border
**Description**

This test will test on whether Assam is at the border of the board.
The positions that contains x and y coordinate equals to 0 or 6 are all considered as border of the board.

**Scenario**

1. The input of Assam's position `IntPair`: (5,0)
2. call the method `isBorder()`

**Expected output**

The expected output of `isBorder()` is true

**Assertions**

Employing assertions to validate the correctness of whether the position is border:
- `assertEquals(boolean expectedResult, assam.isBorder())`: verify that the position is at border or not
- Examples:
  - position (4,3) `assertEquals(false, false)` returns true
  - position (4,3) `assertEquals(false, true)` returns false
  - position (0,0) `assertEquals(true, true)` returns true
  - position (0,0) `assertEquals(true, false)` returns false

### IsMovementSafeTest - Testing whether Assam is safe to move forward
**Description**

This test will test on whether Assam is safe to move forward given position and direction.
If Assam is at (0,0), (1,0), ... , (6,0) and facing north, if Assam move forward then he will be out of the board, hence it is not safe and return false
Same goes to other directions and the corresponding borders.

**Scenario**

1. The input of Assam's position `IntPair`: (5,0)
2. The input of Assam's direction: Direction.SOUTH
3. call the method `isMovementSafe()`

**Expected output**

The expected output of `isMovementSafe()` is true

**Assertions**

Employing assertions to validate the correctness of whether Assam is safe to move forward:
- `assertEquals(boolean expectedResult, assam.isMovementSafe())`: verify that the movement is safe or not
- Examples:
  - position (4,3) with any directions `assertEquals(true, true)` returns true
  - position (4,3) with any directions `assertEquals(true, false)` returns false
  - position (0,0) with Direction.WEST `assertEquals(false, true)` returns false
  - position (0,0) with Direction.NORTH `assertEquals(false, false)` returns true


## Board

### StringToBoardTest - Testing the conversion from String to Board works fine
**Description**

This test will test on converting Board's string representation to its class type,
and verified by comparing the direction and position.

**Scenario**

1. The input of string representation: Bp15y29c26c26p24r25c24p20y29y15c22c22r25c24r23y26c13n00c12p22p25c21r26r17r17n00r24p25r21r26r20c19c20r24p16y20p18c15n00c02r10p16y20c18r14r14n00r10r13
2. Convert to `Board` type by calling `StringToBoard("Bp15y29c26c26p24r25c24p20y29y15c22c22r25c24r23y26c13n00c12p22p25c21r26r17r17n00r24p25r21r26r20c19c20r24p16y20p18c15n00c02r10p16y20c18r14r14n00r10r13")`
3. verified by calling `BoardToString()`

**Expected output**

The expected output of `AssamToString()` is "Bp15y29c26c26p24r25c24p20y29y15c22c22r25c24r23y26c13n00c12p22p25c21r26r17r17n00r24p25r21r26r20c19c20r24p16y20p18c15n00c02r10p16y20c18r14r14n00r10r13"

**Assertions**

Employing assertions to validate the correctness of the conversion of string representation of board:
- `assertEquals(String stringRepresentation, board.BoardToString())`: verify that the string we get is the string representation that we first used to convert into `Board`
- Examples:
  - `assertEquals("Bp15y29c26c26p24r25c24p20y29y15c22c22r25c24r23y26c13n00c12p22p25c21r26r17r17n00r24p25r21r26r20c19c20r24p16y20p18c15n00c02r10p16y20c18r14r14n00r10r13", "Bp15y29c26c26p24r25c24p20y29y15c22c22r25c24r23y26c13n00c12p22p25c21r26r17r17n00r24p25r21r26r20c19c20r24p16y20p18c15n00c02r10p16y20c18r14r14n00r10r13")` returns true
  - `assertEquals("Bp15y29c26c26p24r25c24p20y29y15c22c22r25c24r23y26c13n00c12p22p25c21r26r17r17n00r24p25r21r26r20c19c20r24p16y20p18c15n00c02r10p16y20c18r14r14n00r10r13", "Bn00n00c26c26p24r25c24p20y29y15c22c22r25c24r23y26c13n00n00n00n00c21r26r17r17n00r24p25r21r26r20c19c20r24p16y20p18c15n00c02r10p16y20c18r14r14n00r10r13")` returns false

### TestUpdateRugTile - Testing whether the method update the correct rug tile on the board
**Description**

This test will test on whether the rug tile at certain position is updated correctly.
The rug tile is separated to two from a rug, but the method take one in a time
The updated rug tile on the board should show on the Board string

**Scenario**

1. Original board string representation: Bp15y29c26c26p24r25c24p20y29y15c22c22r25c24r23y26c13n00c12p22p25c21r26r17r17n00r24p25r21r26r20c19c20r24p16y20p18c15n00c02r10p16y20c18r14r14n00r10r13
2. The input of a segment of rug `RugTile`: RugTile(Color.RED, (0,0), 8) which is red color, at position (0,0) and has ID of 8
3. call the method `updateRugTile()`

**Expected output**

The expected output of the modified board string representation is Br08y29c26c26p24r25c24p20y29y15c22c22r25c24r23y26c13n00c12p22p25c21r26r17r17n00r24p25r21r26r20c19c20r24p16y20p18c15n00c02r10p16y20c18r14r14n00r10r13

**Assertions**

Employing assertions to validate the correctness of updated rug tile on a specific position:
- `assertEquals(String expectedResult, board.BoardToString())`: verify that the given position on board has changed to desired rug tile
- Examples:
  - Original string: Bp15y29c26c26p24r25c24p20y29y15c22c22r25c24r23y26c13n00c12p22p25c21r26r17r17n00r24p25r21r26r20c19c20r24p16y20p18c15n00c02r10p16y20c18r14r14n00r10r13
  - RugTile(Color.CYAN, (0,1), 15) `assertEquals(Bp15c15c26c26p24r25c24p20y29y15c22c22r25c24r23y26c13n00c12p22p25c21r26r17r17n00r24p25r21r26r20c19c20r24p16y20p18c15n00c02r10p16y20c18r14r14n00r10r13, Bp15c15c26c26p24r25c24p20y29y15c22c22r25c24r23y26c13n00c12p22p25c21r26r17r17n00r24p25r21r26r20c19c20r24p16y20p18c15n00c02r10p16y20c18r14r14n00r10r13)` returns true
  - RugTile(Color.CYAN, (0,1), 15) `assertEquals(Bp15c15c26c26p24r25c24p20y29y15c22c22r25c24r23y26c13n00c12p22p25c21r26r17r17n00r24p25r21r26r20c19c20r24p16y20p18c15n00c02r10p16y20c18r14r14n00r10r13, Bp15y29c26c26p24r25c24p20y29y15c22c22r25c24r23y26c13n00c12p22p25c21r26r17r17n00r24p25r21r26r20c19c20r24p16y20p18c15n00c02r10p16y20c18r14r14n00r10r13)` returns false

### TestGetRugTileonPosition - Testing whether the method return the correct rug tile given a position
**Description**

This test will test on whether the rug tile at a specific position is returned correctly.
The method will loop over the list of rug tiles and get the rug tile on the given position

**Scenario**

1. Information of the board: Bp15y29c26c26p24r25c24p20y29y15c22c22r25c24r23y26c13n00c12p22p25c21r26r17r17n00r24p25r21r26r20c19c20r24p16y20p18c15n00c02r10p16y20c18r14r14n00r10r13
2. The input of a position `IntPair`: (6,6)
3. call the method `getRugTileOnPosition((6,6))`

**Expected output**

The expected output of `getRugTileOnPosition((6,6))` is RugTile(Color.RED, (6,6), 13) which is red in color, at position (6,6), and has ID of 13

**Assertions**

Employing assertions to validate the correctness of updated rug tile on a specific position:
- `assertEquals(RugTile, board.getRugTileOnPosition(position))`: verify that the returned rug tile at the position is correct
- Examples:
  - Original string: Bp15y29c26c26p24r25c24p20y29y15c22c22r25c24r23y26c13n00c12p22p25c21r26r17r17n00r24p25r21r26r20c19c20r24p16y20p18c15n00c02r10p16y20c18r14r14n00r10r13
  - position (0,1) `assertEquals(RugTile(Color.YELLOW, (0,1), 29), RugTile(Color.YELLOW, (0,1), 29))` returns true
  - position (0,1) `assertEquals(RugTile(Color.YELLOW, (0,1), 29), RugTile(Color.PURPLE, (0,1), 15))` returns false


## RugTile

### TestSetAndGetColor - Testing the setter and getter to get the color of a segment of rug on the board works fine
**Description**

This test will test on setting a value to the setter, and verified by using getter.
If we get the same value as input from getter, it means that both methods work.

**Scenario**

1. The input of `Color` object: Color.CYAN
2. set rug tile color to the input by calling `setColor(Color.CYAN)`
3. verified by calling `getColor()`

**Expected output**

The expected output of `getColor()` is an enum object `Color` with value Color.CYAN

**Assertions**

Employing assertions to validate the correctness of the color of rug tile:
- `assertEquals(Color expectedColor, rugTile.getColor())`: verify that the color we get is the color that we first set into it using the expected color
- Examples:
  - `assertEquals(Color.CYAN, Color.CYAN)` returns true
  - `assertEquals(Color.CYAN, Color.RED)` returns false
  - `assertEquals(Color.CYAN, null)` returns false

### TestSetAndGetAbsolutePosition - Testing the setter and getter to get position of one segment of rug on the board works fine
**Description**

This test will test on setting a value to the setter, and verified by using getter.
If we get the same value as input from getter, it means that both methods work.

**Scenario**

1. The input of `IntPair` object: (1,2)
2. set Assam's absolute position to the input by calling `setAbsolutePosition(IntPair(1,2))`
3. verified by calling `getAbsolutePosition()`

**Expected output**

The expected output of `getAbsolutePosition()` is an object `IntPair` with value (1,2)

**Assertions**

Employing assertions to validate the correctness of the absolute position of rug tile:
- `assertEquals(IntPair expectedPosition, rugTile.getAbsolutePosition())`: verify that the absolute position we get is the position that we first set into it using the expected position
- Examples:
  - `assertEquals((3,5), (3,5))` returns true
  - `assertEquals((3,5), (0,0))` returns false
  - `assertEquals((3,5), (6,3))` returns false

### TestSetAndGetId - Testing the setter and getter to get the id of a segment of rug on the board works fine
**Description**

This test will test on setting a value to the setter, and verified by using getter.
If we get the same value as input from getter, it means that both methods work.

**Scenario**

1. The input of `int` object: 27
2. set id of rug tile to the input by calling `setId(27)`
3. verified by calling `getId()`

**Expected output**

The expected output of `getId()` is 27

**Assertions**

Employing assertions to validate the correctness of the id of rug tile:
- `assertEquals(int expectedResult, rugTile.getId())`: verify that the integer we get is the integer that we first set into it using the input
- Examples:
  - `assertEquals(5, 5)` returns true
  - `assertEquals(5, 0)` returns false

### TestStringAndRugTileConversion - Testing on conversion between abbreviated rug string representation to RugTile works well
**Description**

This test will test on converting RugTile's string representation to its class type, and verified by converting it back to string representation.
If the string representation after converting back is same as the string given initially, it means that both methods works well.

**Scenario**

1. The input of string representation: y11
2. Convert to `Board` type by calling `StringToRugTile("y11")`
3. verified by calling `RugTileToString()`

**Expected output**

The expected output of `RugTileToString()` is "y11"

**Assertions**

Employing assertions to validate the correctness of the conversion of string representation of rug tile:
- `assertEquals(String stringRepresentation, rugTile.RugTileToString())`: verify that the string we get is the string representation that we first used to convert into `RugTile`
- Examples:
  - `assertEquals("p10", "p10")` returns true
  - `assertEquals("p10", "n00")` returns false


## Color (Enum)

### ColorTest - Testing whether the colors are valid
**Description**

This test will test on whether the colors are valid
The valid colors are red, purple, yellow, cyan

**Scenario**

1. input of a color: p
2. set for color `Color` with `Color.Purple` for `p`, `Color.YELLOW` for `y`, `Color.CYAN` for `c`, and `Color.RED` for `r`, `null` otherwise
3. compare with expected string

**Expected output**

The expected output is the color `p`

**Assertions**

Employing assertions to validate the correctness of the conversion of string representation of assam:
- `assertEquals(String expectedResult, String String.valueOf(color.value))`: verify that the testingColor is valid if it is not null
- `assertEquals(String "null", String String.valueOf(color))`: verify that the testingColor is invalid if it is null
- Examples:
  - y `assertContains("y", String String.valueOf(Color.YELLOW.value))` returns true
  - a `assertContains("null", String.valueOf(null))` returns true
  - a `assertContains("null", String.valueOf(Color.YELLOW.value))` returns false

