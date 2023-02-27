package controller;

import model.MockSnakeGameModel;
import view.SnakeGameView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

public class SnakeGameControllerTest {
    public static MockSnakeGameModel model = null;
    public static SnakeGameController controller = null;

    @BeforeAll
    public static void setup() {
        model = new MockSnakeGameModel();
        controller = new SnakeGameController(model);
    }

    @Test
    public void testSnakeGameControllerInit() {
        String targetLog = "model appended the observer of type class controller.SnakeGameController\n"
                + "model appended the observer of type class view.SnakeGameView";

        assertEquals(targetLog, SnakeGameControllerTest.model.printLog());
    }
}
