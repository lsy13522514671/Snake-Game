package controller;

import java.util.Timer;
import java.util.TimerTask;

import model.ISnakeGameModel;
import view.ISnakeGameView;

/**
 * This SnakeGameTimer class implements a timer that interacts with the model
 * and the view. The controller use it to call model and view in a period of
 * time. Each time, it asks model to move a snake in its current direction,
 * and view to display the screen.
 * 
 */
class SnakeGameTimer {
    private Timer gameTimer; // game timer
    private TimerTask timerTask; // timer task used by the timer

    SnakeGameTimer(ISnakeGameModel model, ISnakeGameView view) {
        this.gameTimer = new Timer();
        this.timerTask = new TimerTask() {
            @Override
            public void run() {
                model.moveSnake();
                view.paintGameFrame();
            }
        };
    }

    /**
     * This method keeps executing the timer to perform the snake game timer task in
     * a specified period of time.
     * 
     * @param period period to perform the snake game timer task
     */
    public void run(int period) {
        gameTimer.schedule(timerTask, 0, period);
    }

    /**
     * This method destroys the game timer, which in turn pauses the controller.
     */
    public void destroy() {
        timerTask.cancel();
        gameTimer.cancel();
        gameTimer.purge();
    }
}
