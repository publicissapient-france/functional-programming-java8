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

    private ExecutorService executor = Executors.newFixedThreadPool(4);

    private ProductRepository productRepository = ProductRepository.CURRENT;

    private StockRepository stockRepository = StockRepository.CURRENT;

    // TODO make asynch call with CompletableFuture and user combine for create a Merchant
    public Merchant retrieveMerchant() throws ExecutionException, InterruptedException {
        Future<List<Product>> products = executor.submit(new Callable<List<Product>>() {
            @Override
            public List<Product> call() throws Exception {
                return productRepository.initProducts();
            }

        });

        Future<List<Integer>> stocks = executor.submit(new Callable<List<Integer>>() {
            @Override
            public List<Integer> call() throws Exception {
                return stockRepository.initStocks();
            }
        });


        return new Merchant(products.get(), stocks.get());
    }

    //TODO: refactor in functional way : you need use CompletableFuture.allOf for check that all task ared completed
    public Map<Product.Category, List<Product>> retrieveProductByCategories() throws ExecutionException, InterruptedException {
        Map<Product.Category, List<Product>> productsByCategories = new HashMap<>();

        for (Product.Category category : Product.Category.values()) {

            Future<List<Product>> productsByCategory = executor.submit(new Callable<List<Product>>() {
                @Override
                public List<Product> call() throws Exception {
                    return productRepository.productsByCategory(category);
                }
            });
            productsByCategories.put(category, productsByCategory.get());
        }

        return productsByCategories;
    }
}
