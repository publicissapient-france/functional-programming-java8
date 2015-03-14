package fr.xebia.java8.refactoring.step5.repository;

import fr.xebia.java8.refactoring.step5.Stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum StockRepository {
    CURRENT;

    private static final Map<Integer, Stock> STOCKS_BY_STOCKID = new HashMap<>();

    static {
        addStocks(STOCKS_BY_STOCKID, 7, 3);
        addStocks(STOCKS_BY_STOCKID, 8, 10);
        addStocks(STOCKS_BY_STOCKID, 9, 8);
        addStocks(STOCKS_BY_STOCKID, 10, 7);
        addStocks(STOCKS_BY_STOCKID, 11, 20);
        addStocks(STOCKS_BY_STOCKID, 12, 4);
    }

    private static void addStocks(Map<Integer, Stock> stocks, int stockId, int quantity) {
        stocks.put(stockId, new Stock(stockId, quantity));
    }

    public List<Stock> initStocks() {
        FakeRepositoryTools.waitInSecond(1);
        return new ArrayList<>(STOCKS_BY_STOCKID.values());
    }

    public void decrementStock(Integer stockId) {
         STOCKS_BY_STOCKID.computeIfPresent(stockId, (id, stock) -> new Stock(id, stock.getQuantity() - 1));
    }

    public Stock findById(int stockId) {
        return STOCKS_BY_STOCKID.get(stockId);
    }
}
