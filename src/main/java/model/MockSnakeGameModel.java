package model;

import java.util.ArrayList;

import gameUtils.DirectionEnum;
import gameUtils.ISnakeGameObserver;

public class MockSnakeGameModel implements ISnakeGameModel {
    private StringBuilder log;
    private boolean gameOver;
    private ArrayList<ISnakeGameObserver> observers;

    public MockSnakeGameModel(StringBuilder log) {
        this.log = log;
        this.gameOver = false;
        this.observers = new ArrayList<>();
    }

    @Override
    public void attach(ISnakeGameObserver observer) {
        log.append("The model appended the observer of type " + observer.getClass().toString() + ".\n");
        observers.add(observer);
    }

    @Override
    public void detach(ISnakeGameObserver observer) {
        log.append("The model removed the observer of type " + observer.getClass().toString() + ".\n");
        observers.remove(observer);
    }

    @Override
    public void gameOverNotify() {
        log.append("The model notified its observer(s) that the game was over.\n");
        for (ISnakeGameObserver observer : observers) {
            observer.gameOverUpdate();
        }
    }

    @Override
    public void setSnakeDirection(DirectionEnum direction) {
        log.append("The model set the snake direction to " + direction.toString()+ ".\n");
    }

    @Override
    public void moveSnake() {
        if(isGameOver()) {
            gameOverNotify();
        } else {
            log.append("The model moved the snake one step.\n");
        }

    }

    @Override
    public boolean isGameOver() {
        if(gameOver) {
            log.append("The model verified that the game was over.\n");
        } else {
            log.append("The model verified that the game was not over. The game continued.\n");
        }
        return gameOver;
    }


    public String getLog() {
        String logContent = log.toString();
        return logContent.stripTrailing();
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
