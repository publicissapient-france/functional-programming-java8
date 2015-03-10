package fr.xebia.java8.refactoring.data;

import java.util.Objects;

public class UsersAgeStatistic {

    private final long count;
    private final int min;
    private final int max;
    private final double average;

    public UsersAgeStatistic(long count, int min, int max, double average) {
        this.count = count;
        this.min = min;
        this.max = max;
        this.average = average;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, min, max, average);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final UsersAgeStatistic other = (UsersAgeStatistic) obj;
        return Objects.equals(this.count, other.count)
                && Objects.equals(this.min, other.min)
                && Objects.equals(this.max, other.max)
                && Objects.equals(this.average, other.average);
    }
}
