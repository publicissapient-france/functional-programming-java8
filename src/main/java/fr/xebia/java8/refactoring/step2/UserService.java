package fr.xebia.java8.refactoring.step2;


import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import fr.xebia.java8.refactoring.data.Address;
import fr.xebia.java8.refactoring.data.Role;
import fr.xebia.java8.refactoring.data.User;
import fr.xebia.java8.refactoring.data.UsersAgeStatistic;
import fr.xebia.java8.refactoring.other.UserParser;

public class UserService {

    private static final String DEFAULT_FORMATED_ADDRESS = "1 rue de Rivoli\n75001 Paris";
    private List<User> users;

    public UserService() {
        users = UserParser.fromCsv("users.csv");
    }

    //TODO: convert users List to stream and use filter and count
    public long countUserWithRole(Role role) {
        return users.stream()
                    .filter(user -> user.getRole() == role)
                    .count();
    }

    //TODO: use anyMatch
    public boolean isLoginAlreadyExist(String login) {
        return users.stream()
                    .anyMatch(user -> user.getLogin().equals(login));
    }

    //TODO: user user.getOptionalAddress() instead of user.getAddress(), use filter and findFirst.
    //TODO: to finish this refactoring you need Stream.flatMap, Optional.Map, and Optional.orElse
    public String retrieveFormatedUserAddressByLogin(String login) {
        return users.stream()
                    .filter(user -> user.getLogin().equals(login))
                    .findFirst()
                    .flatMap(User::getOptionalAddress)
                    .map(Address::formatForEnveloppe)
                    .orElse(DEFAULT_FORMATED_ADDRESS);
    }

    /**
     * @return first 50 users ordered by first name and last name
     */
    //TODO: use sorted and replace specific comparator with Comparator.comparing static methods.
    //TODO: use limit method and collect with Collectors for generate new List
    public List<User> firstFiftyUsers() {
        return users.stream()
                    .sorted(Comparator.comparing(User::getLastname).thenComparing(User::getFirstname))
                    .limit(50)
                    .collect(Collectors.toList());
    }

    //TODO: Use filter on expired method and collect with Collectors.groupingBy
    public Map<Role, List<User>> retrieveActiveUserByRole() {
        return users.stream()
                    .filter(user -> !user.isExpired())
                    .collect(Collectors.groupingBy(User::getRole));
    }

    //TODO: Use filter on role and collect with Collectors.toMap and Function.identity() as value mapper
    public Map<String, User> retrieveUserWithRoleByLogin(Role role) {
        return users.stream()
                    .filter(user -> user.getRole() == role)
                    .collect(Collectors.toMap(User::getLogin, Function.identity()));
    }

    //TODO: use collect with Collectors.summarizingInt
    //TODO: use returned IntSummaryStatistics object to create UsersAgeStatistic object
    public UsersAgeStatistic generateAgeStatistic() {
        IntSummaryStatistics summaryStatistics = users.stream().collect(Collectors.summarizingInt(User::age));

        return new UsersAgeStatistic(summaryStatistics.getCount(), summaryStatistics.getMin(), summaryStatistics.getMax(), summaryStatistics.getAverage());
    }
}
