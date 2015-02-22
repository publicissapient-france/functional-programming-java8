package fr.xebia.java8.refactoring.other;

import java.time.LocalDate;

public class CurrentDate {

    public static LocalDate get() {
        return LocalDate.now();
    }
}
