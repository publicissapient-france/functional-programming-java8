package fr.xebia.java8.refactoring.step5;

import fr.xebia.java8.refactoring.step5.repository.ProductRepository;
import fr.xebia.java8.refactoring.step5.repository.StockRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class MerchantService {

    private ExecutorService executor = Executors.newFixedThreadPool(5);

    private ProductRepository productRepository = ProductRepository.CURRENT;

    private StockRepository stockRepository = StockRepository.CURRENT;

    // TODO Call initProducts and initStocks with CompletableFuture and Combine them for create a Merchant
    public Merchant retrieveMerchant() throws ExecutionException, InterruptedException {
        Future<List<Product>> products = executor.submit(productRepository::initProducts);
        Future<List<Integer>> stocks = executor.submit(stockRepository::initStocks);

        return new Merchant(products.get(), stocks.get());
    }

    // Solution to put in branche solution
    public Merchant retrieveMerchantResult() throws ExecutionException, InterruptedException {

        return supplyAsync(productRepository::initProducts, executor)
                .thenCombine(supplyAsync(stockRepository::initStocks, executor), Merchant::new)
                .get();
    }

    public Map<Product.Category, List<Product>> retrieveProductByCategories() throws ExecutionException, InterruptedException {
        long startTime = System.nanoTime();
        Map<Product.Category, List<Product>> productsByCategories = new HashMap<>();
        for (Product.Category category : Product.Category.values()) {
            Future<List<Product>> productsByCategory = executor.submit(() -> productRepository.productsByCategory(category));
            productsByCategories.put(category, productsByCategory.get());

        }

        long elapsedTime = System.nanoTime() - startTime;
        System.out.println(TimeUnit.NANOSECONDS.toMillis(elapsedTime) + "ms with future");

        return productsByCategories;
    }

    public Map<Product.Category, List<Product>> retrieveProductByCategoriesResult() throws ExecutionException, InterruptedException {
        long startTime = System.nanoTime();
        Map<Product.Category, List<Product>> productsByCategories = new ConcurrentHashMap<>();

        CompletableFuture<Void> tasks = CompletableFuture.allOf(Stream.of(Product.Category.values())
                .map(category -> {
                    CompletableFuture<List<Product>> productsByCategory = supplyAsync(() -> productRepository.productsByCategory(category),executor);
                    productsByCategory.thenAccept(val -> productsByCategories.put(category, val));
                    return productsByCategory;
                }).toArray(CompletableFuture[]::new));


         tasks.get();

        long elapsedTime = System.nanoTime() - startTime;
        System.out.println(TimeUnit.NANOSECONDS.toMillis(elapsedTime) + "ms with CompletableFuture future");

        return productsByCategories;
    }


}
