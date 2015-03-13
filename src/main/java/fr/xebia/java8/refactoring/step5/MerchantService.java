package fr.xebia.java8.refactoring.step5;

import fr.xebia.java8.refactoring.step5.repository.ProductRepository;
import fr.xebia.java8.refactoring.step5.repository.StockRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class MerchantService {

    private ExecutorService executor = Executors.newFixedThreadPool(4);

    private ProductRepository productRepository = ProductRepository.CURRENT;

    private StockRepository stockRepository = StockRepository.CURRENT;

    // TODO make asynch call with CompletableFuture and user combine for create a Merchant
    public Merchant retrieveMerchant() throws ExecutionException, InterruptedException {
        return supplyAsync(productRepository::initProducts, executor)
                .thenCombine(supplyAsync(stockRepository::initStocks, executor), Merchant::new)
                .get();
    }

    //TODO: refactor in functional way : you need use CompletableFuture.allOf for check that all task ared completed
    public Map<Product.Category, List<Product>> retrieveProductByCategories() throws ExecutionException, InterruptedException {
        Map<Product.Category, List<Product>> productsByCategories = new ConcurrentHashMap<>();

        CompletableFuture<Void> tasks = CompletableFuture.allOf(Stream.of(Product.Category.values())
                .map(category -> supplyAsync(() -> productRepository.productsByCategory(category))
                        .thenAccept(products -> productsByCategories.put(category, products)))
                .toArray(CompletableFuture[]::new));

        tasks.get();

        return productsByCategories;
    }
}
