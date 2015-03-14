package fr.xebia.java8.refactoring.step5;

import java.util.List;

public class Merchant {

    private List<Product> products;

    private List<Stock> stocks;

    public Merchant() {
    }

    public Merchant(final List<Product> products, final List<Stock> stocks) {
        this.products = products;
        this.stocks = stocks;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Stock> getStocks() {
        return stocks;
    }
}
