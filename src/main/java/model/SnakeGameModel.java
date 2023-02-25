package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

import gameUtils.DirectionEnum;
import gameUtils.IGameObserver;

public class SnakeGameModel implements ISnakeGameModel {
    private int rowNum;
    private int colNum;
    private Snake snake;
    private LinkedList<Position> lastSnakePos = new LinkedList<>();
    private Position apple;
    private ArrayList<IGameObserver> observers;
    private Random seed;

    public SnakeGameModel(int rowNum, int colNum, Random seed) throws IllegalArgumentException {
        if ((rowNum < 4) || (colNum < 4)) {
            throw new IllegalArgumentException("Illegal Board Size!");
        }

        this.rowNum = rowNum;
        this.colNum = colNum;
        this.snake = new Snake(new Position(colNum / 2, rowNum / 2));
        this.seed = seed;
        generateApple();
        this.observers = new ArrayList<>();
    }

    private void recordLastSnakePos() {
        lastSnakePos.clear();
        LinkedList<Position> snakePos = snake.getBodyPartPos();
        for(Position pos: snakePos) {
            lastSnakePos.add(pos);
        }
    }


    private boolean isValidApplePos(Position applePos) {
        LinkedList<Position> snakeBodyPos = snake.getBodyPartPos();

        // return !(Utils.containsPos(snakeBodyPos, applePos));
        return !snakeBodyPos.contains(applePos);
    }

    private void generateApple() {
        int appleX = ModelUtils.generateRandomInt(seed, 0, colNum - 1);
        int appleY = ModelUtils.generateRandomInt(seed, 0, rowNum - 1);
        Position applePos = new Position(appleX, appleY);

        while (!isValidApplePos(applePos)) {
            appleX = ModelUtils.generateRandomInt(seed, 0, colNum - 1);
            appleY = ModelUtils.generateRandomInt(seed, 0, rowNum - 1);
            applePos = new Position(appleX, appleY);
        }

        apple = applePos;
    }



    private boolean isEatingApple() {
        Position head = snake.getHeadPos();
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
            notifyGameOver();
            return;
        }

        if (isEatingApple()) {
            snake.grow(lastSnakePos.peekLast());
            generateApple();
        }
    }

    @Override
    public boolean isGameOver() {
        Position head = snake.getHeadPos();

        if ((head.getXPos() < 0) || (head.getYPos() < 0) || (head.getXPos() >= colNum)
                || (head.getYPos() >= rowNum)) {
            return true;
        }

        LinkedList<Position> snakePos = snake.getBodyPartPos();

        LinkedList<Position> bodyPos = new LinkedList<>();
        for (int i = 1; i < snakePos.size(); i++) {
            Position pos = snakePos.get(i);
            bodyPos.add(new Position(pos.getXPos(), pos.getYPos()));
        }

        if (bodyPos.contains(head)) {
            return true;
        }

        return false;
    }

    @Override
    public void printMap() {
        char[][] map = new char[rowNum][colNum];

        for (int i = 0; i < rowNum; i++) {
            Arrays.fill(map[i], 'O');
        }

        map[apple.getYPos()][apple.getXPos()] = 'A';
        
        LinkedList<Position> snakePos = snake.getBodyPartPos();

        if(isGameOver()) {
            snakePos = lastSnakePos;
        }

        for(Position pos: snakePos) {
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
    public void attach(IGameObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(IGameObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyGameOver() {
        for (IGameObserver observer : observers) {
            observer.update();
        }
    }

    public int getRowNum() {
        return rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public LinkedList<Position> getSnakePosition() {
        return snake.getBodyPartPos();
    }

    public Position getApplePos() {
        return apple;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    public static void main(String[] args) {
        int rowNum = 4;
        int colNum = 4;

        SnakeGameModel model = new SnakeGameModel(rowNum, colNum, new Random(0));

    }
}