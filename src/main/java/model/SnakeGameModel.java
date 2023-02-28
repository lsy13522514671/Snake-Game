package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

import gameUtils.DirectionEnum;
import gameUtils.ISnakeGameObserver;

public class SnakeGameModel implements ISnakeGameModel {
    private int rowNum;
    private int colNum;
    private Snake snake;
    private LinkedList<Posn> lastSnakePos = new LinkedList<>();
    private Posn apple;
    private ArrayList<ISnakeGameObserver> observers;
    private Random rand;

    public SnakeGameModel(int rowNum, int colNum, Random rand) throws IllegalArgumentException {
        if ((rowNum < 4) || (colNum < 4)) {
            throw new IllegalArgumentException("Illegal Board Size!");
        }

        this.rowNum = rowNum;
        this.colNum = colNum;
        this.snake = new Snake(new Posn(colNum / 2, rowNum / 2));
        this.rand = rand;
        generateApple();
        this.observers = new ArrayList<>();
    }

    private void recordLastSnakePos() {
        lastSnakePos.clear();
        LinkedList<Posn> snakePos = snake.getBodyPartPos();
        for(Posn pos: snakePos) {
            lastSnakePos.add(pos);
        }
    }


    private boolean isValidApplePos(Posn applePos) {
        LinkedList<Posn> snakeBodyPos = snake.getBodyPartPos();

        // return !(Utils.containsPos(snakeBodyPos, applePos));
        return !snakeBodyPos.contains(applePos);
    }

    private void generateApple() {
        int appleX = ModelUtils.generateRandomInt(rand, 0, colNum - 1);
        int appleY = ModelUtils.generateRandomInt(rand, 0, rowNum - 1);
        Posn applePos = new Posn(appleX, appleY);

        while (!isValidApplePos(applePos)) {
            appleX = ModelUtils.generateRandomInt(rand, 0, colNum - 1);
            appleY = ModelUtils.generateRandomInt(rand, 0, rowNum - 1);
            applePos = new Posn(appleX, appleY);
        }

        apple = applePos;
    }



    private boolean isEatingApple() {
        Posn head = snake.getHeadPos();
        return head.equals(apple);
    }

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

    public void printMap() {
        char[][] map = new char[rowNum][colNum];

        for (int i = 0; i < rowNum; i++) {
            Arrays.fill(map[i], 'O');
        }

        map[apple.getYPos()][apple.getXPos()] = 'A';
        
        LinkedList<Posn> snakePos = snake.getBodyPartPos();

        if(isGameOver()) {
            snakePos = lastSnakePos;
        }

        for(Posn pos: snakePos) {
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

    public int getRowNum() {
        return rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public LinkedList<Posn> getSnakePosition() {
        return snake.getBodyPartPos();
    }

    public Posn getApplePos() {
        return apple;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }
}