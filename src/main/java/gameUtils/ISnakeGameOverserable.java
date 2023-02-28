package gameUtils;

public interface ISnakeGameOverserable {
    public void attach(ISnakeGameObserver observer);

    public void detach(ISnakeGameObserver observer);
    
    public void gameOverNotify();
}
