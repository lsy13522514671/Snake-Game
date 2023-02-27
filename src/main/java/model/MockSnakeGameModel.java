package model;

import gameUtils.DirectionEnum;
import gameUtils.IGameObserver;

public class MockSnakeGameModel implements IGameModel {
    StringBuilder log;

    public MockSnakeGameModel() {
        this.log = new StringBuilder();
    }

    @Override
    public void attach(IGameObserver observer) {
        log.append("model appended the observer of type " + observer.getClass().toString() + "\n");
    }

    @Override
    public void detach(IGameObserver observer) {
        log.append("model removed the observer of type " + observer.getClass().toString() + "\n");
    }

    @Override
    public void notifyGameOver() {
        log.append("model notified the game is over\n");
    }

    @Override
    public void setSnakeDirection(DirectionEnum direction) {
        log.append("model set the snake direction to " + direction.toString()+ "\n");
    }

    @Override
    public void moveSnake() {
        log.append("model moved the snake one step \n");
    }

    @Override
    public boolean isGameOver() {
        log.append("model checked whether the game is over or not!");
        return false;
    }

    @Override
    public void printMap() {
        log.append("model printed the game map!");
    }

    public String printLog() {
        String logContent = log.toString();
        return logContent.stripTrailing();
    }
}
