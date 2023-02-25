package controller;

import gameUtils.DirectionEnum;
import model.ISnakeGameModel;
import model.SnakeGameModel;
import view.ISnakeGameView;
import view.SnakeGameView;

import java.awt.Window;
import java.util.Random;

public class SnakeGameController implements ISnakeGameController{
    private ISnakeGameModel model;
    private ISnakeGameView view;
    private SnakeGameTimer gameTimer;

    public SnakeGameController() {
        model = new SnakeGameModel(4, 4, new Random(0));
        view = new SnakeGameView(model, this);
        // ((Window) view).setVisible(true);
        model.attach(this);
        model.attach(view);
        gameTimer = generateTimer(model, view);
    }

    private SnakeGameTimer generateTimer(ISnakeGameModel model, ISnakeGameView view) {
        return new SnakeGameTimer(model, view);
    }

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

        SnakeGameController controller = new SnakeGameController();

        controller.start();
    }
    
}
