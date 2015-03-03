package fr.xebia.java8.refactoring.step2;


import fr.xebia.java8.refactoring.data.Role;
import fr.xebia.java8.refactoring.data.User;
import fr.xebia.java8.refactoring.other.UserParser;

import java.util.*;

public class UserService {

    private static final String DEFAULT_FORMATED_ADDRESS = "1 rue de Rivoli\n75001 Paris";
    private List<User> users;

    public UserService() {
        users = UserParser.fromCsv("users.csv");
    }

    public UserService(List<User> users) {
        this.users = users;
    }

    public long countUserWithRole(Role role) {
        //TODO: convert user List to stream and use filter and count
        long count = 0;
        for (User user : users) {
            if (user.getRole() == role) {
                count++;
            }
        }

        return count;
    }

    public boolean isLoginAlreadyExist(String login) {
        //TODO: use anyMatch
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return true;
            }
        }

        return false;
    }

    public String retrieveFormatedUserAddressByLogin(String login) {
        //TODO:  Replace user.address type by Optional<Address>, user filter and findFirst. Then Use flatMap with getAddress and map with formatForEnveloppe method
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                if (user.getAddress() != null) {
                    return user.getAddress().formatForEnveloppe();
                }
            }
        }

        return DEFAULT_FORMATED_ADDRESS;
    }

    /**
     * @return a copy of users list ordered by lastname and firstname
     */
    public List<User> findAll() {
        //TODO: replace specific comparator with 'Comparator static methods' and collect with Collectors
        List<User> usersOrdered = new ArrayList<>(users.size());
        usersOrdered.addAll(users);

        Collections.sort(usersOrdered, new UserComparator());

        return usersOrdered;
    }

    private static class UserComparator implements Comparator<User> {

        public int compare(User userLeft, User userRight) {
            int lastNameComparaison = userLeft.getLastname().compareTo(userRight.getLastname());
            if (lastNameComparaison == 0) {
                return userLeft.getFirstname().compareTo(userRight.getFirstname());
            } else {

                return lastNameComparaison;
            }
        }
    }

    public Map<Role, List<User>> retrieveActiveUserByRole() {
        //TODO: Use collect with Collectors.groupingBy
        Map<Role, List<User>> result = new HashMap<>();

        for (User user : users) {
            if (!user.isExpired()) {

                List<User> currentRoleUsers = result.get(user.getRole());
                if (currentRoleUsers == null) {
                    currentRoleUsers = new ArrayList<>();
                    result.put(user.getRole(), currentRoleUsers);
                }
                currentRoleUsers.add(user);
            }
        }

        return result;
    }

    public Map<String, User> retrieveUserwithRoleByLogin(Role role) {
        //TODO: Use collect with Collectors.toMap and Function.identity() as value mapper
        Map<String, User> result = new HashMap<>();

        for (User user : users) {
            if (user.getRole() == role) {
                result.put(user.getLogin(), user);
            }
        }

        return result;
    }

    public String generateAgeStatistic() {
        //TODO: use collect with Collectors.summarizingInt
        int count = 0;
        int min = Integer.MAX_VALUE;
        int max = 0;
        int sum = 0;

        for (User user : users) {
            int age = user.age();
            if (age > max) {
                max = age;
            }
            if (age < min) {
                min = age;
            }
            count++;
            sum += age;
        }
        double average = (double) sum / count;

        return String.format("Number of user : %d\nAge min : %d\nAge max : %d\nAge average : %.2f", count, min, max, average);
    }
}
