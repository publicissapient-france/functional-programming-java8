package fr.xebia.java8.refactoring.step5;

import fr.xebia.java8.refactoring.step5.repository.ProductRepository;
import fr.xebia.java8.refactoring.step5.repository.StockRepository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.concurrent.*;

public class MerchantService {

    private ExecutorService executor = Executors.newFixedThreadPool(10);

    private ProductRepository productRepository = ProductRepository.CURRENT;

    private StockRepository stockRepository = StockRepository.CURRENT;

    public Merchant retrieveMerchant() throws ExecutionException, InterruptedException {
        long startTime = System.nanoTime();

        Future<List<Product>> products = executor.submit(productRepository::initProducts);
        Future<List<Integer>> stocks = executor.submit(stockRepository::initStocks);

        Merchant merchant = new Merchant(products.get(), stocks.get());

        long elapsedTime = System.nanoTime() - startTime;
        System.out.println(TimeUnit.NANOSECONDS.toMillis(elapsedTime) + "ms with future");

        return merchant;
    }

    public Merchant retrieveMerchantAsync() throws ExecutionException, InterruptedException {
        long startTime = System.nanoTime();

        // TODO Call initProducts and initStocks with CompletableFuture
        // TODO Combine the CompletableFutures and create a Merchant in one call

        long elapsedTime = System.nanoTime() - startTime;
        System.out.println(TimeUnit.NANOSECONDS.toMillis(elapsedTime) + "ms with completable future");

        throw new NotImplementedException();
    }

}
