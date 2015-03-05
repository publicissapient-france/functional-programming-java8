package fr.xebia.java8.refactoring.step5.repository;

import fr.xebia.java8.refactoring.step5.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.lang.Thread.sleep;

public enum ProductRepository {
    CURRENT;

    public List<Product> initProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "name1", "description1", Product.Category.BOOKS));
        FakeRepositoryTools.waitInSecond(1);
        return products;
    }

    public List<Product> productsByCategory(Product.Category category) {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "product category " + category.name(), "description1", category));
        FakeRepositoryTools.waitInSecond(1);
        return products;
    }


}
