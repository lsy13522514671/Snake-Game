package controller;

import gameUtils.DirectionEnum;
import gameUtils.IGameTimer;
import model.IGameModel;
import model.MockSnakeGameModel;
import view.IGameView;
import view.SnakeGameView;

import java.awt.Window;
import java.util.Random;
import java.util.TimerTask;

public class SnakeGameController implements IGameController{
    private IGameModel model;
    private IGameView view;
    private IGameTimer gameTimer;
    private TimerTask task;
    private int frequency;

    public SnakeGameController(IGameModel model, IGameTimer gameTimer) {
        this.model = model;
        this.view = new SnakeGameView(model, this);
        // ((Window) view).setVisible(true);
        this.model.attach(this);
        this.model.attach(this.view);
        this.task = new TimerTask() {
            @Override
            public void run() {
                model.moveSnake();
                // view.paint();
                System.out.println("display round: ");
                model.printMap();
                System.out.println("\n");
            }
        };
        this.gameTimer = gameTimer;
    }

    public SnakeGameController(IGameModel model, SnakeGameTimer gameTimer, int frequency) {
        this.model = model;
        this.view = new SnakeGameView(model, this);
        this.frequency = frequency;
        // ((Window) view).setVisible(true);
        this.model.attach(this);
        this.model.attach(this.view);
        this.gameTimer = gameTimer;
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
        gameTimer = new SnakeGameTimer(frequency, task);
    }

    @Override
    public void update() {
        pause();
    }

    public IGameView getView() {
        return view;
    }

    public static void main(String[] args) {
        MockSnakeGameModel model = new MockSnakeGameModel();
        SnakeGameTimer timer =  new SnakeGameTimer(model, view, frequency);
        SnakeGameController controller = new SnakeGameController(model);

        controller.start();

        System.out.println(model.printLog());
    }
    
}
