package fr.xebia.java8.refactoring.step5;

import java.util.Objects;

public class Stock {
    private Integer stockId;
    private Integer quantity;

    public Stock(Integer stockId, Integer quantity) {
        this.stockId = stockId;
        this.quantity = quantity;
    }

    public Integer getStockId() {
        return stockId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(stockId, quantity);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Stock other = (Stock) obj;
        return Objects.equals(this.stockId, other.stockId)
                && Objects.equals(this.quantity, other.quantity);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stockId=" + stockId +
                ", quantity=" + quantity +
                '}';
    }
}
