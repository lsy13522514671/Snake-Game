import java.util.Random;

import controller.SnakeGameController;
import model.SnakeGameModel;
import view.ISnakeGameStartView;
import view.ISnakeGameView;
import view.SnakeGameStartWindowView;
import view.SnakeGameView;

public class SnakeGameMain {
    public static void main(String[] args) {
        SnakeGameStartWindowView startView = new SnakeGameStartWindowView();
        startView.setVisible(true);
    }
}
