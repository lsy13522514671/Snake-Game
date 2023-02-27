package controller;

import java.util.Timer;
import java.util.TimerTask;

import gameUtils.IGameTimer;

class SnakeGameTimer implements IGameTimer {
    private Timer gameTimer;
    private TimerTask timerTask;

    SnakeGameTimer(int frequency, TimerTask timerTask) {
        this.gameTimer = new Timer();
        this.timerTask = timerTask;
    }

    @Override
    public void run(int frequency) {
        gameTimer.schedule(timerTask, 0, frequency);
    }

    @Override
    public void destroy() {
        timerTask.cancel();
        gameTimer.cancel();
        gameTimer.purge();
    }
}
