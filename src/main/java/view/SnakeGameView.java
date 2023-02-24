package view;

import javax.swing.JFrame;

import controller.ISnakeGameController;
import model.ISnakeGameModel;

public class SnakeGameView extends JFrame implements ISnakeGameView {
    ISnakeGameModel model;
    ISnakeGameController controller;

    public SnakeGameView(ISnakeGameModel model, ISnakeGameController controller) {
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
