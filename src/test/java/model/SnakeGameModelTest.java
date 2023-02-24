package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import gameUtils.DirectionEnum;
import model.utils.Position;

public class SnakeGameModelTest {
    @Test
    public void testSnakeGameModelInitException() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new SnakeGameModel(3, 4),
                "Expected the model to complain about the board size input, but it didn't");

        assertTrue(thrown.getMessage().contentEquals("Illegal Board Size!"));
    }

    @Test
    public void testSnakeGameModelInit() {
        int rowNum = 4;
        int colNum = 4;

        SnakeGameModel model = new SnakeGameModel(rowNum, colNum);
        assertEquals(model.getRowNum(), rowNum);
        assertEquals(model.getColNum(), colNum);

        // This test
        Position applePos = model.getApplePos();
        assertTrue((applePos.getXPos() >= 0) && (applePos.getXPos() < colNum));
        assertTrue((applePos.getYPos() >= 0) && (applePos.getYPos() < rowNum));

        Snake snake = model.getSnake();
        LinkedList<Position> positions = snake.getBodyPartPos();

        int[][] targetPos = { { 2, 2 }, { 1, 2 } };

        for (int i = 0; i < targetPos.length; i++) {
            assertEquals(positions.get(i).getXPos(), targetPos[i][0]);
            assertEquals(positions.get(i).getYPos(), targetPos[i][1]);
        }

        char[][] gameMap = model.getMap();
        int appleNum = countMap(gameMap, 'A');
        int snkBodyNum = countMap(gameMap, 'S');
        assertEquals(appleNum, 1);
        assertEquals(snkBodyNum, 2);
    }

    @Test
    public void testSnakeMove() {
        int rowNum = 4;
        int colNum = 4;

        SnakeGameModel model = new SnakeGameModel(rowNum, colNum);
        model.setApple(0, 0);
        model.updateMap();

        Snake snake = model.getSnake();
        int initLength = snake.getCurLength();
        assertEquals(initLength, 2);

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

        snake = model.getSnake();
        int curLength = snake.getCurLength();
        // The snake length does not change as it does not eat anything.
        assertEquals(curLength, initLength);

        int[][] targetPos = { { 2, 1 }, { 2, 0 } };
        LinkedList<Position> positions = snake.getBodyPartPos();

        for (int i = 0; i < targetPos.length; i++) {
            assertEquals(positions.get(i).getXPos(), targetPos[i][0]);
            assertEquals(positions.get(i).getYPos(), targetPos[i][1]);
        }

        char[][] gameMap = model.getMap();
        int appleNum = countMap(gameMap, 'A');
        int snkBodyNum = countMap(gameMap, 'S');
        assertEquals(appleNum, 1);
        assertEquals(snkBodyNum, 2);
    }

    @Test
    public void testSnakeGrow() {
        int rowNum = 4;
        int colNum = 4;

        SnakeGameModel model = new SnakeGameModel(rowNum, colNum);
        model.setApple(1, 0);
        Position initApplePos = new Position(1, 0);
        model.updateMap();

        Snake snake = model.getSnake();
        int initLength = snake.getCurLength();
        assertEquals(initLength, 2);

        // Snake moves down.
        model.setSnakeDirection(DirectionEnum.DOWN);
        model.moveSnake();

        // Snake moves down again.
        model.moveSnake();

        // Snake moves left.
        model.setSnakeDirection(DirectionEnum.LEFT);
        model.moveSnake();

        snake = model.getSnake();
        int curLength = snake.getCurLength();
        // The snake grows as it ate an apple.
        assertEquals(curLength, initLength + 1);

        int[][] targetPos = { { 1, 0 }, { 2, 0 }, { 2, 1 } };
        LinkedList<Position> positions = snake.getBodyPartPos();

        for (int i = 0; i < targetPos.length; i++) {
            assertEquals(positions.get(i).getXPos(), targetPos[i][0]);
            assertEquals(positions.get(i).getYPos(), targetPos[i][1]);
        }

        Position applePos = model.getApplePos();
        assertTrue(!applePos.equals(initApplePos));

        assertTrue((applePos.getXPos() >= 0) && (applePos.getXPos() < colNum));
        assertTrue((applePos.getYPos() >= 0) && (applePos.getYPos() < rowNum));

        char[][] gameMap = model.getMap();
        int appleNum = countMap(gameMap, 'A');
        int snkBodyNum = countMap(gameMap, 'S');
        assertEquals(appleNum, 1);
        assertEquals(snkBodyNum, 3);
    }

    @Test
    public void testSnakeCrashWall() {
        int rowNum = 4;
        int colNum = 4;

        SnakeGameModel model = new SnakeGameModel(rowNum, colNum);
        model.setApple(0, 0);
        model.updateMap();

        // Snake moves right.
        model.moveSnake();

        // Snake moves right again and crash on the wall.
        model.moveSnake();

        assertTrue(model.isGameOver());
    }

    @Test
    public void testSnakeCrashBody() {
        int rowNum = 4;
        int colNum = 4;

        SnakeGameModel model = new SnakeGameModel(rowNum, colNum);
        // The test sets an apple to the right of snake head.
        model.setApple(3, 2);
        model.updateMap();

        // Snake moves right and eat the apple.
        model.moveSnake();

        // The test sets an apple below snake head.
        model.setApple(3, 1);
        model.updateMap();

        // Snake moves down and eat the apple.
        model.setSnakeDirection(DirectionEnum.DOWN);
        model.moveSnake();

        // The test sets an apple to the left of snake head.
        model.setApple(2, 1);
        model.updateMap();

        // Snake moves left and eat the apple.
        model.setSnakeDirection(DirectionEnum.LEFT);
        model.moveSnake();

        // Snake moves up and crash on its body.
        model.setSnakeDirection(DirectionEnum.UP);
        model.moveSnake();

        assertTrue(model.isGameOver());
    }

    private int countMap(char[][] gameMap, char content) {
        int count = 0;

        for (int i = 3; i >= 0; i--) {
            for (int j = 0; j < 4; j++) {
                if (gameMap[i][j] == content) {
                    count++;
                }
            }
        }

        return count;
    }
}
