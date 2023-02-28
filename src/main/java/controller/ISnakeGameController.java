package controller;

import gameUtils.DirectionEnum;
import gameUtils.ISnakeGameObserver;

public interface ISnakeGameController extends ISnakeGameObserver {

    public void setSnakeDirection(DirectionEnum direction);

    public void start(int frequency);

    public void pause();

    public void recover();
}
