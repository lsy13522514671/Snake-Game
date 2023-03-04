package view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

class SnakeGameStartPanel extends JPanel {

    SnakeGameStartPanel() {
        initPanel();
        drawTitle();
    }

    private void initPanel() {
        setLayout(null);
        setBackground(Color.gray);
        drawInputCombo("Board Row", 16, 65, 100);
    }

    private void drawTitle() {
        JLabel label = new JLabel("Game Setting", SwingConstants.CENTER);
        label.setFont(new Font("Century", Font.BOLD, 20));
        label.setBounds(100, 25, 200, 50);
        label.setForeground(Color.white);
        add(label);
    }

    private void drawInputCombo(String title, int fontSize, int xPos, int yPos) {
        JLabel label = new JLabel(title, SwingConstants.CENTER);
        int width = 150;
        int height = 25;
        int space = 80;
        int inputWidth = 60;
        label.setBounds(xPos, yPos, width, height);
        label.setFont(new Font("Century Schoolbook", Font.PLAIN, fontSize));
        label.setForeground(Color.blue);

        JTextField input = new JTextField();
        input.setBounds(xPos+width+space, yPos, inputWidth, height);
        add(label);
        add(input);
    }

    // @Override
    // public void paintComponent(Graphics g) {
    //     initPanel();

    //     drawTitle();
    //     // drawInputCombo("Board Row", 16, 65, 100);
    //     // super.paintComponent(g);
    //     // drawInputCombo("Board Column", 16, 115, 155);
    //     // drawInputCombo("Snake Speed", 16, 115, 230);
    // }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }
}
