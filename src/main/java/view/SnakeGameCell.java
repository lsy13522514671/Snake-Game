package view;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Dimension;

import gameUtils.Posn;

class SnakeGameCell extends JPanel {
    SnakeGameCell(Color backgroundColor) {
        setBackground(backgroundColor);
        setBorder(new LineBorder(Color.black));
        setOpaque(true);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50, 50);
    }
}
