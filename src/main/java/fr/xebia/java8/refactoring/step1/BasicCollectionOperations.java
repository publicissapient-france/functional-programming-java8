package fr.xebia.java8.refactoring.step1;


import fr.xebia.java8.refactoring.data.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicCollectionOperations {

    private static Map<String, BigDecimal> CURRENCIES_BY_ISOCODE = new HashMap<>();

    private static Map<Integer, Long> FIBONACCI_CACHE = new HashMap<>();

    static {
        CURRENCIES_BY_ISOCODE.put("USD", BigDecimal.valueOf(0.91));
        CURRENCIES_BY_ISOCODE.put("GPB", BigDecimal.valueOf(1.38));
        CURRENCIES_BY_ISOCODE.put("AUD", BigDecimal.valueOf(0.70));
        CURRENCIES_BY_ISOCODE.put("EUR", BigDecimal.valueOf(1));

        FIBONACCI_CACHE.put(0, 0L);
        FIBONACCI_CACHE.put(1, 1L);
        FIBONACCI_CACHE.put(2, 1L);
    }

    //TODO :Refactor with forEach method
    public static void resetPassword(List<User> users) {

        for (User user : users) {
            user.password = null;
        }
    }

    //TODO :Refactor with removeIf method and use method reference
    public static void removeExpiredUsers(List<User> users) {
        List<User> usersToRemove = new ArrayList<>();

        for (User user : users) {
            if (user.isExpired()) {
                usersToRemove.add(user);
            }
        }

        users.removeAll(usersToRemove);
    }

    //TODO :Refactor with replaceAll method
    public static void addOneDayToDates(List<LocalDate> localDates) {

        for (int i = 0; i < localDates.size(); i++) {
            LocalDate localDate = localDates.get(i);
            LocalDate newDate = localDate.plusDays(1);

            localDates.set(i, newDate);
        }
    }

    //TODO :Refactor Map computation with merge method and you can eventually change loop by forEach method
    public static Map<String, Integer> countWord(List<String> words) {
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

    //TODO : use getOrElse Map method
    public static BigDecimal exchangeRateWithEuro(String isoCode) {
        BigDecimal currencyByIsocode = CURRENCIES_BY_ISOCODE.get(isoCode);
        if (currencyByIsocode != null) {
            return currencyByIsocode;
        }

        return BigDecimal.ONE; //Default Currency is One (no conversion)
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
        if (FIBONACCI_CACHE.containsKey(number)) {
            return FIBONACCI_CACHE.get(number);
        }

        long fibonacci = fibonacciComputation(number - 1) + fibonacciComputation(number - 2);

        FIBONACCI_CACHE.put(number, fibonacci);

        return fibonacci;
    }
}
