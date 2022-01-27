package utils;

import java.util.Random;

// todo rename class to Random and collect here methods that generates not numbers only, but strings, booleans, floats...
public class RandomNumber {
    public static int getRandomNumber(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }
}
