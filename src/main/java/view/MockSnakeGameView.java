package view;

import controller.ISnakeGameController;
import model.ISnakeGameModel;

/**
 * This MockSnakeGameView class mocks a real snake game view for the testing
 * purpose. It maintains a execution log and its corresponding controller.
 */
public class MockSnakeGameView implements ISnakeGameView {
    public StringBuilder log; 
    public ISnakeGameController control;

    public MockSnakeGameView(StringBuilder log, ISnakeGameModel model) {
        this.log = log;
        model.attach(this);
    }

    /**
     * This methods sets its corresponding controller.
     * 
     * @param control
     */
    public void setControl(ISnakeGameController control) {
        this.control = control;
    }

    @Override
    public void paintGameFrame() {
        log.append("The view painted the current game screen.\n");
    }

    @Override
    public void gameOverUpdate() {
        log.append("The view painted the game over screen.\n");
    }
}
