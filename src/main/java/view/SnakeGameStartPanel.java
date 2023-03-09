package view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.SnakeGameController;
import model.SnakeGameModel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.util.Random;


import java.awt.Dimension;

class SnakeGameStartPanel extends JPanel implements ChangeListener {
    private JFrame parent;
    private JTextField rowContent;
    private JTextField colContent;
    private JSlider sliderContent;
    private JLabel speedDisplay;
    private JTextArea logContent;

    SnakeGameStartPanel(JFrame parent) {
        this.parent = parent;
        TitledBorder border = BorderFactory.createTitledBorder("Snake Game Setting");
        border.setTitleJustification(TitledBorder.CENTER);
        setBorder(border);

        setLayout(new GridLayout(5,1));

        rowContent = drawInputCombo("Row Number");
        colContent = drawInputCombo("Column Number");
        logContent = createLogPanel();
        drawSpeedPanel();
        drawStartButton();
        add(logContent);
    }

    private JTextField drawInputCombo(String fieldName) {
        JPanel inputPanel = new JPanel();

        JLabel title = new JLabel(fieldName);
        title.setPreferredSize(new Dimension(100, 50));
        JTextField content = new JTextField();
        content.setPreferredSize(new Dimension(50, 25));

        inputPanel.add(title);
        inputPanel.add(content);
        add(inputPanel);

        return content;
    }

    private void drawSpeedPanel() {
        JPanel sliderMainPanel = new JPanel();
        JLabel speed = new JLabel("Snake Speed");
        speed.setPreferredSize(new Dimension(100, 50));

        sliderMainPanel.add(speed);

        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new BorderLayout());

        JSlider speedSlider = new JSlider(500, 3000, 1000);
        speedSlider.setMinorTickSpacing(100);
        speedSlider.setMajorTickSpacing(100);
        speedSlider.setPaintTicks(true);
        this.sliderContent = speedSlider;
        speedSlider.addChangeListener(this);

        JLabel speedDisPlay = new JLabel(Integer.toString(speedSlider.getValue()));
        speedDisPlay.setHorizontalAlignment(JLabel.CENTER);
        this.speedDisplay = speedDisPlay;

        sliderPanel.add(new JLabel("quick"), BorderLayout.WEST);
        sliderPanel.add(new JLabel("slow"), BorderLayout.EAST);
        sliderPanel.add(speedSlider, BorderLayout.CENTER);
        sliderPanel.add(speedDisPlay, BorderLayout.SOUTH);

        sliderMainPanel.add(sliderPanel);
        add(sliderMainPanel);
    }

    private JTextArea createLogPanel() {
        JPanel logPanel = new JPanel();
        JTextArea content = new JTextArea();
        content.setPreferredSize(new Dimension(300, 80));
        content.setEditable(false);
        logPanel.add(content);

        return content;
    }

    private void cleanInputContent() {
        colContent.setText("");
        rowContent.setText("");
        logContent.setText("");
    }

    private void drawStartButton() {
        JPanel buttonPanel = new JPanel();
        JButton startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(100, 50));
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int rowNum;
                int colNum;
                SnakeGameModel model;

                try {
                    rowNum = Integer.parseInt(rowContent.getText());
                    colNum = Integer.parseInt(colContent.getText());
                    model = new SnakeGameModel(rowNum, colNum, new Random());
                } catch (IllegalArgumentException err) {
                    logContent.setText(err.getMessage());
                    throw err;
                }

                cleanInputContent();

                SnakeGameController control = new SnakeGameController(model, sliderContent.getValue());
                SnakeGameView view = new SnakeGameView(parent, model);
                control.setView(view);
                view.setControl(control);
            }});

        buttonPanel.add(startButton);

        add(buttonPanel);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        speedDisplay.setText(sliderContent.getValue() + "ms");
    }
}
