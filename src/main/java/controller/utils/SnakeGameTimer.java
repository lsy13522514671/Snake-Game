package controller.utils;

import java.util.Timer;
import java.util.TimerTask;

import model.ISnakeGameModel;
import view.ISnakeGameView;

public class SnakeGameTimer {
    private Timer gameTimer = new Timer();
    private TimerTask timerTask;
    private ISnakeGameModel model;
    private ISnakeGameView view;

    public SnakeGameTimer(ISnakeGameModel model, ISnakeGameView view) {
        this.model = model;
        this.view = view;
        initTimerTask();
    }

    private void initTimerTask() {
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

    public void run() {
        gameTimer.schedule(timerTask, 0, 1000);
    }

    public void destroy() {
        System.out.println("timer gets destroyed!");
        timerTask.cancel();
        gameTimer.cancel();
        gameTimer.purge();
    }

}
