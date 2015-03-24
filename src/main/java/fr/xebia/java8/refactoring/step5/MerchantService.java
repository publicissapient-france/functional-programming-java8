package fr.xebia.java8.refactoring.step5;

import fr.xebia.java8.refactoring.step5.repository.ProductRepository;
import fr.xebia.java8.refactoring.step5.repository.StockRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class MerchantService {

    private ExecutorService executor = Executors.newFixedThreadPool(4);

    private ProductRepository productRepository = ProductRepository.CURRENT;

    private StockRepository stockRepository = StockRepository.CURRENT;

    // TODO make asynch call with CompletableFuture and use combine to create a Merchant
    public Merchant retrieveMerchant() throws ExecutionException, InterruptedException {
        Future<List<Product>> products = executor.submit(new Callable<List<Product>>() {
            @Override
            public List<Product> call() throws Exception {
                return productRepository.initProducts();
            }

        });

        Future<List<Stock>> stocks = executor.submit(new Callable<List<Stock>>() {
            @Override
            public List<Stock> call() throws Exception {
                return stockRepository.initStocks();
            }
        });


        return new Merchant(products.get(), stocks.get());
    }

    //TODO: Use CompletableFuture for chain the two async call
    public void buyProduct(Integer productId) throws ExecutionException, InterruptedException {
        Future<Product> product = executor.submit(new Callable<Product>() {
            @Override
            public Product call() throws Exception {
                return productRepository.findById(productId);
            }

        });

        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    stockRepository.decrementStock(product.get().getStockId());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }

        });

    }

    //TODO: refactor in functional way : you need use CompletableFuture.allOf for check that all task are completed
    public Map<Product.Category, List<Product>> retrieveProductByCategories() throws ExecutionException, InterruptedException {
        Map<Product.Category, List<Product>> productsByCategories = new ConcurrentHashMap<>();

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
