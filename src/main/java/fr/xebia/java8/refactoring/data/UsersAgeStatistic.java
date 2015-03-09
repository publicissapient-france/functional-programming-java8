package fr.xebia.java8.refactoring.data;

public class UsersAgeStatistic {

    private final int count;
    private final int min;
    private final int max;
    private final double average;

    public UsersAgeStatistic(int count, int min, int max, double average) {
        this.count = count;
        this.min = min;
        this.max = max;
        this.average = average;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersAgeStatistic that = (UsersAgeStatistic) o;

        if (Double.compare(that.average, average) != 0) return false;
        if (count != that.count) return false;
        if (max != that.max) return false;
        if (min != that.min) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = count;
        result = 31 * result + min;
        result = 31 * result + max;
        temp = Double.doubleToLongBits(average);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
