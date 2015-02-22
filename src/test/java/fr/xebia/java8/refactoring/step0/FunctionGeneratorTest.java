package fr.xebia.java8.refactoring.step0;

import org.junit.Test;

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
    public void should_return_yesterday_date() {
        assertThat(FunctionGenerator.todaySupplier().get()).isToday();
    }
}