package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import gameUtils.DirectionEnum;

public class SnakeTest {
    @Test
    public void testSnakeInit() {
        Snake snake = new Snake(new Position(2, 1));
        int[][] targetPos = { { 2, 1 }, { 1, 1 } };
        LinkedList<Position> positions = snake.getBodyPartPos();

        // This test ensures the snake is initialized with a length of 3.
        assertEquals(targetPos.length, snake.getCurLength());

        // This test ensures each position is correct.
        for (int i = 0; i < targetPos.length; i++) {
            Position curPos = positions.get(i);
            assertEquals(curPos.getXPos(), targetPos[i][0]);
            assertEquals(curPos.getYPos(), targetPos[i][1]);
        }
    }

    @Test
    public void testSnakeMove() {
        Snake snake = new Snake(new Position(2, 1));
        // moving right.
        snake.move();
        // moving right again.
        snake.move();
        // setting up.
        snake.setDirection(DirectionEnum.UP);
        // moving left.
        snake.move();
        // setting left.
        snake.setDirection(DirectionEnum.LEFT);
        // moving left.
        snake.move();
        // setting down.
        snake.setDirection(DirectionEnum.DOWN);
        // moving down
        snake.move();

        int[][] targetPos = { { 3, 1 }, { 3, 2 } };
        LinkedList<Position> positions = snake.getBodyPartPos();

        // This test ensures each body part position is correct.
        for (int i = 0; i < targetPos.length; i++) {
            Position curPos = positions.get(i);
            assertEquals(curPos.getXPos(), targetPos[i][0]);
            assertEquals(curPos.getYPos(), targetPos[i][1]);
        }

        // This test ensures the move does not grow the body.
        assertEquals(positions.size(), targetPos.length);
    }

    @Test
    public void testSnakeGrow() {
        Snake snake = new Snake(new Position(2, 1));
        // moving right
        snake.move();
        // moving right again
        snake.move();
        // setting up
        snake.setDirection(DirectionEnum.UP);
        // moving up
        snake.move();
        // This test ensures the move does not grow the body.
        LinkedList<Position> positions = snake.getBodyPartPos();
        assertEquals(positions.size(), 2);
        //moving up again
        snake.move();
        //eating an apple
        snake.grow(new Position(4, 1));

        int[][] targetPos = { { 4, 3 }, { 4, 2 }, { 4, 1 } };
        // This test makes sure the snake grows by only one body part.
        assertEquals(positions.size(), targetPos.length);

        // This test ensures each body part position is correct.
        positions = snake.getBodyPartPos();
        for (int i = 0; i < targetPos.length; i++) {
            Position curPos = positions.get(i);
            assertEquals(curPos.getXPos(), targetPos[i][0]);
            assertEquals(curPos.getYPos(), targetPos[i][1]);
        }
        
    }
}
