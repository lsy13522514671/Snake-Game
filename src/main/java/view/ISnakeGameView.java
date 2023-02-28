package view;

/**
 * The ISnakeGameView interface represents a snake game view standard.
 */
public interface ISnakeGameView {
    /**
     * This method displays the current game screen. It gets all information
     * directly from model.
     */
    public void paint();

    /**
     * This method is called while the game is over. It displays the game over
     * screen. It is similar to paint method; however, it displays the snake in the
     * last round instead.
     */
    public void paintEndFrame();
}
