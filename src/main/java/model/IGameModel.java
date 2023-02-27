package model;

import gameUtils.DirectionEnum;
import gameUtils.IGameOverserable;

public interface IGameModel extends IGameOverserable{
    public void setSnakeDirection(DirectionEnum direction);

    public void moveSnake();

    public boolean isGameOver();

    public void printMap();
}