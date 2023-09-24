
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
3. verified by comparing `assam.getAbsolutePosition()` and `assam.getCurrentDirection()` to the string representation

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

This test will test on converting `Assam` to its string representation by inputting position and direction.
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

### AdjacentEdgeTest - Testing whether the method returns all adjacent positions of Assam are correct
**Description**

This test will test on whether the list of positions are the adjacent positions of Assam.
The adjacent positions contain the top, bottom, left, and right position of Assam.
There might be cases where the adjacent positions have only two or three given Assam is at border or corner.

**Scenario**

1. The input of Assam's position `IntPair`: (3,3)
2. call the method `adjacentEdge()`

**Expected output**

The expected output of `adjacentEdge()` is (3,2),(3,4),(2,3),(4,3)

**Assertions**

Employing assertions to validate the correctness of all adjacent positions of Assam:
- `assertEquals(List<IntPair> expectedPositions, assam.adjacentEdge())`: verify that the return list are same as expected adjacent position (without considering the order of sequence)
- Examples:
  - position (0,0) `assertEquals([(1,0),(0,1)], [(1,0),(0,1)])` returns true
  - position (0,0) `assertEquals([(1,0),(0,1)], [(-1,0),(1,0),(0,1),(0,-1)])` returns false
  - position (2,6) `assertEquals([(1,6),(3,6),(2,5)], [(1,6),(3,6),(2,5)])` returns true
  - position (2,6) `assertEquals([(1,6),(3,6),(2,5)], [(1,6),(3,6),(2,5),(2,7)])` returns false
  - position (1,2) `assertEquals([(0,2),(2,2),(1,1),(1,3)], [(0,2),(2,2),(1,1),(1,3)])` returns true


## Board

### StringToBoardTest - Testing the conversion from String to Board works fine
**Description**

This test will test on converting Board's string representation to its class type,
and verified by comparing list of rug tile.

**Scenario**

1. The input of string representation: Bp15y29c26c26p24r25c24p20y29y15c22c22r25c24r23y26c13n00c12p22p25c21r26r17r17n00r24p25r21r26r20c19c20r24p16y20p18c15n00c02r10p16y20c18r14r14n00r10r13
2. Convert to `Board` type by calling `StringToBoard("Bp15y29c26c26p24r25c24p20y29y15c22c22r25c24r23y26c13n00c12p22p25c21r26r17r17n00r24p25r21r26r20c19c20r24p16y20p18c15n00c02r10p16y20c18r14r14n00r10r13")`
3. verified by comparing with the expected output

**Expected output**

The expected output of `StringToBoard()` is the combination of rug tiles that turns into string which is `p15y29c26c26p24r25c24p20y29y15c22c22r25c24r23y26c13c12p22p25c21r26r17r17r24p25r21r26r20c19c20r24p16y20p18c15c02r10p16y20c18r14r14r10r13` without `n00`

**Assertions**

Employing assertions to validate the correctness of the conversion of string representation of board:
- `assertEquals(String stringRepresentation, board.StringToBoard())`: verify that the string we get is the string of combination of rug tiles without n00
- Examples:
  - Bp15y29c26c26p24r25c24p20y29y15c22c22r25c24r23y26c13n00c12p22p25c21r26r17r17n00r24p25r21r26r20c19c20r24p16y20p18c15n00c02r10p16y20c18r14r14n00r10r13 `assertEquals("p15y29c26c26p24r25c24p20y29y15c22c22r25c24r23y26c13n00c12p22p25c21r26r17r17r24p25r21r26r20c19c20r24p16y20p18c15c02r10p16y20c18r14r14r10r13", "p15y29c26c26p24r25c24p20y29y15c22c22r25c24r23y26c13n00c12p22p25c21r26r17r17r24p25r21r26r20c19c20r24p16y20p18c15c02r10p16y20c18r14r14r10r13")` returns true
  - Br23n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00 `assertEquals("r23", "r23")` returns true

### BoardToStringTest - Testing the conversion from Board to String works fine
**Description**

This test will test on converting `Board` object to string,
and verified by comparing the direction and position.

**Scenario**

1. The input of `Board` object: r06-r13-r13-r22-r22-c19-r18-c20-y20-r03-p13-p17-c19-null-c10-y20-null-r26-null-r32-r32-c10-p24-p24-r26-p26-p33-r21-y27-y30-y30-p29-r20-p33-p32-y27-c02-p05-p29-r28-r08-p32-r24-r30-r30-p09-r28-null-r31
2. Convert to String type by calling `BoardToString()`
3. verified by comparing with the string representation

**Expected output**

The expected output of `BoardToString()` is "Br06r13r13r22r22c19r18c20y20r03p13p17c19n00c10y20n00r26n00r32r32c10p24p24r26p26p33r21y27y30y30p29r20p33p32y27c02p05p29r28r08p32r24r30r30p09r28n00r31"

**Assertions**

Employing assertions to validate the correctness of the conversion of string representation of board:
- `assertEquals(String stringRepresentation, board.BoardToString())`: verify that the string we get is the string that we get is same with expected string representation
- Examples:
  - `assertEquals("Bp15y29c26c26p24r25c24p20y29y15c22c22r25c24r23y26c13n00c12p22p25c21r26r17r17n00r24p25r21r26r20c19c20r24p16y20p18c15n00c02r10p16y20c18r14r14n00r10r13", "Bp15y29c26c26p24r25c24p20y29y15c22c22r25c24r23y26c13n00c12p22p25c21r26r17r17n00r24p25r21r26r20c19c20r24p16y20p18c15n00c02r10p16y20c18r14r14n00r10r13")` returns true
  - `assertEquals("Bp15y29c26c26p24r25c24p20y29y15c22c22r25c24r23y26c13n00c12p22p25c21r26r17r17n00r24p25r21r26r20c19c20r24p16y20p18c15n00c02r10p16y20c18r14r14n00r10r13", "Bn00n00c26c26p24r25c24p20y29y15c22c22r25c24r23y26c13n00n00n00n00c21r26r17r17n00r24p25r21r26r20c19c20r24p16y20p18c15n00c02r10p16y20c18r14r14n00r10r13")` returns false

### UpdateRugTileTest - Testing whether the method update the correct rug tile on the board
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

### GetRugTileonPositionTest - Testing whether the method return the correct rug tile given a position
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
- `assertEquals(RugTile expectedRugTile, board.getRugTileOnPosition(position))`: verify that the returned rug tile at the position is correct
- Examples:
  - Original string: Bp15y29c26c26p24r25c24p20y29y15c22c22r25c24r23y26c13n00c12p22p25c21r26r17r17n00r24p25r21r26r20c19c20r24p16y20p18c15n00c02r10p16y20c18r14r14n00r10r13
  - position (0,1) `assertEquals(RugTile(Color.YELLOW, (0,1), 29), RugTile(Color.YELLOW, (0,1), 29))` returns true
  - position (0,1) `assertEquals(RugTile(Color.YELLOW, (0,1), 29), RugTile(Color.PURPLE, (0,1), 15))` returns false
  - position (6,4) `assertEquals(null, null)` returns true


## RugTile

### StringToRugTileTest - Testing on conversion from abbreviated rug string representation to RugTile works well
**Description**

This test will test on converting rug tile's string representation to `RugTile` type, 
and verified by comparing the color and id. 
Position will not be verifying here because the string representation does not tell about the position, the position is set from `Board`.

**Scenario**

1. The input of string representation: y11
2. Convert to `RugTile` type by calling `StringToRugTile("y11")`
3. verified by comparing `getColor()` and `getId()` to the string representation

**Expected output**

The expected output of `getColor()` is `Color.YELLOW` and `getId()` is `11` after calling `StringToRugTile()`

**Assertions**

Employing assertions to validate the correctness of the conversion of string representation of rug tile:
- `assertEquals(String expectedColor, String String.valueOf(rugaTile.getColor().value))`: verify that the string of color we get is the expected color.
- `assertEquals(String expectedId, String String.valueOf(rugaTile.getId()))`: verify that the string of id we get is the expected id.
- Examples:
  - p30 `assertEquals("p", "p")` returns true
  - p30 `assertEquals("30", "30")` returns true
  - p30 `assertEquals("p", "c")` returns false

### RugTileToStringTest - Testing on conversion from RugTile to abbreviated rug string representation works well
**Description**

This test will test on converting `RugTile` type to rug tile's string representation,
and verified by comparing with the expected string representation.
Position will not be verifying here because the string representation does not tell about the position, the position is set from `Board`.

**Scenario**

1. The input of `RugTile`: color = Color.RED, id = 3
2. set the information into a `RugTile` object by calling `setColor(Color.RED)` and `setId(3)`
3. Call the method `RugTileToString()`
4. verified by comparing the output of `RugTileToString()` and its expected output

**Expected output**

The expected output of `RugTileToString()` is `r03`

**Assertions**

Employing assertions to validate the correctness of the conversion of string representation of rug tile:
- `assertEquals(String expectedResult, String RugTileToString)`: verify that the string that we get is same as the expected string representation
- Examples:
  - Color.YELLOW, 5 `assertEquals("y05", "y05")` returns true
  - Color.YELLOW, 5 `assertEquals("y05", "y5")` returns false
  - Color.YELLOW, 5 `assertEquals("y05", "y50")` returns false


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


## Coin

### UpdateNumOfCoin_5Test - Testing 5-dirham Coins Update
**Description**

This test case assesses the updateNumOfCoin_5 method of the Coin class.
It evaluates how the number of 5-dirham coins is updated.

**Scenario**

  Creating a Coin object
  - Calling the `updateNumOfCoin_5` method to get 6 connected rug score. 
  - Calling the `updateNumOfCoin_5` method to lose 6 connected rug score.

**Expected output**

  - The expected output is 6.
  - The expected output is 4.

**Assertions**

Employing assertions to validate that the method returns the expected 5-dirham coins numbers
- `asserEquals(6, coin.getCurrentNumOfCoin_5())`: Checks the updated number of 5-dirham coins matches the expected value (6).
- `assertEqual(4, coin.getCurrentNumOfCoin_5())`: Checks the updated number of 5-dirham coins matches the expected value (4).


### UpdateNumOfCoin_1Test - Testing 1-dirham Coins Update
**Description**

This test case assesses the updateNumOfCoin_1 method of the Coin class.
It evaluates how the number of 1-dirham coins is updated.

**Scenario**

  Creating a Coin object
  - Calling the `updateNumOfCoin_1` method to get 3 connected rug score.
  - Calling the `updateNumOfCoin_1` method to lose 6 connected rug score.

**Expected output**

  - The expected output is 8.
  - The expected output is 4.

**Assertions**

Employing assertions to validate that the method returns the expected 5-dirham coins numbers
- `asserEquals(8, coin.getCurrentNumOfCoin_1())`: Checks the updated number of 5-dirham coins matches the expected value (8).
- `assertEqual(4, coin.getCurrentNumOfCoin_1())`: Checks the updated number of 5-dirham coins matches the expected value (4).


## Players

### SetIsInGameTest - Testing whether the player is in game
**Description**

This test case assesses the setIsInGame method of the Players class.
It checks whether a player is in game.

**Scenario**

  1. The input of char representation: i
  2. Calling the `setIsInGame(char representation)` and `isInGame()` method

**Expected output**
  The expected output is true.

**Assertions**
  - `assertEquals(true, player.isInGame())`: Ensures the player is in game.

### PlayerToStringTest
**Description**

This test will test on converting Player to its string representation by inputting color, number of rugs, number of dirhams, and the player's game state('i' or 'o').
and verified by comparing to the expected string representation.

**Scenario**

  1. The input of color, number of dirhams, number of rugs, and game state: r, 10, 6, i
  2. Convert to String type by calling PlayerToString() 
  3. verified by comparing with the expected string representation `Pr01006i`

**Expected output**

  The expected output of Player with color 'r', 10 dirhams, 6 rugs, and true after calling AssamToString is `Pr01006i`
  
**Assertions**

  Employing assertions to validate the correctness of the conversion of string representation of player:
  - `assertEquals(String stringRepresentation, String player.PlayerToString())`: verify that the string we get is same as the expected output
  

### StringToPlayerTest
**Description**

This test will test on converting Player's string representation to its class type,
and verified by comparing the color, number of dirhams, number of rugs, and its game state('i' or 'o').

**Scenario**

  1. The input of string representation: Pr01006i
  2. Convert to Player type by calling `StringToPlayer("Pr01006i")`
  3. verified by comparing `player.getColor()` , `player.getNumDirham()`, `player.getNumRug()`and `player.isInGame()` to the string representation 

**Expected output**

  The expected output of `player.getColor()` is r. The expected output of `player.getNumDirham()` is 010. 
  The expected output of `player.getNumRug()` is 06. The expect output of `player.isInGame()` is true.

**Assertions**

  Employing assertions to validate the correctness of the conversion of string representation of player:
  - `assertEquals(String color, String String.valueOf(player.StringToPlayer(stringPlayer).getColor().value))`: verify that the string we get is the string of color that we first used to convert into Player
  - `assertEquals(int numDirham, player.StringToPlayer(String playerString).getNumDirham())`: verify that the string we get is the number of dirhams that we first used to convert into Assam
  - `assertEquals(int numRug, player.StringToPlayer(String playerString).getNumRug())`: verify that the string we get is the number of rugs that we first used to convert into Assam
  - `assertEquals(boolean isInGame, player.StringToPlayer(String playerString).isInGame())`: verify that the game state we get is the game state that we first used to convert into Assam


## Game

### GameToStringTest - Testing the conversion from String to gameState works fine
**Description**

This test will test on converting Game object to string, and verified by comparing the playerLists, assam string, and board string.

**Scenario**

  1. The input of `Game` object: c04403i-y05403i-p00003o-r02203i-41N-n00-y00-y00-n00-y19-y19-c04-y18-p02-y28-y28-r14-y20-y20-y18-y29-p19-c23-r19-r19-c03-n00-y29-p19-c29-c29-r10-c03-n00-r27-c24-c14-c14-r10-r16-y26-r27-r23-y22-y22-y14-r16-n00-y13-p23-c07-p13-r20-r20
  2. Convert to String type by calling `GameToString()`
  3. verified by comparing with the string representation

**Expected output**

  The expected output of `GameToString()` is "Pc04403iPy05403iPp00003oPr02203iA41NBn00y00y00n00y19y19c04y18p02y28y28r14y20y20y18y29p19c23r19r19c03n00y29p19c29c29r10c03n00r27c24c14c14r10r16y26r27r23y22y22y14r16n00y13p23c07p13r20r20"

**Assertions**

Employing assertions to validate the correctness of the conversion of string representation of game:
  - `assertEquals(String stringRepresentation, game.GameToString())`: verify that the string we get is the same with expected string representation

### StringToGameTest - Testing the conversion from String to Game works fine
**Description**

This test will test on converting Game's string representation to its class type.

**Scenario**
  1. The input of string representation: Pc04403iPy05403iPp00003oPr02203iA41NBn00y00y00n00y19y19c04y18p02y28y28r14y20y20y18y29p19c23r19r19c03n00y29p19c29c29r10c03n00r27c24c14c14r10r16y26r27r23y22y22y14r16n00y13p23c07p13r20r20
  2. Convert to `Game` type by calling `StringToGame("Pc04403iPy05403iPp00003oPr02203iA41NBn00y00y00n00y19y19c04y18p02y28y28r14y20y20y18y29p19c23r19r19c03n00y29p19c29c29r10c03n00r27c24c14c14r10r16y26r27r23y22y22y14r16n00y13p23c07p13r20r20")`
  3. verified by comparing with the expected output

**Expected output**

The expected output of `StringToGame()` is: c04403iy05403ip00003or02203i-41N-y00y00n00y19y19c04y18p02y28y28r14y20y20y18y29p19c23r19r19c03y29p19c29c29r10c03r27c24c14c14r10r16y26r27r23y22y22y14r16y13p23c07p13r20r20

**Assertions**

Employing assertions to validate the correctness of the conversion of string representation of game:
  - `assertEquals(String stringRepresentation, game.StringToGame())`: verify the result we get is correct.


