package view;

import javax.swing.JFrame;

import controller.ISnakeGameController;
import model.ISnakeGameModel;

public class SnakeGameView extends JFrame implements ISnakeGameView {
    ISnakeGameModel model;
    ISnakeGameController control = null;

    public SnakeGameView(ISnakeGameModel model) {
        this.model = model;
    }

    private void initFrame() {
        setSize(540, 575);
        setLocation(240, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public void setControl(ISnakeGameController control) {
        this.control = control;
    }

    @Override
    public void paint() {
        System.out.println("The view displayed the current game screen.\n");
    }

    @Override
    public void paintEndFrame() {
        System.out.println("The view displayed the game over screen.\n");
    }
}
