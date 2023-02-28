package view;

import controller.ISnakeGameController;

public class MockSnakeGameView implements ISnakeGameView {
    public StringBuilder log;
    public ISnakeGameController control;

    public MockSnakeGameView(StringBuilder log) {
        this.log = log;
    }

    @Override
    public void paint() {
        log.append("The view painted the current game screen.\n");
    }
    
    public void setControl(ISnakeGameController control) {
        this.control = control;
    }

    @Override
    public void paintEndFrame() {
        log.append("The view painted the game over screen.\n");
    }
}
