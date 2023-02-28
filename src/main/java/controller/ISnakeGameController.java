package controller;

import gameUtils.DirectionEnum;
import gameUtils.ISnakeGameObserver;

/**
 * The ISnakeGameController interface represents a snake game controller
 * standard.
 */
public interface ISnakeGameController extends ISnakeGameObserver {
    /**
     * This method is used to turn snake direction in the model.
     * 
     * @param direction target direction
     */
    public void setSnakeDirection(DirectionEnum direction);

    /**
     * This method is used to start a timer to play the game. The controller moves
     * the snake in a period of time.
     * 
     * @param frequency timer frequency controlling the snake movement speed
     */
    public void start(int frequency);

    /**
     * This method pauses the game timer. It stops the controller from moving the
     * snake.
     */
    public void pause();

    /**
     * This method recovers the game timer. The controller uses this method to
     * continue the snake game from previous status.
     */
    public void recover();
}
