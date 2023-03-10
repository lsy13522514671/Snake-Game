package view;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;

import gameUtils.Posn;
import model.SnakeGameModel;

import java.util.LinkedList;
import java.awt.Color;

public class SnakeGameBoard extends JPanel {
    private SnakeGameModel model;
    private int rowNum;
    private int colNum;

    SnakeGameBoard(SnakeGameModel model) {
        this.model = model;
        this.rowNum = model.getRowNum();
        this.colNum = model.getColNum();
        setLayout(new GridBagLayout());
        drawBoard();
    }

    void drawBoard() {
        GridBagConstraints gbc = new GridBagConstraints();
        LinkedList<Posn> snake = model.getSnakePosition();
        LinkedList<Posn> lastSnakePos = model.getLastSnakePos();
        Posn applePos = model.getApplePos();

        for (int i = 0; i < colNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                Posn curPos = new Posn(i, j);
                gbc.gridx = i;
                gbc.gridy = j;

                // draws the apple
                if(curPos.equals(applePos)) {
                    add(new SnakeGameCell(Color.red), gbc);
                    continue;
                }
                
                // draws the snake
                if(!model.isGameOver()) {
                    if (snake.contains(curPos)) {
                        add(new SnakeGameCell(Color.blue), gbc);
                        continue;
                    }
                } else {
                    if (lastSnakePos.contains(curPos)) {
                        add(new SnakeGameCell(Color.blue), gbc);
                        continue;
                    }
                }

                // draws the normal cell
                add(new SnakeGameCell(Color.white), gbc);
            }
        }

    }
}
