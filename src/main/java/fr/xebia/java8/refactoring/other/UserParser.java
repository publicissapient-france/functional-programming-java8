package fr.xebia.java8.refactoring.other;


import fr.xebia.java8.refactoring.data.Address;
import fr.xebia.java8.refactoring.data.Role;
import fr.xebia.java8.refactoring.data.Title;
import fr.xebia.java8.refactoring.data.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserParser {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.");

    public static List<User> fromCsv(String csvPath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(getFileFromPath(csvPath).toFile()))) {
            String line;
            boolean firstLine = true;
            List<User> users = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if (!firstLine) {
                    users.add(lineToUser(line));
                }
                firstLine = false;
            }

            return users;

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private static User lineToUser(String line) {
        String[] columns = line.split(",");
        User user = new User(Title.valueOf(columns[0].replace(".", "")), columns[1], columns[2]);

        user.withLogin(columns[3])
                .withPassword(columns[4])
                .withExpireDate(LocalDate.parse(columns[5], dateTimeFormatter))
                .withRole(Role.valueOf(columns[6]))
                .withBirthday(LocalDate.parse(columns[7], dateTimeFormatter))
        ;
        if (columns.length > 9) {
            user.withAddress(new Address(columns[8], columns[9], columns[10]));
        }

        return user;
    }

    private static Path getFileFromPath(String csvPath) throws URISyntaxException {
        return Paths.get(UserParser.class.getClassLoader().getResource(csvPath).toURI());
    }

}
