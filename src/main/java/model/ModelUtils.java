package model;

import java.util.Random;

public class ModelUtils {
    public static int generateRandomInt(Random seed, int min, int max) {
        return seed.nextInt((max - min) + 1) + min;
    }
}
