package fr.xebia.java8.refactoring.step0;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.function.*;

public class FunctionGenerator {

    public static Function<Integer, Integer> plusOneFunction() {
        //TODO: replace with lambda syntax
        return new Function<Integer, Integer>() {

            @Override
            public Integer apply(Integer value) {
                return value + 1;
            }
        };
    }

    public static Predicate<Integer> evenNumberPredicate() {
        //TODO: replace with lambda syntax
        return new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer % 2 == 0;
            }
        };
    }

    public static Consumer<int[]> incrementArrayValueConsumer() {
        //TODO: replace with lambda syntax
        return new Consumer<int[]>() {
            @Override
            public void accept(int[] arrayValues) {
                for (int i = 0; i < arrayValues.length; i++) {
                    arrayValues[i] = arrayValues[i] + 1;

                }
            }
        };
    }

    public static Supplier<Integer> todaySupplier() {
        //TODO: replace with lambda syntax
        return new Supplier<Integer>() {
            @Override
            public Integer get() {
                return LocalDate.now().get(ChronoField.DAY_OF_MONTH);

            }
        };
    }

    public static BiFunction<String, Integer, String> concatAsStringFunction() {
        //TODO: replace with lambda syntax
        return new BiFunction<String, Integer, String>() {

            @Override
            public String apply(String firstValue, Integer secondValue) {
                return firstValue + " " + secondValue;
            }
        };
    }

    //BinaryOperator is BiFunction with same generic type
    public static BinaryOperator<Integer> sumFunction() {
        //TODO: replace with static methode reference
        return new BinaryOperator<Integer>() {

            @Override
            public Integer apply(Integer firstValue, Integer secondValue) {
                return Integer.sum(firstValue, secondValue);
            }
        };
    }
}
