package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

import gameUtils.DirectionEnum;
import gameUtils.ISnakeGameObserver;

/**
 * This SnakeGameModel class represents the game model, which implements the
 * game logic. It stores all information about snake game, including the
 * row number, column number, snake, last snake body part positions, apple
 * position, model observers, and a random object that is used to generate
 * random integers. Note that all positions are represented in the first
 * quadrant starting from (0,0) at the bottom left corner.
 */
public class SnakeGameModel implements ISnakeGameModel {
    private int rowNum; // row number of the game board
    private int colNum; // column number of the game board
    private Snake snake; // a real snake
    // snake body part positions from the last round of game
    private LinkedList<Posn> lastSnakePos = new LinkedList<>();
    private Posn apple; // apple position
    private ArrayList<ISnakeGameObserver> observers; // model observers
    private Random rand; // random object

    public SnakeGameModel(int rowNum, int colNum, Random rand) throws IllegalArgumentException {
        if ((rowNum < 4) || (colNum < 4)) {
            throw new IllegalArgumentException("Illegal Board Size!");
        }

        // below code initializes the model
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.snake = new Snake(new Posn(colNum / 2, rowNum / 2));
        this.rand = rand;
        generateApple();
        this.observers = new ArrayList<>();
    }

    /**
     * This method clears and updates the last snake body part positions. It is
     * called whenever the snake moves. It useless except the view needs it to
     * display the game over screen.
     */
    private void recordLastSnakePos() {
        lastSnakePos.clear();
        LinkedList<Posn> snakePos = snake.getBodyPartPos();
        for (Posn pos : snakePos) {
            lastSnakePos.add(pos);
        }
    }

    /**
     * This method checks whether an apple is generated at a valid position, i.e,
     * not in a snake body.
     * 
     * @param applePos apple position to be checked
     * @return a boolean indicating whether it's a valid position to place an apple
     */
    private boolean isValidApplePos(Posn applePos) {
        LinkedList<Posn> snakeBodyPos = snake.getBodyPartPos();

        return !snakeBodyPos.contains(applePos);
    }

    /**
     * This method generates a valid apple position and updates it for the model.
     */
    private void generateApple() {
        int appleX = ModelUtils.generateRandomInt(rand, 0, colNum - 1);
        int appleY = ModelUtils.generateRandomInt(rand, 0, rowNum - 1);
        Posn applePos = new Posn(appleX, appleY);

        // If a position is invalid, the method generates a new one.
        while (!isValidApplePos(applePos)) {
            appleX = ModelUtils.generateRandomInt(rand, 0, colNum - 1);
            appleY = ModelUtils.generateRandomInt(rand, 0, rowNum - 1);
            applePos = new Posn(appleX, appleY);
        }

        apple = applePos;
    }

    /**
     * This method checks whether a snake eats an apple. By definition, a snake eats
     * an apple if and only if its head reaches the apple position.
     * 
     * @return a boolean indicating whether a snake eats an apple
     */
    private boolean isEatingApple() {
        Posn head = snake.getHeadPos();
        return head.equals(apple);
    }

    /**
     * This method checks whether a snake could be turned to the target direction.
     * By definition, a snake can't be turned to a direction opposite to its current
     * direction.
     * 
     * @param targetDir target direction
     * @return a boolean indicating whether a snake can be turned to the target
     *         direction
     */
    private boolean isValidDirection(DirectionEnum targetDir) {
        DirectionEnum snakeDir = snake.getDirection();

        switch (snakeDir) {
            case UP:
                if (targetDir == DirectionEnum.DOWN) {
                    return false;
                }
                break;
            case DOWN:
                if (targetDir == DirectionEnum.UP) {
                    return false;
                }
                break;
            case LEFT:
                if (targetDir == DirectionEnum.RIGHT) {
                    return false;
                }
                break;
            case RIGHT:
                if (targetDir == DirectionEnum.LEFT) {
                    return false;
                }
                break;
        }

        return true;
    }

    /**
     * This method prints the game board in the terminal. It serves for testing
     * purpose.
     */
    public void printMap() {
        char[][] map = new char[rowNum][colNum];

        // 'O' represents a normal grid
        for (int i = 0; i < rowNum; i++) {
            Arrays.fill(map[i], 'O');
        }

        // 'A' represents the apple position
        map[apple.getYPos()][apple.getXPos()] = 'A';

        LinkedList<Posn> snakePos = snake.getBodyPartPos();

        if (isGameOver()) {
            snakePos = lastSnakePos;
        }

        // 'S' represents the snake body part position
        for (Posn pos : snakePos) {
            map[pos.getYPos()][pos.getXPos()] = 'S';
        }

        for (int i = rowNum - 1; i >= 0; i--) {
            for (int j = 0; j < colNum; j++) {
                System.out.print(map[i][j]);
                if (j == colNum - 1) {
                    System.out.print("\n");
                } else {
                    System.out.print(" ");
                }
            }
        }
    }

    /**
     * This method gets the row number of the game board.
     * 
     * @return the row number
     */
    public int getRowNum() {
        return rowNum;
    }

    /**
     * This method gets the column number of the game board.
     * 
     * @return the column number
     */
    public int getColNum() {
        return colNum;
    }

    /**
     * This method gets the snake body part positions.
     * 
     * @return a linked list of positions representing snake body parts
     */
    public LinkedList<Posn> getSnakePosition() {
        return snake.getBodyPartPos();
    }

    /**
     * This method gets the apple position.
     * 
     * @return apple position
     */
    public Posn getApplePos() {
        return apple;
    }

    /**
     * This method sets the row number of game board.
     * 
     * @param rowNum row number
     */
    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    /**
     * This method sets the column number of game board.
     * 
     * @param colNum column number
     */
    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    @Override
    public void attach(ISnakeGameObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(ISnakeGameObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void gameOverNotify() {
        for (ISnakeGameObserver observer : observers) {
            observer.gameOverUpdate();
        }
    }

    @Override
    public void setSnakeDirection(DirectionEnum direction) {
        if (isValidDirection(direction)) {
            snake.setDirection(direction);
        }
    }

    @Override
    public void moveSnake() {
        recordLastSnakePos();
        snake.move();

        if (isGameOver()) {
            gameOverNotify();
            return;
        }

        if (isEatingApple()) {
            snake.grow(lastSnakePos.peekLast());
            generateApple();
        }
    }

    @Override
    public boolean isGameOver() {
        Posn head = snake.getHeadPos();

        if ((head.getXPos() < 0) || (head.getYPos() < 0) || (head.getXPos() >= colNum)
                || (head.getYPos() >= rowNum)) {
            return true;
        }

        LinkedList<Posn> snakePos = snake.getBodyPartPos();

        LinkedList<Posn> bodyPos = new LinkedList<>();
        for (int i = 1; i < snakePos.size(); i++) {
            Posn pos = snakePos.get(i);
            bodyPos.add(new Posn(pos.getXPos(), pos.getYPos()));
        }

        if (bodyPos.contains(head)) {
            return true;
        }

        return false;
    }
}