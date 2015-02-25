package fr.xebia.java8.refactoring.step0;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.*;

import static org.assertj.core.api.Assertions.assertThat;


public class FunctionGeneratorTest {

    @Test
    public void should_add_one_to_value() {
        //Given
        Integer value = 1;

        Function<Integer, Integer> function = FunctionGenerator.plusOneFunction();

        //When //Then
        assertThat(function.apply(value)).isEqualTo(2);
    }

    @Test
    public void should_return_true_if_number_is_even() {
        // Given
        Predicate<Integer> predicate = FunctionGenerator.evenNumberPredicate();

        // When Then
        assertThat(predicate.test(2)).isTrue();
        assertThat(predicate.test(4)).isTrue();

        assertThat(predicate.test(1)).isFalse();
        assertThat(predicate.test(5)).isFalse();
    }

    @Test
    public void should_increment_int_array_values() {
        // Given
        int[] values = {1, 2, 3, 4, 5};

        Consumer<int[]> consumer = FunctionGenerator.incrementArrayValueConsumer();

        // When
        consumer.accept(values);

        // Then
        assertThat(values).isEqualTo(new int[]{2, 3, 4, 5, 6});
    }

    @Test
    public void should_return_day_of_month() {
        // When
        Supplier<Integer> supplier = FunctionGenerator.todaySupplier();

        // Then
        assertThat(supplier.get()).isEqualTo(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void should_contact_values() {
        // Given
        String name = "Java";
        Integer version = 8;

        BiFunction<String, Integer, String> function = FunctionGenerator.concatAsStringFunction();

        // when
        String result = function.apply(name, version);

        // Then
        assertThat(result).isEqualTo("Java 8");
    }

    @Test
    public void should_sum_values() {
        // Given
        Integer firstNumber = 3;
        Integer secondNumber = 4;

        BinaryOperator<Integer> function = FunctionGenerator.sumFunction();

        // When
        Integer result = function.apply(firstNumber, secondNumber);

        // Then
        assertThat(result).isEqualTo(7);
    }

    @Test
    public void should_add_string_to_list() {
        // Given
        List<String> list = new ArrayList<>();
        Consumer<String> consumer = FunctionGenerator.addToListConsumer(list);

        // When
        consumer.accept("abc");
        consumer.accept("def");
        consumer.accept("ghi");

        // Then
        assertThat(list).containsExactly("abc", "def", "ghi");
    }

    @Test
    public void should_convert_string_to_big_decimal() {
        // Given
        String value = "1.246";

        // When
        BigDecimal result = FunctionGenerator.stringToBiDecimalFunction().apply(value);

        // Then
        assertThat(result).isEqualByComparingTo(BigDecimal.valueOf(1.246));
    }
}