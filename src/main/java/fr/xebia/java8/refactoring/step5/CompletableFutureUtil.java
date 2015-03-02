package fr.xebia.java8.refactoring.step5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CompletableFutureUtil {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new CompletableFutureUtil().getProductsInformations();
        new CompletableFutureUtil().getProductsInformationsAsync();
    }

    private void getProductsInformations() throws ExecutionException, InterruptedException {
        long startTime = System.nanoTime();

        ExecutorService executor = Executors.newFixedThreadPool(10);

        Future<List<Product>> products = executor.submit(this::getProducts);
        Future<List<Float>> stocks = executor.submit(this::getStocks);

        products.get();
        stocks.get();

        long elapsedTime = System.nanoTime() - startTime;
        System.out.println(TimeUnit.NANOSECONDS.toMillis(elapsedTime) + "ms");

    }

    private void getProductsInformationsAsync() throws ExecutionException, InterruptedException {
        long startTime = System.nanoTime();

        ExecutorService executor = Executors.newFixedThreadPool(10);

        CompletableFuture<List<Product>> products = CompletableFuture.supplyAsync(this::getProducts, executor);

        products.thenApplyAsync(stocks -> getStocks(), executor);

        long elapsedTime = System.nanoTime() - startTime;
        System.out.println(TimeUnit.NANOSECONDS.toMillis(elapsedTime) + "ms");

    }

    private List<Product> getProducts() {
        return new ArrayList<>();
    }

    private List<Float> getStocks() {
        return new ArrayList<>();
    }
}
