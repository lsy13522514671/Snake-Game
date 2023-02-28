import java.util.Random;

import controller.SnakeGameController;
import model.MockSnakeGameModel;
import model.SnakeGameModel;
import view.MockSnakeGameView;
import view.SnakeGameView;

public class SnakeGameMain {
    public static void main(String[] args) {
        // Random rand = new Random();
        // SnakeGameModel model = new SnakeGameModel(10, 10, rand);
        StringBuilder log = new StringBuilder();
        MockSnakeGameModel model = new MockSnakeGameModel(log);
        MockSnakeGameView view = new MockSnakeGameView(log);
        SnakeGameController control = new SnakeGameController(model);
        // view.setControl(control);
        control.setView(view);

        long start=System.currentTimeMillis();
        control.start(1000);
        while(System.currentTimeMillis()-start<=3000) {
            continue;
        }

        model.setGameOver(true);
        model.moveSnake();
        // System.out.println(model.log.toString());
        System.out.println(model.getLog());
    }
}
