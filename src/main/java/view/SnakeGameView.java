package view;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.ISnakeGameController;
import model.ISnakeGameModel;
import model.SnakeGameModel;

public class SnakeGameView extends JDialog implements ISnakeGameView {
    ISnakeGameController control = null;

    public SnakeGameView(JFrame startWindow, SnakeGameModel model) {
        super(startWindow);
        setTitle("Snake Game");
        SnakeGamePlayPanel mainPanel = new SnakeGamePlayPanel(model);
        add(mainPanel);

        pack();
        setModal(true);
        setVisible(true);
    }

    public void setControl(ISnakeGameController control) {
        this.control = control;
    }

    @Override
    public void paintGameFrame() {
        System.out.println("The view displayed the current game screen.\n");
        repaint();
    }

    @Override
    public void paintEndFrame() {
        System.out.println("The view displayed the game over screen.\n");
        repaint();
    }
}
