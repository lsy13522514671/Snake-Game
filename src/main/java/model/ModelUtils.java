package model;

import java.util.Random;

/**
 * The ModelUtils class is a utility class for model.
 */
public class ModelUtils {

    /**
     * This class method generates a random integer in a specified range based on a
     * given random object. The range includes two specified boundries.
     * 
     * @param rand the random object which is used to create the random integer
     * @param min the lower boundry
     * @param max the upper boundry
     * @return
     */
    public static int generateRandomInt(Random rand, int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
}
