package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Graphics;

public class SnakeGameStartWindowView extends JFrame {
    public SnakeGameStartWindowView() {
        initFrameData();
        drawStartWindow();
    }

    private void initFrameData() {
        setTitle("Snake Game Initialization Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    // @Override
    public void drawStartWindow() {
        JPanel panel = new SnakeGameStartPanel();
        add(panel);
        pack();
    }
}
