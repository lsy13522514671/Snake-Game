package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import gameUtils.DirectionEnum;
import gameUtils.Posn;

class SnakeTest {
    @Test
    void testSnakeInit() {
        Snake snake = new Snake(new Posn(2, 1));
        int[][] targetPos = { { 2, 1 }, { 1, 1 } };
        LinkedList<Posn> positions = snake.getBodyPartPos();

        // this test ensures the snake is initialized with a length of 3
        assertEquals(targetPos.length, snake.getCurLength());

        // this test ensures each position is correct
        for (int i = 0; i < targetPos.length; i++) {
            Posn curPos = positions.get(i);
            assertEquals(curPos.getXPos(), targetPos[i][0]);
            assertEquals(curPos.getYPos(), targetPos[i][1]);
        }
    }

    @Test
    void testSnakeMove() {
        Snake snake = new Snake(new Posn(2, 1));
        // moving right
        snake.move();
        // moving right again
        snake.move();
        // setting up
        snake.setDirection(DirectionEnum.UP);
        // moving up
        snake.move();
        // setting left
        snake.setDirection(DirectionEnum.LEFT);
        // moving left
        snake.move();
        // setting down
        snake.setDirection(DirectionEnum.DOWN);
        // moving down
        snake.move();

        int[][] targetPos = { { 3, 1 }, { 3, 0 } };
        LinkedList<Posn> positions = snake.getBodyPartPos();

        // this test ensures each body part position is correct
        for (int i = 0; i < targetPos.length; i++) {
            Posn curPos = positions.get(i);
            assertEquals(curPos.getXPos(), targetPos[i][0]);
            assertEquals(curPos.getYPos(), targetPos[i][1]);
        }

        // this test ensures the move does not grow the body
        assertEquals(positions.size(), targetPos.length);
    }

    @Test
    void testSnakeGrow() {
        Snake snake = new Snake(new Posn(2, 1));
        // moving right
        snake.move();
        // moving right again
        snake.move();
        // setting up
        snake.setDirection(DirectionEnum.UP);
        // moving up
        snake.move();
        // This test ensures the move does not grow the body
        LinkedList<Posn> positions = snake.getBodyPartPos();
        assertEquals(positions.size(), 2);
        // setting right
        snake.setDirection(DirectionEnum.RIGHT);
        // moving right
        snake.move();
        // eating an apple
        snake.grow(new Posn(4, 1));

        int[][] targetPos = { { 5, 0 }, { 4, 0 }, { 4, 1 } };
        // this test makes sure the snake grows by only one body part
        assertEquals(positions.size(), targetPos.length);

        // this test ensures each body part position is correct
        positions = snake.getBodyPartPos();
        for (int i = 0; i < targetPos.length; i++) {
            Posn curPos = positions.get(i);
            assertEquals(curPos.getXPos(), targetPos[i][0]);
            assertEquals(curPos.getYPos(), targetPos[i][1]);
        }

    }
}
