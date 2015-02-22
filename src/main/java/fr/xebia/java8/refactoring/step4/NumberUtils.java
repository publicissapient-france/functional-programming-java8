package fr.xebia.java8.refactoring.step4;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumberUtils {

    private static Map<Integer, Long> fibonacciValues = new HashMap<>();

    static {
        fibonacciValues.put(0, 0L);
        fibonacciValues.put(1, 1L);
        fibonacciValues.put(2, 1L);
    }

    /**
     * Return the 'number' random int between 0 and 'number * 10'
     *
     * @param number
     * @param seed
     * @return
     */
    public static int[] generateRandom(int number, @Nullable Long seed) {
        //TODO:Use Random ints method
        int[] randomValues = new int[number];

        Random random = getRandom(seed);

        for (int i = 0; i < number; i++) {
            randomValues[i] = random.nextInt(number * 10);
        }

        return randomValues;
    }

    public static Map<Boolean, List<Integer>> splitEvenAndOddNumber(int[] numbers) {
        //TODO: Use Collectors.partitioningBy. To transform an array to stream, use Arrays.stream

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

    public static List<Long> fibonacci(int expectedNumberResult) {
        //TODO: replace for by stream generation with IntStream.rangeClosed, mapToLong and mapToObj

        List<Long> result = new ArrayList<>(expectedNumberResult);

        for (int i = 1; i <= expectedNumberResult; i++) {

            result.add(fibonacciComputation(i));
        }
        return result;
    }

    /**
     * @return Infinite stream who return each next number of fibonacci sequence
     */
    public static Stream<Long> fibonacciStream() {
        //TODO: Implemente method, update test for validation. Use Stream.generate
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
