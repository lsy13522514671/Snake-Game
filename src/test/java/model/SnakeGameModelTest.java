package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import gameUtils.DirectionEnum;
import gameUtils.Posn;

class SnakeGameModelTest {
    int rowNum = 4;
    int colNum = 4;
    SnakeGameModel model = null;
    int[][] initSnakePos = { { 2, 2 }, { 1, 2 } };
    int initLength = 2;

    @BeforeEach
    void setup() {
        model = new SnakeGameModel(rowNum, colNum, new Random(0));
    }

    @Test
    void testSnakeGameModelInitLowRowException() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new SnakeGameModel(3, 4, new Random()),
                "Expected the model to complain about the board size input, but it didn't");

        assertTrue(thrown.getMessage().contentEquals("Illegal row number: 3!"));
    }

    @Test
    void testSnakeGameModelInitLowColException() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new SnakeGameModel(4, 3, new Random()),
                "Expected the model to complain about the board size input, but it didn't");

        assertTrue(thrown.getMessage().contentEquals("Illegal column number: 3!"));
    }

    @Test
    void testSnakeGameModelInit() {
        assertEquals(rowNum, model.getRowNum());
        assertEquals(colNum, model.getColNum());

        Posn applePos = model.getApplePos();
        assertTrue((applePos.getXPos() >= 0) && (applePos.getXPos() < colNum));
        assertTrue((applePos.getYPos() >= 0) && (applePos.getYPos() < rowNum));

        LinkedList<Posn> positions = model.getSnakePosition();

        for (int i = 0; i < initSnakePos.length; i++) {
            assertEquals(initSnakePos[i][0], positions.get(i).getXPos());
            assertEquals(initSnakePos[i][1], positions.get(i).getYPos());
        }
    }

    @Test
    void testSnakeMove() {
        LinkedList<Posn> positions = model.getSnakePosition();
        int curLength = positions.size();
        assertEquals(initLength, curLength);

        // snake moves right
        model.moveSnake();

        // snake does not turn left, because it's an invalid direction at the moment
        model.setSnakeDirection(DirectionEnum.LEFT);

        // snake moves up
        model.setSnakeDirection(DirectionEnum.UP);
        model.moveSnake();

        // snake moves up again
        model.moveSnake();

        // snake moves left
        model.setSnakeDirection(DirectionEnum.LEFT);
        model.moveSnake();

        positions = model.getSnakePosition();
        curLength = positions.size();

        // the snake length does not change as it eats nothing
        assertEquals(initLength, curLength);

        int[][] targetPos = { { 2, 0 }, { 3, 0 } };

        for (int i = 0; i < targetPos.length; i++) {
            assertEquals(positions.get(i).getXPos(), targetPos[i][0]);
            assertEquals(positions.get(i).getYPos(), targetPos[i][1]);
        }
    }

    @Test
    void testSnakeGrow() {
        LinkedList<Posn> positions = model.getSnakePosition();
        int curLength = positions.size();
        assertEquals(initLength, curLength);

        // snake moves right
        model.moveSnake();

        // snake moves down
        model.setSnakeDirection(DirectionEnum.DOWN);
        model.moveSnake();

        // snake moves down again
        model.moveSnake();

        // snake moves left
        model.setSnakeDirection(DirectionEnum.LEFT);
        model.moveSnake();

        // snake moves up
        model.setSnakeDirection(DirectionEnum.UP);
        model.moveSnake();

        // snake moves up again
        model.moveSnake();

        // and again, snake moves up
        model.moveSnake();

        positions = model.getSnakePosition();
        curLength = positions.size();
        assertEquals(initLength + 1, curLength);

        int[][] targetPos = { { 2, 1 }, { 2, 2 }, { 2, 3 } };

        for (int i = 0; i < targetPos.length; i++) {
            assertEquals(positions.get(i).getXPos(), targetPos[i][0]);
            assertEquals(positions.get(i).getYPos(), targetPos[i][1]);
        }

    }

    @Test
    void testSnakeCrashTopWall() {
        // snake moves right
        model.moveSnake();

        // snake moves up
        model.setSnakeDirection(DirectionEnum.UP);
        model.moveSnake();

        // snake moves up again
        model.moveSnake();

        // snake moves up again and crash on the wall
        model.moveSnake();

        assertTrue(model.isGameOver());
    }

    @Test
    void testSnakeCrashBottomWall() {
        // snake moves right
        model.moveSnake();

        // snake moves down
        model.setSnakeDirection(DirectionEnum.DOWN);
        model.moveSnake();

        // snake moves down again and crash on the wall
        model.moveSnake();

        assertTrue(model.isGameOver());
    }

    @Test
    void testSnakeCrashLeftWall() {
        // snake moves right
        model.moveSnake();

        // snake moves down
        model.moveSnake();

        // snake moves left
        model.setSnakeDirection(DirectionEnum.LEFT);
        model.moveSnake();

        // snake moves left again
        model.moveSnake();

        // and again, snake moves left
        model.moveSnake();

        // snake moves left again and crash on the wall
        model.moveSnake();

        assertTrue(model.isGameOver());
    }

    @Test
    void testSnakeCrashRightWall() {
        // snake moves right
        model.moveSnake();

        // snake moves right again and crash on the wall
        model.moveSnake();

        assertTrue(model.isGameOver());
    }

    @Test
    void testSnakeCrashBody() {
        // snake moves down
        model.setSnakeDirection(DirectionEnum.DOWN);
        model.moveSnake();

        // snake moves left
        model.setSnakeDirection(DirectionEnum.LEFT);
        model.moveSnake();

        // snake moves left again
        model.moveSnake();

        // snake moves up
        model.setSnakeDirection(DirectionEnum.UP);
        model.moveSnake();

        // snake moves up again
        model.moveSnake();

        // snake moves right
        model.setSnakeDirection(DirectionEnum.RIGHT);
        model.moveSnake();

        // snake moves right again
        model.moveSnake();

        // snake moves down
        model.setSnakeDirection(DirectionEnum.DOWN);
        model.moveSnake();

        // snake moves left
        model.setSnakeDirection(DirectionEnum.LEFT);
        model.moveSnake();

        // snake moves up and crash on its body
        model.setSnakeDirection(DirectionEnum.UP);
        model.moveSnake();

        assertTrue(model.isGameOver());
    }
}
