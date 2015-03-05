package fr.xebia.java8.refactoring.step5;

import java.util.List;
import java.util.concurrent.*;

import fr.xebia.java8.refactoring.step5.repository.ProductRepository;
import fr.xebia.java8.refactoring.step5.repository.StockRepository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static java.lang.Thread.sleep;

public class MerchantService {

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    private ProductRepository productRepository = ProductRepository.CURRENT;

    private StockRepository stockRepository = StockRepository.CURRENT;

    public Merchant retrieveMerchant() throws ExecutionException, InterruptedException {
        long startTime = System.nanoTime();

        Future<List<Product>> products = executor.submit(() -> {
            // Simulate long process
            sleep(1000);
            return productRepository.initProducts();
        });
        Future<List<Integer>> stocks = executor.submit(() -> {
            // Simulate long process
            sleep(1000);
            return stockRepository.initStocks();
        });

        Merchant merchant = new Merchant(products.get(), stocks.get());

        long elapsedTime = System.nanoTime() - startTime;
        System.out.println(TimeUnit.NANOSECONDS.toMillis(elapsedTime) + "ms with future");

        return merchant;
    }

    public Merchant retrieveMerchantAsync() throws ExecutionException, InterruptedException {
        long startTime = System.nanoTime();

        // TODO Call initProducts and initStocks with CompletableFuture and simulate long process as the above method
        // TODO Combine the CompletableFutures and create a Merchant in one call

        long elapsedTime = System.nanoTime() - startTime;
        System.out.println(TimeUnit.NANOSECONDS.toMillis(elapsedTime) + "ms with completable future");

        throw new NotImplementedException();
    }

}
