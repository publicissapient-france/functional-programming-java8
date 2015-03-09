package fr.xebia.java8.refactoring.step1;


import fr.xebia.java8.refactoring.data.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicCollectionOperations {

    public static void resetPassword(List<User> users) {
        //TODO :Refactor with forEach method

        for (User user : users) {
            user.password = null;
        }
    }

    public static void removeExpiredUsers(List<User> users) {
        //TODO :Refactor with removeIf method and use method reference

        List<User> usersToRemove = new ArrayList<>();

        for (User user : users) {
            if (user.isExpired()) {
                usersToRemove.add(user);
            }
        }

        users.removeAll(usersToRemove);
    }

    public static void addOneDayToDates(List<LocalDate> localDates) {
        //TODO :Refactor with replaceAll method

        for (int i = 0; i < localDates.size(); i++) {
            LocalDate localDate = localDates.get(i);
            LocalDate newDate = localDate.plusDays(1);

            localDates.set(i, newDate);
        }
    }

    public static Map<String, Integer> countWord(List<String> words) {
        //TODO :Refactor Map computation with merge method and you can eventually change loop by forEach method
        Map<String, Integer> count = new HashMap<>();

        for (String word : words) {
            Integer countOfCurrentWord = count.get(word);
            if (countOfCurrentWord == null) {
                countOfCurrentWord = 0;
            }

            count.put(word, countOfCurrentWord + 1);
        }

        return count;
    }

    private static Map<Integer, Long> fibonacciCache = new HashMap<>();

    static {
        fibonacciCache.put(0, 0L);
        fibonacciCache.put(1, 1L);
        fibonacciCache.put(2, 1L);
    }

    public static List<Long> fibonacci(int expectedNumberResult) {
        List<Long> result = new ArrayList<>(expectedNumberResult);

        for (int i = 1; i <= expectedNumberResult; i++) {

            result.add(fibonacciComputation(i));
        }
        return result;
    }

    //TODO: Use computeIfAbsent map method
    private static long fibonacciComputation(int number) {
        if (fibonacciCache.containsKey(number)) {
            return fibonacciCache.get(number);
        }

        long fibonacci = fibonacciComputation(number - 1) + fibonacciComputation(number - 2);

        fibonacciCache.put(number, fibonacci);

        return fibonacci;
    }
}
