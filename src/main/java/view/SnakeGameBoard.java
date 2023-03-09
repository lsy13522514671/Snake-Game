package view;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;

import gameUtils.Posn;
import model.SnakeGameModel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;

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

    private void drawBoard() {
        GridBagConstraints gbc = new GridBagConstraints();
        LinkedList<Posn> snake = model.getSnakePosition();

        for (int i = 0; i < colNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                Posn curPos = new Posn(i, j);
                gbc.gridx = i;
                gbc.gridy = j;

                if (snake.contains(curPos)) {
                    add(new SnakeGameCell(Color.blue), gbc);
                } else if (model.getApplePos().equals(new Posn(i, j))) {
                    add(new SnakeGameCell(Color.red), gbc);
                } else {
                    add(new SnakeGameCell(Color.white), gbc);
                }
            }
        }

    }
}
