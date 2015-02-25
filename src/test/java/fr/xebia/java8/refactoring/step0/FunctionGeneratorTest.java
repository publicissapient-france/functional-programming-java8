package fr.xebia.java8.refactoring.step0;

import org.junit.Test;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;


public class FunctionGeneratorTest {

    @Test
    public void should_add_one_to_value() {
        //Given
        Integer value = 1;

        //When //Then
        assertThat(FunctionGenerator.plusOneFunction().apply(value)).isEqualTo(2);
    }

    @Test
    public void should_return_true_if_number_is_even() {
        assertThat(FunctionGenerator.evenNumberPredicate().test(2)).isTrue();
        assertThat(FunctionGenerator.evenNumberPredicate().test(4)).isTrue();

        assertThat(FunctionGenerator.evenNumberPredicate().test(1)).isFalse();
        assertThat(FunctionGenerator.evenNumberPredicate().test(5)).isFalse();
    }

    @Test
    public void should_increment_int_array_values() {
        // Given
        int[] values = {1, 2, 3, 4, 5};

        // When
        FunctionGenerator.incrementArrayValueConsumer().accept(values);

        // Then
        assertThat(values).isEqualTo(new int[]{2, 3, 4, 5, 6});
    }

    @Test
    public void should_return_day_of_month() {
        // When
        int dayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        // Then
        assertThat(FunctionGenerator.todaySupplier().get()).isEqualTo(dayOfMonth);
    }

    @Test
    public void should_contact_values() {
        // Given
        String name = "Java";
        Integer version = 8;

        // when
        String result = FunctionGenerator.concatAsStringFunction().apply(name, version);

        // Then
        assertThat(result).isEqualTo("Java 8");
    }

    @Test
    public void should_sum_values() {
        // Given
        Integer firstNumber = 3;
        Integer secondNumber = 4;

        // When
        Integer result = FunctionGenerator.sumFunction().apply(firstNumber, secondNumber);

        // Then
        assertThat(result).isEqualTo(7);
    }
}