package view;

import controller.ISnakeGameController;

public class MockSnakeGameView implements ISnakeGameView {
    public StringBuilder log;
    public ISnakeGameController control;

    public MockSnakeGameView(StringBuilder log) {
        this.log = log;
    }
    
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
