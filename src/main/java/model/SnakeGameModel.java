package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

import gameUtils.DirectionEnum;
import gameUtils.IGameObserver;

public class SnakeGameModel implements ISnakeGameModel {
    int rowNum;
    int colNum;
    char[][] map;
    Snake snake;
    Position apple;
    ArrayList<IGameObserver> observers;
    Random seed = new Random();

    public SnakeGameModel(int rowNum, int colNum) throws IllegalArgumentException {
        if ((rowNum < 4) || (colNum < 4)) {
            throw new IllegalArgumentException("Illegal Board Size!");
        }

        this.rowNum = rowNum;
        this.colNum = colNum;
        this.map = new char[rowNum][colNum];
        this.snake = new Snake(new Position(colNum / 2, rowNum / 2));
        generateApple();
        updateMap();
        this.observers = new ArrayList<>();
    }

    void updateMap() {
        for (int i = 0; i < rowNum; i++) {
            Arrays.fill(map[i], 'O');
        }

        LinkedList<Position> snakeBodyPos = snake.getBodyPartPos();
        for (Position position : snakeBodyPos) {
            map[position.getYPos()][position.getXPos()] = 'S';
        }

        // System.out.println("apple pos:" + "x-"+apple.getXPos()+",
        // y-"+apple.getYPos());
        map[apple.getYPos()][apple.getXPos()] = 'A';
    }

    private boolean isValidApplePos(Position applePos) {
        LinkedList<Position> snakeBodyPos = snake.getBodyPartPos();

        // return !(Utils.containsPos(snakeBodyPos, applePos));
        return !snakeBodyPos.contains(applePos);
    }

    private void generateApple() {
        int appleX = Utils.generateRandomInt(seed, 0, colNum - 1);
        int appleY = Utils.generateRandomInt(seed, 0, rowNum - 1);
        Position applePos = new Position(appleX, appleY);

        while (!isValidApplePos(applePos)) {
            appleX = Utils.generateRandomInt(seed, 0, colNum - 1);
            appleY = Utils.generateRandomInt(seed, 0, rowNum - 1);
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
        snake.move();

        if (isGameOver()) {
            notifyGameOver();
            return;
        }

        if (isEatingApple()) {
            snake.grow();
            generateApple();
        }

        updateMap();
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

    public char[][] getMap() {
        return map;
    }

    public Snake getSnake() {
        return snake;
    }

    Position getApplePos() {
        return apple;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    void setApple(int xPos, int yPos) {
        apple = new Position(xPos, yPos);
    }

    public static void main(String[] args) {
        int rowNum = 4;
        int colNum = 4;

        SnakeGameModel model = new SnakeGameModel(rowNum, colNum);
        // We set an apple to the right of snake head.
        model.setApple(3, 2);
        model.updateMap();
        // Snake moves right and eat the apple.
        model.moveSnake();

        // We set an apple below snake head.
        model.setApple(3, 1);
        model.updateMap();

        // Snake moves down.
        model.setSnakeDirection(DirectionEnum.DOWN);
        // Snake moves down and eat the apple.
        model.moveSnake();

        // We set the apple to the left down corner so that it does not dirturb the
        // testing.
        model.setApple(0, 0);
        model.updateMap();

        // Snake moves left.
        model.setSnakeDirection(DirectionEnum.LEFT);
        // Snake moves left.
        model.moveSnake();

        model.setSnakeDirection(DirectionEnum.UP);
        // Snake moves up and crash on its body.
        model.moveSnake();

        Snake snake = model.getSnake();
        LinkedList<Position> positions = snake.getBodyPartPos();

        for (Position position : positions) {
            System.out.println("body x: " + position.getXPos() + ", y: " + position.getYPos());
        }
    }
}