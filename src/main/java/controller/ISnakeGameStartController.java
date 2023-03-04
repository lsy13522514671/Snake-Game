package controller;

import model.ISnakeGameModel;

public interface ISnakeGameStartController {
    public ISnakeGameModel createSnakeGameModel(int row, int col);
}
