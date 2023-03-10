package view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.ISnakeGameController;
import controller.SnakeGameController;
import gameUtils.DirectionEnum;
import model.SnakeGameModel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

/**
 * This SnakeGameView class is the actual view that displays the game content to
 * the user. It is a JDialog to avoid user to irrationally create multiple games
 * at a time. Everytime the user wants to change the game setting, he or she has
 * to step back to the start window and initilize the game again.
 */
public class SnakeGameView extends JDialog implements ISnakeGameView {
    private ISnakeGameController control;
    private SnakeGameModel model;
    private JButton startButton;
    private JButton stopButton;
    private String startButtonActionCommand = "START"; // action command for start button
    private String stopButtonActionCommand = "STOP"; // action command for stop button
    private JPanel mainPanel;
    private SnakeGameBoard board;
    private JTextArea logContent;
    private SnakeGameKeyListener keyListener;

    /**
     * This SnakeGameButtonActionListener class implements the logic for button
     * press action in the game. While a user clicks start button, the stop button
     * is disabled and vice versa. Note that clicking the start button also
     * activates key listener for the game and vice versa.
     */
    class SnakeGameButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "START":
                    startButton.setEnabled(false);
                    stopButton.setEnabled(true);
                    control.start();

                    // clicking the start button activates the key listener
                    keyListener = new SnakeGameKeyListener();
                    SnakeGameView.this.addKeyListener(keyListener);
                    SnakeGameView.this.setFocusable(true);
                    SnakeGameView.this.requestFocus();
                    break;
                case "STOP":
                    stopButton.setEnabled(false);
                    startButton.setEnabled(true);

                    // clicking the stop button deactivates the key listener
                    SnakeGameView.this.removeKeyListener(keyListener);
                    control.pause();
                    break;
            }
        }
    }

    /**
     * This SnakeGameKeyListener defines the program behavior while a user press
     * keys. In our game, the user uses arrow keys to control snake direction in
     * the game. Note that the key press information is also shown in the log panel.
     */
    class SnakeGameKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_UP: // pressing up arrow
                    control.setSnakeDirection(DirectionEnum.UP);
                    logContent.setText("up pressed");
                    break;
                case KeyEvent.VK_DOWN: // pressing down arrow
                    control.setSnakeDirection(DirectionEnum.DOWN);
                    logContent.setText("down pressed");
                    break;
                case KeyEvent.VK_LEFT: // pressing left arrow
                    control.setSnakeDirection(DirectionEnum.LEFT);
                    logContent.setText("left pressed");
                    break;
                case KeyEvent.VK_RIGHT: // pressing right arrow
                    control.setSnakeDirection(DirectionEnum.RIGHT);
                    logContent.setText("right pressed");
                    break;
            }
        }
    }

    public SnakeGameView(JFrame startWindow, SnakeGameModel model, SnakeGameController control) {
        // set the parent for this dialog
        super(startWindow);
        this.model = model;
        model.attach(this);
        this.control = control;
        control.setView(this);
        setTitle("Snake Game");

        // draw the main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // draw button panel on the top of window
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        // set action command for buttons
        startButton.setActionCommand(startButtonActionCommand);
        stopButton.setActionCommand(stopButtonActionCommand);
        // add action listener for buttons
        SnakeGameButtonActionListener actionListener = new SnakeGameButtonActionListener();
        startButton.addActionListener(actionListener);
        stopButton.addActionListener(actionListener);
        // the stop button is initially disabled
        stopButton.setEnabled(false);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        // draw the game board
        board = new SnakeGameBoard(model);
        mainPanel.add(board, BorderLayout.CENTER);
        mainPanel.setPreferredSize(new Dimension(600, 600));

        // draw log panel
        JPanel logPanel = new JPanel();
        logContent = new JTextArea();
        logPanel.add(logContent);
        logContent.setPreferredSize(new Dimension(600, 80));
        logContent.setEditable(false);
        mainPanel.add(logPanel, BorderLayout.SOUTH);
        add(mainPanel);

        // pack the frame to its content
        pack();
        setModal(true);
        setVisible(true);
    }

    @Override
    public void paintGameFrame() {
        mainPanel.remove(board);
        board = new SnakeGameBoard(model);
        mainPanel.add(board, BorderLayout.CENTER);
        repaint();
        setVisible(true);
    }

    @Override
    public void gameOverUpdate() {
        startButton.setEnabled(false);
        stopButton.setEnabled(false);
        logContent.setText("Game Over!");
        removeKeyListener(keyListener);
    }
}
