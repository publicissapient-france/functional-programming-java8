package fr.xebia.java8.refactoring.step5;

import fr.xebia.java8.refactoring.step5.repository.StockRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.jayway.awaitility.Awaitility.await;
import static org.assertj.core.api.Assertions.assertThat;

public class MerchantServiceTest {

    private MerchantService merchantService;

    @Before
    public void setUp() {
        merchantService = new MerchantService();
    }

    @Test
    public void should_execute_future_for_products_and_stocks() throws Exception {
        Merchant merchant = merchantService.retrieveMerchant();


        assertThat(merchant.getProducts()).hasSize(6);
        assertThat(merchant.getProducts().get(0)).isEqualTo(new Product(1, "product 1", "Description product 1", Product.Category.BOOKS, 7));
        assertThat(merchant.getStocks().get(0)).isEqualTo(new Stock(7, 3));
    }

    @Test
    public void should_decrement_stock_when_buy_product() throws ExecutionException, InterruptedException {
        merchantService.buyProduct(1);

        await().until(() -> StockRepository.CURRENT.findById(7).getQuantity() == 2);
    }

    @Test
    public void should_execute_retrieveProductByCategories() throws Exception {
        Map<Product.Category, List<Product>> productByCategories = merchantService.retrieveProductByCategories();

        assertThat(productByCategories.size()).isEqualTo(5);
        assertThat(productByCategories.keySet()).contains(Product.Category.values());
    }
}