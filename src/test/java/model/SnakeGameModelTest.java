package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import gameUtils.DirectionEnum;

public class SnakeGameModelTest {
    int rowNum = 4;
    int colNum = 4;
    SnakeGameModel model = null;
    int[][] initSnakePos = { { 2, 2 }, { 1, 2 } };
    int initLength = 2;

    @BeforeEach
    public void setup() {
        model = new SnakeGameModel(rowNum, colNum, new Random(0));
    }

    @Test
    public void testSnakeGameModelInitException() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new SnakeGameModel(3, 4, new Random()),
                "Expected the model to complain about the board size input, but it didn't");

        assertTrue(thrown.getMessage().contentEquals("Illegal Board Size!"));
    }

    @Test
    public void testSnakeGameModelInit() {
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
    public void testSnakeMove() {
        LinkedList<Posn> positions = model.getSnakePosition();
        int curLength = positions.size();
        assertEquals(initLength, curLength);

        // Snake moves right.
        model.moveSnake();

        // Snake does not turn left, because it's an invalid direction at the moment.
        model.setSnakeDirection(DirectionEnum.LEFT);

        // Snake moves down.
        model.setSnakeDirection(DirectionEnum.DOWN);
        model.moveSnake();

        // Snake moves down again.
        model.moveSnake();

        // Snake moves left.
        model.setSnakeDirection(DirectionEnum.LEFT);
        model.moveSnake();

        // Snake moves up.
        model.setSnakeDirection(DirectionEnum.UP);
        model.moveSnake();

        positions = model.getSnakePosition();
        curLength = positions.size();

        // The snake length does not change as it does not eat anything.
        assertEquals(initLength, curLength);

        int[][] targetPos = { { 2, 1 }, { 2, 0 } };

        for (int i = 0; i < targetPos.length; i++) {
            assertEquals(positions.get(i).getXPos(), targetPos[i][0]);
            assertEquals(positions.get(i).getYPos(), targetPos[i][1]);
        }
    }

    @Test
    public void testSnakeGrow() {
        LinkedList<Posn> positions = model.getSnakePosition();
        int curLength = positions.size();
        assertEquals(initLength, curLength);

        // Snake moves right.
        model.moveSnake();

        // Snake moves down.
        model.setSnakeDirection(DirectionEnum.DOWN);
        model.moveSnake();

        // Snake moves down again.
        model.moveSnake();

        // Snake moves left.
        model.setSnakeDirection(DirectionEnum.LEFT);
        model.moveSnake();

        // Snake moves up.
        model.setSnakeDirection(DirectionEnum.UP);
        model.moveSnake();

        // Snake moves up again.
        model.moveSnake();

        // And again, snake moves up.
        model.moveSnake();

        positions = model.getSnakePosition();
        curLength = positions.size();
        assertEquals(initLength + 1, curLength);

        int[][] targetPos = { { 2, 3 }, { 2, 2 }, { 2, 1 } };

        for (int i = 0; i < targetPos.length; i++) {
            assertEquals(positions.get(i).getXPos(), targetPos[i][0]);
            assertEquals(positions.get(i).getYPos(), targetPos[i][1]);
        }

    }

    @Test
    public void testSnakeCrashTopWall() {
        // Snake moves right.
        model.moveSnake();

        // Snake moves up.
        model.setSnakeDirection(DirectionEnum.UP);
        model.moveSnake();

        // Snake moves up again and crash on the wall.
        model.moveSnake();

        assertTrue(model.isGameOver());
    }

    @Test
    public void testSnakeCrashBottomWall() {
        // Snake moves right.
        model.moveSnake();

        // Snake moves down.
        model.setSnakeDirection(DirectionEnum.DOWN);
        model.moveSnake();

        // Snake moves down.
        model.moveSnake();

        // Snake moves down again and crash on the wall.
        model.moveSnake();

        assertTrue(model.isGameOver());
    }

    @Test
    public void testSnakeCrashLeftWall() {
        // Snake moves right.
        model.moveSnake();

        // Snake moves right.
        model.moveSnake();

        // Snake moves down.
        model.moveSnake();

        // Snake moves left.
        model.setSnakeDirection(DirectionEnum.LEFT);
        model.moveSnake();

        // Snake moves left again.
        model.moveSnake();

        // And again, snake moves left.
        model.moveSnake();

        // Snake moves left again and crash on the wall.
        model.moveSnake();

        assertTrue(model.isGameOver());
    }

    @Test
    public void testSnakeCrashRightWall() {
        // Snake moves right.
        model.moveSnake();

        // Snake moves right again and crash on the wall.
        model.moveSnake();

        assertTrue(model.isGameOver());
    }

    @Test
    public void testSnakeCrashBody() {
        // Snake moves up.
        model.setSnakeDirection(DirectionEnum.UP);
        model.moveSnake();

        // Snake moves left.
        model.setSnakeDirection(DirectionEnum.LEFT);
        model.moveSnake();

        // Snake moves left again.
        model.moveSnake();

        // Snake moves down.
        model.setSnakeDirection(DirectionEnum.DOWN);
        model.moveSnake();

        // Snake moves down again.
        model.moveSnake();

        // Snake moves right.
        model.setSnakeDirection(DirectionEnum.RIGHT);
        model.moveSnake();

        // Snake moves right again
        model.moveSnake();

        // Snake moves up.
        model.setSnakeDirection(DirectionEnum.UP);
        model.moveSnake();

        // Snake moves left.
        model.setSnakeDirection(DirectionEnum.LEFT);
        model.moveSnake();

        // Snake moves down and crash on its body.
        model.setSnakeDirection(DirectionEnum.DOWN);
        model.moveSnake();

        assertTrue(model.isGameOver());
    }
}
