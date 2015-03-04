package fr.xebia.java8.refactoring.step5.repository;

import fr.xebia.java8.refactoring.step5.Product;

import java.util.ArrayList;
import java.util.List;

public enum ProductRepository {
    CURRENT;

    public List<Product> initProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "name1", "description1"));

        return products;
    }



}
