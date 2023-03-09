package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SnakeGameStartWindowView extends JFrame {
    public SnakeGameStartWindowView() {
        setTitle("Snake Game Initialization Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        JPanel panel = new SnakeGameStartPanel(this);
        add(panel);
        pack();
        setVisible(true);
    }
}
