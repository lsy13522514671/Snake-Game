package gameUtils;

public interface IGameOverserable {
    public void attach(IGameObserver observer);

    public void detach(IGameObserver observer);
    
    public void notifyGameOver();
}
