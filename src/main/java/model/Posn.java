package model;

public class Posn {
    private int xPos;
    private int yPos;

    public Posn(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }


    int getXPos(){
        return xPos;
    }

    int getYPos(){
        return yPos;
    }

    void setXPos(int xPos) {
        this.xPos = xPos;
    }

    void setYPos(int yPos) {
        this.yPos = yPos;
    }

    void setPos(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public int hashCode()
    {
        final int p = 37;
        int code = 1;
        code = p * code + xPos;
        code = p * code + yPos;
        return code;
    }

    @Override
    public boolean equals(Object target) {
        if (target == null) {
            return false;
        }

        if (this == target) {
            return true;
        }

        if(this.getClass() != target.getClass()) {
            return false;
        }

        Posn position = (Posn) target;
        if((xPos == position.getXPos()) && (yPos == position.getYPos())) {
            return true;
        }

        return false;
    }
}
