package fr.xebia.java8.refactoring.step5.repository;

import fr.xebia.java8.refactoring.step5.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum ProductRepository {
    CURRENT;

    private static final Map<Integer, Product> PRODUCTS = new HashMap<>();

    static {
        addProduct(PRODUCTS, 1, Product.Category.BOOKS, 7);
        addProduct(PRODUCTS, 2, Product.Category.BOOKS, 8);
        addProduct(PRODUCTS, 3, Product.Category.COMPUTERS, 9);
        addProduct(PRODUCTS, 4, Product.Category.ELECTRONICS, 10);
        addProduct(PRODUCTS, 5, Product.Category.GAME, 11);
        addProduct(PRODUCTS, 6, Product.Category.MOVIES, 12);
    }

    public List<Product> initProducts() {
        FakeRepositoryTools.waitInSecond(1);

        return new ArrayList<>(PRODUCTS.values());
    }

    public List<Product> productsByCategory(Product.Category category) {
        FakeRepositoryTools.waitInSecond(1);
        return PRODUCTS.values().stream().filter(product -> product.getCategory() == category).collect(Collectors.toList());
    }

    public Product findById(Integer productId) {
        return PRODUCTS.get(productId);
    }


    private static void addProduct(Map<Integer, Product> products, int id, Product.Category category, Integer stockId) {
        products.put(id, new Product(id, "product " + id, "Description product " + id, category, stockId));

    }
}
