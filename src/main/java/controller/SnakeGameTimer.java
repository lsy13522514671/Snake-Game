package controller;

import java.util.Timer;
import java.util.TimerTask;

import model.ISnakeGameModel;
import view.ISnakeGameView;

class SnakeGameTimer {
    private Timer gameTimer;
    private TimerTask timerTask;

    SnakeGameTimer(ISnakeGameModel model, ISnakeGameView view) {
        this.gameTimer = new Timer();
        this.timerTask = new TimerTask() {
            @Override
            public void run() {
                model.moveSnake();
                // view.paint();
                // System.out.println("display round: ");
                // model.printMap();
                // System.out.println("\n");
                view.paint();
            }
        };
    }

    public void run(int period) {
        gameTimer.schedule(timerTask, 0, period);
    }

    public void destroy() {
        timerTask.cancel();
        gameTimer.cancel();
        gameTimer.purge();
    }
}
