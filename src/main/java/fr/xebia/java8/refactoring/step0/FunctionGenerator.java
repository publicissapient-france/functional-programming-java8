package fr.xebia.java8.refactoring.step0;

import java.util.Date;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionGenerator {

    public static Function<Integer, Integer> plusOneFunction() {

        return new Function<Integer, Integer>() {

            @Override
            public Integer apply(Integer value) {
                return value + 1;
            }
        };
    }

    public static Predicate<Integer> evenNumberPredicate() {
        return new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer % 2 == 0;
            }
        };
    }

    public static Supplier<Date> todaySupplier() {
        return new Supplier<Date>() {
            @Override
            public Date get() {
                return new Date();
            }
        };
    }
}
