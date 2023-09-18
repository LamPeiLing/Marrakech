
# Test plan

## List of classes
//AngleTest code (enums)
This test consist of three different methods
1.testGetAngleFromValue: to see if it correctly returns Angle enumerations for various input values, 
and remember to include the values outside the 0-359 range and negative values,
and also tests the case where the input value is not a multiple of 90, 
if it is not a multiple of 90, it should return a null value.

2.testAdd:to see whether adding the two Angle enumerations correctly and returns the expected result.

3.testGetRad: to see if it converts angles to radians correctly and returns the expected result.


//IntpairTest code
This test consist of seven different methods
1.testAdd: to test by adding two Intpairs objects and checking if the result is as expected.
2.testIsValidPosition: to check whether the Intpair is within the 7*7 boundary board.
3.testIsAdjacentTo: to check whether Intpair is adjacent to another Intpair
4.testIsWithinRange: check whether the intpair is within another intpair
5.testCalculateVectorTo: to calculate the vector from one intpair to another intpair and check wheter the result is as expected
6.testStringToIntPair: to convert the string representation of an Intpair in an actual Intpair and checking if the result is expected
7.testIntPairToString:to convert the string representation and check the result whether is expected or not



//rug test code

This test consist of 4 different methods
1.testConstructorAndGetters: test the constructor and getter methods of 
rug class to ensure the correct initialize and retrieve rug properties
2.testGetRugNumber: test the getRugNumber static method of the rug class to verify whether 
it returns the expected value of rugs based on number of players.
3.testEquals:check the equal method to ensure that it correctly compares two rug objects for equality
4.testStringToRug: check the StringtoRug method, which converts as string representation of a rug to a
rug object, and check whether the conversion is done correctly

//score
1.testSetAndGetDirhamScore: check the SetDirhamScore and getDirhamScore to ensure that 
they correctly set and retrieve the dirham score.
2.testSetAndGetRugScore: check setRugscore and getRugscore method to nsure that
they correctly set and retrieve the rug score.
3.testGetTotalScore: to check GetTotalScore method to ensure it is correctly calculates 
the total scores by summing the dirham and rug scores.
4.testUpdateDirhamScore: tests the updatedirhamScore method to ensure it correctly updates
the dirham score when either adding or deducing dirhams.
5.testUpdateRugScore: check the updatedRugscore method to ensure whether its is correclt update
the rug score when either adding 2 points for putting a rug or deducing 1 point for overlap




* List below all classes in your implementation that should have unit tests.
* For each class, list methods that can be tested in isolation.
* For each class, if there are conditions on the class' behaviour that cannot
  be tested by calling one method in isolation, give at least one example of
  a test for such a condition.


Do **not** include in your test plan the `Marrakech` class or the predefined
static methods for which we have already provided unit tests.
