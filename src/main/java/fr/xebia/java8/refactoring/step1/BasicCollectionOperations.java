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
        users.forEach(user -> user.password = null);
    }

    //TODO :Refactor with removeIf method and use method reference
    public static void removeExpiredUsers(List<User> users) {
        users.removeIf(User::isExpired);
    }

    //TODO :Refactor with replaceAll method
    public static void addOneDayToDates(List<LocalDate> localDates) {
        localDates.replaceAll(date -> date.plusDays(1));
    }

    //TODO :Refactor Map computation with merge method and you can eventually change loop by forEach method
    public static Map<String, Integer> countWord(List<String> words) {
        Map<String, Integer> count = new HashMap<>();

        words.forEach(word -> count.merge(word, 1, Integer::sum));

        return count;
    }

    //TODO : use getOrElse Map method
    public static BigDecimal exchangeRateWithEuro(String isoCode) {
        return CURRENCIES_BY_ISOCODE.getOrDefault(isoCode, BigDecimal.ONE); //Default Currency is One (no conversion)
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
        return FIBONACCI_CACHE.computeIfAbsent(number, val -> fibonacciComputation(val - 1) + fibonacciComputation(val - 2));
    }
}
