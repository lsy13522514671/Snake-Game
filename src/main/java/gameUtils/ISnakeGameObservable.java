package gameUtils;

/**
 * The ISnakeGameObserver interface represents a snake game observable standard.
 */
public interface ISnakeGameObservable {
    /**
     * This method attachs observer to this observable.
     * 
     * @param observer observer object
     */
    public void attach(ISnakeGameObserver observer);

    /**
     * This method detaches observer from this observable.
     * 
     * @param observer target observer object
     */
    public void detach(ISnakeGameObserver observer);

    /**
     * This method notifies all observers to this observable that the game is over,
     * and they need to update themselves for the change.
     */
    public void gameOverNotify();
}
