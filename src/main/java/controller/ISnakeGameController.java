package controller;

import gameUtils.IGameObserver;

public interface ISnakeGameController extends IGameObserver {
    public void start();

    public void pause();

    public void recover();
}
