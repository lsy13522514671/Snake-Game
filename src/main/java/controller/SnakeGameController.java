package controller;

import gameUtils.DirectionEnum;
import model.ISnakeGameModel;
import view.ISnakeGameView;

/**
 * The SnakeGameController class implements the snake game controller. It
 * controls the game. The controller uses a timer to interact with the game
 * model and view under the hood. It moves a snake and repaints the game screen
 * in a specified period of time. It stores a game model, a game view, a game
 * timer, and a timer period.
 */
public class SnakeGameController implements ISnakeGameController {
    private ISnakeGameModel model; // snake game model
    private ISnakeGameView view; // snake game view
    private SnakeGameTimer gameTimer; // snake game timer
    // a period of time in miliseconds which is used to control how fast the
    // controller will interact with the model and view
    private int period;

    public SnakeGameController(ISnakeGameModel model) {
        this.model = model;
        this.model.attach(this);
        this.period = 1000; // by default, it is set to 1000
    }

    public SnakeGameController(ISnakeGameModel model, int period) {
        this.model = model;
        this.model.attach(this);
        this.period = period;
    }

    /**
     * This method gets the game timer frequency.
     * 
     * @return timer frequency
     */
    public int getPeriod() {
        return period;
    }

    /**
     * This method set the view used by this controller. Once set, a snake game
     * timer is initilized.
     * 
     * @param view
     */
    public void setView(ISnakeGameView view) {
        this.view = view;
    }

    /**
     * This method sets the timer frequency.
     * 
     * @param period target timer frequency
     */
    public void setPeriod(int period) {
        this.period = period;
    }

    /**
     * This method is being called while the game is over. This controller stops
     * invoking the model and asks its view to paint the game over screen.
     * 
     */
    @Override
    public void gameOverUpdate() {
        pause();
    }

    @Override
    public void setSnakeDirection(DirectionEnum direction) {
        model.setSnakeDirection(direction);
    }

    @Override
    public void start() {
        gameTimer = new SnakeGameTimer(model, view);
        gameTimer.run(period);
    }

    @Override
    public void pause() {
        gameTimer.destroy();
    }
}
