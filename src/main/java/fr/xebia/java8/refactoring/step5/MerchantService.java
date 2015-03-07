package fr.xebia.java8.refactoring.step5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Stream;

import fr.xebia.java8.refactoring.step5.repository.ProductRepository;
import fr.xebia.java8.refactoring.step5.repository.StockRepository;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class MerchantService {

    private ExecutorService executor = Executors.newSingleThreadExecutor();

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
        Map<Product.Category, List<Product>> productsByCategories = new HashMap<>();
        for (Product.Category category : Product.Category.values()) {
            Future<List<Product>> productsByCategory = executor.submit(() -> productRepository.productsByCategory(category));
            productsByCategories.put(category, productsByCategory.get());
        }

        return productsByCategories;
    }

    public Map<Product.Category, List<Product>> retrieveProductByCategoriesAsync() throws ExecutionException, InterruptedException {
        Map<Product.Category, List<Product>> productsByCategories = new ConcurrentHashMap<>();

        CompletableFuture<Void> tasks = CompletableFuture.allOf(Stream.of(Product.Category.values())
                .map(category -> supplyAsync(() -> productRepository.productsByCategory(category))
                                    .thenAccept(products -> productsByCategories.put(category, products)))
                                    .toArray(CompletableFuture[]::new));

         tasks.get();

        return productsByCategories;
    }


}
