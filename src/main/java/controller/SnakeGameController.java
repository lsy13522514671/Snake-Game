package controller;

import gameUtils.DirectionEnum;
import model.ISnakeGameModel;
import view.ISnakeGameView;

public class SnakeGameController implements ISnakeGameController{
    private ISnakeGameModel model;
    private ISnakeGameView view;
    private SnakeGameTimer gameTimer;
    private int period;

    public SnakeGameController(ISnakeGameModel model) {
        this.model = model;
        this.model.attach(this);
        this.period = 1000;
    }

    public SnakeGameController(ISnakeGameModel model, int period) {
        this.model = model;
        this.model.attach(this);
        this.period = period;
    }

    @Override
    public void setSnakeDirection(DirectionEnum direction) {
        model.setSnakeDirection(direction);
    }
    
    @Override
    public void start(int frequency) {
        gameTimer.run(frequency);
    }

    @Override
    public void pause() {
        gameTimer.destroy();
    }

    @Override
    public void recover() {
        this.gameTimer = new SnakeGameTimer(model, view);
        start(period);
    }

    @Override
    public void gameOverUpdate() {
        pause();
        view.paintEndFrame();
    }

    public int getPeriod() {
        return period;
    }

    public void setView(ISnakeGameView view) {
        this.view = view;
        this.gameTimer = new SnakeGameTimer(model, view);
    }
}
