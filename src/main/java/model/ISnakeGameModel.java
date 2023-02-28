package model;

import gameUtils.DirectionEnum;
import gameUtils.ISnakeGameOverserable;

public interface ISnakeGameModel extends ISnakeGameOverserable{
    public void setSnakeDirection(DirectionEnum direction);

    public void moveSnake();

    public boolean isGameOver();
}