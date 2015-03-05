package fr.xebia.java8.refactoring.step5.repository;

public class FakeRepositoryTools {
    public static void waitInSecond(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
