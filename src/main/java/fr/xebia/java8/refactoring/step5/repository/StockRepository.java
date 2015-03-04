package fr.xebia.java8.refactoring.step5.repository;

import java.util.ArrayList;
import java.util.List;

public enum StockRepository {
    CURRENT;

    public List<Integer> initStocks() {
        List<Integer> stocks = new ArrayList<>();
        stocks.add(100);
        return stocks;
    }
}
