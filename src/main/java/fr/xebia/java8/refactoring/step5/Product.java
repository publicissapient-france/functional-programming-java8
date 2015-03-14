package fr.xebia.java8.refactoring.step5;

import java.util.Objects;

public class Product {

    public enum Category {
        BOOKS,
        MOVIES,
        GAME,
        COMPUTERS,
        ELECTRONICS;
    }
    private Integer id;

    private String name;
    private String description;
    private Category category;
    private Integer stockId;
    public Product(final Integer id, final String name, final String description, Category category, Integer stockId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.stockId = stockId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getStockId() {
        return stockId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", stockId=" + stockId +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, category, stockId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        return Objects.equals(this.id, other.id)
                && Objects.equals(this.name, other.name)
                && Objects.equals(this.description, other.description)
                && Objects.equals(this.category, other.category)
                && Objects.equals(this.stockId, other.stockId);
    }
}
