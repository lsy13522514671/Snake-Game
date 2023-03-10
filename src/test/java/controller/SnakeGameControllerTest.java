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

    /**
     * This method keeps programs waiting for a period of time.
     * 
     * @param duration duration of time that the program need to wait
     */
    private void waitInMS(int duration) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < duration) {
            continue;
        }
    }

    /**
     * This method starts the test controller for a period of time
     * and pause it.
     * 
     * @param duration duration of time that the controller runs
     */
    private void runControl(int duration) {
        control.start();
        waitInMS(duration);
        control.pause();
    }

    @BeforeEach
    void setup() {
        // this method initializes prerequsite objects for each test
        model = new MockSnakeGameModel(log);
        view = new MockSnakeGameView(log, model);
        control = new SnakeGameController(model);
        view.setControl(control);
        control.setView(view);
    }

    @Test
    public void testSnakeGameControllerInit() {
        // the controller is added as a observer of model
        String targetLog = "The model appended the observer of type class view.MockSnakeGameView.\n"
                + "The model appended the observer of type class controller.SnakeGameController.";
        assertEquals(targetLog, model.getLog());
    }

    @Test
    public void testSnakeGameControllerSetSnakeDirectionUp() {
        String targetLog = "The model appended the observer of type class view.MockSnakeGameView.\n"
                + "The model appended the observer of type class controller.SnakeGameController.\n" +
                "The model set the snake direction to UP.";

        // the snake direction is turned up
        control.setSnakeDirection(DirectionEnum.UP);
        assertEquals(targetLog, model.getLog());
    }

    @Test
    public void testSnakeGameControllerSetSnakeDirectionDown() {
        String targetLog = "The model appended the observer of type class view.MockSnakeGameView.\n"
                + "The model appended the observer of type class controller.SnakeGameController.\n" +
                "The model set the snake direction to DOWN.";

        // the snake direction is turned down
        control.setSnakeDirection(DirectionEnum.DOWN);
        assertEquals(targetLog, model.getLog());
    }

    @Test
    public void testSnakeGameControllerSetSnakeDirectionLeft() {
        String targetLog = "The model appended the observer of type class view.MockSnakeGameView.\n"
                + "The model appended the observer of type class controller.SnakeGameController.\n" +
                "The model set the snake direction to LEFT.";

        // the snake direction is turned left
        control.setSnakeDirection(DirectionEnum.LEFT);
        assertEquals(targetLog, model.getLog());
    }

    @Test
    public void testSnakeGameControllerSetSnakeDirectionRight() {
        String targetLog = "The model appended the observer of type class view.MockSnakeGameView.\n"
                + "The model appended the observer of type class controller.SnakeGameController.\n" +
                "The model set the snake direction to RIGHT.";

        // the snake direction is turned right
        control.setSnakeDirection(DirectionEnum.RIGHT);
        assertEquals(targetLog, model.getLog());
    }

    @Test
    public void testSnakeGameControllerSetSnakeDirectionMix() {
        String targetLog = "The model appended the observer of type class view.MockSnakeGameView.\n"
                + "The model appended the observer of type class controller.SnakeGameController.\n" +
                "The model set the snake direction to UP.\n" +
                "The model set the snake direction to DOWN.\n" +
                "The model set the snake direction to LEFT.\n" +
                "The model set the snake direction to RIGHT.";

        // the snake direction is turned up
        control.setSnakeDirection(DirectionEnum.UP);
        // the snake direction is turned down
        control.setSnakeDirection(DirectionEnum.DOWN);
        // the snake direction is turned left
        control.setSnakeDirection(DirectionEnum.LEFT);
        // the snake direction is turned right
        control.setSnakeDirection(DirectionEnum.RIGHT);
        assertEquals(targetLog, model.getLog());
    }

    @Test
    public void testSnakeGameControllerStartAndPauseForThreeSeconds() {
        String targetLog = "The model appended the observer of type class view.MockSnakeGameView.\n"
                + "The model appended the observer of type class controller.SnakeGameController.\n" +
                "The model verified that the game was not over. The game continued.\n" +
                "The model moved the snake one step.\n" +
                "The view painted the current game screen.\n" +
                "The model verified that the game was not over. The game continued.\n" +
                "The model moved the snake one step.\n" +
                "The view painted the current game screen.\n" +
                "The model verified that the game was not over. The game continued.\n" +
                "The model moved the snake one step.\n" +
                "The view painted the current game screen.";

        // controller runs for three seconds so that the snake moves three steps
        runControl(3000);
        assertEquals(targetLog, model.getLog());
    }

    @Test
    public void testSnakeGameControllerStartAndPauseForFiveSeconds() {
        String targetLog = "The model appended the observer of type class view.MockSnakeGameView.\n"
                + "The model appended the observer of type class controller.SnakeGameController.\n" +
                "The model verified that the game was not over. The game continued.\n" +
                "The model moved the snake one step.\n" +
                "The view painted the current game screen.\n" +
                "The model verified that the game was not over. The game continued.\n" +
                "The model moved the snake one step.\n" +
                "The view painted the current game screen.\n" +
                "The model verified that the game was not over. The game continued.\n" +
                "The model moved the snake one step.\n" +
                "The view painted the current game screen.\n" +
                "The model verified that the game was not over. The game continued.\n" +
                "The model moved the snake one step.\n" +
                "The view painted the current game screen.\n" +
                "The model verified that the game was not over. The game continued.\n" +
                "The model moved the snake one step.\n" +
                "The view painted the current game screen.";

        // controller runs for five seconds so that the snake moves five steps
        runControl(5000);
        assertEquals(targetLog, model.getLog());
    }

    @Test
    public void testSnakeGameControllerSetDirectionInMove() {
        String targetLog = "The model appended the observer of type class view.MockSnakeGameView.\n"
                + "The model appended the observer of type class controller.SnakeGameController.\n" +
                "The model verified that the game was not over. The game continued.\n" +
                "The model moved the snake one step.\n" +
                "The view painted the current game screen.\n" +
                "The model verified that the game was not over. The game continued.\n" +
                "The model moved the snake one step.\n" +
                "The view painted the current game screen.\n" +
                "The model set the snake direction to LEFT.\n" +
                "The model verified that the game was not over. The game continued.\n" +
                "The model moved the snake one step.\n" +
                "The view painted the current game screen.\n" +
                "The model set the snake direction to RIGHT.\n" +
                "The model verified that the game was not over. The game continued.\n" +
                "The model moved the snake one step.\n" +
                "The view painted the current game screen.";

        // controller runs for two seconds so that the snake moves two steps
        control.start();
        waitInMS(2000);

        // controller turns the snake direction to left
        control.setSnakeDirection(DirectionEnum.LEFT);

        // controller runs for a second so that the snake moves one step
        waitInMS(1000);

        // controller turns the snake direction to right
        control.setSnakeDirection(DirectionEnum.RIGHT);

        // controller runs for a second so that the snake moves one step
        waitInMS(1000);

        control.pause();

        assertEquals(targetLog, model.getLog());
    }

    @Test
    public void testSnakeGameControllerGameOverUpdate() {
        String targetLog = "The model appended the observer of type class view.MockSnakeGameView.\n"
                + "The model appended the observer of type class controller.SnakeGameController.\n" +
                "The model verified that the game was not over. The game continued.\n" +
                "The model moved the snake one step.\n" +
                "The view painted the current game screen.\n" +
                "The model verified that the game was not over. The game continued.\n" +
                "The model moved the snake one step.\n" +
                "The view painted the current game screen.\n" +
                "The model verified that the game was not over. The game continued.\n" +
                "The model moved the snake one step.\n" +
                "The view painted the current game screen.\n" +
                "The model verified that the game was over.\n" +
                "The model notified its observer(s) that the game was over.\n" +
                "The view painted the game over screen.";

        // controller runs for three seconds so that the snake moves three steps
        runControl(3000);

        // controller terminates the game
        model.setGameOver(true);

        // this test deliberately moves a snake as the game status is checked while the
        // snake is moving
        model.moveSnake();
        assertEquals(targetLog, model.getLog());
    }
}
