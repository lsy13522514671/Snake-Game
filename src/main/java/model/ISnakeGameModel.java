package model;

import gameUtils.DirectionEnum;
import gameUtils.ISnakeGameObservable;

/**
 * This ISnakeGameModel interface represents a snake game model standard.
 */
public interface ISnakeGameModel extends ISnakeGameObservable {
    /**
     * This method turns the snake to a valid direction. If the direction is
     * invalid, it does nothing.
     * 
     * @param direction target direciton
     */
    public void setSnakeDirection(DirectionEnum direction);

    /**
     * This method moves a snake one step along its current direction in the model.
     */
    public void moveSnake();

    /**
     * This method checks whether the game is over. By definition, the game is over
     * if the snake crashes on the wall or its own body.
     * 
     * @return a boolean indicating whether the game is over or not
     */
    public boolean isGameOver();
}