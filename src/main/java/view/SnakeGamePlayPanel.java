package view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import java.awt.Dimension;

import model.SnakeGameModel;

public class SnakeGamePlayPanel extends JPanel{
    SnakeGamePlayPanel(SnakeGameModel model) {
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,3));
        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");
        JButton recoverButton = new JButton("Recover");
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(recoverButton);
        
        add(buttonPanel, BorderLayout.NORTH);

        SnakeGameBoard board = new SnakeGameBoard();
        board.setPreferredSize(new Dimension(400, 400));

        add(board, BorderLayout.CENTER);
        // board.setPreferredSize(new Dimension(board.getHeight(), board.getWidth()));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 600);
    }
}
