package controller;

import java.util.Timer;
import java.util.TimerTask;

import gameUtils.IGameTimer;
import model.IGameModel;
import view.IGameView;

public class SnakeGameTimer implements IGameTimer {
    private Timer gameTimer = new Timer();
    private TimerTask timerTask;
    private IGameModel model;
    private IGameView view;

    public SnakeGameTimer(IGameModel model, IGameView view) {
        this.model = model;
        this.view = view;
        initTimer();
    }

    @Override
    public void initTimer() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                model.moveSnake();
                // view.paint();
                System.out.println("display round: ");
                model.printMap();
                System.out.println("");
            }
        };
    }

    @Override
    public void run() {
        gameTimer.schedule(timerTask, 0, 1000);
    }

    @Override
    public void destroy() {
        System.out.println("timer gets destroyed!");
        timerTask.cancel();
        gameTimer.cancel();
        gameTimer.purge();
    }

}
