package model;

/**
 * The Posn class is used to record a position and it is used by the model to
 * keep track of the position of apple and snake body parts. Its fields
 * represent the x coordinate and y coordiante respectively.
 */
public class Posn {
    private int xPos; // x coordinate
    private int yPos; // y coordinate

    Posn(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * This method gets the x coordinate of the position.
     * 
     * @return the x coordinate
     */
    int getXPos() {
        return xPos;
    }

    /**
     * This method gets the y coordinate of the position.
     * 
     * @return the y coordinate
     */
    int getYPos() {
        return yPos;
    }

    /**
     * This method sets the x coordinate of the position.
     * 
     * @param xPos the x coordinate
     */
    void setXPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     * This method sets the y coordinate of the position.
     * 
     * @param yPos the y coordinate
     */
    void setYPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     * This method sets both the x coordinate and they coordinate.
     * 
     * @param xPos the x coordinate
     * @param yPos the y coordinate
     */
    void setPos(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * The hashCode is rewritten in order to maintain the consistency of the equal
     * function. It is used to generate a hash code for a position object.
     */
    @Override
    public int hashCode() {
        final int p = 37;
        int code = 1;
        code = p * code + xPos;
        code = p * code + yPos;
        return code;
    }

    /**
     * The equals method is rewritten to compare two position objects. Two position
     * objects are same if they the same coordinate.
     */
    @Override
    public boolean equals(Object target) {
        if (target == null) {
            return false;
        }

        if (this == target) {
            return true;
        }

        if (this.getClass() != target.getClass()) {
            return false;
        }

        Posn position = (Posn) target;
        if ((xPos == position.getXPos()) && (yPos == position.getYPos())) {
            return true;
        }

        return false;
    }
}
