package controller;

import model.MockSnakeGameModel;
import view.MockSnakeGameView;
import view.SnakeGameView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import gameUtils.DirectionEnum;

import org.junit.jupiter.api.BeforeEach;

public class SnakeGameControllerTest {
    private StringBuilder log = new StringBuilder();
    private MockSnakeGameModel model = null;
    private SnakeGameController control = null;
    private MockSnakeGameView view = null;

    @BeforeEach
    void setup() {
        model = new MockSnakeGameModel(log);
        view = new MockSnakeGameView(log);
        control = new SnakeGameController(model);
        view.setControl(control);
        control.setView(view);
    }

    @Test
    public void testSnakeGameControllerInit() {
        String targetLog = "The model appended the observer of type class controller.SnakeGameController.";
        assertEquals(targetLog, model.getLog());
    }

    @Test
    public void testSnakeGameControllerSetSnakeDirectionUp() {
        String targetLog = "The model appended the observer of type class controller.SnakeGameController.\n" +
        "The model set the snake direction to UP.";

        control.setSnakeDirection(DirectionEnum.UP);
        assertEquals(targetLog, model.getLog());
    }

    @Test
    public void testSnakeGameControllerSetSnakeDirectionDown() {
        String targetLog = "The model appended the observer of type class controller.SnakeGameController.\n" +
        "The model set the snake direction to DOWN.";

        control.setSnakeDirection(DirectionEnum.DOWN);
        assertEquals(targetLog, model.getLog());
    }

    @Test
    public void testSnakeGameControllerSetSnakeDirectionLeft() {
        String targetLog = "The model appended the observer of type class controller.SnakeGameController.\n" +
        "The model set the snake direction to LEFT.";

        control.setSnakeDirection(DirectionEnum.LEFT);
        assertEquals(targetLog, model.getLog());
    }

    @Test
    public void testSnakeGameControllerSetSnakeDirectionRight() {
        String targetLog = "The model appended the observer of type class controller.SnakeGameController.\n" +
        "The model set the snake direction to RIGHT.";

        control.setSnakeDirection(DirectionEnum.RIGHT);
        assertEquals(targetLog, model.getLog());
    }

    @Test
    public void testSnakeGameControllerSetSnakeDirectionMix() {
        String targetLog = "The model appended the observer of type class controller.SnakeGameController.\n" +
        "The model set the snake direction to UP.\n"+
        "The model set the snake direction to DOWN.\n"+
        "The model set the snake direction to LEFT.\n"+
        "The model set the snake direction to RIGHT.";

        control.setSnakeDirection(DirectionEnum.UP);
        control.setSnakeDirection(DirectionEnum.DOWN);
        control.setSnakeDirection(DirectionEnum.LEFT);
        control.setSnakeDirection(DirectionEnum.RIGHT);
        assertEquals(targetLog, model.getLog());
    }

    @Test
    public void testSnakeGameControllerStartAndPauseForThreeSeconds() {
        String targetLog = "The model appended the observer of type class controller.SnakeGameController.\n" +
        "The model verified that the game was not over. The game continued.\n" +
        "The model moved the snake one step.\n" +
        "The view painted the current game screen.\n"+
        "The model verified that the game was not over. The game continued.\n" +
        "The model moved the snake one step.\n" +
        "The view painted the current game screen.\n"+
        "The model verified that the game was not over. The game continued.\n" +
        "The model moved the snake one step.\n"+
        "The view painted the current game screen.";

        runControl(3000);
        assertEquals(targetLog, model.getLog());
    }

    @Test
    public void testSnakeGameControllerStartAndPauseForFiveSeconds() {
        String targetLog = "The model appended the observer of type class controller.SnakeGameController.\n" +
        "The model verified that the game was not over. The game continued.\n" +
        "The model moved the snake one step.\n" +
        "The view painted the current game screen.\n"+
        "The model verified that the game was not over. The game continued.\n" +
        "The model moved the snake one step.\n" +
        "The view painted the current game screen.\n"+
        "The model verified that the game was not over. The game continued.\n" +
        "The model moved the snake one step.\n"+
        "The view painted the current game screen.\n"+
        "The model verified that the game was not over. The game continued.\n" +
        "The model moved the snake one step.\n"+
        "The view painted the current game screen.\n"+
        "The model verified that the game was not over. The game continued.\n" +
        "The model moved the snake one step.\n"+
        "The view painted the current game screen.";

        runControl(5000);

        assertEquals(targetLog, model.getLog());
    }

    @Test
    public void testSnakeGameControllerSetDirectionInMove() {
        String targetLog = "The model appended the observer of type class controller.SnakeGameController.\n" +
        "The model verified that the game was not over. The game continued.\n" +
        "The model moved the snake one step.\n" +
        "The view painted the current game screen.\n"+
        "The model verified that the game was not over. The game continued.\n" +
        "The model moved the snake one step.\n" +
        "The view painted the current game screen.\n"+
        "The model set the snake direction to LEFT.\n"+
        "The model verified that the game was not over. The game continued.\n" +
        "The model moved the snake one step.\n" +
        "The view painted the current game screen.\n"+
        "The model set the snake direction to RIGHT.\n"+
        "The model verified that the game was not over. The game continued.\n" +
        "The model moved the snake one step.\n" +
        "The view painted the current game screen.";


        control.start(1000);
        waitInMS(2000);
        control.setSnakeDirection(DirectionEnum.LEFT);
        waitInMS(1000);
        control.setSnakeDirection(DirectionEnum.RIGHT);
        waitInMS(1000);
        control.pause();

        assertEquals(targetLog, model.getLog());
    }

    @Test
    public void testSnakeGameControllerRecover() {
        String targetLog = "The model appended the observer of type class controller.SnakeGameController.\n" +
        "The model verified that the game was not over. The game continued.\n" +
        "The model moved the snake one step.\n" +
        "The view painted the current game screen.\n"+
        "The model verified that the game was not over. The game continued.\n" +
        "The model moved the snake one step.\n" +
        "The view painted the current game screen.\n"+
        "The model verified that the game was not over. The game continued.\n" +
        "The model moved the snake one step.\n"+
        "The view painted the current game screen.";

        runControl(2000);

        int initPeriod = control.getPeriod();

        long start = System.currentTimeMillis();
        control.recover();
        int curPeriod = control.getPeriod();
        assertEquals(initPeriod, curPeriod);
        waitInMS(1000);
        control.pause();

        assertEquals(targetLog, model.getLog());
    }

    @Test
    public void testSnakeGameControllerGameOverUpdate() {
        String targetLog = "The model appended the observer of type class controller.SnakeGameController.\n" +
        "The model verified that the game was not over. The game continued.\n" +
        "The model moved the snake one step.\n" +
        "The view painted the current game screen.\n"+
        "The model verified that the game was not over. The game continued.\n" +
        "The model moved the snake one step.\n" +
        "The view painted the current game screen.\n"+
        "The model verified that the game was not over. The game continued.\n" +
        "The model moved the snake one step.\n"+
        "The view painted the current game screen.\n"+
        "The model verified that the game was over.\n"+
        "The model notified its observer(s) that the game was over.\n"+
        "The view painted the game over screen.";

        runControl(3000);
        model.setGameOver(true);
        model.moveSnake();
        assertEquals(targetLog, model.getLog());
    }

    private void waitInMS(int duration) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start <= duration) {
            continue;
        }
    }

    private void runControl(int duration) {
        control.start(control.getPeriod());
        waitInMS(duration);
        control.pause();
    }
}
