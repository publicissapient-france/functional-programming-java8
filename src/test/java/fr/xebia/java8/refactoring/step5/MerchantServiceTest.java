package fr.xebia.java8.refactoring.step5;

import org.junit.Before;
import org.junit.Test;

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

        assertThat(merchant.getProducts().get(0).getName()).isEqualTo("name1");
        assertThat(merchant.getStocks().get(0)).isEqualTo(100);
    }

    @Test
    public void should_execute_async_future_for_products_and_stocks() throws Exception {
        Merchant merchant = merchantService.retrieveMerchantAsync();

        assertThat(merchant.getProducts().get(0).getName()).isEqualTo("name1");
        assertThat(merchant.getStocks().get(0)).isEqualTo(100);
    }
}