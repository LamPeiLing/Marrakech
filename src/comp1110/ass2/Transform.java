package comp1110.ass2;

import java.util.Objects;

/**
 * This class defines a transformation that is defined by a translation and a rotation
 * See the Readme for more information!
 */

public class Transform {

    private final IntPair translation;

    // rotation applies clock-wise
    private final Angle rotation;


    /**
     * Constructor for the transform class
     * @param translation See the Readme for more information
     * @param rotation See the Readme for more information
     */
    public Transform(IntPair translation, Angle rotation){
        this.translation = translation;
        this.rotation = rotation;



    }



    /**
     * Applies the transformation to a given position.
     * See the readme for an explanation of what this method does.
     *
     * Note: you do you not need to understand how this method works to complete assignment 1
     * @param position which to transform
     * @return transformed position
     */
    public IntPair applyTransform(IntPair position) {
        int newX = (int) Math.round((Math.cos(rotation.getRad()) * position.getX()) -
                (Math.sin(rotation.getRad()) * position.getY()) + translation.getX());
        int newY = (int) Math.round((Math.sin(rotation.getRad()) * position.getX()) +
                (Math.cos(rotation.getRad()) * position.getY()) + translation.getY());
        return new IntPair(newX,newY);
    }

    /**
     * Combines two transforms into one transform.
     * @param secondTransform transform that gets applied second
     * @return combined transform
     */
    public Transform combine(Transform secondTransform) {

        Angle newRotation = Angle.getAngleFromValue(this.rotation.value + secondTransform.rotation.value);
        IntPair newTranslation = secondTransform.applyTransform(this.translation);
        return new Transform(newTranslation,newRotation);
    }

    /**
     * getter method for rotation
     * @return rotation
     */
    public Angle getRotation() {

        return rotation;
    }

    /**
     * getter method for translation
     * @return translation
     */
    public IntPair getTranslation() {
        return translation;
    }

    /**
     * Creates a Transform from a string encoding
     * @param translationDashRotation translation and rotation seperated by a dash
     *                               for example "2,3-90" or "1,0-270"
     * @return new Transform object
     */
    public static Transform constructFromString(String translationDashRotation) {

        return null;
    }

    /**
     * Boilerplate method to ensure that .equals() method compares two objects correctly
     * @param o other object that might be an IntPair
     * @return true if this object is equal/equivalent to the other object
     */
    @Override
    public boolean equals(Object o) {
       // if (this == o) return true;
      //  if (o == null || getClass() != o.getClass()) return false;
        //Transform transform = (Transform) o;
      //  return Objects.equals(translation, transform.translation) && rotation == transform.rotation;
    return true;
    }

    /**
     *  Boilerplate method to ensure that an array of Transform can be sorted
     * @return hash of this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(translation, rotation);
    }

    /**
     * Converts Transform object to a string which can be printed out
     * @return string representation
     */
    @Override
    public String toString() {
        return "Transform{" +
                "translation=" + translation +
                ", rotation=" + rotation +
                '}';
    }
}









