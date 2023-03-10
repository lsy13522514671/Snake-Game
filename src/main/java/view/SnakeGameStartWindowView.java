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
import java.awt.Dimension;
import java.util.Random;

/**
 * This SnakeGameStartWindowView is a start window. It asks the user for
 * information and uses that information to initalize the game. Once the game is
 * intialized, the user has to step back and refill information to start a new
 * game.
 */
public class SnakeGameStartWindowView extends JFrame implements ChangeListener {
    private JPanel startPanel;
    private JTextField rowContent;
    private JTextField colContent;
    private JSlider sliderContent;
    private JLabel speedDisplay;
    private JTextArea logContent;

    public SnakeGameStartWindowView() {
        setTitle("Snake Game Initialization Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        startPanel = new JPanel();
        startPanel.setPreferredSize(new Dimension(400, 400));
        TitledBorder border = BorderFactory.createTitledBorder("Snake Game Setting");
        border.setTitleJustification(TitledBorder.CENTER);
        startPanel.setBorder(border);

        startPanel.setLayout(new GridLayout(5, 1));

        rowContent = drawInputCombo("Row Number");
        colContent = drawInputCombo("Column Number");
        drawSpeedPanel();
        drawStartButton();
        drawLogPanel();

        add(startPanel);
        pack();
        setVisible(true);
    }

    /**
     * This method draws a combination of title and input box. It currently draws
     * the row input combination and column input combination.
     * 
     * @param fieldname title for input box
     */
    private JTextField drawInputCombo(String fieldName) {
        JPanel inputPanel = new JPanel();

        JLabel title = new JLabel(fieldName);
        title.setPreferredSize(new Dimension(100, 50));
        JTextField content = new JTextField();
        content.setPreferredSize(new Dimension(50, 25));

        inputPanel.add(title);
        inputPanel.add(content);
        startPanel.add(inputPanel);

        return content;
    }

    /**
     * This method draws a speed panel. There is a slider in this panel that user
     * could use to set snake speed, that is, a period that the game timer uses to
     * manipulate model and render the view.
     */
    private void drawSpeedPanel() {
        JPanel sliderMainPanel = new JPanel();
        JLabel speed = new JLabel("Snake Speed");
        speed.setPreferredSize(new Dimension(100, 50));

        sliderMainPanel.add(speed);

        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new BorderLayout());

        JSlider speedSlider = new JSlider(100, 2000, 1000);
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
        startPanel.add(sliderMainPanel);
    }

    /**
     * This method draws the log panel.
     */
    private void drawLogPanel() {
        JPanel logPanel = new JPanel();
        JTextArea content = new JTextArea();
        content.setPreferredSize(new Dimension(300, 80));
        content.setEditable(false);
        logPanel.add(content);
        logContent = content;
        startPanel.add(logPanel);
    }

    /**
     * This method cleans all input content and set them to initial value.
     */
    private void cleanInputContent() {
        colContent.setText("");
        rowContent.setText("");
        logContent.setText("");
        sliderContent.setValue(1000);
    }

    /**
     * This method draws the start button and implements the user input validation.
     * Once a user input is invalid, the program prints the error message in the
     * log and asks the user to input new values.
     * 
     * @throws IllegalArgumentException indicates that the user input is invalid.
     */
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
                new SnakeGameView(SnakeGameStartWindowView.this, model, control);
            }
        });

        buttonPanel.add(startButton);

        startPanel.add(buttonPanel);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        speedDisplay.setText(sliderContent.getValue() + "ms");
    }
}
