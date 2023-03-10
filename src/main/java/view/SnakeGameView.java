package view;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import controller.ISnakeGameController;
import controller.SnakeGameController;
import gameUtils.DirectionEnum;
import model.ISnakeGameModel;
import model.SnakeGameModel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;


public class SnakeGameView extends JDialog implements ISnakeGameView{
    private ISnakeGameController control;
    private SnakeGameModel model;
    private JButton startButton;
    private JButton stopButton;
    private String startButtonActionCommand = "START";
    private String stopButtonActionCommand = "STOP";
    private JPanel mainPanel;
    private SnakeGameBoard board;
    private JTextArea logContent;
    private SnakeGameKeyListener keyListener;

    class SnakeGameButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "START":
                    startButton.setEnabled(false);
                    stopButton.setEnabled(true);
                    control.start();
                    // SnakeGameView.this.addKeyListener(null);
                    keyListener = new SnakeGameKeyListener();
                    SnakeGameView.this.addKeyListener(keyListener);
                    SnakeGameView.this.setFocusable(true);
                    SnakeGameView.this.requestFocus(); 
                    break;
                case "STOP":
                    stopButton.setEnabled(false);
                    startButton.setEnabled(true);
                    SnakeGameView.this.removeKeyListener(keyListener);
                    control.pause();
                    break;
            }
        }
    }

    class SnakeGameKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_UP:
                    control.setSnakeDirection(DirectionEnum.UP);
                    logContent.setText("up pressed");
                    break;
                case KeyEvent.VK_DOWN:
                    control.setSnakeDirection(DirectionEnum.DOWN);
                    logContent.setText("down pressed");
                    break;
                case KeyEvent.VK_LEFT:
                    control.setSnakeDirection(DirectionEnum.LEFT);
                    logContent.setText("left pressed");
                    break;
                case KeyEvent.VK_RIGHT:
                    control.setSnakeDirection(DirectionEnum.RIGHT);
                    logContent.setText("right pressed");
                    break;
            }
        }
    }

    public SnakeGameView(JFrame startWindow, SnakeGameModel model, SnakeGameController control) {
        super(startWindow);
        this.model = model;
        model.attach(this);
        this.control = control;
        control.setView(this);
        setTitle("Snake Game");

        // draw the main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        SnakeGameButtonActionListener actionListener = new SnakeGameButtonActionListener();

        // button panel on the top of window
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        startButton.setActionCommand(startButtonActionCommand);
        stopButton.setActionCommand(stopButtonActionCommand);

        startButton.addActionListener(actionListener);
        stopButton.addActionListener(actionListener);

        stopButton.setEnabled(false);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        board = new SnakeGameBoard(model);
        mainPanel.add(board, BorderLayout.CENTER);
        mainPanel.setPreferredSize(new Dimension(600, 600));

        JPanel logPanel = new JPanel();
        logContent = new JTextArea();
        logPanel.add(logContent);
        logContent.setPreferredSize(new Dimension(600, 80));
        logContent.setEditable(false);
        mainPanel.add(logPanel, BorderLayout.SOUTH);
        add(mainPanel);

        pack();
        setModal(true);
        setVisible(true);
    }

    // public void setControl(ISnakeGameController control) {
    // this.control = control;
    // }

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
