package comp1110.ass2;

/**
 * @author u7754637 Pei Ling Lam
 */
public enum Color {
    YELLOW('y'),
    CYAN('c'),
    RED('r'),
    PURPLE('p');

    public final char value;

    Color(char value) {this.value = value;}

    /**
     * get Color given the value
     * @param value c, y, r, or p
     * @return Color type
     *
     * @author u7754637 Pei Ling Lam
     */
    public Color getColorFromValue(char value) {
        if (value == 'c') {
            return CYAN;
        } else if (value == 'y') {
            return YELLOW;
        } else if (value == 'p') {
            return PURPLE;
        } else if (value == 'r') {
            return RED;
        }
        return null;
    }
}
