package controller;

import gameUtils.DirectionEnum;
import gameUtils.IGameObserver;

public interface IGameController extends IGameObserver {

    public void setSnakeDirection(DirectionEnum direction);

    public void start();

    public void pause();

    public void recover();
}
