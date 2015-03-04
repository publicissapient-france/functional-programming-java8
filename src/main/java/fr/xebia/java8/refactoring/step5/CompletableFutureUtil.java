package fr.xebia.java8.refactoring.step5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CompletableFutureUtil {

    public Merchant executeMerchant() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        long startTime = System.nanoTime();

        Future<List<Product>> products = executor.submit(this::initProducts);
        Future<List<Integer>> stocks = executor.submit(this::initStocks);

        Merchant merchant = new Merchant(products.get(), stocks.get());

        long elapsedTime = System.nanoTime() - startTime;
        System.out.println(TimeUnit.NANOSECONDS.toMillis(elapsedTime) + "ms with future");

        return merchant;
    }

    public Merchant executeMerchantAsync() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        long startTime = System.nanoTime();

        // TODO Call initProducts and initStocks with CompletableFuture
        // TODO Combine the CompletableFutures and create a Merchant in one call

        long elapsedTime = System.nanoTime() - startTime;
        System.out.println(TimeUnit.NANOSECONDS.toMillis(elapsedTime) + "ms with completable future");

        return new Merchant();
    }

    private List<Product> initProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "name1", "description1"));

        return products;
    }

    private List<Integer> initStocks() {
        List<Integer> stocks = new ArrayList<>();
        stocks.add(100);

        return stocks;
    }
}
