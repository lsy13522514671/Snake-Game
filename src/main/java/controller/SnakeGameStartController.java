package controller;

import java.util.Random;

import model.ISnakeGameModel;
import model.SnakeGameModel;
import view.ISnakeGameStartView;

public class SnakeGameStartController implements ISnakeGameStartController{
    ISnakeGameStartView view;


    @Override
    public ISnakeGameModel createSnakeGameModel(int row, int col) throws IllegalArgumentException {
        ISnakeGameModel model = null;
        try {
            model = new SnakeGameModel(row, col, new Random());
        } catch (IllegalArgumentException e) {
            throw e;
        }
        return model;
    }
}
