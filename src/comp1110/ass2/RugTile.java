package comp1110.ass2;

public class RugTile {
    private Color color;  // Color of the rug
    private IntPair absolutePosition; // absolute position of a rug segment on board

    private int id;

    /**
     * Constructor that does nothing
     */
    public RugTile() {}

    /**
     * Constructor of Rug Tile
     * This is a rug segment on the board
     * @param color color of rug
     * @param absolutePosition position of rug on the board
     */
    public RugTile(Color color, IntPair absolutePosition) {
        this.color = color;
        this.absolutePosition = absolutePosition;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setAbsolutePosition(IntPair absolutePosition) {
        this.absolutePosition = absolutePosition;
    }

    public IntPair getAbsolutePosition() {
        return absolutePosition;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String RugTileToString() {
        String rugString = "";
        if(this.color == null) {
            rugString += "n00";
        } else {
            rugString += this.color.value;
            String idString = String.valueOf(getId());
            if(idString.length() == 1) {
                rugString += "0" + idString;
            } else {
                rugString += idString;
            }
//            rugString += absolutePosition.IntPairToString(absolutePosition);
        }

        return rugString;
    }


    /**
     * Convert String to one segment of rug
     * @param rugString string representation of a segment of rug
     * @return rug segment
     */
    public RugTile StringToRugTile(String rugString, int x, int y) {
        if(rugString.charAt(0) == Color.RED.value) {
            setColor(Color.RED);
        } else if(rugString.charAt(0) == Color.CYAN.value) {
            setColor(Color.CYAN);
        } else if(rugString.charAt(0) == Color.YELLOW.value) {
            setColor(Color.YELLOW);
        } else if(rugString.charAt(0) == Color.PURPLE.value){
            setColor(Color.PURPLE);
        } else {
            setColor(null);
        }
        IntPair position = new IntPair(x, y);
        setAbsolutePosition(position);

        setId(Integer.parseInt(rugString.substring(1)));
//        setAbsolutePosition(position.StringToIntPair(rugString.substring(1)));

        return this;
    }


}
