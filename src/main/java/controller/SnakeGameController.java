package controller;

import gameUtils.DirectionEnum;
import model.IGameModel;
import model.MockSnakeGameModel;
import model.SnakeGameModel;
import view.IGameView;
import view.SnakeGameView;

import java.awt.Window;
import java.util.Random;

public class SnakeGameController implements IGameController{
    private IGameModel model;
    private IGameView view;
    private SnakeGameTimer gameTimer;

    public SnakeGameController(IGameModel model) {
        this.model = model;
        this.view = new SnakeGameView(model, this);
        // ((Window) view).setVisible(true);
        this.model.attach(this);
        this.model.attach(view);
        this.gameTimer = generateTimer(model, view);
    }

    private SnakeGameTimer generateTimer(IGameModel model, IGameView view) {
        return new SnakeGameTimer(model, view);
    }

    @Override
    public void setSnakeDirection(DirectionEnum direction) {
        model.setSnakeDirection(direction);
    }
    
    @Override
    public void start() {
        gameTimer.run();
    }

    @Override
    public void pause() {
        gameTimer.destroy();
    }

    @Override
    public void recover() {
        gameTimer = generateTimer(model, view);
    }

    @Override
    public void update() {
        pause();
    }

    public static void main(String[] args) {
        MockSnakeGameModel model = new MockSnakeGameModel();
        SnakeGameController controller = new SnakeGameController(model);

        controller.start();

        System.out.println(model.printLog());
    }
    
}
