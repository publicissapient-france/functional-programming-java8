package fr.xebia.java8.refactoring.step1;

import fr.xebia.java8.refactoring.data.User;

import org.assertj.core.api.Condition;
import org.assertj.core.data.MapEntry;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicCollectionOperationsTest {

    @Test
    public void should_empty_password() {
        //given
        List<User> users = new ArrayList<>();
        users.add(new User("M", "Eddard", "Stark").withPassword("1234").withExpireDate(LocalDate.of(2018, 12, 1)));
        users.add(new User("M", "Eddard", "Robb").withPassword("456"));
        users.add(new User("Me", "Eddard", "Arya").withPassword("789").withExpireDate(LocalDate.of(2012, 12, 1)));
        users.add(new User("M", "Snow", "Jon").withPassword("1011").withExpireDate(LocalDate.of(2011, 12, 1)));

        //when
        BasicCollectionOperations.resetPassword(users);

        //then
        assertThat(users).doNotHave(userWithPassword());

    }

    @Test
    public void should_remove_expired_user() {
        //given
        List<User> users = new ArrayList<>();
        users.add(new User("M", "Eddard", "Stark").withExpireDate(LocalDate.of(2010, 1, 1)));
        users.add(new User("M", "Eddard", "Robb"));
        users.add(new User("Me", "Eddard", "Arya"));
        users.add(new User("M", "Snow", "Jon"));

        //when
        BasicCollectionOperations.removeExpiredUsers(users);

        //then
        assertThat(users).hasSize(3);
        assertThat(users).doNotHave(userExpired());
    }

    @Test
    public void should_replace_all_date_by_date_plus_one_day() {
        List<LocalDate> localDates = new ArrayList<>();
        localDates.add(LocalDate.of(2014, 2, 10));
        localDates.add(LocalDate.of(2014, 2, 15));
        localDates.add(LocalDate.of(2014, 2, 18));


        BasicCollectionOperations.addOneDayToDates(localDates);

        assertThat(localDates).containsExactly(LocalDate.of(2014, 2, 11), LocalDate.of(2014, 2, 16), LocalDate.of(2014, 2, 19));

    }

    @Test
    public void should_count_words() {
        List<String> words = new ArrayList<>();
        words.add("java");
        words.add("javascript");
        words.add("java");
        words.add("scala");
        words.add("java");
        words.add("scala");

        Map<String, Integer> wordCount = BasicCollectionOperations.countWord(words);

        assertThat(wordCount).contains(
                MapEntry.entry("java", 3),
                MapEntry.entry("javascript", 1),
                MapEntry.entry("scala", 2));

    }

    @Test
    public void should_return_exchange_rate() {
        assertThat(BasicCollectionOperations.exchangeRateWithEuro("EUR")).isEqualByComparingTo("1");
        assertThat(BasicCollectionOperations.exchangeRateWithEuro("GPB")).isEqualByComparingTo("1.38");
        assertThat(BasicCollectionOperations.exchangeRateWithEuro("USD")).isEqualByComparingTo("0.91");
        assertThat(BasicCollectionOperations.exchangeRateWithEuro("AUD")).isEqualByComparingTo("0.70");
        assertThat(BasicCollectionOperations.exchangeRateWithEuro("AZERTY")).isEqualByComparingTo("1");
    }

    @Test
    public void should_return_45_fibonacci_number() {
        assertThat(BasicCollectionOperations.fibonacci(45)).containsExactly(1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L, 55L, 89L, 144L, 233L, 377L, 610L, 987L, 1597L, 2584L, 4181L, 6765L, 10946L, 17711L, 28657L, 46368L, 75025L, 121393L, 196418L, 317811L, 514229L, 832040L, 1346269L, 2178309L, 3524578L, 5702887L, 9227465L, 14930352L, 24157817L, 39088169L, 63245986L, 102334155L, 165580141L, 267914296L, 433494437L, 701408733L, 1134903170L);
    }

    private Condition<User> userExpired() {
        return new Condition<User>() {
            public boolean matches(User user) {
                return user.isExpired();
            }
        };
    }

    private Condition<User> userWithPassword() {
        return new Condition<User>() {
            public boolean matches(User user) {
                return user.password != null;
            }
        };
    }
}