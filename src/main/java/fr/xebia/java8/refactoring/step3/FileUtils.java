package fr.xebia.java8.refactoring.step3;

import fr.xebia.java8.refactoring.data.Address;
import fr.xebia.java8.refactoring.data.Role;
import fr.xebia.java8.refactoring.data.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FileUtils {

    //TODO: Replace By Files.lines, use static method reference
    public static List<User> loadUsersFromCsv(Path csvPath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvPath.toFile()))) {
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

        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    //TODO:replace by Files.walk and remove visitor. Use Optional.orElseThrow for throw FileNotFoundException
    public static Path findRecursivelyFileByName(String path, String fileName) throws IOException {
        Path rootDictory = Paths.get(path);

        SearchVisitor searchVisitor = new SearchVisitor(fileName);

        Files.walkFileTree(rootDictory, searchVisitor);
        Path fileFound = searchVisitor.fileFound;
        if (fileFound == null) {
            throw new FileNotFoundException();
        }
        return fileFound;
    }

    public static class SearchVisitor extends SimpleFileVisitor<Path> {

        private Path fileFound;

        private String fileNameToSearch;

        public SearchVisitor(String fileNameToSearch) {
            this.fileNameToSearch = fileNameToSearch;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (attrs.isRegularFile() && file.getFileName().toString().equals(fileNameToSearch)) {
                fileFound = file;
                return FileVisitResult.TERMINATE;
            }
            return FileVisitResult.CONTINUE;
        }

    }

    private static User lineToUser(String line) {
        String[] columns = line.split(",");
        User user = new User(columns[0], columns[1], columns[2]);
        user.withLogin(columns[3])
                .withPassword(columns[4])
                .withExpireDate(LocalDate.parse(columns[5], DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.")))
                .withRole(Role.valueOf(columns[6]))
        ;
        if (columns.length > 8) {
            user.withAddress(new Address(columns[7], columns[8], columns[9]));
        }

        return user;
    }

}
