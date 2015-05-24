package fr.xebia.java8.refactoring.step3;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Stream;

public class NumberUtils {

    private static Map<Integer, Long> fibonacciValues = new HashMap<>();

    static {
        fibonacciValues.put(0, 0L);
        fibonacciValues.put(1, 1L);
        fibonacciValues.put(2, 1L);
    }

    /**
     *
     * Return the random 'number' int between 0 and 'number * 10'
     *
     * @param number
     * @param seed
     * @return
     */
    //TODO: Use Random.ints method
    public static int[] generateRandom(int number, @Nullable Long seed) {
        int[] randomValues = new int[number];

        Random random = getRandom(seed);

        for (int i = 0; i < number; i++) {
            randomValues[i] = random.nextInt(number * 10);
        }

        return randomValues;
    }

    //TODO: To transform an array to a stream, use Arrays.stream. You'll also need Collectors.partitioningBy
    public static Map<Boolean, List<Integer>> splitEvenAndOddNumber(int[] numbers) {
        List<Integer> evenNumber = new ArrayList<>();
        List<Integer> oddNumber = new ArrayList<>();

        for (int number : numbers) {
            if (number % 2 == 0) {
                evenNumber.add(number);
            } else {
                oddNumber.add(number);
            }
        }

        Map<Boolean, List<Integer>> result = new HashMap<>();
        result.put(Boolean.TRUE, evenNumber);
        result.put(Boolean.FALSE, oddNumber);

        return result;
    }

    //TODO: Replace for loop with stream generation using IntStream.rangeClosed
    //TODO: You will also need mapToLong and mapToObj
    public static List<Long> fibonacci(int expectedNumberResult) {
        List<Long> result = new ArrayList<>(expectedNumberResult);

        for (int i = 1; i <= expectedNumberResult; i++) {
            result.add(fibonacciComputation(i));
        }

        return result;
    }

    /**
     * @return Infinite stream who returns the fibonacci sequence
     */
    //TODO: This method is not implemented. Implement this with Stream.generate, update the corresponding test to check your implementation
    public static Stream<Long> fibonacciStream() {
        throw new NotImplementedException();
    }

    private static long fibonacciComputation(int number) {
        return fibonacciValues.computeIfAbsent(number, newNumber -> fibonacciComputation(newNumber - 1) + fibonacciComputation(newNumber - 2));
    }

    private static Random getRandom(Long seed) {
        if (seed == null) {
            return new Random();
        } else {
            return new Random(seed);
        }
    }
}
