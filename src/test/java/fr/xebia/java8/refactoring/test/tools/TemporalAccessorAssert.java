package fr.xebia.java8.refactoring.test.tools;


import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

public class TemporalAccessorAssert extends AbstractAssert<TemporalAccessorAssert, TemporalAccessor> {
    protected TemporalAccessorAssert(TemporalAccessor actual) {
        super(actual, TemporalAccessorAssert.class);
    }

    public static TemporalAccessorAssert assertThat(TemporalAccessor actual) {
        return new TemporalAccessorAssert(actual);
    }

    public TemporalAccessorAssert isInSameDayAs(String dayDate) {
        Assertions.assertThat(LocalDate.from(actual)).isEqualTo(LocalDate.parse(dayDate));

        return this;
    }

    public TemporalAccessorAssert isWithinHourOfDay(int hourOfDay) {
        Assertions.assertThat(actual.get(ChronoField.HOUR_OF_DAY)).isEqualTo(hourOfDay);

        return this;
    }

    public TemporalAccessorAssert isWithinMinute(int minute) {
        Assertions.assertThat(actual.get(ChronoField.MINUTE_OF_HOUR)).isEqualTo(minute);

        return this;
    }

    public TemporalAccessorAssert isWithinSecond(int second) {
        Assertions.assertThat(actual.get(ChronoField.SECOND_OF_MINUTE)).isEqualTo(second);

        return this;
    }
}
