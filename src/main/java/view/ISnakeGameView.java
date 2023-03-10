package view;
import gameUtils.ISnakeGameObserver;

/**
 * The ISnakeGameView interface represents a snake game view standard.
 */
public interface ISnakeGameView extends ISnakeGameObserver {
    /**
     * This method displays the current game screen. It gets all information
     * directly from model.
     */
    public void paintGameFrame();
}
