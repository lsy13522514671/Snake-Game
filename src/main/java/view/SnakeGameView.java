package view;

import javax.swing.JFrame;

import controller.IGameController;
import model.IGameModel;

public class SnakeGameView extends JFrame implements IGameView {
    IGameModel model;
    IGameController controller;

    public SnakeGameView(IGameModel model, IGameController controller) {
        this.model = model;
        this.controller = controller;
    }

    private void initFrame() {
        setSize(540, 575);
        setLocation(240, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    @Override
    public void paint() {
        System.out.println("displayed the screen!");
    }

    @Override
    public void update() {
        System.out.println("displayed ending msg!");
    }
    
}
