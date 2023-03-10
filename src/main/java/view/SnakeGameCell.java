package view;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Dimension;

class SnakeGameCell extends JPanel {
    SnakeGameCell(Color backgroundColor) {
        setBackground(backgroundColor);
        setBorder(new LineBorder(Color.black));
        setOpaque(true);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(40, 40);
    }
}
