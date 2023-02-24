package model;

import java.util.LinkedList;

import gameUtils.DirectionEnum;

/**
 * The Snake class implements a real snake! It stores the current moving
 * direction, the body part positions, the head position, and the tail in the
 * last round of the game. The model uses this class to maintain and manipulate
 * the snake in the game.
 */
class Snake {
    // current direction
    private DirectionEnum direction;
    // each snake body part position represented in a Cartisian coordinate
    private LinkedList<Position> bodyPartPos;
    private Position headPos; // a pointer to the snake head
    private Position lastTailPos = null;

    Snake(Position position) {
        // the initial direction towards right
        direction = DirectionEnum.RIGHT;
        // the snake initially has two body parts by default
        bodyPartPos = new LinkedList<>();
        bodyPartPos.addLast(position);
        bodyPartPos.addLast(new Position(position.getXPos() - 1, position.getYPos()));
        // head position pointer
        headPos = bodyPartPos.peek();
    }

    /**
     * This method is used by the model to move a snake. The snake moves and updates
     * its head and tail. A move action only creates a new head and removes the
     * tail under the hood.
     * 
     */

    void move() {
        switch (direction) {
            case UP:
                bodyPartPos.addFirst(new Position(headPos.getXPos(), headPos.getYPos() + 1));
                break;
            case DOWN:
                bodyPartPos.addFirst(new Position(headPos.getXPos(), headPos.getYPos() - 1));
                break;
            case LEFT:
                bodyPartPos.addFirst(new Position(headPos.getXPos() - 1, headPos.getYPos()));
                break;
            case RIGHT:
                bodyPartPos.addFirst(new Position(headPos.getXPos() + 1, headPos.getYPos()));
                break;
        }
        setHeadPos(bodyPartPos.peek());
        Position tailPos = bodyPartPos.peekLast();
        setLastTailPos(tailPos.getXPos(), tailPos.getYPos());
        bodyPartPos.removeLast();
    }

    /**
     * This method is used by the model to make a snake grow. While a snake eats an
     * apple, it grows from its tail by one body part.
     */
    void grow() {
        bodyPartPos.add(new Position(lastTailPos.getXPos(), lastTailPos.getYPos()));
    }

    /**
     * This method gets the snake direction.
     * 
     * @return the snake direction
     */
    DirectionEnum getDirection() {
        return direction;
    }

    /**
     * This method gets all snake body parts positions.
     * 
     * @return the snake body parts positions
     */
    LinkedList<Position> getBodyPartPos() {
        return bodyPartPos;
    }

    /**
     * This method gets a pointer to the snake head.
     * 
     * @return head position pointer
     */
    Position getHeadPos() {
        return headPos;
    }

    /**
     * This method gets the snake tail in the last round.
     * 
     * @return tail in the last round
     */
    Position getLastTailPosition() {
        return lastTailPos;
    }

    /**
     * This method gets the current snake length.
     * 
     * @return current length
     */
    int getCurLength() {
        return bodyPartPos.size();
    }

    /**
     * This method sets the snake direction.
     */
    void setDirection(DirectionEnum direction) {
        this.direction = direction;
    }

    /**
     * This method sets the head position.
     */
    void setHeadPos(Position headPos) {
        this.headPos = headPos;
    }

    /**
     * This method sets the snake tail in the last round.
     * 
     */
    void setLastTailPos(int xPos, int yPos) {
        lastTailPos = new Position(xPos, yPos);
    }

    // public static void main(String[] args) {
    // Snake snake = new Snake(new Position(2, 1));

    // snake.move(false);
    // snake.move(false);
    // snake.setDirection(DirectionEnum.UP);
    // snake.move(false);
    // snake.setDirection(DirectionEnum.LEFT);
    // snake.move(false);
    // snake.setDirection(DirectionEnum.DOWN);
    // snake.move(false);
    // for(int i=0; i<snake.getBodyPartPos().size(); i++) {
    // Position curPos = snake.getBodyPartPos().get(i);
    // System.out.println("x: " + curPos.getXPos() + ", y:" + curPos.getYPos());
    // }
    // }
}